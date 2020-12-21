/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author fcastillo
 */
@Embeddable
public class DetallePagoPK implements Serializable {

  @Basic(optional = false)
  @NotNull
  @Column(name = "id_pago")
  private int idPago;
  @Basic(optional = false)
  @NotNull
  @Column(name = "id_cuota")
  private int idCuota;

  public DetallePagoPK() {
  }

  public DetallePagoPK(int idPago, int idCuota) {
    this.idPago = idPago;
    this.idCuota = idCuota;
  }

  public int getIdPago() {
    return idPago;
  }

  public void setIdPago(int idPago) {
    this.idPago = idPago;
  }

  public int getIdCuota() {
    return idCuota;
  }

  public void setIdCuota(int idCuota) {
    this.idCuota = idCuota;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (int) idPago;
    hash += (int) idCuota;
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof DetallePagoPK)) {
      return false;
    }
    DetallePagoPK other = (DetallePagoPK) object;
    if (this.idPago != other.idPago) {
      return false;
    }
    if (this.idCuota != other.idCuota) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.fcastillo.facilidades.soft.DetallePagoPK[ idPago=" + idPago + ", idCuota=" + idCuota + " ]";
  }
  
}
