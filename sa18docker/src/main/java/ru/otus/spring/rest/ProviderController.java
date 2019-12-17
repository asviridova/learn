package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Provider;
import ru.otus.spring.service.ProviderService;

import java.util.List;
import java.util.Optional;

@RestController
public class ProviderController {


    private final ProviderService providerService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping("/providers")
    public List<Provider> getAllProviders() {
        return providerService.getAll();
    }

    @GetMapping("/providers/{id}")
    public Optional<Provider> findProvider(@RequestParam("id") Long id) {
        return providerService.getById(id);
    }

    @DeleteMapping("/providers/{id}")
    public void removeProvider(@RequestParam("id") Long id) {
        providerService.deleteById(id);
    }

    @PutMapping("/providers/{id}")
    public ResponseEntity<?> saveProvider(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam(value = "inn", required = false) String inn, @RequestParam(value = "address", required = false) String address) {
        providerService.update(id, name, inn, address);
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }

    @PostMapping("/providers")
    public ResponseEntity<?> insertProvider(@RequestParam("name") String name, @RequestParam(value = "inn", required = false) String inn, @RequestParam(value = "address", required = false) String address) {
        Long id =  providerService.insert(name, inn, address);
        if(id!=null && id>0) {
            return new ResponseEntity<>("OK, ProviderId="+id, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

