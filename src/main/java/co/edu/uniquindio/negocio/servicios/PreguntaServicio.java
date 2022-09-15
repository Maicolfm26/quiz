package co.edu.uniquindio.negocio.servicios;

import co.edu.uniquindio.persistencia.entidades.Categoria;
import co.edu.uniquindio.persistencia.entidades.Pregunta;

public interface PreguntaServicio {

    void eliminarPregunta(int idPregunta) throws Exception;

    Pregunta obtenerPregunta(int idPregunta) throws Exception;

    Pregunta agregarPregunta(Pregunta pregunta) throws Exception;

}
