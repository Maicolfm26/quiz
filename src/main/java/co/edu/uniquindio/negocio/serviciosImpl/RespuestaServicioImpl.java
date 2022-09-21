package co.edu.uniquindio.negocio.serviciosImpl;

import co.edu.uniquindio.negocio.servicios.RespuestaServicio;
import co.edu.uniquindio.persistencia.entidades.Pregunta;
import co.edu.uniquindio.persistencia.entidades.Respuesta;
import co.edu.uniquindio.persistencia.repositorios.PreguntaRepo;
import co.edu.uniquindio.persistencia.repositorios.RespuestaRepo;
import org.springframework.stereotype.Service;

@Service
public class RespuestaServicioImpl implements RespuestaServicio {

    private final RespuestaRepo respuestaRepo;
    private final PreguntaRepo preguntaRepo;

    public RespuestaServicioImpl(RespuestaRepo respuestaRepo, PreguntaRepo preguntaRepo) {
        this.respuestaRepo = respuestaRepo;
        this.preguntaRepo = preguntaRepo;
    }

    @Override
    public void eliminarRespuesta(int idRespuesta) throws Exception {
        Respuesta respuesta = obtenerRespuesta(idRespuesta);
        Pregunta pregunta = respuesta.getPregunta();
        pregunta.eliminarRespuesta(respuesta);
        preguntaRepo.save(pregunta);
        respuestaRepo.deleteById(idRespuesta);
    }

    @Override
    public Respuesta obtenerRespuesta(int idRespuesta) throws Exception {
        return respuestaRepo.findById(idRespuesta).orElseThrow(() -> new Exception("La respuesta no existe"));
    }

    @Override
    public Respuesta agregarRespuesta(Respuesta respuesta) throws Exception {
        return respuestaRepo.save(respuesta);
    }
}
