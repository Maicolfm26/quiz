package co.edu.uniquindio.persistencia.repositorios;

import co.edu.uniquindio.persistencia.entidades.Juego;
import co.edu.uniquindio.persistencia.entidades.JuegoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuegoRepo extends JpaRepository<Juego, JuegoId> {
}
