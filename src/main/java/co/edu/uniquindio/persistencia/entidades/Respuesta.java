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
 * La clase Respuesta contendrá toda la información de una opción de respuesta de una determinada pregunta.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Respuesta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idRespuesta;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank
    private String descripcion;

    @Column(nullable = false)
    private boolean valida;

    @ManyToOne(optional = false)
    private Pregunta pregunta;

    @OneToMany(mappedBy = "respuesta", cascade = {CascadeType.REMOVE})
    private List<Registro_Juego> listadoRegistros;
}
