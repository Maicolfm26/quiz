package co.edu.uniquindio.web.converter;

import co.edu.uniquindio.persistencia.entidades.Respuesta;
import co.edu.uniquindio.persistencia.repositorios.RespuestaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import java.io.Serializable;

/**
 * RespuestaConverter es el encargado de convertir la respuesta de la vista en el objeto del modelo.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Component
public class RespuestaConverter implements Converter<Respuesta>, Serializable {

    @Autowired
    private RespuestaRepo respuestaRepo;

    /**
     * Consulta la respuesta que viene desde la vista.
     * @param facesContext
     * @param uiComponent
     * @param s
     * @return devuelve la respuesta en el objeto.
     */
    @Override
    public Respuesta getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Respuesta respuesta = null;
        if (s != null && !"".equals(s)) {
            try {
                respuesta = respuestaRepo.findById(Integer.parseInt(s)).get(); //Hacer casting si es necesario
            } catch (Exception e) {
                throw new ConverterException(new FacesMessage(uiComponent.getClientId() + ":id no válido"));
            }
        }
        return respuesta;
    }

    /**
     * Obtiene el identificador de un objeto respuesta.
     * @param facesContext
     * @param uiComponent
     * @param respuesta
     * @return devuelve el identificador de una respuesta.
     */
    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Respuesta respuesta) {
        if (respuesta != null) {
            return respuesta.getIdRespuesta() + "";
        }
        return "";
    }
}
