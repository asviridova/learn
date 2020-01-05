package ru.otus.spring.page;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String addGoodsPage(Model model) {
        return "creategoods";
    }


    @GetMapping("/edit")
    public String editGoodsPage(@RequestParam("id") String id, Model model) {
        log.debug("selected goods id:"+id);
        return "editgoods";
    }

    @PostMapping("/viewpartners")
    public String viewPartnersPage(Model model) {
        return "listpartners";
    }

    @GetMapping("/viewpartners")
    public String viewPartnersPageWithGet(Model model) {
        return "listpartners";
    }

    @PostMapping("/viewproviders")
    public String viewProvidersPage(Model model) {
        return "listproviders";
    }

    @GetMapping("/viewproviders")
    public String viewProvidersPageWithGet(Model model) {
        return "listproviders";
    }

    @GetMapping("/editprovider")
    public String editProviderPage(@RequestParam("id") String id, Model model) {
        log.debug("selected provider id:"+id);
        return "editprovider";
    }

    @PostMapping("/addprovider")
    public String addProviderPage(Model model) {
        return "createprovider";
    }

    @PostMapping("/loadgoods")
    public String addLoadFilePage(Model model) {
        return "loadgoods";
    }

    @GetMapping("/loadgoods")
    public String addLoadFilePageWithGet(Model model) {
        return "loadgoods";
    }


    @PostMapping("/loadattribute")
    public String loadGoods(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes ) {
        log.info("loadGoods() called, file="+file.getOriginalFilename());
        String message = goodsService.parseCsvFile(file);
        redirectAttributes.addFlashAttribute("message",message);
        return "redirect:/loadgoods";
    }

    @PostMapping("/listprotocol")
    public String listProtocolPage(Model model) {
        return "listprotocol";
    }

    @GetMapping("/listprotocol")
    public String listProtocolPageWithGet(Model model) {
        return "listprotocol";
    }


}

