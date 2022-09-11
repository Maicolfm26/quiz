package co.edu.uniquindio.negocio.serviciosImpl;

import co.edu.uniquindio.negocio.servicios.ProfesorServicio;
import co.edu.uniquindio.persistencia.entidades.Profesor;
import co.edu.uniquindio.persistencia.repositorios.ProfesorRepo;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Service;

@Service
public class ProfesorServicioImpl implements ProfesorServicio {

    private final ProfesorRepo profesorRepo;

    public ProfesorServicioImpl(ProfesorRepo profesorRepo) {
        this.profesorRepo = profesorRepo;
    }

    @Override
    public Profesor iniciarSesion(String userName, String clave) throws Exception {
        Profesor profesor = profesorRepo.findById(userName).orElseThrow(() -> new Exception("Los datos de autenticacion son incorrectos"));

        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        if(passwordEncryptor.checkPassword(clave, profesor.getClave())) {
            return profesor;
        } else {
            throw new Exception("Los datos de autenticacion son incorrectos");
        }
    }
}
