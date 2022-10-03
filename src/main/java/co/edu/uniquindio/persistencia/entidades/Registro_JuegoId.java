package co.edu.uniquindio.persistencia.entidades;

import lombok.*;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Registro_JuegoId representa el id para un Registro_Juego, el cual es el user name del usuario y el id de la pregunta.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Registro_JuegoId implements Serializable {
    private String userName;
    private Integer idPregunta;
}
