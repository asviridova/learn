package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Store;
import ru.otus.spring.service.StoreService;

import java.util.List;
import java.util.Optional;

@RestController
public class StoreController {


    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/stores")
    public List<Store> getAllStores() {
        return storeService.getAll();
    }

    @GetMapping("/stores/{id}")
    public Optional<Store> findStore(@RequestParam("id") Long id) {
        return storeService.getById(id);
    }

    @DeleteMapping("/stores/{id}")
    public void removeStore(@RequestParam("id") Long id) {
        storeService.deleteById(id);
    }

    @PutMapping("/stores/{id}")
    public ResponseEntity<?> saveStore(@RequestParam("id") Long id, @RequestParam("code") String code, @RequestParam(value = "address", required = false) String address) {
        storeService.update(id, code, address);
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }

    @PostMapping("/stores")
    public ResponseEntity<?> insertStore(@RequestParam("code") String code, @RequestParam(value = "address", required = false) String address) {
        Long id =  storeService.insert(code, address);
        if(id!=null && id>0) {
            return new ResponseEntity<>("OK, StoreId="+id, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}