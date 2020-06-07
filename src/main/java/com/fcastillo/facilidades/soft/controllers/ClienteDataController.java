package com.fcastillo.facilidades.soft.controllers;

import com.fcastillo.facilidades.soft.Clientes;

import com.fcastillo.facilidades.soft.Personas;
import com.fcastillo.facilidades.soft.ejb.ClientesFacadeLocal;
import com.fcastillo.facilidades.soft.ejb.PersonasFacadeLocal;
import com.fcastillo.facilidades.soft.interfaces.Operaciones;
import com.fcastillo.facilidades.soft.utilidades.Constantes;
import com.fcastillo.facilidades.soft.utilidades.Mensajes;
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

/**
 *
 * @author fcastillo
 */
@Named(value = "clienteDataController")
@ViewScoped
public class ClienteDataController implements Serializable, Operaciones {

    //<editor-fold defaultstate="collapsed" desc="fields">
    @EJB
    ClientesFacadeLocal clientesEJB;

    private Clientes nuevoCliente = new Clientes();
    private Personas nuevaPersona = new Personas();
    private Map<String, Integer> mapSexo = new LinkedHashMap<>();
    private List<Clientes> lstClientes = new ArrayList<>();
    private Clientes clienteSeleccionado = new Clientes();
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="get/set">
    public List<Clientes> getLstClientes() {
        return lstClientes;
    }

    public void setLstClientes(List<Clientes> lstClientes) {
        this.lstClientes = lstClientes;
    }

    public Clientes getNuevoCliente() {
        return nuevoCliente;
    }

    public void setNuevoCliente(Clientes nuevoCliente) {
        this.nuevoCliente = nuevoCliente;
    }

    public Personas getNuevaPersona() {
        return nuevaPersona;
    }

    public void setNuevaPersona(Personas nuevaPersona) {
        this.nuevaPersona = nuevaPersona;
    }

    public Map<String, Integer> getMapSexo() {
        return mapSexo;
    }

    public void setMapSexo(Map<String, Integer> mapSexo) {
        this.mapSexo = mapSexo;
    }

    public Clientes getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Clientes clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }
    //</editor-fold>

    @PostConstruct
    public void init() {
        lstClientes = clientesEJB.findAll();
        loadMapSexo();
    }

    @Override
    public void crear() {
        try {
            nuevoCliente.setEstado(1);
            nuevoCliente.setFalta(new Date());
            nuevoCliente.setId(nuevaPersona);
            clientesEJB.create(nuevoCliente);
            Mensajes.info("adsasdsad");
        } catch (Exception e) {
            System.out.println("error : " + e.getMessage());
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

    public List<Clientes> findByNameLike(String nombre) {
        List<Clientes> sugerencias = new ArrayList<>();
        try {
            nombre = nombre.trim();
            lstClientes = clientesEJB.findByNameLike(nombre.toLowerCase());
            lstClientes.stream().forEach((p) -> {
                sugerencias.add(p);
            });
        } catch (Exception e) {
            Mensajes.error("Ocurrio un error" + e.getMessage());
        }
        return sugerencias;
    }
}
