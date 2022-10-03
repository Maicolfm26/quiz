package co.edu.uniquindio.persistencia.repositorios;

import co.edu.uniquindio.persistencia.entidades.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * RespuestaRepo está encargado de hacer las consultas a la base de datos de la tabla respuesta.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Repository
public interface RespuestaRepo extends JpaRepository<Respuesta, Integer> {
}
