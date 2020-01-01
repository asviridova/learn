package ru.otus.spring.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.PartnerRepository;
import ru.otus.spring.dao.ProviderRepository;
import ru.otus.spring.domain.Goods;
import ru.otus.spring.domain.Partner;
import ru.otus.spring.domain.Provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class PartnerServiceImpl implements PartnerService {
    private final PartnerRepository partnerRepository;

    @Autowired
    public PartnerServiceImpl(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public long count() {
        return partnerRepository.count();
    }

    @Override
    public Long insert(Partner partner) {
        Partner partnerNew = partnerRepository.save(partner);
        return partnerNew.getId();
    }

    @Override
    @HystrixCommand(commandKey="getPartnersById", fallbackMethod="buildFallbackGetPartnersById")
    public Optional<Partner> getById(Long id){
        sleepRandomly();
        return partnerRepository.findById(id);
    }

    @Override
    @HystrixCommand(commandKey="getAllPartners", fallbackMethod="buildFallbackGetAllPartners")
    public List<Partner> getAll() {
        sleepRandomly();
        return partnerRepository.findAll();
    }


    @Override
    public void deleteById(Long id) {
        partnerRepository.deleteById(id);
    }

    @Override
    public Long insert(String name, String inn, String address) {
        Partner partner = new Partner(name, inn);
        Partner partnerNew = partnerRepository.save(partner);
        Long id = partnerNew.getId();
        log.info("partner inserted with id = " + id + ", name = " + name + ", inn = " + inn);
        return id;
    }

    @Override
    public Long update(Long id, String name, String inn, String address) {
        Partner partner = new Partner(id, name, inn, address);
        Partner partnerNew = partnerRepository.save(partner);
        log.info("partner updated with id = " + id + ", name = " + name + ", inn = " + inn);
        return partnerNew.getId();
    }

    //***** Hystrix ******
    public List<Partner> buildFallbackGetAllPartners() {
        Partner partner = new Partner("Too many requests", "Please, try to make request later");
        List<Partner> partnerList = new ArrayList<>();
        partnerList.add(partner);
        return partnerList;
    }

    public Optional<Partner> buildFallbackGetPartnersById(Long id) {
        Partner partner = new Partner("NO CODE with id="+id, "Please, try to make request later");
        return Optional.of(partner);
    }

    private void sleepRandomly() {
        Random rand = new Random();
        int randomNum = rand.nextInt(3) + 1;
        if(randomNum == 3) {
            System.out.println("It is a chance for demonstrating Hystrix action");
            try {
                System.out.println("Start sleeping...." + System.currentTimeMillis());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Hystrix thread interupted...." + System.currentTimeMillis());
                e.printStackTrace();
            }
        }
    }

}