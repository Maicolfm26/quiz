package co.edu.uniquindio.negocio.servicios;

import co.edu.uniquindio.persistencia.entidades.Respuesta;

/**
 * RespuestaServicio define los servicios que se deben implementar para la entidad Respuesta.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

public interface RespuestaServicio {

    /**
     * Elimina una respuesta
     * @param idRespuesta
     * @throws Exception
     */
    void eliminarRespuesta(int idRespuesta) throws Exception;

    /**
     * Consulta la respuesta que contiene el id especificado.
     * @param idRespuesta
     * @return devuelve la respuesta con el id especificado.
     * @throws Exception
     */
    Respuesta obtenerRespuesta(int idRespuesta) throws Exception;

    /**
     * Agrega una respuesta
     * @param respuesta
     * @return devuelve la respuesta agregada.
     * @throws Exception
     */
    Respuesta agregarRespuesta(Respuesta respuesta) throws Exception;

}
