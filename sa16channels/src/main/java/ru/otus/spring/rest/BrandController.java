package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Brand;
import ru.otus.spring.service.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BrandController {


    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brands")
    public List<Brand> getAllBrands() {
        return brandService.getAll();
    }

    @GetMapping("/brands/{id}")
    public Optional<Brand> findBrand(@RequestParam("id") Long id) {
        return brandService.getById(id);
    }

    @DeleteMapping("/brands/{id}")
    public void removeBook(@RequestParam("id") Long id) {
        brandService.deleteById(id);
    }

    @PutMapping("/brands/{id}")
    public ResponseEntity<?> saveBook(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam(value = "country", required = false) String country) {
        brandService.update(id, name, country);
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }

    @PostMapping("/brands")
    public ResponseEntity<?> insertBrand(@RequestParam("name") String name, @RequestParam(value = "country", required = false) String country) {
        Long id =  brandService.insert(name, country);
        if(id!=null && id>0) {
            return new ResponseEntity<>("OK, BrandId="+id, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
