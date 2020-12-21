/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.controllers;

import com.fcastillo.facilidades.soft.Cliente;
import com.fcastillo.facilidades.soft.Cuota;
import com.fcastillo.facilidades.soft.Prestamo;
import com.fcastillo.facilidades.soft.utilidades.Mensajes;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.SelectEvent;
import com.fcastillo.facilidades.soft.ejb.PrestamoFacadeLocal;
import com.fcastillo.facilidades.soft.ejb.ClienteFacadeLocal;

/**
 *
 * @author fcastillo
 */
@Named(value = "cobroController")
@ViewScoped
public class CobroController implements Serializable {

    @EJB
    ClienteFacadeLocal clienteEJB;
    @EJB
    PrestamoFacadeLocal prestamosEJB;

    private List<Cliente> lstClientes = new ArrayList<>();
    private Cliente cliente = new Cliente();
    private List<Prestamo> lsPrestamos = new ArrayList<>();
    private List<Cuota> cuotasSeleccionadas = new ArrayList<>();
    private Prestamo prestamoSeleccionado = new Prestamo();
    private Date fechaActual = new Date();
    private BigDecimal totalAcobrar;

    public List<Cliente> getLstClientes() {
        return lstClientes;
    }

    public void setLstClientes(List<Cliente> lstClientes) {
        this.lstClientes = lstClientes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Prestamo> getLsPrestamos() {
        return lsPrestamos;
    }

    public void setLsPrestamos(List<Prestamo> lsPrestamos) {
        this.lsPrestamos = lsPrestamos;
    }

    public Prestamo getPrestamoSeleccionado() {
        return prestamoSeleccionado;
    }

    public void setPrestamoSeleccionado(Prestamo prestamoSeleccionado) {
        this.prestamoSeleccionado = prestamoSeleccionado;
    }

    public List<Cuota> getCuotasSeleccionadas() {
        return cuotasSeleccionadas;
    }

    public void setCuotasSeleccionadas(List<Cuota> cuotasSeleccionadas) {
        this.cuotasSeleccionadas = cuotasSeleccionadas;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public BigDecimal getTotalAcobrar() {
        return totalAcobrar;
    }

    public void setTotalAcobrar(BigDecimal totalAcobrar) {
        this.totalAcobrar = totalAcobrar;
    }

    public List<Cliente> findByNameLike(String nombre) {
        List<Cliente> sugerencias = new ArrayList<>();
        try {
            nombre = nombre.trim();
            lstClientes = clienteEJB.findByNameLike(nombre.toLowerCase());
            lstClientes.stream().forEach((p) -> {
                sugerencias.add(p);
            });
        } catch (Exception e) {
            Mensajes.error("Ocurrio un error" + e.getMessage());
        }
        return sugerencias;
    }

    public List<Prestamo> buscarPrestamos() {
        return lsPrestamos = prestamosEJB.findByIdCliente(cliente.getId().getId());
    }

    /**
     *
     * @param event
     */
    public void onRowSelect(SelectEvent event) {
        prestamoSeleccionado = (Prestamo) event.getObject();
    }

    public void totalFacturado() {
        BigDecimal t = new BigDecimal(0);
        for (int i = 0; i < cuotasSeleccionadas.size(); i++) {
            t = t.add(cuotasSeleccionadas.get(i).getValor());

        }
        totalAcobrar = t;

    }

    private double calculaRecargo(Cuota cuota) {
        int dias;
        double valorDeRecargo = 0;
        
        if (cuota.getEstado() != 1) {
            dias = (int) ((this.fechaActual.getTime() - cuota.getFechaVencimiento().getTime()) / 86400000);
            valorDeRecargo = (((cuota.getValor().doubleValue()) * 0.01) * dias);
        }
        
        return valorDeRecargo;
    }
}
