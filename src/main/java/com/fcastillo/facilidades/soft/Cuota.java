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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fcastillo
 */
@Entity
@Table(name = "cuotas")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Cuota.findAll", query = "SELECT c FROM Cuota c"),
  @NamedQuery(name = "Cuota.findById", query = "SELECT c FROM Cuota c WHERE c.id = :id"),
  @NamedQuery(name = "Cuota.findByNro", query = "SELECT c FROM Cuota c WHERE c.nro = :nro"),
  @NamedQuery(name = "Cuota.findByValor", query = "SELECT c FROM Cuota c WHERE c.valor = :valor"),
  @NamedQuery(name = "Cuota.findByFechaVencimiento", query = "SELECT c FROM Cuota c WHERE c.fechaVencimiento = :fechaVencimiento"),
  @NamedQuery(name = "Cuota.findByEstado", query = "SELECT c FROM Cuota c WHERE c.estado = :estado")})
public class Cuota implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Column(name = "nro")
  private int nro;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Basic(optional = false)
  @NotNull
  @Column(name = "valor")
  private BigDecimal valor;
  @Basic(optional = false)
  @NotNull
  @Column(name = "fecha_vencimiento")
  @Temporal(TemporalType.DATE)
  private Date fechaVencimiento;
  @Basic(optional = false)
  @NotNull
  @Column(name = "estado")
  private int estado;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuota")
  private Collection<DetallePago> detallePagoCollection;
  @JoinColumn(name = "id_prestamo", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Prestamo idPrestamo;

  public Cuota() {
  }

  public Cuota(Integer id) {
    this.id = id;
  }

  public Cuota(Integer id, int nro, BigDecimal valor, Date fechaVencimiento, int estado) {
    this.id = id;
    this.nro = nro;
    this.valor = valor;
    this.fechaVencimiento = fechaVencimiento;
    this.estado = estado;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public int getNro() {
    return nro;
  }

  public void setNro(int nro) {
    this.nro = nro;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }

  public Date getFechaVencimiento() {
    return fechaVencimiento;
  }

  public void setFechaVencimiento(Date fechaVencimiento) {
    this.fechaVencimiento = fechaVencimiento;
  }

  public int getEstado() {
    return estado;
  }

  public void setEstado(int estado) {
    this.estado = estado;
  }

  @XmlTransient
  public Collection<DetallePago> getDetallePagoCollection() {
    return detallePagoCollection;
  }

  public void setDetallePagoCollection(Collection<DetallePago> detallePagoCollection) {
    this.detallePagoCollection = detallePagoCollection;
  }

  public Prestamo getIdPrestamo() {
    return idPrestamo;
  }

  public void setIdPrestamo(Prestamo idPrestamo) {
    this.idPrestamo = idPrestamo;
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
    if (!(object instanceof Cuota)) {
      return false;
    }
    Cuota other = (Cuota) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.fcastillo.facilidades.soft.Cuota[ id=" + id + " ]";
  }
  
}
