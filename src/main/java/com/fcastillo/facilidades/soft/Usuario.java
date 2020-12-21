/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fcastillo
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
  @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
  @NamedQuery(name = "Usuario.findByUsername", query = "SELECT u FROM Usuario u WHERE u.username = :username"),
  @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password"),
  @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado"),
  @NamedQuery(name = "Usuario.findByFechaAlta", query = "SELECT u FROM Usuario u WHERE u.fechaAlta = :fechaAlta"),
  @NamedQuery(name = "Usuario.findByFechaUltimoLogin", query = "SELECT u FROM Usuario u WHERE u.fechaUltimoLogin = :fechaUltimoLogin")})
public class Usuario implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id", nullable = false)
  private Persona id;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 15)
  @Column(name = "username")
  private String username;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "password")
  private String password;
  @Basic(optional = false)
  @NotNull
  @Column(name = "estado")
  private int estado;
  @Basic(optional = false)
  @NotNull
  @Column(name = "fecha_alta")
  @Temporal(TemporalType.DATE)
  private Date fechaAlta;
  @Column(name = "fecha_ultimo_login")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaUltimoLogin;
  @JoinColumn(name = "id_tipo_usuario", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Perfil idTipoUsuario;
  @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
  @OneToOne(optional = false)
  private Persona persona;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOperador")
  private Collection<Prestamo> prestamoCollection;

  public Usuario() {
  }

  public Usuario(Persona id) {
    this.id = id;
  }

  public Usuario(Persona id, String username, String password, int estado, Date fechaAlta) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.estado = estado;
    this.fechaAlta = fechaAlta;
  }

  

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getEstado() {
    return estado;
  }

  public void setEstado(int estado) {
    this.estado = estado;
  }

  public Date getFechaAlta() {
    return fechaAlta;
  }

  public void setFechaAlta(Date fechaAlta) {
    this.fechaAlta = fechaAlta;
  }

  public Date getFechaUltimoLogin() {
    return fechaUltimoLogin;
  }

  public void setFechaUltimoLogin(Date fechaUltimoLogin) {
    this.fechaUltimoLogin = fechaUltimoLogin;
  }

  public Perfil getIdTipoUsuario() {
    return idTipoUsuario;
  }

  public void setIdTipoUsuario(Perfil idTipoUsuario) {
    this.idTipoUsuario = idTipoUsuario;
  }

  public Persona getPersona() {
    return persona;
  }

  public void setPersona(Persona persona) {
    this.persona = persona;
  }

  @XmlTransient
  public Collection<Prestamo> getPrestamoCollection() {
    return prestamoCollection;
  }

  public void setPrestamoCollection(Collection<Prestamo> prestamoCollection) {
    this.prestamoCollection = prestamoCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Usuario)) {
      return false;
    }
    Usuario other = (Usuario) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.fcastillo.facilidades.soft.Usuario[ id=" + id + " ]";
  }

  public Persona getId() {
    return id;
  }

  public void setId(Persona id) {
    this.id = id;
  }

}
