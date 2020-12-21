package com.fcastillo.facilidades.soft.seguridad;

import com.fcastillo.facilidades.soft.Credencial;
import com.fcastillo.facilidades.soft.Usuario;
import com.fcastillo.facilidades.soft.utilidades.Mensajes;
import com.fcastillo.utilidades.Password;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import com.fcastillo.facilidades.soft.ejb.UsuarioFacadeLocal;

/**
 *
 * @author fcastillo
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioEJB;
    private Credencial credenciales = new Credencial();
    private Usuario usuarioLogueado;

    public Credencial getCredenciales() {
        return credenciales;
    }

    public void setCredenciales(Credencial credenciales) {
        this.credenciales = credenciales;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public String iniciarSesion() {
        String redirect;
        if (validarUsuario() == 0) {
            return "";
        } else {
            redirect = "/pages/home.xhtml?faces-redirect=true";
        }
        return redirect;
    }

    private int validarUsuario() {
        if (credenciales.getNombreUsuario().isEmpty() || credenciales.getNombreUsuario().equals("") || credenciales == null) {
            Mensajes.error("Ingrese su número de documento");
            return 0;
        }

        if (credenciales.getPassword().isEmpty() || credenciales.getPassword().equals("")) {
            Mensajes.error("Ingrese su contraseña");
            return 0;
        }

        Usuario usuario = null;

        try {
            usuario = usuarioEJB.findByUsername(credenciales.getNombreUsuario());

            if (usuario.getEstado() == 0) {
                Mensajes.error("Su cuenta se encuentra inactiva");
                return 0;
            }

            if (!usuario.getPassword().equals(Password.encriptar(credenciales.getPassword()))) {
                Mensajes.error("Contraseña incorrecta");
                return 0;
            }
        } catch (Exception e) {
            Mensajes.error(e.getMessage());
        }
        usuarioLogueado = usuario;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioLogueado);
        return 1;
    }

    public String cerrarSesion() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().invalidateSession();
        return "/login?faces-redirect=true";
    }
}
