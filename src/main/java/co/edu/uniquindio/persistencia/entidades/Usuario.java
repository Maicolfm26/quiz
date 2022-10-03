package co.edu.uniquindio.persistencia.entidades;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * La clase Usuario representa un estudiante que resolvería el test.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Usuario extends Persona implements Serializable {

    @Column
    @Min(value = 8, message = "La edad minima es de 8 años")
    @Max(value = 11, message = "La edad maxima es de 11 años")
    private int edad;

    @OneToOne(mappedBy = "usuario")
    private Juego juego;
}
