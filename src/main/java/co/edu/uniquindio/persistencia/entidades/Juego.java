package co.edu.uniquindio.persistencia.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Juego representa que un usuario determinado ha resuelto el test.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Juego implements Serializable {
    @Id
    @Column
    @EqualsAndHashCode.Include
    private String userName;

    @MapsId
    @OneToOne Usuario usuario;

    @Column(nullable = false)
    private LocalDate fecha;

    /**
     * Crea un juego para el usuario especificado y añade la hora actual.
     * @param usuario
     */
    public Juego(Usuario usuario) {
        userName = usuario.getUserName();
        fecha = LocalDate.now();
        this.usuario = usuario;
    }
}
