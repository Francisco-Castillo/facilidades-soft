package com.fcastillo.facilidades.soft.utilidades;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class ResourcesFiles implements Serializable {

    private static final long serialVersionUID = 1L;
    Locale currentLocale;
    ResourceBundle mrb; 
    ResourceBundle arb; 

    public ResourcesFiles() {
    }

    @PostConstruct
    public void init() {
        saveLocale();
    }

    public void saveLocale() {
        currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        arb = ResourceBundle.getBundle("propiedades.aplicacion",
                currentLocale);

    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    public ResourceBundle getMrb() {
        return mrb;
    }

    public void setMrb(ResourceBundle mrb) {
        this.mrb = mrb;
    }

    public ResourceBundle getArb() {
        return arb;
    }

    public void setArb(ResourceBundle arb) {
        this.arb = arb;
    }

    public String getMensajeMrb(String mensaje) {
        return mrb.getString(mensaje);
    }

    public String getMensajeArb(String mensaje) {
        return arb.getString(mensaje);
    }
}
