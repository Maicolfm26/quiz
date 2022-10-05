package co.edu.uniquindio.negocio.serviciosImpl;

import co.edu.uniquindio.negocio.servicios.JuegoServicio;
import co.edu.uniquindio.persistencia.entidades.Juego;
import co.edu.uniquindio.persistencia.entidades.Registro_Juego;
import co.edu.uniquindio.persistencia.repositorios.JuegoRepo;
import co.edu.uniquindio.persistencia.repositorios.RegistroJuegoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * JuegoServicioImpl implementa los servicios que se definieron para la entidad Juego.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Service
public class JuegoServicioImpl implements JuegoServicio {


    private final JuegoRepo juegoRepo;
    private final RegistroJuegoRepo registroJuegoRepo;

    /**
     * Crea un JuegoServicioImpl con el juegoRepo y registroJuegoRepo recibidos por parámetro.
     * @param juegoRepo
     * @param registroJuegoRepo
     */
    public JuegoServicioImpl(JuegoRepo juegoRepo, RegistroJuegoRepo registroJuegoRepo) {
        this.juegoRepo = juegoRepo;
        this.registroJuegoRepo = registroJuegoRepo;
    }


    /**
     * Crea un juego.
     * @param juego
     * @return devuelve el juego que hemos guardado.
     * @throws Exception
     */
    @Override
    public Juego guardarJuego(Juego juego, List<Registro_Juego> listado_registro_juego) throws Exception {
        Juego juegoGuardado = juegoRepo.saveAndFlush(juego);
        listado_registro_juego.forEach((registro_juego -> {registro_juego.setJuego(juegoGuardado); registroJuegoRepo.save(registro_juego);}));
        return juego;
    }
}
