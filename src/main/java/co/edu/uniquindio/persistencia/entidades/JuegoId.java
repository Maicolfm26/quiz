package co.edu.uniquindio.persistencia.entidades;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class JuegoId implements Serializable {
    private String userName;
}
