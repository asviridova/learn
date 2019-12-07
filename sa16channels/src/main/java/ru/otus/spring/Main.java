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
    public IntegrationFlow goodsFlow() {
        return IntegrationFlows.from( "goodsChannel" )
                .split()
                .handle( "defineService", "defineProvider" )
                .handle( "defineService", "defineBrand" )
                .handle( "defineService", "defineGoodsType" )
                .handle( "filterService", "filter" )
                .handle( "goodsServiceImpl", "save" )
                .aggregate()
                .channel( "replyChannel" )
                .get();
    }


    public static void main(String[] args) throws Exception {

        ApplicationContext context = SpringApplication.run(Main.class);

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
