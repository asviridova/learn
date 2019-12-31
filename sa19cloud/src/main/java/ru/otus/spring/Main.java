package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;

import ru.otus.spring.dao.*;
import ru.otus.spring.service.BrandService;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/*
Dashboard
http://localhost:9001/hystrix/monitor?stream=http://localhost:9001/turbine.stream
 */

@EnableHystrixDashboard
@EnableTurbine
@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = SpringApplication.run(Main.class, args);


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


    }
}
