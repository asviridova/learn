package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.GoodsType;
import ru.otus.spring.service.GoodsTypeService;

import java.util.List;
import java.util.Optional;

@RestController
public class GoodsTypeController {


    private final GoodsTypeService goodsTypeService;

    @Autowired
    public GoodsTypeController(GoodsTypeService goodsTypeService) {
        this.goodsTypeService = goodsTypeService;
    }

    @GetMapping("/goodstypes")
    public List<GoodsType> getAllGoodsTypes() {
        return goodsTypeService.getAll();
    }

    @GetMapping("/goodstypes/{id}")
    public Optional<GoodsType> findGoodsType(@RequestParam("id") Long id) {
        return goodsTypeService.getById(id);
    }

    @DeleteMapping("/goodstypes/{id}")
    public void removeGoodsType(@RequestParam("id") Long id) {
        goodsTypeService.deleteById(id);
    }

    @PutMapping("/goodstypes/{id}")
    public ResponseEntity<?> saveGoodsType(@RequestParam("id") Long id, @RequestParam("code") String code, @RequestParam(value = "name", required = false) String name) {
        goodsTypeService.update(id, code, name);
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }

    @PostMapping("/goodstypes")
    public ResponseEntity<?> insertGoodsType(@RequestParam("code") String code, @RequestParam(value = "name", required = false) String name) {
        Long id =  goodsTypeService.insert(code, name);
        if(id!=null && id>0) {
            return new ResponseEntity<>("OK, GoodsType="+id, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
