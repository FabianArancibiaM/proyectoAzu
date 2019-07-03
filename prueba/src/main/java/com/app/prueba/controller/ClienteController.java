package com.app.prueba.controller;

import com.app.prueba.dto.ClienteDto;
import com.app.prueba.dto.RegionDto;
import com.app.prueba.exception.ExceptionSistem;
import com.app.prueba.model.ClienteModel;
import com.app.prueba.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody ClienteDto dto) {
        LOGGER.info("Se invoca servicio listar");
        Map<String,String> map = new HashMap<>();
        try{
            map = clienteService.save(dto,false);
            if (map.get("error")==null){
                return new ResponseEntity<>(map,HttpStatus.CREATED);
            }
            return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            map.put("mesagge","Error al guardar");
            return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/actualizar")
    public ResponseEntity<?> actualizar(@RequestBody ClienteDto dto) {
        LOGGER.info("Se invoca servicio listar");
        Map<String,String> map = new HashMap<>();
        try{
            map = clienteService.save(dto,true);
            if (map.get("error")==null){
                return new ResponseEntity<>(map,HttpStatus.CREATED);
            }
            return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            map.put("mesagge","Error al guardar");
            return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable(value = "id") long id) {
        LOGGER.info("Se invoca servicio listar");
        Map<String,String> map = new HashMap<>();
        try{
            map = clienteService.delete(id);
            if (map.get("error")==null){
                return new ResponseEntity<>(map,HttpStatus.OK);
            }
            return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
        }catch (Exception ex){
            map.put("mesagge","Error al guardar");
            return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/listarClientes")
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

    @GetMapping("/listarRegion")
    public ResponseEntity<?> getListaRegiones() {
        LOGGER.info("Se invoca servicio listar");
        try{
            List<RegionDto> lista = clienteService.getListRegiones();
            if (lista==null){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }catch (ExceptionSistem ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
