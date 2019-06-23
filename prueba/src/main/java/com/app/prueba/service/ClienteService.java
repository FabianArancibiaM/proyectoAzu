package com.app.prueba.service;

import com.app.prueba.dto.ClienteDto;
import com.app.prueba.exception.ExceptionSistem;

import java.util.List;

public interface ClienteService {
    List<ClienteDto> getList() throws ExceptionSistem;
}
