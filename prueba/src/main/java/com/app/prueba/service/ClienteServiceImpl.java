package com.app.prueba.service;

import com.app.prueba.controller.ClienteController;
import com.app.prueba.dto.ClienteDto;
import com.app.prueba.dto.RegionDto;
import com.app.prueba.exception.ExceptionSistem;
import com.app.prueba.model.ClienteModel;
import com.app.prueba.model.RegionModel;
import com.app.prueba.repository.ClienteRepository;
import com.app.prueba.repository.RegionRepository;
import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.dozer.loader.api.BeanMappingBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClienteServiceImpl implements ClienteService, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private DozerBeanMapper dozerBeanMapper;

    @Override
    public void afterPropertiesSet(){
        BeanMappingBuilder builder = new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(ClienteModel.class, ClienteDto.class);
                mapping(RegionModel.class, RegionDto.class);
            }
        };
        dozerBeanMapper.addMapping(builder);
    }

    @Override
    public List<ClienteDto> getList() throws ExceptionSistem {
        try{
            List<ClienteModel>models = clienteRepository.findAll();
            List<ClienteDto> dtoList = new ArrayList<>();
            for (ClienteModel cli : models){
                dtoList.add(crearObjectClienteDto(cli));
            }
            return dtoList;
        }catch (MappingException e){
            LOGGER.error("Se produjo un error en el metodo getList -> ",e.getMessage());
            throw new ExceptionSistem("Error al mapear la data",new Throwable());
        }catch (DataAccessException e){
            LOGGER.error("Se produjo un error en el metodo getList -> ",e.getMessage());
            throw new ExceptionSistem("Error en la base de datos",new Throwable());
        }
    }

    @Override
    public Map<String,String> save(ClienteDto cli,boolean update) {
        Map<String,String> map = new HashMap<>();
        String msj = "Guardado";
        if (update){
            msj = "Actualizado";
        }
        try{
            ClienteModel model = crearObjectClienteModel(cli);
            clienteRepository.save(model);
            map.put("mesagge",msj+" con exito");
        }catch (MappingException | DataAccessException e){
            LOGGER.error("Se produjo un error en el metodo save -> ",e.getMessage());
            map.put("error","Error al "+msj);
        }
        return map;
    }

    @Override
    public Map<String,String> delete(Long id) {
        Map<String,String> map = new HashMap<>();
        try{
            clienteRepository.deleteById(id);
            map.put("mesagge","Eliminado con exito");
        }catch (MappingException | DataAccessException e){
            LOGGER.error("Se produjo un error en el metodo save -> ",e.getMessage());
            map.put("error","Error al Eliminar");
        }
        return map;
    }

    @Override
    public List<RegionDto> getListRegiones() throws ExceptionSistem {
        try{
            List<RegionModel>models = regionRepository.findAll();
            List<RegionDto> dtoList = new ArrayList<>();
            for (RegionModel reg : models){
                dtoList.add(crearObjectRegionDto(reg));
            }
            return dtoList;
        }catch (MappingException e){
            LOGGER.error("Se produjo un error en el metodo getListRegiones -> ",e.getMessage());
            throw new ExceptionSistem("Error al mapear la data",new Throwable());
        }catch (DataAccessException e){
            LOGGER.error("Se produjo un error en el metodo getListRegiones -> ",e.getMessage());
            throw new ExceptionSistem("Error en la base de datos",new Throwable());
        }
    }

    private ClienteDto crearObjectClienteDto(ClienteModel cli) {
        ClienteDto dto = new ClienteDto();
        dozerBeanMapper.map(cli,dto);
        RegionDto rDto = new RegionDto();
        dozerBeanMapper.map(cli.getRegionModel(),rDto);
        dto.setRegionDto(rDto);
        return dto;
    }

    private RegionDto crearObjectRegionDto(RegionModel rDto) {
        RegionDto rModel = new RegionDto();
        dozerBeanMapper.map(rDto,rModel);
        return rModel;
    }

    private ClienteModel crearObjectClienteModel( ClienteDto cli) {
        ClienteModel model = new ClienteModel();
        dozerBeanMapper.map(cli,model);
        RegionModel rModel = new RegionModel();
        dozerBeanMapper.map(cli.getRegionDto(),rModel);
        model.setRegionModel(rModel);
        return model;
    }
}
