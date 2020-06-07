package com.fcastillo.facilidades.soft.utilidades;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author fcastillo
 */
@Named
@RequestScoped
public class Mensajes {

    public static void info(String msj) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msj));
    }

    public static void error(String msj) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, null));
    }

    public static void warn(String msj) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", msj));
    }

    public static void success(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg,
                msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }
}
