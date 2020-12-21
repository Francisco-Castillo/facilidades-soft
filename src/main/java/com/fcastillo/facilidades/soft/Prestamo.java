/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "prestamos")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p"),
  @NamedQuery(name = "Prestamo.findById", query = "SELECT p FROM Prestamo p WHERE p.id = :id"),
  @NamedQuery(name = "Prestamo.findByDineroPrestado", query = "SELECT p FROM Prestamo p WHERE p.dineroPrestado = :dineroPrestado"),
  @NamedQuery(name = "Prestamo.findByFechaSolicitud", query = "SELECT p FROM Prestamo p WHERE p.fechaSolicitud = :fechaSolicitud"),
  @NamedQuery(name = "Prestamo.findByCantidadCuotas", query = "SELECT p FROM Prestamo p WHERE p.cantidadCuotas = :cantidadCuotas"),
  @NamedQuery(name = "Prestamo.findByTna", query = "SELECT p FROM Prestamo p WHERE p.tna = :tna"),
  @NamedQuery(name = "Prestamo.findByGastosAdmin", query = "SELECT p FROM Prestamo p WHERE p.gastosAdmin = :gastosAdmin"),
  @NamedQuery(name = "Prestamo.findBySeguroVida", query = "SELECT p FROM Prestamo p WHERE p.seguroVida = :seguroVida"),
  @NamedQuery(name = "Prestamo.findByIva", query = "SELECT p FROM Prestamo p WHERE p.iva = :iva"),
  @NamedQuery(name = "Prestamo.findByObservaciones", query = "SELECT p FROM Prestamo p WHERE p.observaciones = :observaciones"),
  @NamedQuery(name = "Prestamo.findByEstado", query = "SELECT p FROM Prestamo p WHERE p.estado = :estado")})
public class Prestamo implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Basic(optional = false)
  @NotNull
  @Column(name = "dinero_prestado")
  private BigDecimal dineroPrestado;
  @Basic(optional = false)
  @NotNull
  @Column(name = "fecha_solicitud")
  @Temporal(TemporalType.DATE)
  private Date fechaSolicitud;
  @Basic(optional = false)
  @NotNull
  @Column(name = "cantidad_cuotas")
  private int cantidadCuotas;
  @Column(name = "tna")
  private BigDecimal tna;
  @Column(name = "gastos_admin")
  private BigDecimal gastosAdmin;
  @Column(name = "seguro_vida")
  private BigDecimal seguroVida;
  @Column(name = "iva")
  private BigDecimal iva;
  @Size(max = 400)
  @Column(name = "observaciones")
  private String observaciones;
  @Basic(optional = false)
  @NotNull
  @Column(name = "estado")
  private int estado;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPrestamo")
  private Collection<Cuota> cuotaCollection;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPrestamo")
  private Collection<Pago> pagoCollection;
  @JoinColumn(name = "id_cliente", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Cliente idCliente;
  @JoinColumn(name = "id_operador", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Usuario idOperador;

  public Prestamo() {
  }

  public Prestamo(Integer id) {
    this.id = id;
  }

  public Prestamo(Integer id, BigDecimal dineroPrestado, Date fechaSolicitud, int cantidadCuotas, int estado) {
    this.id = id;
    this.dineroPrestado = dineroPrestado;
    this.fechaSolicitud = fechaSolicitud;
    this.cantidadCuotas = cantidadCuotas;
    this.estado = estado;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public BigDecimal getDineroPrestado() {
    return dineroPrestado;
  }

  public void setDineroPrestado(BigDecimal dineroPrestado) {
    this.dineroPrestado = dineroPrestado;
  }

  public Date getFechaSolicitud() {
    return fechaSolicitud;
  }

  public void setFechaSolicitud(Date fechaSolicitud) {
    this.fechaSolicitud = fechaSolicitud;
  }

  public int getCantidadCuotas() {
    return cantidadCuotas;
  }

  public void setCantidadCuotas(int cantidadCuotas) {
    this.cantidadCuotas = cantidadCuotas;
  }

  public BigDecimal getTna() {
    return tna;
  }

  public void setTna(BigDecimal tna) {
    this.tna = tna;
  }

  public BigDecimal getGastosAdmin() {
    return gastosAdmin;
  }

  public void setGastosAdmin(BigDecimal gastosAdmin) {
    this.gastosAdmin = gastosAdmin;
  }

  public BigDecimal getSeguroVida() {
    return seguroVida;
  }

  public void setSeguroVida(BigDecimal seguroVida) {
    this.seguroVida = seguroVida;
  }

  public BigDecimal getIva() {
    return iva;
  }

  public void setIva(BigDecimal iva) {
    this.iva = iva;
  }

  public String getObservaciones() {
    return observaciones;
  }

  public void setObservaciones(String observaciones) {
    this.observaciones = observaciones;
  }

  public int getEstado() {
    return estado;
  }

  public void setEstado(int estado) {
    this.estado = estado;
  }

  @XmlTransient
  public Collection<Cuota> getCuotaCollection() {
    return cuotaCollection;
  }

  public void setCuotaCollection(Collection<Cuota> cuotaCollection) {
    this.cuotaCollection = cuotaCollection;
  }

  @XmlTransient
  public Collection<Pago> getPagoCollection() {
    return pagoCollection;
  }

  public void setPagoCollection(Collection<Pago> pagoCollection) {
    this.pagoCollection = pagoCollection;
  }

  public Cliente getIdCliente() {
    return idCliente;
  }

  public void setIdCliente(Cliente idCliente) {
    this.idCliente = idCliente;
  }

  public Usuario getIdOperador() {
    return idOperador;
  }

  public void setIdOperador(Usuario idOperador) {
    this.idOperador = idOperador;
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
    if (!(object instanceof Prestamo)) {
      return false;
    }
    Prestamo other = (Prestamo) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.fcastillo.facilidades.soft.Prestamo[ id=" + id + " ]";
  }
  
}
