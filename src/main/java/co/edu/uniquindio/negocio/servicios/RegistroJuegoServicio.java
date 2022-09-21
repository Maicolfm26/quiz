package co.edu.uniquindio.negocio.servicios;

import co.edu.uniquindio.persistencia.entidades.Juego;
import co.edu.uniquindio.persistencia.entidades.Registro_Juego;
import co.edu.uniquindio.persistencia.entidades.Registro_JuegoId;

import java.util.List;

public interface RegistroJuegoServicio {

    Registro_Juego guardarRespuesta(Registro_Juego registro_juego) throws Exception;

    List<Registro_Juego> obtenerRegistroJuegoPorUsuario(String user_name);
}
