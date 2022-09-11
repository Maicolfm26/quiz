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

@Component
@Scope("session")
public class SeguridadProfesorBean implements Serializable {

    private final ProfesorServicio profesorServicio;

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


    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/profesor/login?faces-redirect=true";
    }
}
