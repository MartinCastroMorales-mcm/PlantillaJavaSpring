package cl.ubiobio_gps.plantilla_gps.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;

public interface UsuarioRepository 
    extends JpaRepository<UsuarioModel, Long> {

    @NativeQuery(value = "SELECT * FROM usuario WHERE nombre_usuario = ?1")
    Optional<UsuarioModel> findByNombreUsuario(String nombre_usuario);
}
