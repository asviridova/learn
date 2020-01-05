package ru.otus.spring.service;

import ru.otus.spring.domain.Protocol;


import java.util.List;

public interface ProtocolService {

    List<Protocol> getAll() ;

    Long insert(Protocol protocol) ;
}
