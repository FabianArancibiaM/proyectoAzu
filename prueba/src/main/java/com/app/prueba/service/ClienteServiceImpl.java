package com.app.prueba.service;

import com.app.prueba.controller.ClienteController;
import com.app.prueba.dto.ClienteDto;
import com.app.prueba.dto.RegionDto;
import com.app.prueba.exception.ExceptionSistem;
import com.app.prueba.model.ClienteModel;
import com.app.prueba.model.RegionModel;
import com.app.prueba.repository.ClienteRepository;
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
import java.util.List;
@Service
public class ClienteServiceImpl implements ClienteService, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteRepository clienteRepository;

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
                dtoList.add(crearObject(cli));
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

    private ClienteDto crearObject(ClienteModel cli) {
        ClienteDto dto = new ClienteDto();
        dozerBeanMapper.map(cli,dto);
        RegionDto rDto = new RegionDto();
        dozerBeanMapper.map(cli.getRegionModel(),rDto);
        dto.setRegionDto(rDto);
        return dto;
    }

}
