package ru.otus.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.ProtocolRepository;
import ru.otus.spring.domain.Protocol;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Service
@Slf4j
public class ProtocolServiceImpl implements ProtocolService {

    private final ProtocolRepository protocolRepository;

    @Autowired
    public ProtocolServiceImpl(ProtocolRepository protocolRepository) {
        this.protocolRepository = protocolRepository;
    }

    @Override
    public List<Protocol> getAll() {
        return protocolRepository.findAll();
    }

    @Override
    public Long insert(Protocol protocol) {
        Protocol protocolNew = protocolRepository.save(protocol);
        return protocolNew.getId();
    }
}
