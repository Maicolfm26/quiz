package co.edu.uniquindio.negocio.servicios;

import co.edu.uniquindio.persistencia.entidades.Usuario;
public interface UsuarioServicio {

    Usuario registrarUsuario(Usuario usuario) throws Exception;

    Usuario iniciarSesion(String userName, String clave) throws Exception;

    Usuario actualizarDinero(Usuario usuario) throws Exception;

}
