package co.edu.uniquindio.negocio.servicios;

import co.edu.uniquindio.persistencia.entidades.Juego;

/**
 * JuegoServicio define los servicios que se deben implementar para la entidad Juego.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

public interface JuegoServicio {

    /**
     * Crea un juego.
     * @param juego
     * @return devuelve el juego que hemos guardado.
     * @throws Exception
     */
    Juego guardarJuego(Juego juego) throws Exception;

}
