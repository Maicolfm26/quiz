package co.edu.uniquindio.persistencia.repositorios;

import co.edu.uniquindio.persistencia.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CategoriaRepo está encargado de hacer las consultas a la base de datos de la tabla categoría.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Integer> {
}
