package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.ProviderRepository;
import ru.otus.spring.domain.Provider;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProviderServiceImpl implements ProviderService {
    private ProviderRepository providerRepository;

    @Autowired
    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public long count() {
        return providerRepository.count();
    }

    @Override
    public Long insert(Provider provider) {
        Provider providerNew = providerRepository.save(provider);
        return providerNew.getId();
    }

    @Override
    public Optional<Provider> getById(Long id) {
        return providerRepository.findById(id);
    }

    @Override
    public List<Provider> getAll() {
        return providerRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        providerRepository.deleteById(id);
    }

    @Override
    public Long insert(String name, String inn, String address) {
        Provider provider = new Provider(name, inn);
        Provider providerNew = providerRepository.save(provider);
        Long id = providerNew.getId();
        log.info("brand inserted with id = " + id + ", name = " + name + ", inn = " + inn);
        return id;
    }

    @Override
    public Long update(Long id, String name, String inn, String address) {
        Provider provider = new Provider(id, name, inn, address, Provider.FLAG_NOT_BLACK_LIST);
        Provider providerNew = providerRepository.save(provider);
        log.info("provider updated with id = " + id + ", name = " + name + ", inn = " + inn);
        return providerNew.getId();
    }
}