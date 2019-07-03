package com.app.prueba.service;

import com.app.prueba.dto.ClienteDto;
import com.app.prueba.dto.RegionDto;
import com.app.prueba.exception.ExceptionSistem;

import java.util.List;
import java.util.Map;

public interface ClienteService {
    List<ClienteDto> getList() throws ExceptionSistem;
    List<RegionDto> getListRegiones() throws ExceptionSistem;
    Map<String,String> save(ClienteDto cli,boolean update);
    Map<String,String> delete(Long id);
}
