package co.edu.uniquindio.negocio.servicios;

import co.edu.uniquindio.persistencia.entidades.Juego;
import co.edu.uniquindio.persistencia.entidades.Registro_Juego;

import java.util.List;

/**
 * JuegoServicio define los servicios que se deben implementar para la entidad Juego.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

public interface JuegoServicio {

    /**
     * Crea un juego.
     * @param juego
     * @param listado_registro_juego
     * @return devuelve el juego que hemos guardado.
     * @throws Exception
     */
    Juego guardarJuego(Juego juego, List<Registro_Juego> listado_registro_juego) throws Exception;

}
