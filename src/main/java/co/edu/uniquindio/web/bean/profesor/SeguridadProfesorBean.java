package co.edu.uniquindio.web.bean.profesor;

import co.edu.uniquindio.negocio.servicios.ProfesorServicio;
import co.edu.uniquindio.persistencia.entidades.Profesor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * SeguridadProfesorBean es el encargado de controlar la vista de inicio de sesión.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Component
@Scope("session")
public class SeguridadProfesorBean implements Serializable {

    private final ProfesorServicio profesorServicio;

    /**
     * Crea una SeguridadProfesorBean con el profesorServicio recibido por parámetro.
     * @param profesorServicio
     */
    public SeguridadProfesorBean(ProfesorServicio profesorServicio) {
        this.profesorServicio = profesorServicio;
    }

    @Getter
    @Setter
    private boolean autenticado;

    @Getter @Setter
    private String userName;

    @Getter @Setter
    private String clave;

    @Getter @Setter
    private Profesor profesorSesion;

    /**
     * Verificamos el inicio de sesión del profesor y redireccionamos a la vista categorías.
     * @return devuelve la redirección de vista.
     */
    public String iniciarSesion() {
        try {
            profesorSesion = profesorServicio.iniciarSesion(userName, clave);
            autenticado = true;
            return "/profesor/categorias.xhtml?faces-redirect=true";
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        }
        return null;
    }

    /**
     * Cerramos la sesión del profesor.
     * @return redireccionamos al login del profesor.
     */
    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/profesor/login?faces-redirect=true";
    }
}
