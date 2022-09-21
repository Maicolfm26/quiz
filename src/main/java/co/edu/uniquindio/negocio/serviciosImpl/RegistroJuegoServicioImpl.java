package co.edu.uniquindio.negocio.serviciosImpl;

import co.edu.uniquindio.negocio.servicios.JuegoServicio;
import co.edu.uniquindio.negocio.servicios.RegistroJuegoServicio;
import co.edu.uniquindio.persistencia.entidades.Juego;
import co.edu.uniquindio.persistencia.entidades.Registro_Juego;
import co.edu.uniquindio.persistencia.repositorios.RegistroJuegoRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroJuegoServicioImpl implements RegistroJuegoServicio {


    private final RegistroJuegoRepo registroJuegoRepo;

    public RegistroJuegoServicioImpl(RegistroJuegoRepo registroJuegoRepo) {
        this.registroJuegoRepo = registroJuegoRepo;
    }

    @Override
    public Registro_Juego guardarRespuesta(Registro_Juego registro_juego) throws Exception {
        return registroJuegoRepo.save(registro_juego);
    }

    @Override
    public List<Registro_Juego> obtenerRegistroJuegoPorUsuario(String user_name) {
        return registroJuegoRepo.obtenerRegistroJuegoPorUsuario(user_name);
    }
}
