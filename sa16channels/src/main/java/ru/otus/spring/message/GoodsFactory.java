package ru.otus.spring.message;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class GoodsFactory {

    private static final String[] PROVIDER = { "Поставщик 1", "Поставщик 2", "Поставщик 3"};

    private static final String[] BRAND = { "KUOMA", "Nike", "Columbia"};

    private static final String[] CODES = { "34RR", "78YY", "43ET"};

    private static final String[] NAMES = { "Куртка", "Сапоги", "Джинсы"};

    private static final String[] COLOURS = { "red", "blue", "green"};

    private static final String[] SIZES = { "38", "39", "40"};

    private static final Double[] PRICES = { 1200d, 1500d, 3600d};


    public static Collection<GoodsItem> generateGoodsItemList() {
        List<GoodsItem> items = new ArrayList<>();
        for (int i = 0; i < RandomUtils.nextInt( 1, 5 ); ++ i ) {
            items.add( generateGoodsItem() );
        }
        return items;
    }

    private static GoodsItem generateGoodsItem(){
        GoodsItem goodsItem = new GoodsItem();
        goodsItem.setName( NAMES[ RandomUtils.nextInt( 0, NAMES.length ) ]);
        goodsItem.setCode( CODES[ RandomUtils.nextInt( 0, CODES.length ) ]);
        goodsItem.setColour(COLOURS[ RandomUtils.nextInt( 0, COLOURS.length ) ]);
        goodsItem.setSize(SIZES[ RandomUtils.nextInt( 0, SIZES.length ) ]);
        goodsItem.setPrice(PRICES[ RandomUtils.nextInt( 0, PRICES.length ) ]);
        goodsItem.setBrandName(BRAND[ RandomUtils.nextInt( 0, BRAND.length ) ]);
        goodsItem.setProviderName(PROVIDER[ RandomUtils.nextInt( 0, PROVIDER.length ) ]);
        return goodsItem;
    }



}
