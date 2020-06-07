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
@Table(name = "Usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findById", query = "SELECT u FROM Usuarios u WHERE u.id = :id"),
    @NamedQuery(name = "Usuarios.findByUsername", query = "SELECT u FROM Usuarios u WHERE u.username = :username"),
    @NamedQuery(name = "Usuarios.findByPassword", query = "SELECT u FROM Usuarios u WHERE u.password = :password"),
    @NamedQuery(name = "Usuarios.findByEstado", query = "SELECT u FROM Usuarios u WHERE u.estado = :estado"),
    @NamedQuery(name = "Usuarios.findByFalta", query = "SELECT u FROM Usuarios u WHERE u.falta = :falta"),
    @NamedQuery(name = "Usuarios.findByFultimologin", query = "SELECT u FROM Usuarios u WHERE u.fultimologin = :fultimologin")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", nullable = false)
    private Personas id;
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
    @Column(name = "falta")
    @Temporal(TemporalType.DATE)
    private Date falta;
    @Column(name = "fultimologin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fultimologin;
    @JoinColumn(name = "idtipousuario", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Perfiles idtipousuario;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Personas personas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idoperador")
    private Collection<Prestamos> prestamosCollection;

    public Usuarios() {
    }

    public Usuarios(Personas id) {
        this.id = id;
    }

    public Usuarios(Personas id, String username, String password, int estado, Date falta) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.estado = estado;
        this.falta = falta;
    }

    public Personas getId() {
        return id;
    }
   
    public void setId(Personas id) {
        this.id = id;
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

    public Date getFalta() {
        return falta;
    }

    public void setFalta(Date falta) {
        this.falta = falta;
    }

    public Date getFultimologin() {
        return fultimologin;
    }

    public void setFultimologin(Date fultimologin) {
        this.fultimologin = fultimologin;
    }

    public Perfiles getIdtipousuario() {
        return idtipousuario;
    }

    public void setIdtipousuario(Perfiles idtipousuario) {
        this.idtipousuario = idtipousuario;
    }

    public Personas getPersonas() {
        return personas;
    }

    public void setPersonas(Personas personas) {
        this.personas = personas;
    }

    @XmlTransient
    public Collection<Prestamos> getPrestamosCollection() {
        return prestamosCollection;
    }

    public void setPrestamosCollection(Collection<Prestamos> prestamosCollection) {
        this.prestamosCollection = prestamosCollection;
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
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fcastillo.facsoft.Usuarios[ id=" + id + " ]";
    }
    
}
