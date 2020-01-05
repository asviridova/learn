package ru.otus.spring.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.otus.spring.dao.GoodsRepository;
import ru.otus.spring.dao.ProtocolRepository;
import ru.otus.spring.data.GoodsStruct;
import ru.otus.spring.domain.*;
import ru.otus.spring.exception.DataNotFoundException;
import ru.otus.spring.exception.IncorrectFormatException;

import javax.annotation.security.RolesAllowed;
import java.util.*;


@Service
@Slf4j
public class GoodsServiceImpl implements GoodsService {
    private final GoodsRepository goodsRepository;
    private final GoodsTypeService goodsTypeService;
    private final ProviderService providerService;
    private final BrandService brandService;
    private final StoreService storeService;
    private final ProtocolService protocolService;

    @Autowired
    public GoodsServiceImpl(GoodsRepository goodsRepository, GoodsTypeService goodsTypeService,
                            ProviderService providerService, BrandService brandService, StoreService storeService,
                            ProtocolService protocolService) {
        this.goodsRepository = goodsRepository;
        this.goodsTypeService = goodsTypeService;
        this.providerService = providerService;
        this.brandService = brandService;
        this.storeService = storeService;
        this.protocolService = protocolService;
    }

    @Override
    public long count() {
        return goodsRepository.count();
    }

    @Override
    @RolesAllowed({ "ROLE_ADMIN", "ROLE_PROVIDER" })
    public Long insert(Goods goods) {
        Goods goodsNew = goodsRepository.save(goods);
        return goodsNew.getId();
    }

    @Override
    public Optional<Goods> getById(Long id) {
        return goodsRepository.findById(id);
    }

    @Override
    public List<Goods> getAll() {
        return goodsRepository.findAll();
    }

    @Override
    @RolesAllowed({ "ROLE_ADMIN", "ROLE_PROVIDER" })
    public void deleteById(Long id) {
        goodsRepository.deleteById(id);
    }

    @Override
    @RolesAllowed({ "ROLE_ADMIN", "ROLE_PROVIDER" })
    public Long insert(String code, String name,  String colour, String size, Integer quantity, Double price, GoodsType goodsType, Brand brand, Provider provider, Store store) {
        Goods goods = new Goods(code, name, colour, size, quantity, price, goodsType, brand, provider, store);
        Goods providerNew = goodsRepository.save(goods);
        Long id = providerNew.getId();
        log.info("goods inserted with id = " + id + ", name = " + name + ", price = " + price);
        return id;
    }

    @Override
    @RolesAllowed({ "ROLE_ADMIN", "ROLE_PROVIDER" })
    public Long insert(String code, String name,  String colour, String size, Integer quantity, Double price, Long goodsTypeId, Long brandId, Long providerId, Long storeId) {
        GoodsType goodsType = null;
        if(goodsTypeId!=null) {
            Optional<GoodsType> goodsTypeOptional = goodsTypeService.getById(goodsTypeId);
            goodsType = goodsTypeOptional.get();
        }
        Provider provider = null;
        if(providerId!=null) {
            Optional<Provider> providerOptional = providerService.getById(providerId);
            provider = providerOptional.get();
        }
        Brand brand = null;
        if(brandId!=null){
            Optional<Brand> brandOptional = brandService.getById(brandId);
            brand = brandOptional.get();
        }
        Store store = null;
        if(storeId !=null){
            Optional<Store> storeOptional = storeService.getById(storeId);
            store = storeOptional.get();
        }


        Goods goods = new Goods(code, name, colour, size, quantity, price, goodsType, brand, provider, store);
        Goods providerNew = goodsRepository.save(goods);
        Long id = providerNew.getId();
        log.info("goods inserted with id = " + id + ", name = " + name + ", price = " + price);
        return id;
    }


    @Override
    @RolesAllowed({ "ROLE_ADMIN", "ROLE_PROVIDER" })
    public Long update(Long id, String code, String name, String colour, String size, Integer quantity, Double price, GoodsType goodsType, Brand brand, Provider provider, Store store) {
        Goods goods = new Goods(id, code, name, colour, size, quantity, price, goodsType, brand, provider, store);
        Goods goodsNew = goodsRepository.save(goods);
        log.info("goods updated with id = " + id + ", name = " + name + ", price = " + price);
        return goodsNew.getId();
    }

    @Override
    public String parseCsvFile(MultipartFile file){
        String resultText = "Успешно завершен процесс разбора файла: "+file.getOriginalFilename();
        String errorText = "";
        boolean flagSuccess = true;
        try {
            if(!file.getOriginalFilename().endsWith(".csv")){
                throw new IncorrectFormatException("Incorrect file extension, required .csv");
            }
            String fileContent = new String(file.getBytes(), "UTF-8");
            List<GoodsStruct> list = new ArrayList<>();
            innerParseCsvFile(fileContent, list);


            for (GoodsStruct goodsStruct: list){
                Goods goods = new Goods();
                goods.setCode(goodsStruct.getCode());
                goods.setName(goodsStruct.getName());
                goods.setColour(goodsStruct.getColour());
                goods.setSize(goodsStruct.getSize());
                goods.setQuantity(goodsStruct.getQuantity());
                goods.setPrice(goodsStruct.getPrice());
                //GoodsType
                if(!StringUtils.isEmpty(goodsStruct.getGoodsTypeCode())) {
                    GoodsType goodsType = goodsTypeService.getByCode(goodsStruct.getGoodsTypeCode());
                    if(goodsType==null){
                        throw new DataNotFoundException("Not found GoodsType for code="+goodsStruct.getGoodsTypeCode());
                    }
                    goods.setGoodsType(goodsType);
                }
                else{
                    throw new DataNotFoundException("Required field GoodsType is empty in source file");
                }
                //Brand
                if(!StringUtils.isEmpty(goodsStruct.getBrandName())) {
                    Brand brand = brandService.getByName(goodsStruct.getBrandName());
                    if(brand==null){
                        throw new DataNotFoundException("Not found Brand for name="+goodsStruct.getBrandName());
                    }
                    goods.setBrand(brand);
                }
                else{
                    throw new DataNotFoundException("Required field Brand is empty in source file");
                }
                //Provider
                if(!StringUtils.isEmpty(goodsStruct.getProviderName())) {
                    Provider provider = providerService.getByName(goodsStruct.getProviderName());
                    if(provider==null){
                        throw new DataNotFoundException("Not found Provider for name="+goodsStruct.getProviderName());
                    }
                    goods.setProvider(provider);
                }
                else{
                    throw new DataNotFoundException("Required field Provider is empty in source file");
                }
                //Store
                if(!StringUtils.isEmpty(goodsStruct.getStoreCode())) {
                    Store store = storeService.getByCode(goodsStruct.getStoreCode());
                    if(store==null){
                        throw new DataNotFoundException("Not found Store for code="+goodsStruct.getStoreCode());
                    }
                    goods.setStore(store);
                }

                Goods providerNew = goodsRepository.save(goods);
                Long id = providerNew.getId();
                log.info("goods inserted with id = " + id + ", name = " + goodsStruct.getName() + ", price = " + goodsStruct.getPrice());
            }

        }
        catch (Exception ex){
            flagSuccess = false;
            resultText = "Процесс разбора файла "+file.getOriginalFilename()+" завершен с ошибкой";
            errorText = (ex.getMessage()!=null && ex.getMessage().length()>255)?ex.getMessage().substring(0, 255):ex.getMessage();
        }

        //Наполнение протокола
        Protocol protocol = null;
        if(flagSuccess){
            protocol = new Protocol(file.getOriginalFilename(), new Date(), "OK", Protocol.RESULT_CODE_OK);
        }
        else{
            protocol = new Protocol(file.getOriginalFilename(), new Date(), errorText, Protocol.RESULT_CODE_ERROR);
        }
        //Сохранение протокола
        protocolService.insert(protocol);

        return resultText;
    }

    private void innerParseCsvFile(String fileContent, List<GoodsStruct> list) throws IncorrectFormatException{
        if(fileContent!=null){
            String[] lines = fileContent.split(System.lineSeparator());
            int i = 0;
            for (String line: lines){
                String[] elements = line.split(";");
                if(elements.length>9){

                    String code = elements[0];
                    String name = StringUtils.trimWhitespace(elements[1]);
                    String colour= StringUtils.trimWhitespace(elements[2]);
                    String size= StringUtils.trimWhitespace(elements[3]);
                    String q = StringUtils.trimWhitespace(elements[4]);
                    Integer quantity= !StringUtils.isEmpty(q)?Integer.parseInt(q):null;
                    String d = StringUtils.trimWhitespace(elements[5]);
                    Double price= !StringUtils.isEmpty(d)?Double.parseDouble(d):null;

                    String goodsTypeCode = StringUtils.trimWhitespace(elements[6]);
                    String brandName = StringUtils.trimWhitespace(elements[7]);
                    String providerName = StringUtils.trimWhitespace(elements[8]);
                    String storeCode = StringUtils.trimWhitespace(elements[9]);
                    GoodsStruct goodsStruct = new GoodsStruct(code, name, colour, size, quantity, price, goodsTypeCode,
                            brandName, providerName, storeCode);
                    list.add(goodsStruct);
                }
                else{
                    throw new IncorrectFormatException("Некорректный формат данных в строке: количество элементов менее 10");
                }
            }
        }
    }


}