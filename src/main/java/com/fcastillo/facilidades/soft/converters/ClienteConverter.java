/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.converters;

import com.fcastillo.facilidades.soft.Cliente;
import com.fcastillo.facilidades.soft.Persona;
import com.fcastillo.facilidades.soft.utilidades.Mensajes;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import com.fcastillo.facilidades.soft.ejb.ClienteFacadeLocal;

/**
 *
 * @author fcastillo
 */
@Named
@RequestScoped
public class ClienteConverter implements Converter<Object> {
    
    @EJB
    private ClienteFacadeLocal clienteEJB;
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Cliente cliente = new Cliente();
        try {
            if (null != string) {
                Persona p = new Persona();
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
            if (t instanceof Cliente) {
                Cliente cliente = (Cliente) t;
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
