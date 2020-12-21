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
@Table(name = "pagos")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Pago.findAll", query = "SELECT p FROM Pago p"),
  @NamedQuery(name = "Pago.findById", query = "SELECT p FROM Pago p WHERE p.id = :id"),
  @NamedQuery(name = "Pago.findByFecha", query = "SELECT p FROM Pago p WHERE p.fecha = :fecha")})
public class Pago implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @NotNull
  @Column(name = "fecha")
  @Temporal(TemporalType.DATE)
  private Date fecha;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "pago")
  private Collection<DetallePago> detallePagoCollection;
  @JoinColumn(name = "id_prestamo", referencedColumnName = "id")
  @ManyToOne(optional = false)
  private Prestamo idPrestamo;

  public Pago() {
  }

  public Pago(Integer id) {
    this.id = id;
  }

  public Pago(Integer id, Date fecha) {
    this.id = id;
    this.fecha = fecha;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
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
    if (!(object instanceof Pago)) {
      return false;
    }
    Pago other = (Pago) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.fcastillo.facilidades.soft.Pago[ id=" + id + " ]";
  }
  
}
