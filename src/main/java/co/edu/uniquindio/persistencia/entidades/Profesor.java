package co.edu.uniquindio.persistencia.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Profesor representa en pocas palabras el administrador del sistema, el cual administrara las categorías, preguntas y respuestas del test.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Profesor extends Persona implements Serializable {

    /**
     * Crea un profesor con el user name y clave que le pasemos por parámetro.
     * @param user_name
     * @param clave
     */
    public Profesor(String user_name, @NotBlank String clave) {
        super(user_name, clave);
    }
}
