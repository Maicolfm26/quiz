package co.edu.uniquindio.negocio.serviciosImpl;

import co.edu.uniquindio.negocio.servicios.PreguntaServicio;
import co.edu.uniquindio.persistencia.entidades.Categoria;
import co.edu.uniquindio.persistencia.entidades.Pregunta;
import co.edu.uniquindio.persistencia.repositorios.PreguntaRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PreguntaServicioImpl implements PreguntaServicio {

    private final PreguntaRepo preguntaRepo;

    public PreguntaServicioImpl(PreguntaRepo preguntaRepo) {
        this.preguntaRepo = preguntaRepo;
    }

    @Override
    public void eliminarPregunta(int idPregunta) throws Exception {
        Pregunta pregunta = obtenerPregunta(idPregunta);
        preguntaRepo.deleteById(idPregunta);
    }

    @Override
    public Pregunta obtenerPregunta(int idPregunta) throws Exception {
        return preguntaRepo.findById(idPregunta).orElseThrow(() -> new Exception("La pregunta no existe"));
    }

    @Override
    public Pregunta agregarPregunta(Pregunta pregunta) throws Exception {
        return preguntaRepo.save(pregunta);
    }
}
