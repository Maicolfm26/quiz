package co.edu.uniquindio.persistencia.repositorios;

import co.edu.uniquindio.persistencia.entidades.Registro_Juego;
import co.edu.uniquindio.persistencia.entidades.Registro_JuegoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * RegistroJuegoRepo está encargado de hacer las consultas a la base de datos de la tabla registro_juego.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Repository
public interface RegistroJuegoRepo extends JpaRepository<Registro_Juego, Registro_JuegoId> {
    /**
     * Consulta los registros juegos que contengan el user name pasado por parámetro.
     * @param user_name
     * @return devuelve todos los registros de juego asociados al usuario.
     */
    @Query("select r from Registro_Juego r where r.id.userName = :user_name order by r.numeroPregunta")
    List<Registro_Juego> obtenerRegistroJuegoPorUsuario(String user_name);
}
