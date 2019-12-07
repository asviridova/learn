package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.BrandRepository;
import ru.otus.spring.domain.Brand;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository){
        this.brandRepository = brandRepository;
    }

    @Override
    public long count() {
        return brandRepository.count();
    }

    @Override
    public Long insert(Brand brand) {
        Brand brandNew = brandRepository.save(brand);
        return brandNew.getId();
    }

    @Override
    public Optional<Brand> getById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }

    @Override
    public Long insert(String name, String country) {
        Brand brand = new Brand(name, country);
        Brand brandNew = brandRepository.save(brand);
        Long id = brandNew.getId();
        log.info("brand inserted with id = " + id + ", name = " + name + ", country = " + country);
        return id;
    }

    @Override
    public Long update(Long id, String name, String country) {
        Brand brand = new Brand(id, name, country);
        Brand brandNew = brandRepository.save(brand);
        log.info("brand updated with id = " + id + ", name = " + name + ", country = " + country);
        return brandNew.getId();
    }

    @Override
    public Brand findBrandByName(String name){
        List<Brand> brandList = brandRepository.findBrandByName(name);
        if(brandList.size() > 0){
            return brandList.get(0);
        }
        else{
            return null;
        }

    }

}
