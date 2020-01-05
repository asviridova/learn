package ru.otus.spring.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.spring.domain.Protocol;
import ru.otus.spring.service.ProtocolService;

import java.util.List;

@RestController
@Slf4j
public class ProtocolController {

    private final ProtocolService protocolService;

    @Autowired
    public ProtocolController(ProtocolService protocolService) {
        this.protocolService = protocolService;
    }

    @GetMapping("/protocols")
    public List<Protocol> getAllProtocols() {
        return protocolService.getAll();
    }
}
