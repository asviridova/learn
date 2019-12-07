package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.message.GoodsFactory;
import ru.otus.spring.message.GoodsItem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DefineService {

    private static ProviderService providerService;
    private static BrandService brandService;
    private static GoodsTypeService goodsTypeService;

    public static Long GOODS_TYPE_SHOES = 1L;
    public static Long GOODS_TYPE_CLOTHES = 2L;
    public static Long GOODS_TYPE_HOME = 3L;


    public static Set<String> shoes = new HashSet<String>();
    static {
        shoes.add("сапоги");
        shoes.add("ботинки");
        shoes.add("туфли");
    }
    public static Set<String> clothes = new HashSet<String>();
    static {
        clothes.add("куртка");
        clothes.add("джинсы");
        clothes.add("платье");
    }
    public static Set<String> home = new HashSet<String>();
    static {
        shoes.add("подушка");
        shoes.add("одеяло");
        shoes.add("полотенце");
    }

    public  DefineService(ProviderService providerService, BrandService brandService, GoodsTypeService goodsTypeService){
        this.providerService = providerService;
        this.brandService = brandService;
        this.goodsTypeService = goodsTypeService;
    }

    public void defineProvider(GoodsItem goodsItem){
        goodsItem.setProvider(providerService.findProviderByName(goodsItem.getProviderName()));
    }

    public void defineBrand(GoodsItem goodsItem){
        goodsItem.setBrand(brandService.findBrandByName(goodsItem.getBrandName()));
    }

    public void defineGoodsType(GoodsItem goodsItem) {
        defineGoodsType(shoes, GOODS_TYPE_SHOES, goodsItem );
        if(goodsItem.getGoodsType() == null) {
            defineGoodsType(clothes, GOODS_TYPE_CLOTHES, goodsItem );
            if(goodsItem.getGoodsType() == null) {
                defineGoodsType(home, GOODS_TYPE_HOME, goodsItem );
            }
        }
    }

    private void defineGoodsType(Set<String> goods, Long goodType, GoodsItem goodsItem ){
        for (String s : goods) {
            if (goodsItem.getName().toLowerCase().contains(s)) {
                goodsItem.setGoodsType(goodsTypeService.getById(goodType).get());
                break;
            }
        }

    }

}
