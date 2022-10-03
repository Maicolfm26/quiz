package co.edu.uniquindio.web.bean.profesor;

import co.edu.uniquindio.negocio.servicios.PreguntaServicio;
import co.edu.uniquindio.negocio.servicios.RespuestaServicio;
import co.edu.uniquindio.persistencia.entidades.Pregunta;
import co.edu.uniquindio.persistencia.entidades.Respuesta;
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
 * RespuestaBean es el encargado de controlar la vista de respuestas.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Component
@ViewScoped
public class RespuestaBean implements Serializable {

    private final PreguntaServicio preguntaServicio;
    private final RespuestaServicio respuestaServicio;

    /**
     * Crea una RespuestaBean con la preguntaServicio y la respuestaServicio recibidas por parámetro.
     * @param preguntaServicio
     * @param respuestaServicio
     */
    public RespuestaBean(PreguntaServicio preguntaServicio, RespuestaServicio respuestaServicio) {
        this.preguntaServicio = preguntaServicio;
        this.respuestaServicio = respuestaServicio;
    }

    @Value("#{param['idPregunta']}")
    @Getter
    private String codigoPregunta;

    @Getter
    private Pregunta pregunta;

    @Getter
    @Setter
    private List<Respuesta> listadoRespuestas;

    @Getter
    @Setter
    private Respuesta selectedRespuesta;

    @Getter
    @Setter
    private List<Respuesta> selectedRespuestas;

    /**
     * Despues de crear RespuestaBean miramos que exista la pregunta y cargamos las respuestas.
     */
    @PostConstruct
    public void init() {
        if (codigoPregunta != null && !codigoPregunta.isEmpty()) {
            try {
                pregunta = preguntaServicio.obtenerPregunta(Integer.parseInt(codigoPregunta));
                listadoRespuestas = pregunta.getListadoRespuestas();
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
     * Crea un nuevo objeto para otra respuesta.
     */
    public void openNew() {
        this.selectedRespuesta = new Respuesta();
        selectedRespuesta.setPregunta(pregunta);
    }

    /**
     * Crea una respuesta.
     */
    public void saveRespuesta() {
        try {
            Integer idRespuesta = selectedRespuesta.getIdRespuesta();
            selectedRespuesta = respuestaServicio.agregarRespuesta(selectedRespuesta);
            if (idRespuesta == null) {
                this.listadoRespuestas.add(this.selectedRespuesta);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Respuesta creada"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Respuesta actualizada"));
            }
            PrimeFaces.current().executeScript("PF('manageRespuestaDialog').hide()");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage()));
            e.printStackTrace();
        }
        PrimeFaces.current().ajax().update("form:messages", "form:dt-respuestas");
    }

    /**
     * Borra una respuesta.
     */
    public void deleteRespuesta() {
        try {
            respuestaServicio.eliminarRespuesta(selectedRespuesta.getIdRespuesta());
            this.listadoRespuestas.remove(this.selectedRespuesta);
            this.selectedRespuesta = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Respuesta eliminada"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-respuestas");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage()));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-respuestas");
        }
    }

    /**
     * Construimos el mensaje del botón de borrar respuestas.
     * @return devuelve el mensaje que debe tener el botón de borrar respuestas.
     */
    public String getDeleteButtonMessage() {
        if (hasSelectedRespuestas()) {
            int size = this.selectedRespuestas.size();
            return size > 1 ? size + " respuestas seleccionadas" : "1 respuesta seleccionada";
        }

        return "Eliminar";
    }

    /**
     * Obtenemos si hay respuestas seleccionadas.
     * @return devuelve si hay respuestas seleccionadas.
     */
    public boolean hasSelectedRespuestas() {
        return this.selectedRespuestas != null && !this.selectedRespuestas.isEmpty();
    }

    /**
     * Borra las respuestas seleccionadas.
     */
    public void deleteSelectedRespuestas() {
        try {
            for (Respuesta respuesta : selectedRespuestas) {
                respuestaServicio.eliminarRespuesta(respuesta.getIdRespuesta());
            }
            this.listadoRespuestas.removeAll(this.selectedRespuestas);
            this.selectedRespuestas = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Respuestas eliminadas"));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-respuestas");
            PrimeFaces.current().executeScript("PF('dtRespuestas').clearFilters()");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage()));
            PrimeFaces.current().ajax().update("form:messages", "form:dt-respuestas");
            PrimeFaces.current().executeScript("PF('dtRespuestas').clearFilters()");
        }
    }

    /**
     * Selecciona una respuesta.
     * @param respuesta
     */
    public void seleccionarRespuesta(Respuesta respuesta) {
        selectedRespuesta = respuesta;
    }
}
