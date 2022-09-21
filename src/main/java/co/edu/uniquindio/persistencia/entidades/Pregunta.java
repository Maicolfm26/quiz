package co.edu.uniquindio.persistencia.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class Pregunta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idPregunta;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank
    private String descripcion;

    @ManyToOne(optional = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "pregunta", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<Respuesta> listadoRespuestas;

    @OneToMany(mappedBy = "pregunta", cascade = {CascadeType.REMOVE})
    private List<Registro_Juego> listadoRegistros;

    public Pregunta(String descripcion, Categoria categoria) {
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public void eliminarRespuesta(Respuesta respuesta) {
        listadoRespuestas.remove(respuesta);
        System.out.println(listadoRespuestas);
    }

}
