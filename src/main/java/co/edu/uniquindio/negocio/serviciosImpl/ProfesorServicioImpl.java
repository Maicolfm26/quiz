package co.edu.uniquindio.negocio.serviciosImpl;

import co.edu.uniquindio.negocio.servicios.ProfesorServicio;
import co.edu.uniquindio.persistencia.entidades.Profesor;
import co.edu.uniquindio.persistencia.repositorios.ProfesorRepo;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Service;

/**
 * ProfesorServicioImpl implementa los servicios que se definieron para la entidad Profesor.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Service
public class ProfesorServicioImpl implements ProfesorServicio {

    private final ProfesorRepo profesorRepo;

    /**
     * Crea un ProfesorServicioImpl con el profesorRepo recibido por parámetro.
     * @param profesorRepo
     */
    public ProfesorServicioImpl(ProfesorRepo profesorRepo) {
        this.profesorRepo = profesorRepo;
    }

    /**
     * Verifica que las credenciales pasadas por parámetro si existan en un profesor.
     * @param userName
     * @param clave
     * @return devuelve el profesor que va a iniciar sesión.
     * @throws Exception
     */
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
