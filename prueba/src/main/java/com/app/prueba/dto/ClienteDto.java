package com.app.prueba.dto;

import java.util.Date;

public class ClienteDto {
    private Long id;

    private String nombre;

    private String apellido;

    private String email;

    private Date createdAt;

    private String foto;

    private RegionDto regionModel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public RegionDto getRegionDto() {
        return regionModel;
    }

    public void setRegionDto(RegionDto regionModel) {
        this.regionModel = regionModel;
    }
}
