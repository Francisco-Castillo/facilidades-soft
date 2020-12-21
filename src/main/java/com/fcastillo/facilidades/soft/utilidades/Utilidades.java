/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fcastillo.facilidades.soft.utilidades;

import com.fcastillo.facilidades.soft.Usuario;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author fcastillo
 */
@Named(value = "utilidades")
@RequestScoped
public class Utilidades {

    Logger logger = LogManager.getLogger(Utilidades.class);

    public static String componerCodigoCliente(String ultimoCodigo) {
        int posicion1 = ultimoCodigo.indexOf("/");
        return "";
    }

    public static String validarUsuario(Usuario u) {
        if (u == null) {
            Mensajes.error("Ud. no se encuentra registrado como usuario.");
        }
        return null;
    }

    public static String getRealPath() {
        try {
            FacesContext facesCtx = FacesContext.getCurrentInstance();
            ServletContext servletCtx = (ServletContext) facesCtx.getExternalContext().getContext();
            return servletCtx.getRealPath("/");
        } catch (Exception e) {
            LogManager.getLogger(Utilidades.class).error("utilidades.getRealPath()");
        }
        return null;
    }
}
