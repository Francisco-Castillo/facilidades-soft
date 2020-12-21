package com.fcastillo.facilidades.soft.controllers;

import com.fcastillo.facilidades.soft.Cliente;

import com.fcastillo.facilidades.soft.Persona;
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
import com.fcastillo.facilidades.soft.ejb.PersonaFacadeLocal;
import com.fcastillo.facilidades.soft.ejb.ClienteFacadeLocal;

/**
 *
 * @author fcastillo
 */
@Named(value = "clienteDataController")
@ViewScoped
public class ClienteDataController implements Serializable, Operaciones {

    //<editor-fold defaultstate="collapsed" desc="fields">
    @EJB
    ClienteFacadeLocal clientesEJB;

    private Cliente nuevoCliente = new Cliente();
    private Persona nuevaPersona = new Persona();
    private Map<String, Integer> mapSexo = new LinkedHashMap<>();
    private List<Cliente> lstClientes = new ArrayList<>();
    private Cliente clienteSeleccionado = new Cliente();
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="get/set">
    public List<Cliente> getLstClientes() {
        return lstClientes;
    }

    public void setLstClientes(List<Cliente> lstClientes) {
        this.lstClientes = lstClientes;
    }

    public Cliente getNuevoCliente() {
        return nuevoCliente;
    }

    public void setNuevoCliente(Cliente nuevoCliente) {
        this.nuevoCliente = nuevoCliente;
    }

    public Persona getNuevaPersona() {
        return nuevaPersona;
    }

    public void setNuevaPersona(Persona nuevaPersona) {
        this.nuevaPersona = nuevaPersona;
    }

    public Map<String, Integer> getMapSexo() {
        return mapSexo;
    }

    public void setMapSexo(Map<String, Integer> mapSexo) {
        this.mapSexo = mapSexo;
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
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
            nuevoCliente.setFechaAlta(new Date());
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

    public List<Cliente> findByNameLike(String nombre) {
        List<Cliente> sugerencias = new ArrayList<>();
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
