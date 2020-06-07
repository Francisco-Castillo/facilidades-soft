/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.converters;

import com.fcastillo.facilidades.soft.Clientes;
import com.fcastillo.facilidades.soft.Personas;
import com.fcastillo.facilidades.soft.ejb.ClientesFacadeLocal;
import com.fcastillo.facilidades.soft.utilidades.Mensajes;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author fcastillo
 */
@Named
@RequestScoped
public class ClienteConverter implements Converter<Object> {
    
    @EJB
    private ClientesFacadeLocal clienteEJB;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Clientes cliente = new Clientes();
        try {
            if (null != string) {
                Personas p = new Personas();
                p.setId(Integer.parseInt(string));
                cliente.setId(p);
                cliente = clienteEJB.findById(cliente.getId().getId());
                System.out.println("retornadno ..." + cliente.getId().getApellido());
            }
            
        } catch (Exception e) {
            Mensajes.error("getAsObject()");
        }
        return cliente;
    }
    
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        String p = "";
        try {
            if (t instanceof Clientes) {
                Clientes cliente = (Clientes) t;
                p = String.valueOf(cliente.getId().getId());
            } else if (t instanceof String) {
                p = (String) t;
            }
        } catch (Exception e) {
            Mensajes.error("getAsString()");
        }
        return p;
    }
}
