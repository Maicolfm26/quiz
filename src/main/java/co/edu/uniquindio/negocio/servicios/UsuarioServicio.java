package co.edu.uniquindio.negocio.servicios;

import co.edu.uniquindio.persistencia.entidades.Usuario;

/**
 * UsuarioServicio define los servicios que se deben implementar para la entidad usuario.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

public interface UsuarioServicio {

    /**
     * Crea un usuario.
     * @param usuario
     * @return devuelve el usuario creado.
     * @throws Exception
     */
    Usuario registrarUsuario(Usuario usuario) throws Exception;

    /**
     * Verifica que las credenciales pasadas por parámetro si existan en un usuario.
     * @param userName
     * @param clave
     * @return devuelve el usuario que va a iniciar sesión.
     * @throws Exception
     */
    Usuario iniciarSesion(String userName, String clave) throws Exception;

    /**
     * Actualiza el usuario especificado por parametro
     * @param usuario
     * @throws Exception
     */
    void actualizarUsuario(Usuario usuario) throws Exception;
}
