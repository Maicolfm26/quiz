package co.edu.uniquindio.persistencia.repositorios;

import co.edu.uniquindio.persistencia.entidades.Categoria;
import co.edu.uniquindio.persistencia.entidades.Pregunta;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * PreguntaRepo está encargado de hacer las consultas a la base de datos de la tabla pregunta.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Repository
public interface PreguntaRepo extends PagingAndSortingRepository<Pregunta, Integer> {
    /**
     * Consulta las preguntas que contiene la categoría especificada
     * @param categoria
     * @return devuelve las preguntas de la categoría especificada
     */
    List<Pregunta> findByCategoria(Categoria categoria);

    /**
     * Calibra las preguntas de acuerdo a la cantidad de usuarios que la resuelto bien y el numero de segundos que tardaron.
     * @param pageable
     * @return devuelve listado de preguntas por el nivel
     */
    @Query("select p from Registro_Juego r join r.pregunta p join p.listadoRespuestas re where re.valida = true and r.respuesta = re group by p order by count(r.id.userName) desc, sum(r.segundos) desc")
    List<Pregunta> getPreguntasByNivel(Pageable pageable);
}
