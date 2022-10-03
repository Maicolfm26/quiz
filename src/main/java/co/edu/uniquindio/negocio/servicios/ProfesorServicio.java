package co.edu.uniquindio.negocio.servicios;

import co.edu.uniquindio.persistencia.entidades.Profesor;

/**
 * ProfesorServicio define los servicios que se deben implementar para la entidad Profesor.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

public interface ProfesorServicio {

    /**
     * Verifica que las credenciales pasadas por parámetro si existan en un profesor.
     * @param userName
     * @param clave
     * @return devuelve el profesor que va a iniciar sesión.
     * @throws Exception
     */
    Profesor iniciarSesion(String userName, String clave) throws Exception;
}
