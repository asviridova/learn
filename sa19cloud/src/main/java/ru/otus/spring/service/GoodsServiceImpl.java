package ru.otus.spring.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.GoodsRepository;
import ru.otus.spring.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {
    private final GoodsRepository goodsRepository;
    private final GoodsTypeService goodsTypeService;
    private final ProviderService providerService;
    private final BrandService brandService;
    private final StoreService storeService;


    @Autowired
    public GoodsServiceImpl(GoodsRepository goodsRepository, GoodsTypeService goodsTypeService, ProviderService providerService, BrandService brandService, StoreService storeService) {
        this.goodsRepository = goodsRepository;
        this.goodsTypeService = goodsTypeService;
        this.providerService = providerService;
        this.brandService = brandService;
        this.storeService = storeService;
    }

    @Override
    public long count() {
        return goodsRepository.count();
    }

    @Override
    public Long insert(Goods goods) {
        Goods goodsNew = goodsRepository.save(goods);
        return goodsNew.getId();
    }

    //@HystrixCommand(commandKey="getGoodsById", fallbackMethod="buildFallbackGetGoodsById")
    @Override
    public Optional<Goods> getById(Long id) {
        //sleepRandomly();
        return goodsRepository.findById(id);
    }

    //@HystrixCommand(commandKey="getAllGoods", fallbackMethod="buildFallbackGetAllGoods")
    @Override
    public List<Goods> getAll() {
        //sleepRandomly();
        return goodsRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        goodsRepository.deleteById(id);
    }

    @Override
    public Long insert(String code, String name,  String colour, String size, Double price, GoodsType goodsType, Brand brand, Provider provider, Store store) {
        Goods goods = new Goods(code, name, colour, size, price, goodsType, brand, provider, store);
        Goods providerNew = goodsRepository.save(goods);
        Long id = providerNew.getId();
        log.info("goods inserted with id = " + id + ", name = " + name + ", price = " + price);
        return id;
    }

    @Override
    public Long insert(String code, String name,  String colour, String size, Double price, Long goodsTypeId, Long brandId, Long providerId, Long storeId) {
        GoodsType goodsType = null;
        if(goodsTypeId!=null) {
            Optional<GoodsType> goodsTypeOptional = goodsTypeService.getById(goodsTypeId);
            goodsType = goodsTypeOptional.get();
        }
        Provider provider = null;
        if(providerId!=null) {
            Optional<Provider> providerOptional = providerService.getById(providerId);
            provider = providerOptional.get();
        }
        Brand brand = null;
        if(brandId!=null){
            Optional<Brand> brandOptional = brandService.getById(brandId);
            brand = brandOptional.get();
        }
        Store store = null;
        if(storeId !=null){
            Optional<Store> storeOptional = storeService.getById(storeId);
            store = storeOptional.get();
        }


        Goods goods = new Goods(code, name, colour, size, price, goodsType, brand, provider, store);
        Goods providerNew = goodsRepository.save(goods);
        Long id = providerNew.getId();
        log.info("goods inserted with id = " + id + ", name = " + name + ", price = " + price);
        return id;
    }


    @Override
    public Long update(Long id, String code, String name, String colour, String size, Double price, GoodsType goodsType, Brand brand, Provider provider, Store store) {
        Goods goods = new Goods(id, code, name, colour, size, price, goodsType, brand, provider, store);
        Goods goodsNew = goodsRepository.save(goods);
        log.info("goods updated with id = " + id + ", name = " + name + ", price = " + price);
        return goodsNew.getId();
    }

//    //***** Hystrix ******
//    public List<Goods> buildFallbackGetAllGoods() {
//        Goods goods = new Goods("Too many requests", "Please, try to make request later", (double)0.0);
//        List<Goods> goodsList = new ArrayList<>();
//        goodsList.add(goods);
//        return goodsList;
//    }
//
//    public Optional<Goods> buildFallbackGetGoodsById(Long id) {
//        Goods goods = new Goods(id,"NO CODE with id="+id, "Please, try to make request later", (double)0.0);
//        return Optional.of(goods);
//    }
//
//    private void sleepRandomly() {
//        Random rand = new Random();
//        int randomNum = rand.nextInt(3) + 1;
//        if(randomNum == 3) {
//            System.out.println("It is a chance for demonstrating Hystrix action");
//            try {
//                System.out.println("Start sleeping...." + System.currentTimeMillis());
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                System.out.println("Hystrix thread interupted...." + System.currentTimeMillis());
//                e.printStackTrace();
//            }
//        }
//    }

}