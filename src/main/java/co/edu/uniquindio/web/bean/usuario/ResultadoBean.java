package co.edu.uniquindio.web.bean.usuario;

import co.edu.uniquindio.negocio.servicios.RegistroJuegoServicio;
import co.edu.uniquindio.persistencia.entidades.Registro_Juego;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

/**
 * ResultadoBean es el encargado de controlar la vista de resultados.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Component
@ViewScoped
public class ResultadoBean implements Serializable {

    private final SeguridadBean seguridadBean;
    private final RegistroJuegoServicio registroJuegoServicio;

    /**
     * Crea un ResultadoBean con la seguridadBean y el registroJuegoServicio recibidos por parámetro.
     * @param seguridadBean
     * @param registroJuegoServicio
     */
    public ResultadoBean(SeguridadBean seguridadBean, RegistroJuegoServicio registroJuegoServicio) {
        this.seguridadBean = seguridadBean;
        this.registroJuegoServicio = registroJuegoServicio;
    }

    @Getter
    @Setter
    private List<Registro_Juego> listaRegistro_juegos;

    /**
     * Después de construir el ResultadoBean cargamos todos los registro_juego del usuario que está en sesión.
     */
    @PostConstruct
    public void init() {
        listaRegistro_juegos = registroJuegoServicio.obtenerRegistroJuegoPorUsuario(seguridadBean.getUsuarioSesion().getUserName());
    }

}
