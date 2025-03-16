package cl.ubiobio_gps.plantilla_gps.usuario;

import org.springframework.lang.NonNull;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginForm(String nombre_usuario, String clave_usuario) {

}

