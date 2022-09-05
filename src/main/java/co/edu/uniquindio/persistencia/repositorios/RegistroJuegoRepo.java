package co.edu.uniquindio.persistencia.repositorios;

import co.edu.uniquindio.persistencia.entidades.Juego;
import co.edu.uniquindio.persistencia.entidades.JuegoId;
import co.edu.uniquindio.persistencia.entidades.Registro_Juego;
import co.edu.uniquindio.persistencia.entidades.Registro_JuegoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroJuegoRepo extends JpaRepository<Registro_Juego, Registro_JuegoId> {
}
