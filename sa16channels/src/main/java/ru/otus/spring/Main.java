package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.spring.dao.*;
import ru.otus.spring.domain.Brand;
import ru.otus.spring.domain.GoodsType;
import ru.otus.spring.domain.Provider;
import ru.otus.spring.domain.Store;
import ru.otus.spring.message.GoodsFactory;
import ru.otus.spring.message.GoodsItem;
import ru.otus.spring.message.ReplyItem;
import ru.otus.spring.service.BrandService;
import ru.otus.spring.service.GoodsService;

import java.util.Collection;
import java.util.stream.Collectors;


@SpringBootApplication
@IntegrationComponentScan
@EnableIntegration
public class Main {

    @Bean
    public QueueChannel goodsChannel() {
        return MessageChannels.queue( 10 ).get();
    }

    @Bean
    public PublishSubscribeChannel replyChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate( 100 ).maxMessagesPerPoll( 5 ).get();
    }

    @Bean
    public IntegrationFlow cafeFlow() {
        return IntegrationFlows.from( "goodsChannel" )
                .split()
                .handle( "defineService", "defineProvider" )
                .handle( "defineService", "defineBrand" )
                .handle( "defineService", "defineGoodsType" )
                .handle( "filterService", "filter" )
                .handle( "goodsService", "save" )
                .aggregate()
                .channel( "replyChannel" )
                .get();
    }


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


        //-----Integration
        GoodsDistributor goodsDistributor = context.getBean( GoodsDistributor.class );

        Collection<GoodsItem> items = GoodsFactory.generateGoodsItemList();
        System.out.println( "New goodsItems: " + items.stream().map( GoodsItem::toString ).collect( Collectors.joining( "," ) ) );
        Collection<ReplyItem> replyItems = goodsDistributor.process( items );
        System.out.println( "ReplyItem: " + replyItems.stream()
                .map( ReplyItem::toString )
                .collect( Collectors.joining( "," ) ) );


    }
}
