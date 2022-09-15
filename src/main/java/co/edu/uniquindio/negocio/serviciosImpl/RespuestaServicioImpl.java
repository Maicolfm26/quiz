package co.edu.uniquindio.negocio.serviciosImpl;

import co.edu.uniquindio.negocio.servicios.RespuestaServicio;
import co.edu.uniquindio.persistencia.entidades.Respuesta;
import co.edu.uniquindio.persistencia.repositorios.RespuestaRepo;
import org.springframework.stereotype.Service;

@Service
public class RespuestaServicioImpl implements RespuestaServicio {

    private final RespuestaRepo respuestaRepo;

    public RespuestaServicioImpl(RespuestaRepo respuestaRepo) {
        this.respuestaRepo = respuestaRepo;
    }

    @Override
    public void eliminarRespuesta(int idRespuesta) throws Exception {
        Respuesta respuesta = obtenerRespuesta(idRespuesta);
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
