package ru.otus.spring;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.message.GoodsItem;
import ru.otus.spring.message.ReplyItem;

import java.util.Collection;

@MessagingGateway
public interface GoodsDistributor {

    @Gateway(requestChannel = "goodsChannel", replyChannel = "replyChannel")
    Collection<ReplyItem> process(Collection<GoodsItem> goodsItem);
}
