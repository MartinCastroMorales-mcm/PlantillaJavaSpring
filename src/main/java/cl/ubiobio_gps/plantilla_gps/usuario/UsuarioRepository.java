package cl.ubiobio_gps.plantilla_gps.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository 
    extends JpaRepository<UsuarioModel, Long> {
}
