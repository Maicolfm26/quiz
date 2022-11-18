package co.edu.uniquindio.negocio.serviciosImpl;

import co.edu.uniquindio.negocio.servicios.PreguntaServicio;
import co.edu.uniquindio.persistencia.entidades.Categoria;
import co.edu.uniquindio.persistencia.entidades.Pregunta;
import co.edu.uniquindio.persistencia.repositorios.PreguntaRepo;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * PreguntaServicioImpl implementa los servicios que se definieron para la entidad Pregunta.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Service
public class PreguntaServicioImpl implements PreguntaServicio {

    private final PreguntaRepo preguntaRepo;

    /**
     * Crea una PreguntaServicioImpl con la preguntaRepo recibida por parámetro.
     * @param preguntaRepo
     */
    public PreguntaServicioImpl(PreguntaRepo preguntaRepo) {
        this.preguntaRepo = preguntaRepo;
    }

    /**
     * Elimina la pregunta con el id especificado.
     * @param idPregunta
     * @throws Exception
     */
    @Override
    public void eliminarPregunta(int idPregunta) throws Exception {
        Pregunta pregunta = obtenerPregunta(idPregunta);
        preguntaRepo.deleteById(idPregunta);
    }

    /**
     * Consulta que pregunta tiene el id especificado.
     * @param idPregunta
     * @return devuelve la pregunta con el id especificado.
     * @throws Exception
     */
    @Override
    public Pregunta obtenerPregunta(int idPregunta) throws Exception {
        return preguntaRepo.findById(idPregunta).orElseThrow(() -> new Exception("La pregunta no existe"));
    }

    /**
     * Crea una pregunta.
     * @param pregunta
     * @return devuelve la pregunta que hemos creado.
     * @throws Exception
     */
    @Override
    public Pregunta agregarPregunta(Pregunta pregunta) throws Exception {
        return preguntaRepo.save(pregunta);
    }

    /**
     * Consulta una pregunta de una determinada categoría de forma aleatoria.
     * @param categoria
     * @return devuelve la pregunta escogida de forma aleatoria.
     */
    @Override
    public Pregunta obtenerPreguntaAleatoria(Categoria categoria) {
        List<Pregunta> preguntas = preguntaRepo.findByCategoria(categoria);
        int numeroPreguntas = preguntas.size();
        int indiceAleatorio = (int) (Math.random()*numeroPreguntas);
        return preguntas.get(indiceAleatorio);
    }

    /**
     * Consulta la cantidad de preguntas que hay en la base de datos
     * @return devuelve la cantidad de preguntas
     */
    @Override
    public int obtenerCantidad() {
        return (int) preguntaRepo.count();
    }

    /**
     * Calibra las preguntas de acuerdo a la cantidad de usuarios que la resuelto bien y el numero de segundos que tardaron.
     * @param pageable
     * @return devuelve listado de preguntas por el nivel
     */
    @Override
    public List<Pregunta> obtenerPreguntasPorNivel(Pageable pageable) {
        return preguntaRepo.getPreguntasByNivel(pageable);
    }
}
