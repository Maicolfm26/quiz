package co.edu.uniquindio.web.bean.usuario;

import co.edu.uniquindio.negocio.servicios.PreguntaServicio;
import co.edu.uniquindio.persistencia.entidades.Pregunta;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * AlgoritmoAdaptativo representa la logica de programacion que estamos utilizando para definir las preguntas que va
 * responder el usuario y el nivel en el que se encuentra este.
 * @author Michael Alexander Florez Muñoz, Gustavo Adolfo Gutierrez Londoño, Juan Camilo Jaramillo De La Torre
 */

@Component
@Scope("session")
public class AlgoritmoAdaptativo {

    private int numeroPreguntas;
    @Getter
    private int numeroPreguntasPorNivel;
    private int niveles;
    @Getter
    private double nivelPersona;
    @Getter
    private int nivelPregunta;

    private double rango;

    @Getter
    private int preguntasResueltas;

    private ArrayList<List<Pregunta>> listadoPreguntas;

    @Getter
    private Pregunta preguntaActual;

    private final PreguntaServicio preguntaServicio;

    public AlgoritmoAdaptativo(PreguntaServicio preguntaServicio) {
        this.preguntaServicio = preguntaServicio;
        numeroPreguntas = 6;
        nivelPersona = 2.0;
        nivelPregunta = 2;
        niveles = 3;
        rango = 0.5;
        preguntasResueltas = 0;
        numeroPreguntasPorNivel = preguntaServicio.obtenerCantidad() / niveles;
        listadoPreguntas = new ArrayList<List<Pregunta>>();
        for(int i = 0; i < niveles; i++) {
            PageRequest pr = PageRequest.of(i,numeroPreguntasPorNivel);
            listadoPreguntas.add(preguntaServicio.obtenerPreguntasPorNivel(pr));
        }
        siguientePregunta();
    }

    /**
     * Carga la siguiente pregunta de forma aleatoria dependiendo del nivel en el que esta el usuario
     */
    public void siguientePregunta() {
        List<Pregunta> nivelActualPreguntas = listadoPreguntas.get(nivelPregunta-1);
        int indiceAleatorio = (int) (Math.random()*nivelActualPreguntas.size());
        preguntaActual = nivelActualPreguntas.get(indiceAleatorio);
        nivelActualPreguntas.remove(indiceAleatorio);

        preguntasResueltas++;
    }

    /**
     * Calcula probabilidad de que un usuario con un nivel de conocimiento responde de manera correcta
     * una pregunta de un nivel
     */
    public void calcularProbabilidad() {
        double probabilidad = Math.pow(Math.E, nivelPersona - nivelPregunta) / (1 + Math.pow(Math.E, nivelPersona - nivelPregunta));
        if(probabilidad > 0.5) {
            if(nivelPregunta !=3) {
                nivelPregunta++;
            }
        } else if (probabilidad < 0.5) {
            if(nivelPregunta != 1) {
                nivelPregunta--;
            }
        }
    }

    /**
     * Calcula en el nivel de la persona
     * @param isCorrect
     */
    public void calcularNivelPersona(boolean isCorrect) {
        if(isCorrect) {
            if(nivelPersona != 5.0) {
                nivelPersona += rango;
            }
        } else {
            if(nivelPersona != 1.0) {
                nivelPersona -= rango;
            }
        }
    }

    /**
     * Verifica que el usuario va responder la ultima pregunta
     * @return
     */
    public boolean isLastQuestion() {
        return preguntasResueltas == numeroPreguntas;
    }
}
