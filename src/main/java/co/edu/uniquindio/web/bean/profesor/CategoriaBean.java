package co.edu.uniquindio.web.bean.profesor;

import co.edu.uniquindio.negocio.servicios.CategoriaServicio;
import co.edu.uniquindio.persistencia.entidades.Categoria;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.List;

/**
 * CategoriaBean es el encargado de controlar la vista de categorías.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Component
@ViewScoped
public class CategoriaBean {

    public final CategoriaServicio categoriaServicio;

    @Getter
    @Setter
    private Categoria categoria;

    @Getter
    @Setter
    private Categoria categoriaSeleccionada;

    /**
     * Crea una CategoriaBean con la categoriaServicio recibida por parámetro.
     * @param categoriaServicio
     */
    public CategoriaBean(CategoriaServicio categoriaServicio) {
        this.categoriaServicio = categoriaServicio;
    }

    @Getter
    @Setter
    public List<Categoria> listaCategorias;

    /**
     * Después de construir la CategoriaBean cargamos el listado de categorías de la base de datos.
     */
    @PostConstruct
    public void init() {
        listaCategorias = categoriaServicio.obtenerCategorias();
        categoria = new Categoria();
    }

    /**
     * Elimina una categoría.
     * @param categoria
     */
    public void eliminarCategoria(Categoria categoria) {
        try {
            categoriaServicio.eliminarCategoria(categoria.getIdCategoria());
            listaCategorias.remove(categoria);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Categoria eliminada");
            FacesContext.getCurrentInstance().addMessage("msj-options", msg);

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-options", msg);
        }
    }

    /**
     * Crea una categoria.
     */
    public void crearCategoria() {
        try {
            categoriaServicio.crearCategoria(categoria);
            listaCategorias.add(categoria);
            categoria = new Categoria();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Categoria creada");
            FacesContext.getCurrentInstance().addMessage("msj-categoria", msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-categoria", msg);
        }
    }

    /**
     * Selecciona una categoría y muestra la ventana emergente para poderla editar.
     * @param categoria
     */
    public void seleccionarCategoria(Categoria categoria) {
        this.categoriaSeleccionada = categoria;
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('editarCategoria').show();");
    }

    /**
     * Edita una categoría.
     */
    public void editarCategoria() {
        try {
            categoriaServicio.editarCategoria(categoriaSeleccionada);
            categoriaSeleccionada = null;
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('PF('editarCategoria').initPosition();PF('editarCategoria').show();");
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Categoria actualizada"));
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-categoria-editar", msg);
        }
    }

    /**
     * Nos redirecciona a la vista de preguntas que contiene la categoría.
     * @param id
     * @return redirecciona a la vista de preguntas.
     */
    public String irAEditar(String id) {
        return "preguntas?faces-redirect=true&amp;idCategoria=" + id;
    }
}
