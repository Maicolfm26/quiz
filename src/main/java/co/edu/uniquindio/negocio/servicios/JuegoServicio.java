package co.edu.uniquindio.negocio.servicios;

import co.edu.uniquindio.persistencia.entidades.Juego;
import co.edu.uniquindio.persistencia.entidades.Usuario;

public interface JuegoServicio {

    Juego guardarJuego(Juego juego) throws Exception;

}
