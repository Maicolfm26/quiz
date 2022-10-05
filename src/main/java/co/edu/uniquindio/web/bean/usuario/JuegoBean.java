package co.edu.uniquindio.web.bean.usuario;

import co.edu.uniquindio.negocio.servicios.CategoriaServicio;
import co.edu.uniquindio.negocio.servicios.JuegoServicio;
import co.edu.uniquindio.negocio.servicios.PreguntaServicio;
import co.edu.uniquindio.negocio.servicios.RegistroJuegoServicio;
import co.edu.uniquindio.persistencia.entidades.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    private final CategoriaServicio categoriaServicio;
    private final PreguntaServicio preguntaServicio;
    private final SeguridadBean seguridadBean;
    private final JuegoServicio juegoServicio;
    private final RegistroJuegoServicio registroJuegoServicio;

    /**
     * Crea un JuegoBean con la categoriaServicio, la preguntaServicio, la seguridadBean, el juegoServicio y el registroJuegoServicio recibidos por parámetro.
     * @param categoriaServicio
     * @param preguntaServicio
     * @param seguridadBean
     * @param juegoServicio
     * @param registroJuegoServicio
     */
    public JuegoBean(CategoriaServicio categoriaServicio, PreguntaServicio preguntaServicio, SeguridadBean seguridadBean, JuegoServicio juegoServicio, RegistroJuegoServicio registroJuegoServicio) {
        this.categoriaServicio = categoriaServicio;
        this.preguntaServicio = preguntaServicio;
        this.seguridadBean = seguridadBean;
        this.juegoServicio = juegoServicio;
        this.registroJuegoServicio = registroJuegoServicio;
        listado_registro_juego = new ArrayList<>();
        termino = seguridadBean.getUsuarioSesion().getJuego() != null;
    }

    @Getter
    @Setter
    private int numeroRonda;

    @Getter
    @Setter
    private List<Categoria> listadoCategorias;

    @Getter
    @Setter
    private Pregunta preguntaActual;

    @Getter
    @Setter
    private Categoria categoriaActual;

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
        numeroRonda = 0;
        listadoCategorias = categoriaServicio.obtenerCategorias();
        if (listadoCategorias.size() == 0) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/usuario/index.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            categoriaActual = listadoCategorias.get(numeroRonda);
            preguntaActual = preguntaServicio.obtenerPreguntaAleatoria(categoriaActual);
        }

        if (numeroRonda == listadoCategorias.size() - 1) {
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
            Registro_Juego registro_juego = new Registro_Juego(juego, preguntaActual, respuesta, (int) endTime/1000);
            listado_registro_juego.add(registro_juego);
            if (respuesta.isValida()) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Respuesta correcta");
                FacesContext.getCurrentInstance().addMessage("msj-juego", msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Respuesta incorrecta");
                FacesContext.getCurrentInstance().addMessage("msj-juego", msg);
            }

            numeroRonda++;
            if (numeroRonda < listadoCategorias.size()) {
                categoriaActual = listadoCategorias.get(numeroRonda);
                preguntaActual = preguntaServicio.obtenerPreguntaAleatoria(categoriaActual);
                respuesta = null;
            } else {
                try {
                    juego = juegoServicio.guardarJuego(juego, listado_registro_juego);
                    seguridadBean.getUsuarioSesion().setJuego(juego);
                } catch (Exception e) {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
                    FacesContext.getCurrentInstance().addMessage("msj-juego", msg);
                }
                termino = true;
                return "resultado?faces-redirect=true";
            }
            if (numeroRonda == listadoCategorias.size() - 1) {
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
