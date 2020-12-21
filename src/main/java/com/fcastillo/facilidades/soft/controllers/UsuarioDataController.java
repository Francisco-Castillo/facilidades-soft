/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.controllers;

import com.fcastillo.facilidades.soft.Perfil;
import com.fcastillo.facilidades.soft.Persona;
import com.fcastillo.facilidades.soft.Usuario;
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
import com.fcastillo.facilidades.soft.ejb.UsuarioFacadeLocal;
import com.fcastillo.facilidades.soft.ejb.PerfilFacadeLocal;

/**
 *
 * @author fcastillo
 */
@Named(value = "usuarioDataController")
@ViewScoped
public class UsuarioDataController implements Serializable, Operaciones {

  @Inject
  UsuarioFacadeLocal usuariosEJB;

  @EJB
  PerfilFacadeLocal perfilesEJB;

  private List<Usuario> lstUsuarios = new ArrayList<>();
  private Usuario usuario = new Usuario();
  private Persona persona = new Persona();
  private List<Perfil> lstPerfiles = new ArrayList<>();
  private Map<String, Integer> mapSexo = new LinkedHashMap<>();
  private Usuario nuevoUsuario = new Usuario();
  private Persona nuevaPersona = new Persona();
  private String password1;
  private String password2;
  private Perfil perfil = new Perfil();

  public List<Usuario> getLstUsuarios() {
    return lstUsuarios;
  }

  public void setLstUsuarios(List<Usuario> lstUsuarios) {
    this.lstUsuarios = lstUsuarios;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Persona getPersona() {
    return persona;
  }

  public void setPersona(Persona persona) {
    this.persona = persona;
  }

  public Usuario getNuevoUsuario() {
    return nuevoUsuario;
  }

  public void setNuevoUsuario(Usuario nuevoUsuario) {
    this.nuevoUsuario = nuevoUsuario;
  }

  public Persona getNuevaPersona() {
    return nuevaPersona;
  }

  public void setNuevaPersona(Persona nuevaPersona) {
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

  public List<Perfil> getLstPerfiles() {
    return lstPerfiles;
  }

  public void setLstPerfiles(List<Perfil> lstPerfiles) {
    this.lstPerfiles = lstPerfiles;
  }

  public Perfil getPerfil() {
    return perfil;
  }

  public void setPerfil(Perfil perfil) {
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
      nuevoUsuario.setFechaAlta(new Date());
      nuevoUsuario.setIdTipoUsuario(perfil);
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
    nuevoUsuario = new Usuario();
    nuevaPersona = new Persona();
  }

}
