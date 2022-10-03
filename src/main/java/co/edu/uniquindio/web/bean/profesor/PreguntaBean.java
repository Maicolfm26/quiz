package co.edu.uniquindio.web.bean.profesor;

import co.edu.uniquindio.negocio.servicios.CategoriaServicio;
import co.edu.uniquindio.negocio.servicios.PreguntaServicio;
import co.edu.uniquindio.persistencia.entidades.Categoria;
import co.edu.uniquindio.persistencia.entidades.Pregunta;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * PreguntaBean es el encargado de controlar la vista de preguntas.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Component
@ViewScoped
public class PreguntaBean implements Serializable {

    private final PreguntaServicio preguntaServicio;
    private final CategoriaServicio categoriaServicio;

    /**
     * Crea una PreguntaBean con la preguntaServicio y la CategoriaServicio recibidas por parámetro.
     * @param preguntaServicio
     * @param categoriaServicio
     */
    public PreguntaBean(PreguntaServicio preguntaServicio, CategoriaServicio categoriaServicio) {
        this.preguntaServicio = preguntaServicio;
        this.categoriaServicio = categoriaServicio;
    }

    @Value("#{param['idCategoria']}")
    @Getter
    private String codigoCategoria;

    @Getter
    private Categoria categoria;

    @Getter
    @Setter
    private List<Pregunta> listadoPreguntas;

    @Getter
    @Setter
    private Pregunta selectedPregunta;

    @Getter
    @Setter
    private List<Pregunta> selectedPreguntas;

    /**
     * Después de construir la PreguntaBean verificamos que la categoría si exista y cargamos las preguntas.
     */
    @PostConstruct
    public void init() {
        if (codigoCategoria != null && !codigoCategoria.isEmpty()) {
            try {
                categoria = categoriaServicio.obtenerCategoria(Integer.parseInt(codigoCategoria));
                listadoPreguntas = categoria.getListadoPreguntas();
            } catch (Exception e) {
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/profesor/categorias.xhtml");
                } catch (IOException ex) {
                }
            }
        } else {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/profesor/categorias.xhtml");
            } catch (IOException e) {
            }
        }
    }

    /**
     * Crea un nuevo objeto para otra pregunta.
     */
    public void openNew() {
        this.selectedPregunta = new Pregunta();
        selectedPregunta.setCategoria(categoria);
    }

    /**
     * Creamos una pregunta
     */
    public void savePregunta() {
        try {
            Integer idPregunta = selectedPregunta.getIdPregunta();
            selectedPregunta = preguntaServicio.agregarPregunta(selectedPregunta);
            if (idPregunta == null) {
                this.listadoPreguntas.add(this.selectedPregunta);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pregunta creada"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pregunta actualizada"));
            }
            PrimeFaces.current().executeScript("PF('managePreguntaDialog').hide()");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage()));
            e.printStackTrace();
        }
        PrimeFaces.current().ajax().update("form:messages", "form:dt-preguntas");
    }

    /**
     * Eliminamos una pregunta
     */
    public void deletePregunta() {
        try {
            preguntaServicio.eliminarPregunta(selectedPregunta.getIdPregunta());
            this.listadoPreguntas.remove(this.selectedPregunta);
            this.selectedPregunta = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pregunta eliminada"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-preguntas");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage()));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-preguntas");
        }
    }

    /**
     * Construimos el mensaje del botón de borrar preguntas.
     * @return devuelve el mensaje que debe tener el botón de borrar preguntas.
     */
    public String getDeleteButtonMessage() {
        if (hasSelectedPreguntas()) {
            int size = this.selectedPreguntas.size();
            return size > 1 ? size + " preguntas seleccionadas" : "1 pregunta seleccionada";
        }

        return "Eliminar";
    }

    /**
     * Obtenemos si hay preguntas seleccionadas.
     * @return devuelve si hay preguntas seleccionadas.
     */
    public boolean hasSelectedPreguntas() {
        return this.selectedPreguntas != null && !this.selectedPreguntas.isEmpty();
    }

    /**
     * Borra las preguntas que hayan seleccionadas.
     */
    public void deleteSelectedPreguntas() {
        try {
            for (Pregunta pregunta : selectedPreguntas) {
                preguntaServicio.eliminarPregunta(pregunta.getIdPregunta());
            }
            this.listadoPreguntas.removeAll(this.selectedPreguntas);
            this.selectedPreguntas = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Preguntas eliminadas"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-preguntas");
            PrimeFaces.current().executeScript("PF('dtPreguntas').clearFilters()");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage()));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-preguntas");
            PrimeFaces.current().executeScript("PF('dtPreguntas').clearFilters()");
        }
    }

    /**
     * selecciona una pregunta
     * @param pregunta
     */
    public void seleccionarPregunta(Pregunta pregunta) {
        selectedPregunta = pregunta;
    }

    /**
     * Nos redirecciona a la vista donde se encuentran las respuestas de la pregunta.
     * @param id
     * @return devuelve la vista de las respuestas de la pregunta
     */
    public String ir_a_respuestas(String id) {
        return "respuestas?faces-redirect=true&amp;idPregunta=" + id;
    }

}
