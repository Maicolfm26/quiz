package co.edu.uniquindio.persistencia.entidades;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class Registro_JuegoId implements Serializable {
    private Usuario usuario;
    private Integer idPregunta;
}
