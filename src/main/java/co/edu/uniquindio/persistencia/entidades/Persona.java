package co.edu.uniquindio.persistencia.entidades;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Persona es una abstracción de usuario y profesor.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS )
@MappedSuperclass
public class Persona implements Serializable {

    @Id
    @Column(length = 50)
    @EqualsAndHashCode.Include
    private String userName;

    @Column(nullable = false, length = 100)
    @NotBlank
    private String clave;

}
