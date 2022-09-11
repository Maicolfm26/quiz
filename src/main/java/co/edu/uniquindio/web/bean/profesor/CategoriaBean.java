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


    public CategoriaBean(CategoriaServicio categoriaServicio) {
        this.categoriaServicio = categoriaServicio;
    }

    @Getter
    @Setter
    public List<Categoria> listaCategorias;

    @PostConstruct
    public void init() {
        listaCategorias = categoriaServicio.obtenerCategorias();
        categoria = new Categoria();
    }

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

    public void seleccionarCategoria(Categoria categoria) {
        this.categoriaSeleccionada = categoria;
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('editarCategoria').show();");
    }

    public void editarCategoria() {
        try {
            categoriaServicio.editarCategoria(categoriaSeleccionada);
            categoriaSeleccionada = null;
            PrimeFaces current = PrimeFaces.current();
            current.executeScript("PF('editarCategoria').hide();");
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Categoria actualizada"));
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage("msj-categoria-editar", msg);
        }
    }

    public String irAEditar(String id) {
        return "preguntas?faces-redirect=true&amp;idCategoria=" + id;
    }
}
