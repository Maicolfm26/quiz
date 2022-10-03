package co.edu.uniquindio.negocio.serviciosImpl;

import co.edu.uniquindio.negocio.servicios.CategoriaServicio;
import co.edu.uniquindio.persistencia.entidades.Categoria;
import co.edu.uniquindio.persistencia.repositorios.CategoriaRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * CategoriaServicioImpl implementa los servicios que se definieron para la entidad categoría.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Service
public class CategoriaServicioImpl implements CategoriaServicio {

    private final CategoriaRepo categoriaRepo;

    /**
     * Crea una CategoriaServicioImpl con la categoriaRepo recibida por parámetro.
     * @param categoriaRepo
     */
    public CategoriaServicioImpl(CategoriaRepo categoriaRepo) {
        this.categoriaRepo = categoriaRepo;
    }

    /**
     * Crea una categoría.
     * @param categoria
     * @return devuelve la categoría creada.
     * @throws Exception
     */
    @Override
    public Categoria crearCategoria(Categoria categoria) throws Exception {
        return  categoriaRepo.save(categoria);
    }

    /**
     * Consulta una categoría por el id especificado.
     * @param idCategoria
     * @return devuelve la categoría que contiene el id.
     * @throws Exception
     */
    @Override
    public Categoria obtenerCategoria(int idCategoria) throws Exception {
        Optional<Categoria> buscado = categoriaRepo.findById(idCategoria);
        if(buscado.isEmpty()){
            throw new Exception("La categoria no existe");
        }
        return buscado.get();
    }

    /**
     * Consulta las categorías.
     * @return devuelve todas las categorías de la base de datos
     */
    @Override
    public List<Categoria> obtenerCategorias() {
        return categoriaRepo.findAll();
    }

    /**
     * Elimina la categoría con el id especificado.
     * @param idCategoria
     * @throws Exception
     */
    @Override
    public void eliminarCategoria(int idCategoria) throws Exception {
        categoriaRepo.deleteById(idCategoria);
    }

    /**
     * Edita la categoría que enviamos por parámetro.
     * @param categoria
     * @throws Exception
     */
    @Override
    public void editarCategoria(Categoria categoria) throws Exception {
        Categoria buscado = categoriaRepo.findById(categoria.getIdCategoria()).orElseThrow(() -> new Exception("La categoria no existe"));
        categoriaRepo.save(categoria);
    }
}
