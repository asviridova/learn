package ru.otus.spring.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.spring.service.*;

@Controller
@Slf4j
public class GoodsPageController {

    private final BrandService brandService;
    private final GoodsTypeService goodsTypeService;
    private final GoodsService goodsService;
    private final ProviderService providerService;
    private final StoreService storeService;

    @Autowired
    public GoodsPageController(BrandService brandService, GoodsTypeService goodsTypeService, GoodsService goodsService, ProviderService providerService, StoreService storeService) {
        this.brandService = brandService;
        this.goodsTypeService = goodsTypeService;
        this.goodsService = goodsService;
        this.providerService = providerService;
        this.storeService = storeService;
    }

    @GetMapping("/")
    public String listPageGoods(Model model) {
        return "listgoods";
    }


    @PostMapping("/add")
    public String addBookPage(Model model) {
        return "create";
    }


    @GetMapping("/edit")
    public String editBookPage(@RequestParam("id") String id, Model model) {
        log.debug("selected goods id:"+id);
        return "edit";
    }


}

