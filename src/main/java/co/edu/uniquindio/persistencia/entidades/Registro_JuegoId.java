package co.edu.uniquindio.persistencia.entidades;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Registro_JuegoId implements Serializable {
    private String userName;
    private Integer idPregunta;
}
