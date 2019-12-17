package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.*;
import ru.otus.spring.service.*;

import java.util.List;
import java.util.Optional;

@RestController
public class GoodsController {


    private final GoodsService goodsService;
    private final GoodsTypeService goodsTypeService;
    private final ProviderService providerService;
    private final BrandService brandService;
    private final StoreService storeService;

    @Autowired
    public GoodsController(GoodsService goodsService, GoodsTypeService goodsTypeService, ProviderService providerService, BrandService brandService, StoreService storeService) {
        this.goodsService = goodsService;
        this.goodsTypeService = goodsTypeService;
        this.providerService = providerService;
        this.brandService = brandService;
        this.storeService = storeService;
    }

    @GetMapping("/goods")
    public List<Goods> getAllGoods() {
        return goodsService.getAll();
    }

    @GetMapping("/goods/{id}")
    public Optional<Goods> findGoods(@RequestParam("id") Long id) {
        return goodsService.getById(id);
    }

    @DeleteMapping("/goods/{id}")
    public void removeGoods(@RequestParam("id") Long id) {
        goodsService.deleteById(id);
    }

    @PutMapping("/goods/{id}")
    public ResponseEntity<?> updateGoods(@RequestParam("id") Long id,
                                         @RequestParam("code") String code,
                                         @RequestParam("name") String name,
                                         @RequestParam(value = "colour", required = false) String colour,
                                         @RequestParam(value = "size", required = false) String size,
                                         @RequestParam("price") Double price,
                                         @RequestParam("goodsTypeId") Long goodsTypeId,
                                         @RequestParam(value = "brandId", required = false) Long brandId,
                                         @RequestParam("providerId") Long providerId,
                                         @RequestParam(value = "storeId", required = false) Long storeId ) {
        Optional<GoodsType> goodsType = goodsTypeService.getById(goodsTypeId);
        Optional<Provider> provider = providerService.getById(providerId);
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

        goodsService.update(id, code, name, colour, size, price, goodsType.get(), brand, provider.get(), store);
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }

    @PostMapping("/goods")
    public ResponseEntity<?> insertGoods(@RequestParam("code") String code,
                                         @RequestParam("name") String name,
                                         @RequestParam(value = "colour", required = false) String colour,
                                         @RequestParam(value = "size", required = false) String size,
                                         @RequestParam("price") Double price,
                                         @RequestParam("goodsTypeId") Long goodsTypeId,
                                         @RequestParam(value = "brandId", required = false) Long brandId,
                                         @RequestParam("providerId") Long providerId,
                                         @RequestParam(value = "storeId", required = false) Long storeId ) {
        Long id =  goodsService.insert(code, name, colour, size, price, goodsTypeId, brandId, providerId, storeId);
        if(id!=null && id>0) {
            return new ResponseEntity<>("OK, GoodsId="+id, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

