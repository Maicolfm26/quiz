package co.edu.uniquindio.negocio.servicios;

import co.edu.uniquindio.persistencia.entidades.Registro_Juego;
import java.util.List;

/**
 * RegistroJuegoServicio define los servicios que se deben implementar para la entidad registro_juego.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

public interface RegistroJuegoServicio {

    /**
     * Guarda un registro de la respuesta que se escogió para un determinado juego y pregunta.
     * @param registro_juego
     * @return devuelve el registro_juego creado.
     * @throws Exception
     */
    Registro_Juego guardarRespuesta(Registro_Juego registro_juego) throws Exception;

    /**
     * Consulta todos los registros juegos de un usuario.
     * @param user_name
     * @return devuelve todos los registro_juego de un usuario.
     */
    List<Registro_Juego> obtenerRegistroJuegoPorUsuario(String user_name);
}
