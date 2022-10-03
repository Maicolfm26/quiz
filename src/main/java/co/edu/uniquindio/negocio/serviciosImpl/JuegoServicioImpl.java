package co.edu.uniquindio.negocio.serviciosImpl;

import co.edu.uniquindio.negocio.servicios.JuegoServicio;
import co.edu.uniquindio.persistencia.entidades.Juego;
import co.edu.uniquindio.persistencia.repositorios.JuegoRepo;
import org.springframework.stereotype.Service;

/**
 * JuegoServicioImpl implementa los servicios que se definieron para la entidad Juego.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Service
public class JuegoServicioImpl implements JuegoServicio {


    private final JuegoRepo juegoRepo;

    /**
     * Crea un JuegoServicioImpl con el juegoRepo recibido por parámetro.
     * @param juegoRepo
     */
    public JuegoServicioImpl(JuegoRepo juegoRepo) {
        this.juegoRepo = juegoRepo;
    }


    /**
     * Crea un juego.
     * @param juego
     * @return devuelve el juego que hemos guardado.
     * @throws Exception
     */
    @Override
    public Juego guardarJuego(Juego juego) throws Exception {
        return juegoRepo.save(juego);
    }
}
