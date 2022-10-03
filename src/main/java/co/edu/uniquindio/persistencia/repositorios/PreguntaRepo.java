package co.edu.uniquindio.persistencia.repositorios;

import co.edu.uniquindio.persistencia.entidades.Categoria;
import co.edu.uniquindio.persistencia.entidades.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * PreguntaRepo está encargado de hacer las consultas a la base de datos de la tabla pregunta.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Repository
public interface PreguntaRepo extends JpaRepository<Pregunta, Integer> {
    /**
     * Consulta las preguntas que contiene la categoría especificada
     * @param categoria
     * @return devuelve las preguntas de la categoría especificada
     */
    List<Pregunta> findByCategoria(Categoria categoria);
}
