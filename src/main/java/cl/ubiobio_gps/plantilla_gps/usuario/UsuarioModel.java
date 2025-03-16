package cl.ubiobio_gps.plantilla_gps.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "usuario")
public class UsuarioModel {

    //TODO: define secret key
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10); 

    public UsuarioModel(
            @NotBlank @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", 
            message = "El nombre solo puede contener letras y espacios") String nombre_usuario,
            @NotBlank String clave_usuario, 
            @NotBlank @Email String correo_usuario, 
            roles role_usuario) {
            encoder.encode(clave_usuario);


        // generates a random 8-byte salt that is then hex-encoded
        this.nombre_usuario = nombre_usuario;
        this.clave_usuario = clave_usuario;
        this.correo_usuario = correo_usuario;
        this.role_usuario = role_usuario;
    }

    public enum roles {
        ADMIN,
        SIMPLE;

        //rol deberia ser un int ¿?

        //private int rol_id;

        //private roles(int rol_id) {
            //this.rol_id = rol_id;
        //}
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long id;

    @NonNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", 
    message = "El nombre solo puede contener letras y espacios")
    private String nombre_usuario;

    @NonNull
    @NotBlank
    private String clave_usuario;
    @NonNull
    @NotBlank
    @Email
    private String correo_usuario;

    private roles role_usuario;
}
