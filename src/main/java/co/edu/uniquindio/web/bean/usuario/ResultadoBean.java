package co.edu.uniquindio.web.bean.usuario;

import co.edu.uniquindio.negocio.servicios.RegistroJuegoServicio;
import co.edu.uniquindio.persistencia.entidades.Registro_Juego;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class ResultadoBean implements Serializable {

    private final SeguridadBean seguridadBean;
    private final RegistroJuegoServicio registroJuegoServicio;

    public ResultadoBean(SeguridadBean seguridadBean, RegistroJuegoServicio registroJuegoServicio) {
        this.seguridadBean = seguridadBean;
        this.registroJuegoServicio = registroJuegoServicio;
    }

    @Getter
    @Setter
    private List<Registro_Juego> listaRegistro_juegos;

    @PostConstruct
    public void init() {
        listaRegistro_juegos = registroJuegoServicio.obtenerRegistroJuegoPorUsuario(seguridadBean.getUsuarioSesion().getUserName());
    }

}
