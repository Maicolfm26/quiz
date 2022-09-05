package co.edu.uniquindio.persistencia.entidades;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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
    @MapsId("usuario")
    private Juego juego;


    @ManyToOne
    @MapsId("idPregunta")
    private Pregunta pregunta;


    @ManyToOne(optional = false)
    private Respuesta respuesta;

}
