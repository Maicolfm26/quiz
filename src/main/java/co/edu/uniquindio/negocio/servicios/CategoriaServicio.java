package co.edu.uniquindio.negocio.servicios;

import co.edu.uniquindio.persistencia.entidades.Categoria;

import java.util.List;

public interface CategoriaServicio {

    Categoria crearCategoria(Categoria categoria) throws Exception;

    Categoria obtenerCategoria(int idCategoria) throws Exception;

    List<Categoria> obtenerCategorias();

    void eliminarCategoria(int idCategoria) throws Exception;

    void editarCategoria(Categoria categoria) throws Exception;

}
