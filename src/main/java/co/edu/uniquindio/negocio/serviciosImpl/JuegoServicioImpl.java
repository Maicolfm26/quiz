package co.edu.uniquindio.negocio.serviciosImpl;

import co.edu.uniquindio.negocio.servicios.JuegoServicio;
import co.edu.uniquindio.persistencia.entidades.Juego;
import co.edu.uniquindio.persistencia.entidades.Usuario;
import co.edu.uniquindio.persistencia.repositorios.JuegoRepo;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JuegoServicioImpl implements JuegoServicio {


    private final JuegoRepo juegoRepo;

    public JuegoServicioImpl(JuegoRepo juegoRepo) {
        this.juegoRepo = juegoRepo;
    }


    @Override
    public Juego guardarJuego(Juego juego) throws Exception {
        return juegoRepo.save(juego);
    }
}
