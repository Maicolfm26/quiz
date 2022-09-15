package co.edu.uniquindio.negocio.servicios;

import co.edu.uniquindio.persistencia.entidades.Respuesta;

public interface RespuestaServicio {

    void eliminarRespuesta(int idRespuesta) throws Exception;

    Respuesta obtenerRespuesta(int idRespuesta) throws Exception;

    Respuesta agregarRespuesta(Respuesta respuesta) throws Exception;

}
