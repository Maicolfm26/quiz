package co.edu.uniquindio.web.bean.usuario;

import co.edu.uniquindio.negocio.servicios.JuegoServicio;
import co.edu.uniquindio.negocio.servicios.UsuarioServicio;
import co.edu.uniquindio.persistencia.entidades.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * JuegoBean es el encargado de controlar la vista del juego.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Component
@Scope("session")
public class JuegoBean implements Serializable {

    private final SeguridadBean seguridadBean;
    private final JuegoServicio juegoServicio;
    private final UsuarioServicio usuarioServicio;
    @Getter
    private final AlgoritmoAdaptativo algoritmoAdaptativo;
    /**
     * Crea un JuegoBean con la seguridadBean, el juegoServicio y el algoritmo adaptativo recibidos por parámetro.
     * @param seguridadBean
     * @param juegoServicio
     * @param usuarioServicio
     * @param algoritmoAdaptativo
     */
    public JuegoBean(SeguridadBean seguridadBean, JuegoServicio juegoServicio, UsuarioServicio usuarioServicio, AlgoritmoAdaptativo algoritmoAdaptativo) {
        this.seguridadBean = seguridadBean;
        this.juegoServicio = juegoServicio;
        this.usuarioServicio = usuarioServicio;
        this.algoritmoAdaptativo = algoritmoAdaptativo;
        listado_registro_juego = new ArrayList<>();
        termino = seguridadBean.getUsuarioSesion().getJuego() != null;
    }

    @Getter
    @Setter
    private Respuesta respuesta;

    @Getter
    private boolean termino;

    @Getter
    @Setter
    private String textoBoton = "Siguiente pregunta";

    private Juego juego;
    private List<Registro_Juego> listado_registro_juego;

    private long startTime;

    /**
     * Después de construir el JuegoBean cargamos las categorías y seleccionamos una pregunta aleatoria de la primera categoría.
     * @return redireccionamos a la vista juego.
     */
    @PostConstruct
    public String iniciarJuego() {
        if (algoritmoAdaptativo.getNumeroPreguntasPorNivel() == 0) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/usuario/index.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (algoritmoAdaptativo.isLastQuestion()) {
            textoBoton = "Terminar";
        }
        juego = new Juego(seguridadBean.getUsuarioSesion());
        startTime = System.currentTimeMillis();
        return "juego?faces-redirect=true";
    }

    /**
     * Cargamos la siguiente pregunta y guardamos el registro de la pregunta resuelta.
     * @return redireccionamos a la vista resultados si es la última pregunta.
     */
    public String siguientePregunta() {
        if (respuesta != null) {
            long endTime = System.currentTimeMillis() - startTime;
            Registro_Juego registro_juego = new Registro_Juego(juego, algoritmoAdaptativo.getPreguntaActual(), respuesta, (int) endTime/1000, algoritmoAdaptativo.getNivelPregunta(), algoritmoAdaptativo.getPreguntasResueltas());
            listado_registro_juego.add(registro_juego);
            boolean respuestaValida = respuesta.isValida();
            if (respuestaValida) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Respuesta correcta");
                FacesContext.getCurrentInstance().addMessage("msj-juego", msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Respuesta incorrecta");
                FacesContext.getCurrentInstance().addMessage("msj-juego", msg);
            }

            algoritmoAdaptativo.calcularNivelPersona(respuestaValida);
            algoritmoAdaptativo.calcularProbabilidad();

            if (!algoritmoAdaptativo.isLastQuestion()) {
                algoritmoAdaptativo.siguientePregunta();
                respuesta = null;
            } else {
                try {
                    juego = juegoServicio.guardarJuego(juego, listado_registro_juego);
                    seguridadBean.getUsuarioSesion().setJuego(juego);
                    seguridadBean.getUsuarioSesion().setNivel(algoritmoAdaptativo.getNivelPersona());
                    usuarioServicio.actualizarUsuario(seguridadBean.getUsuarioSesion());
                } catch (Exception e) {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                    FacesContext.getCurrentInstance().addMessage("msj-juego", msg);
                }
                termino = true;
                return "resultado?faces-redirect=true";
            }
            if (algoritmoAdaptativo.isLastQuestion()) {
                textoBoton = "Terminar";
            }
            startTime = System.currentTimeMillis();
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Debes de seleccionar una respuesta");
            FacesContext.getCurrentInstance().addMessage("msj-juego", msg);
        }
        return null;
    }
}
