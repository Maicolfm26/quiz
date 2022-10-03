package co.edu.uniquindio.negocio.servicios;

import co.edu.uniquindio.persistencia.entidades.Categoria;
import co.edu.uniquindio.persistencia.entidades.Pregunta;

/**
 * PreguntaServicio define los servicios que se deben implementar para la entidad Pregunta.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

public interface PreguntaServicio {

    /**
     * Elimina la pregunta con el id especificado.
     * @param idPregunta
     * @throws Exception
     */
    void eliminarPregunta(int idPregunta) throws Exception;

    /**
     * Consulta que pregunta tiene el id especificado.
     * @param idPregunta
     * @return devuelve la pregunta con el id especificado.
     * @throws Exception
     */
    Pregunta obtenerPregunta(int idPregunta) throws Exception;

    /**
     * Crea una pregunta.
     * @param pregunta
     * @return devuelve la pregunta que hemos creado.
     * @throws Exception
     */
    Pregunta agregarPregunta(Pregunta pregunta) throws Exception;

    /**
     * Consulta una pregunta de una determinada categoría de forma aleatoria.
     * @param categoria
     * @return devuelve la pregunta escogida de forma aleatoria.
     */
    Pregunta obtenerPreguntaAleatoria(Categoria categoria);

}
