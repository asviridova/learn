package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.StoreRepository;
import ru.otus.spring.domain.Store;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository){
        this.storeRepository = storeRepository;
    }

    @Override
    public long count() {
        return storeRepository.count();
    }

    @Override
    public Optional<Store> getById(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> getAll() {
        return storeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        storeRepository.deleteById(id);
    }

    @Override
    public Long insert(String code, String address) {
        Store store = new Store(code, address);
        Store storeNew = storeRepository.save(store);
        Long id = storeNew.getId();
        log.info("store inserted with id = " + id + ", code = " + code);
        return id;
    }

    @Override
    public Long update(Long id, String code, String address) {
        Store store = new Store(id, code, address);
        Store storeNew = storeRepository.save(store);
        log.info("store updated with id = " + id + ", code = " + code );
        return storeNew.getId();
    }

}
