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

}
