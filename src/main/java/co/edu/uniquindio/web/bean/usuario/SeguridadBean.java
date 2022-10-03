package co.edu.uniquindio.web.bean.usuario;

import co.edu.uniquindio.negocio.servicios.UsuarioServicio;
import co.edu.uniquindio.persistencia.entidades.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * SeguridadBean es el encargado de controlar la vista de login del estudiante.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    private final UsuarioServicio usuarioServicio;

    /**
     * Crea una SeguridadBean con el usuarioServicio recibido por parámetro.
     * @param usuarioServicio
     */
    public SeguridadBean(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    @Getter
    @Setter
    private boolean autenticado;

    @Getter @Setter
    private String userName;

    @Getter @Setter
    private String clave;

    @Getter @Setter
    private Usuario usuarioSesion;

    /**
     * Verificamos el inicio de sesión del estudiante y redireccionamos a la vista principal.
     * @return devuelve la redirección de vista.
     */
    public String iniciarSesion() {
        try {
            usuarioSesion = usuarioServicio.iniciarSesion(userName, clave);
            autenticado = true;
            return "/usuario/index?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        }
        return null;
    }

    /**
     * Cerramos la sesión del estudiante.
     * @return redireccionamos al login del estudiante.
     */
    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
    }
}
