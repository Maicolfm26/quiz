package co.edu.uniquindio.persistencia.entidades;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

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

    public Usuario(String userName, @NotBlank String clave, int edad) {
        super(userName, clave);
        this.edad = edad;
    }
}
