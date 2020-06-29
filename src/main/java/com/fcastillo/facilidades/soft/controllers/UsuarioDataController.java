/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.controllers;

import com.fcastillo.facilidades.soft.Perfiles;
import com.fcastillo.facilidades.soft.Personas;
import com.fcastillo.facilidades.soft.Usuarios;
import com.fcastillo.facilidades.soft.ejb.PerfilesFacadeLocal;
import com.fcastillo.facilidades.soft.ejb.UsuariosFacadeLocal;
import com.fcastillo.facilidades.soft.interfaces.Operaciones;
import com.fcastillo.facilidades.soft.utilidades.Constantes;
import com.fcastillo.facilidades.soft.utilidades.Mensajes;
import com.fcastillo.utilidades.Password;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author fcastillo
 */
@Named(value = "usuarioDataController")
@ViewScoped
public class UsuarioDataController implements Serializable, Operaciones {

    @Inject
    UsuariosFacadeLocal usuariosEJB;

    @EJB
    PerfilesFacadeLocal perfilesEJB;

    private List<Usuarios> lstUsuarios = new ArrayList<>();
    private Usuarios usuario = new Usuarios();
    private Personas persona = new Personas();
    private List<Perfiles> lstPerfiles = new ArrayList<>();
    private Map<String, Integer> mapSexo = new LinkedHashMap<>();
    private Usuarios nuevoUsuario = new Usuarios();
    private Personas nuevaPersona = new Personas();
    private String password1;
    private String password2;
    private Perfiles perfil = new Perfiles();

    public List<Usuarios> getLstUsuarios() {
        return lstUsuarios;
    }

    public void setLstUsuarios(List<Usuarios> lstUsuarios) {
        this.lstUsuarios = lstUsuarios;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public Usuarios getNuevoUsuario() {
        return nuevoUsuario;
    }

    public void setNuevoUsuario(Usuarios nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    public Personas getNuevaPersona() {
        return nuevaPersona;
    }

    public void setNuevaPersona(Personas nuevaPersona) {
        this.nuevaPersona = nuevaPersona;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public Map<String, Integer> getMapSexo() {
        return mapSexo;
    }

    public void setMapSexo(Map<String, Integer> mapSexo) {
        this.mapSexo = mapSexo;
    }

    public List<Perfiles> getLstPerfiles() {
        return lstPerfiles;
    }

    public void setLstPerfiles(List<Perfiles> lstPerfiles) {
        this.lstPerfiles = lstPerfiles;
    }

    public Perfiles getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfiles perfil) {
        this.perfil = perfil;
    }

    @PostConstruct
    public void init() {
        lstUsuarios = usuariosEJB.findAll();
        lstPerfiles = perfilesEJB.findAll();
        loadMapSexo();
    }

    @Override
    public void crear() {
        try {
            nuevoUsuario.setFalta(new Date());
            nuevoUsuario.setIdtipousuario(perfil);
            nuevoUsuario.setEstado(Constantes.ESTADO_ACTIVO);
            nuevoUsuario.setPassword(Password.encriptar(password1));
            nuevoUsuario.setId(nuevaPersona);
            usuariosEJB.create(nuevoUsuario);
            Mensajes.info("Usuario registrado exitosamente");
            limpiarFormulario();
        } catch (Exception e) {
            Mensajes.error("crear() " + e.getMessage());
        }
    }

    @Override
    public void editar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void loadMapSexo() {
        mapSexo.put("Masculino", Constantes.MASCULINO);
        mapSexo.put("Femenino", Constantes.FEMENINO);
    }

    private void limpiarFormulario() {
        nuevoUsuario = new Usuarios();
        nuevaPersona = new Personas();
    }

}
