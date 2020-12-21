/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fcastillo
 */
@Entity
@Table(name = "detalle_pagos")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "DetallePago.findAll", query = "SELECT d FROM DetallePago d"),
  @NamedQuery(name = "DetallePago.findByIdPago", query = "SELECT d FROM DetallePago d WHERE d.detallePagoPK.idPago = :idPago"),
  @NamedQuery(name = "DetallePago.findByIdCuota", query = "SELECT d FROM DetallePago d WHERE d.detallePagoPK.idCuota = :idCuota"),
  @NamedQuery(name = "DetallePago.findByRecargo", query = "SELECT d FROM DetallePago d WHERE d.recargo = :recargo"),
  @NamedQuery(name = "DetallePago.findByMontoAbonado", query = "SELECT d FROM DetallePago d WHERE d.montoAbonado = :montoAbonado")})
public class DetallePago implements Serializable {

  private static final long serialVersionUID = 1L;
  @EmbeddedId
  protected DetallePagoPK detallePagoPK;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Column(name = "recargo")
  private BigDecimal recargo;
  @Column(name = "monto_abonado")
  private BigDecimal montoAbonado;
  @JoinColumn(name = "id_cuota", referencedColumnName = "id", insertable = false, updatable = false)
  @ManyToOne(optional = false)
  private Cuota cuota;
  @JoinColumn(name = "id_pago", referencedColumnName = "id", insertable = false, updatable = false)
  @ManyToOne(optional = false)
  private Pago pago;

  public DetallePago() {
  }

  public DetallePago(DetallePagoPK detallePagoPK) {
    this.detallePagoPK = detallePagoPK;
  }

  public DetallePago(int idPago, int idCuota) {
    this.detallePagoPK = new DetallePagoPK(idPago, idCuota);
  }

  public DetallePagoPK getDetallePagoPK() {
    return detallePagoPK;
  }

  public void setDetallePagoPK(DetallePagoPK detallePagoPK) {
    this.detallePagoPK = detallePagoPK;
  }

  public BigDecimal getRecargo() {
    return recargo;
  }

  public void setRecargo(BigDecimal recargo) {
    this.recargo = recargo;
  }

  public BigDecimal getMontoAbonado() {
    return montoAbonado;
  }

  public void setMontoAbonado(BigDecimal montoAbonado) {
    this.montoAbonado = montoAbonado;
  }

  public Cuota getCuota() {
    return cuota;
  }

  public void setCuota(Cuota cuota) {
    this.cuota = cuota;
  }

  public Pago getPago() {
    return pago;
  }

  public void setPago(Pago pago) {
    this.pago = pago;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (detallePagoPK != null ? detallePagoPK.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof DetallePago)) {
      return false;
    }
    DetallePago other = (DetallePago) object;
    if ((this.detallePagoPK == null && other.detallePagoPK != null) || (this.detallePagoPK != null && !this.detallePagoPK.equals(other.detallePagoPK))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.fcastillo.facilidades.soft.DetallePago[ detallePagoPK=" + detallePagoPK + " ]";
  }
  
}
