package co.edu.uniquindio.web.bean.usuario;

import co.edu.uniquindio.negocio.servicios.UsuarioServicio;
import co.edu.uniquindio.persistencia.entidades.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;

/**
 * RegistroBean es el encargado de controlar la vista de registro.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Component
@ViewScoped
public class RegistroBean implements Serializable {

    @Getter
    @Setter
    private Usuario usuario;

    private final UsuarioServicio usuarioServicio;

    /**
     * Crea un RegistroBean con el usuarioServicio recibido por parámetro.
     * @param usuarioServicio
     */
    public RegistroBean(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
        usuario = new Usuario();
    }

    /**
     * Crea un usuario.
     */
    public void registrarUsuario() {
        try {
            usuarioServicio.registrarUsuario(usuario);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro exitoso");
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-bean", msg);
        }
    }
}
