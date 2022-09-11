package co.edu.uniquindio.negocio.servicios;

import co.edu.uniquindio.persistencia.entidades.Profesor;

public interface ProfesorServicio {

    Profesor iniciarSesion(String userName, String clave) throws Exception;
}
