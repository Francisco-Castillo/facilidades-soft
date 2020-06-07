/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.controllers;

import com.fcastillo.facilidades.soft.Personas;
import com.fcastillo.facilidades.soft.Usuarios;
import com.fcastillo.facilidades.soft.ejb.UsuariosFacadeLocal;
import com.fcastillo.facilidades.soft.interfaces.Operaciones;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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

    private List<Usuarios> lstUsuarios = new ArrayList<>();
    private Usuarios usuario = new Usuarios();
    private Personas persona = new Personas();

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

    @PostConstruct
    public void init() {
        lstUsuarios = usuariosEJB.findAll();
    }

    @Override
    public void crear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
