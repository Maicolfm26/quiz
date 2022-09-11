package co.edu.uniquindio.negocio.serviciosImpl;

import co.edu.uniquindio.negocio.servicios.UsuarioServicio;
import co.edu.uniquindio.persistencia.entidades.Usuario;
import co.edu.uniquindio.persistencia.repositorios.UsuarioRepo;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {


    private final UsuarioRepo usuarioRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

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

    @Override
    public Usuario actualizarDinero(Usuario usuario) throws Exception {
        return usuarioRepo.save(usuario);
    }
}
