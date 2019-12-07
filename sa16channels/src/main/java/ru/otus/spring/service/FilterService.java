package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.message.GoodsItem;

@Service
public class FilterService {

    public GoodsItem filter(GoodsItem goodsItem){
        if(goodsItem.getProvider().getFlagBlackList()!=null && goodsItem.getProvider().getFlagBlackList().intValue()==1){
            goodsItem.setFiltered(true);
        }
        if(goodsItem.getGoodsType()==null){
            goodsItem.setFiltered(true);
        }
        return goodsItem;
    }
}
