package co.edu.uniquindio.persistencia.entidades;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Registro_Juego representa una respuesta de un determinado usuario a una pregunta concreta.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class Registro_Juego implements Serializable {

    @EmbeddedId
    private Registro_JuegoId id;

    @ManyToOne
    @MapsId("userName")
    private Juego juego;

    @ManyToOne
    @MapsId("idPregunta")
    private Pregunta pregunta;


    @ManyToOne(optional = false)
    private Respuesta respuesta;

    @Column(nullable = false)
    private int segundos;

    /**
     * Crea un registro de un determinado juego para un usuario y pregunta concreta.
     * @param juego
     * @param pregunta
     * @param respuesta
     */
    public Registro_Juego(Juego juego, Pregunta pregunta, Respuesta respuesta, int segundos) {
        id = new Registro_JuegoId(juego.getUserName(), pregunta.getIdPregunta());
        this.respuesta = respuesta;
        this.juego = juego;
        this.pregunta = pregunta;
        this.segundos = segundos;
    }
}
