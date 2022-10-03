package co.edu.uniquindio.negocio.serviciosImpl;

import co.edu.uniquindio.negocio.servicios.UsuarioServicio;
import co.edu.uniquindio.persistencia.entidades.Usuario;
import co.edu.uniquindio.persistencia.repositorios.UsuarioRepo;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * UsuarioServicioImpl implementa los servicios que se definieron para la entidad usuario.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Service
public class UsuarioServicioImpl implements UsuarioServicio {


    private final UsuarioRepo usuarioRepo;

    /**
     * Crea un UsuarioServicioImpl con el usuarioRepo recibido por parámetro.
     * @param usuarioRepo
     */
    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    /**
     * Crea un usuario.
     * @param usuario
     * @return devuelve el usuario creado.
     * @throws Exception
     */
    @Override
    public Usuario registrarUsuario(Usuario usuario) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(usuario.getUserName());
        if (buscado.isPresent()) {
            throw new Exception("El user name ya existe");
        }
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        usuario.setClave(passwordEncryptor.encryptPassword(usuario.getClave()));

        return usuarioRepo.save(usuario);
    }

    /**
     * Verifica que las credenciales pasadas por parámetro si existan en un usuario.
     * @param userName
     * @param clave
     * @return devuelve el usuario que va a iniciar sesión.
     * @throws Exception
     */
    @Override
    public Usuario iniciarSesion(String userName, String clave) throws Exception {
        Usuario usuario = usuarioRepo.findById(userName).orElseThrow(() -> new Exception("Los datos de autenticacion son incorrectos"));

        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        if(passwordEncryptor.checkPassword(clave, usuario.getClave())) {
            return usuario;
        } else {
            throw new Exception("Los datos de autenticacion son incorrectos");
        }
    }
}
