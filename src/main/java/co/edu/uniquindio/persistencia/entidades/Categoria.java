package co.edu.uniquindio.persistencia.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * Categoría representa un tipo de preguntas en el test.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int idCategoria;

    @Column(nullable = false, length = 50)
    @NotBlank
    private String nombre;

    @OneToMany(mappedBy = "categoria", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    private List<Pregunta> listadoPreguntas;

    /**
     * Crea una categoría con el nombre especificado.
     * @param nombre
     */
    public Categoria(String nombre) {
        this.nombre = nombre;
    }
}
