package co.edu.uniquindio.negocio.serviciosImpl;

import co.edu.uniquindio.negocio.servicios.RegistroJuegoServicio;
import co.edu.uniquindio.persistencia.entidades.Registro_Juego;
import co.edu.uniquindio.persistencia.repositorios.RegistroJuegoRepo;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * RegistroJuegoServicioImpl implementa los servicios que se definieron para la entidad registro_juego.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Service
public class RegistroJuegoServicioImpl implements RegistroJuegoServicio {

    private final RegistroJuegoRepo registroJuegoRepo;

    /**
     * Crea un RegistroJuegoServicioImpl con el registroJuegoRepo recibido por parámetro.
     * @param registroJuegoRepo
     */
    public RegistroJuegoServicioImpl(RegistroJuegoRepo registroJuegoRepo) {
        this.registroJuegoRepo = registroJuegoRepo;
    }

    /**
     * Guarda un registro de la respuesta que se escogió para un determinado juego y pregunta.
     * @param registro_juego
     * @return devuelve el registro_juego creado.
     * @throws Exception
     */
    @Override
    public Registro_Juego guardarRespuesta(Registro_Juego registro_juego) throws Exception {
        return registroJuegoRepo.save(registro_juego);
    }

    /**
     * Consulta todos los registros juegos de un usuario.
     * @param user_name
     * @return devuelve todos los registro_juego de un usuario.
     */
    @Override
    public List<Registro_Juego> obtenerRegistroJuegoPorUsuario(String user_name) {
        return registroJuegoRepo.obtenerRegistroJuegoPorUsuario(user_name);
    }
}
