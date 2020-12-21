/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.converters;

import com.fcastillo.facilidades.soft.Perfil;
import com.fcastillo.facilidades.soft.ejb.PerfilFacadeLocal;
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
public class PerfilConverter implements Converter<Object> {

    @EJB
    PerfilFacadeLocal perfilEJB;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Perfil perfil = new Perfil();
        try {
            if (null != string) {
                perfil.setId(Integer.parseInt(string));
                perfil = perfilEJB.findById(perfil.getId());
            }

        } catch (Exception e) {
            //Mensajes.error("getAsObject()");
        }
        return perfil;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
           String p = "";
        try {
            if (t instanceof Perfil) {
                Perfil perfil = (Perfil) t;
                p = String.valueOf(perfil.getId());
            } else if (t instanceof String) {
                p = (String) t;
            }
        } catch (Exception e) {
            Mensajes.error("getAsString()");
        }
        return p;
    }

}
