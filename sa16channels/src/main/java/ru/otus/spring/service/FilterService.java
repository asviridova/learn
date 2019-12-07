package ru.otus.spring.service;

import ru.otus.spring.message.GoodsItem;

public class FilterService {

    public void filter(GoodsItem goodsItem){
        if(goodsItem.getProvider().getFlagBlackList()!=null && goodsItem.getProvider().getFlagBlackList().intValue()==1){
            goodsItem.setFiltered(true);
        }
        if(goodsItem.getGoodsType()==null){
            goodsItem.setFiltered(true);
        }
    }
}
