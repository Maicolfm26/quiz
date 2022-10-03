package co.edu.uniquindio.negocio.serviciosImpl;

import co.edu.uniquindio.negocio.servicios.RespuestaServicio;
import co.edu.uniquindio.persistencia.entidades.Pregunta;
import co.edu.uniquindio.persistencia.entidades.Respuesta;
import co.edu.uniquindio.persistencia.repositorios.PreguntaRepo;
import co.edu.uniquindio.persistencia.repositorios.RespuestaRepo;
import org.springframework.stereotype.Service;

/**
 * RespuestaServicioImpl implementa los servicios que se definieron para la entidad respuesta.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Service
public class RespuestaServicioImpl implements RespuestaServicio {

    private final RespuestaRepo respuestaRepo;
    private final PreguntaRepo preguntaRepo;

    /**
     * Crea una RespuestaServicioImpl con la respuestaRepo y la preguntaRepo recibidos por parámetro.
     * @param respuestaRepo
     * @param preguntaRepo
     */
    public RespuestaServicioImpl(RespuestaRepo respuestaRepo, PreguntaRepo preguntaRepo) {
        this.respuestaRepo = respuestaRepo;
        this.preguntaRepo = preguntaRepo;
    }

    /**
     * Elimina una respuesta
     * @param idRespuesta
     * @throws Exception
     */
    @Override
    public void eliminarRespuesta(int idRespuesta) throws Exception {
        Respuesta respuesta = obtenerRespuesta(idRespuesta);
        Pregunta pregunta = respuesta.getPregunta();
        pregunta.eliminarRespuesta(respuesta);
        preguntaRepo.save(pregunta);
        respuestaRepo.deleteById(idRespuesta);
    }

    /**
     * Consulta la respuesta que contiene el id especificado.
     * @param idRespuesta
     * @return devuelve la respuesta con el id especificado.
     * @throws Exception
     */
    @Override
    public Respuesta obtenerRespuesta(int idRespuesta) throws Exception {
        return respuestaRepo.findById(idRespuesta).orElseThrow(() -> new Exception("La respuesta no existe"));
    }

    /**
     * Agrega una respuesta
     * @param respuesta
     * @return devuelve la respuesta agregada.
     * @throws Exception
     */
    @Override
    public Respuesta agregarRespuesta(Respuesta respuesta) throws Exception {
        return respuestaRepo.save(respuesta);
    }
}
