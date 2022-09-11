package co.edu.uniquindio.negocio.serviciosImpl;

import co.edu.uniquindio.negocio.servicios.CategoriaServicio;
import co.edu.uniquindio.persistencia.entidades.Categoria;
import co.edu.uniquindio.persistencia.repositorios.CategoriaRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServicioImpl implements CategoriaServicio {

    private final CategoriaRepo categoriaRepo;

    public CategoriaServicioImpl(CategoriaRepo categoriaRepo) {
        this.categoriaRepo = categoriaRepo;
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) throws Exception {
        return  categoriaRepo.save(categoria);
    }

    @Override
    public Categoria obtenerCategoria(int idCategoria) throws Exception {
        Optional<Categoria> buscado = categoriaRepo.findById(idCategoria);
        if(buscado.isEmpty()){
            throw new Exception("La categoria no existe");
        }
        return buscado.get();
    }

    @Override
    public List<Categoria> obtenerCategorias() {
        return categoriaRepo.findAll();
    }

    @Override
    public void eliminarCategoria(int idCategoria) throws Exception {
        categoriaRepo.deleteById(idCategoria);
    }

    @Override
    public void editarCategoria(Categoria categoria) throws Exception {
        Categoria buscado = categoriaRepo.findById(categoria.getIdCategoria()).orElseThrow(() -> new Exception("La categoria no existe"));
        categoriaRepo.save(categoria);
    }
}
