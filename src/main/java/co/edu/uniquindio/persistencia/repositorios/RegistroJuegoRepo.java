package co.edu.uniquindio.persistencia.repositorios;

import co.edu.uniquindio.persistencia.entidades.Registro_Juego;
import co.edu.uniquindio.persistencia.entidades.Registro_JuegoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroJuegoRepo extends JpaRepository<Registro_Juego, Registro_JuegoId> {
    @Query("select r from Registro_Juego r where r.id.userName = :user_name")
    List<Registro_Juego> obtenerRegistroJuegoPorUsuario(String user_name);
}
