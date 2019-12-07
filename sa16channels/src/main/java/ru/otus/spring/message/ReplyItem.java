package ru.otus.spring.message;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ReplyItem {
    GoodsItem goodsItem;
    String status;
    Long goodsId;

    public ReplyItem(GoodsItem goodsItem){
        this.goodsItem = goodsItem;
    }

}
