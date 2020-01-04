package ru.otus.spring.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.otus.spring.dao.GoodsRepository;
import ru.otus.spring.data.GoodsStruct;
import ru.otus.spring.domain.*;

import javax.annotation.security.RolesAllowed;
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
    @RolesAllowed({ "ROLE_ADMIN", "ROLE_PROVIDER" })
    public Long insert(Goods goods) {
        Goods goodsNew = goodsRepository.save(goods);
        return goodsNew.getId();
    }

    @Override
    public Optional<Goods> getById(Long id) {
        return goodsRepository.findById(id);
    }

    @Override
    public List<Goods> getAll() {
        return goodsRepository.findAll();
    }

    @Override
    @RolesAllowed({ "ROLE_ADMIN", "ROLE_PROVIDER" })
    public void deleteById(Long id) {
        goodsRepository.deleteById(id);
    }

    @Override
    @RolesAllowed({ "ROLE_ADMIN", "ROLE_PROVIDER" })
    public Long insert(String code, String name,  String colour, String size, Integer quantity, Double price, GoodsType goodsType, Brand brand, Provider provider, Store store) {
        Goods goods = new Goods(code, name, colour, size, quantity, price, goodsType, brand, provider, store);
        Goods providerNew = goodsRepository.save(goods);
        Long id = providerNew.getId();
        log.info("goods inserted with id = " + id + ", name = " + name + ", price = " + price);
        return id;
    }

    @Override
    @RolesAllowed({ "ROLE_ADMIN", "ROLE_PROVIDER" })
    public Long insert(String code, String name,  String colour, String size, Integer quantity, Double price, Long goodsTypeId, Long brandId, Long providerId, Long storeId) {
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


        Goods goods = new Goods(code, name, colour, size, quantity, price, goodsType, brand, provider, store);
        Goods providerNew = goodsRepository.save(goods);
        Long id = providerNew.getId();
        log.info("goods inserted with id = " + id + ", name = " + name + ", price = " + price);
        return id;
    }


    @Override
    @RolesAllowed({ "ROLE_ADMIN", "ROLE_PROVIDER" })
    public Long update(Long id, String code, String name, String colour, String size, Integer quantity, Double price, GoodsType goodsType, Brand brand, Provider provider, Store store) {
        Goods goods = new Goods(id, code, name, colour, size, quantity, price, goodsType, brand, provider, store);
        Goods goodsNew = goodsRepository.save(goods);
        log.info("goods updated with id = " + id + ", name = " + name + ", price = " + price);
        return goodsNew.getId();
    }

    public void parseCsvFile(String fileContent){
        List<GoodsStruct> list = new ArrayList<>();
        innerParseCsvFile(fileContent, list);
        for (GoodsStruct goodsStruct: list){
            Goods goods = new Goods();
            goods.setCode(goodsStruct.getCode());
            goods.setName(goodsStruct.getName());
            goods.setColour(goodsStruct.getColour());
            goods.setSize(goodsStruct.getSize());
            goods.setQuantity(goodsStruct.getQuantity());
            goods.setPrice(goodsStruct.getPrice());

            if(!StringUtils.isEmpty(goodsStruct.getGoodsTypeCode())) {
                GoodsType goodsType = goodsTypeService.getByCode(goodsStruct.getGoodsTypeCode());
                goods.setGoodsType(goodsType);
            }
            if(!StringUtils.isEmpty(goodsStruct.getBrandName())) {
                Brand brand = brandService.getByName(goodsStruct.getBrandName());
                goods.setBrand(brand);
            }
            if(!StringUtils.isEmpty(goodsStruct.getProviderName())) {
                Provider provider = providerService.getByName(goodsStruct.getProviderName());
                goods.setProvider(provider);
            }
            if(!StringUtils.isEmpty(goodsStruct.getStoreCode())) {
                Store store = storeService.getByCode(goodsStruct.getStoreCode());
                goods.setStore(store);
            }
            
            Goods providerNew = goodsRepository.save(goods);
            Long id = providerNew.getId();
            log.info("goods inserted with id = " + id + ", name = " + goodsStruct.getName() + ", price = " + goodsStruct.getPrice());
        }


    }

    private void innerParseCsvFile(String fileContent, List<GoodsStruct> list){
        if(fileContent!=null){
            String[] lines = fileContent.split(System.lineSeparator());
            int i = 0;
            for (String line: lines){
                String[] elements = line.split(";");
                if(elements.length>9){ //TODO if less

                    String code = elements[0];
                    String name = StringUtils.trimWhitespace(elements[1]);
                    String colour= StringUtils.trimWhitespace(elements[2]);
                    String size= StringUtils.trimWhitespace(elements[3]);
                    String q = StringUtils.trimWhitespace(elements[4]);
                    Integer quantity= !StringUtils.isEmpty(q)?Integer.parseInt(q):null;
                    String d = StringUtils.trimWhitespace(elements[5]);
                    Double price= !StringUtils.isEmpty(d)?Double.parseDouble(d):null;

                    String goodsTypeCode = StringUtils.trimWhitespace(elements[6]);
                    String brandName = StringUtils.trimWhitespace(elements[7]);
                    String providerName = StringUtils.trimWhitespace(elements[8]);
                    String storeCode = StringUtils.trimWhitespace(elements[9]);
                    GoodsStruct goodsStruct = new GoodsStruct(code, name, colour, size, quantity, price, goodsTypeCode,
                            brandName, providerName, storeCode);
                    list.add(goodsStruct);
                }
            }
        }
    }


}