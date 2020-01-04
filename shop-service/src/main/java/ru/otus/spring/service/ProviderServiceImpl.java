package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.ProviderRepository;
import ru.otus.spring.domain.Provider;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepository providerRepository;

    @Autowired
    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public long count() {
        return providerRepository.count();
    }

    @Override
    @RolesAllowed("ROLE_ADMIN")
    public Long insert(Provider provider) {
        Provider providerNew = providerRepository.save(provider);
        return providerNew.getId();
    }

    @Override
    public Optional<Provider> getById(Long id) {
        return providerRepository.findById(id);
    }

    @Override
    public Provider getByName(String name){
        return  providerRepository.getByName(name);
    }
    @Override
    public List<Provider> getAll() {
        return providerRepository.findAll();
    }

    @Override
    @RolesAllowed("ROLE_ADMIN")
    public void deleteById(Long id) {
        providerRepository.deleteById(id);
    }

    @Override
    @RolesAllowed("ROLE_ADMIN")
    public Long insert(String name, String inn, String address) {
        Provider provider = new Provider(name, inn, address);
        Provider providerNew = providerRepository.save(provider);
        Long id = providerNew.getId();
        log.info("provider inserted with id = " + id + ", name = " + name + ", inn = " + inn+ ", address = " + address);
        return id;
    }

    @Override
    @RolesAllowed("ROLE_ADMIN")
    public Long update(Long id, String name, String inn, String address) {
        Provider provider = new Provider(id, name, inn, address, Provider.FLAG_NOT_BLACK_LIST);
        Provider providerNew = providerRepository.save(provider);
        log.info("provider updated with id = " + id + ", name = " + name + ", inn = " + inn+", address = " + address);
        return providerNew.getId();
    }
}