package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import ru.otus.spring.dao.*;
import ru.otus.spring.domain.Brand;
import ru.otus.spring.domain.GoodsType;
import ru.otus.spring.domain.Provider;
import ru.otus.spring.domain.Store;
import ru.otus.spring.service.BrandService;
import ru.otus.spring.service.GoodsService;

/**
 * http://localhost:8080/actuator
 * http://asviridova:8080/actuator/metrics
 * http://asviridova:8080/actuator/health
 */

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = SpringApplication.run(Main.class);


        CustomerRepository customerRepository = context.getBean(CustomerRepository.class);
        System.out.println(customerRepository.findAll());

        GoodsRepository goodsRepository = context.getBean(GoodsRepository.class);
        System.out.println(goodsRepository.findAll());

        GoodsTypeRepository goodsTypeRepository = context.getBean(GoodsTypeRepository.class);
        System.out.println(goodsTypeRepository.findAll());

        BrandRepository brandRepository = context.getBean(BrandRepository.class);
        System.out.println(brandRepository.findAll());

        StoreRepository storeRepository = context.getBean(StoreRepository.class);
        System.out.println(storeRepository.findAll());

        ProviderRepository providerRepository = context.getBean(ProviderRepository.class);
        System.out.println(providerRepository.findAll());

        BrandService brandService = context.getBean(BrandService.class);
        System.out.println(brandService.getAll());


        //----
        GoodsService goodsService = context.getBean(GoodsService.class);

        Long id = goodsService.insert("RT4567", "Куртка синяя", "blue", "44", 1300.45, 1L, 1L, 1L, 1L);
        System.out.println("id="+id);
        System.out.println("goodsService.getById="+goodsService.getById(id));
        //Optional<AuthorModel> author = authorRepository.findById(1);
       // Console.main(args);
    }
}
