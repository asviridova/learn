package ru.otus.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.spring.domain.Partner;
import ru.otus.spring.service.PartnerService;

import java.util.List;
import java.util.Optional;

@RestController
public class PartnerController {


    private final PartnerService partnerService;

    @Autowired
    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GetMapping("/partners")
    public List<Partner> getAllPartners() {
        return partnerService.getAll();
    }

    @GetMapping("/partners/{id}")
    public Optional<Partner> findProvider(@RequestParam("id") Long id) {
        return partnerService.getById(id);
    }

    @DeleteMapping("/partners/{id}")
    public void removeProvider(@RequestParam("id") Long id) {
        partnerService.deleteById(id);
    }

    @PutMapping("/partners/{id}")
    public ResponseEntity<?> savePartner(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam(value = "inn", required = false) String inn, @RequestParam(value = "address", required = false) String address) {
        partnerService.update(id, name, inn, address);
        return new ResponseEntity<>("OK", HttpStatus.ACCEPTED);
    }

    @PostMapping("/partners")
    public ResponseEntity<?> insertPartner(@RequestParam("name") String name, @RequestParam(value = "inn", required = false) String inn, @RequestParam(value = "address", required = false) String address) {
        Long id =  partnerService.insert(name, inn, address);
        if(id!=null && id>0) {
            return new ResponseEntity<>("OK, ProviderId="+id, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("ERROR", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

