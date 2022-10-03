package co.edu.uniquindio.persistencia.repositorios;

import co.edu.uniquindio.persistencia.entidades.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ProfesorRepo está encargado de hacer las consultas a la base de datos de la tabla profesor.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Repository
public interface ProfesorRepo extends JpaRepository<Profesor, String> {
}
