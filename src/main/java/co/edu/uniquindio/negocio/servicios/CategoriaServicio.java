package co.edu.uniquindio.negocio.servicios;

import co.edu.uniquindio.persistencia.entidades.Categoria;
import java.util.List;

/**
 * CategoriaServicio define los servicios que se deben implementar para la entidad categoría.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

public interface CategoriaServicio {

    /**
     * Crea una categoría.
     * @param categoria
     * @return devuelve la categoría creada.
     * @throws Exception
     */
    Categoria crearCategoria(Categoria categoria) throws Exception;

    /**
     * Consulta una categoría por el id especificado.
     * @param idCategoria
     * @return devuelve la categoría que contiene el id.
     * @throws Exception
     */
    Categoria obtenerCategoria(int idCategoria) throws Exception;

    /**
     * Consulta las categorías.
     * @return devuelve todas las categorías de la base de datos
     */
    List<Categoria> obtenerCategorias();

    /**
     * Elimina la categoría con el id especificado.
     * @param idCategoria
     * @throws Exception
     */
    void eliminarCategoria(int idCategoria) throws Exception;

    /**
     * Edita la categoría que enviamos por parámetro.
     * @param categoria
     * @throws Exception
     */
    void editarCategoria(Categoria categoria) throws Exception;

}
