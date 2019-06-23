package com.app.prueba.controller;

import com.app.prueba.dto.ClienteDto;
import com.app.prueba.exception.ExceptionSistem;
import com.app.prueba.model.ClienteModel;
import com.app.prueba.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);
    @Autowired
    private ClienteService clienteService;
    @GetMapping("/listar")
    public ResponseEntity<?> getLista() {
        LOGGER.info("Se invoca servicio listar");
        try{
            List<ClienteDto> lista = clienteService.getList();
            if (lista==null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }catch (ExceptionSistem ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
