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
 * La clase Pregunta contendrá toda la información de una pregunta del test.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Pregunta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idPregunta;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank
    private String descripcion;

    @ManyToOne(optional = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "pregunta", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<Respuesta> listadoRespuestas;

    @OneToMany(mappedBy = "pregunta", cascade = {CascadeType.REMOVE})
    private List<Registro_Juego> listadoRegistros;

    /**
     * Crea una pregunta con la descripción y categoría que le pasemos por parámetro.
     * @param descripcion
     * @param categoria
     */
    public Pregunta(String descripcion, Categoria categoria) {
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    /**
     * Eliminamos la respuesta que le pasemos por parámetro que esta asociada a la pregunta.
     * @param respuesta
     */
    public void eliminarRespuesta(Respuesta respuesta) {
        listadoRespuestas.remove(respuesta);
    }

}
