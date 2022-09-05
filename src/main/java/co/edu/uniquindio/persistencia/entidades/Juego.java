package co.edu.uniquindio.persistencia.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Juego implements Serializable {

    @EmbeddedId
    private JuegoId id;

    @MapsId("userName")  //  maps the empid attribute of embedded id
    @EqualsAndHashCode.Include
    @OneToOne Usuario usuario;

    @Column(nullable = false)
    private LocalDate fecha;

   /* @OneToMany(cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = )
    private List<Registro_Juego> listadoRegistros;*/

    public Juego(Usuario usuario) {
        this.usuario = usuario;
        fecha = LocalDate.now();
    }
}
