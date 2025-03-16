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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "usuario")
public class UsuarioModel {

    @Override
    public String toString() {
        return "UsuarioModel [id=" + id + ", nombre_usuario=" + nombre_usuario + ", clave_usuario=" + clave_usuario
                + ", correo_usuario=" + correo_usuario + ", role_usuario=" + rol_usuario + "]";
    }
    //TODO: define secret key
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10); 

    //No hay que usar constructores con argumentos porque le da problemas a los Bean
    //public UsuarioModel(
            //@NotBlank @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", 
            //message = "El nombre solo puede contener letras y espacios") String nombre_usuario,
            //@NotBlank String clave_usuario, 
            //@NotBlank @Email String correo_usuario, 
            //roles role_usuario) {
            //encoder.encode(clave_usuario);


        //// generates a random 8-byte salt that is then hex-encoded
        //this.nombre_usuario = nombre_usuario;
        //this.clave_usuario = clave_usuario;
        //this.correo_usuario = correo_usuario;
        //this.role_usuario = role_usuario;
    //}

    public enum Roles {
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
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", 
    message = "El nombre solo puede contener letras y espacios")
    private String nombre_usuario;

    @NonNull
    @NotBlank(message = "La clave es obligatoria")
    private String clave_usuario;
    @NonNull
    @NotBlank(message = "El correo es obligatorio")
    @Email
    private String correo_usuario;

    @NotNull(message = "El rol es obligatorio")
    private Roles rol_usuario;

    public static BCryptPasswordEncoder getEncoder() {
        return encoder;
    }

    public static void setEncoder(BCryptPasswordEncoder encoder) {
        UsuarioModel.encoder = encoder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getClave_usuario() {

        return clave_usuario;
    }

    public void setClave_usuario(String clave_usuario) {
        String claveEncriptada = encoder.encode(clave_usuario);
        this.clave_usuario = claveEncriptada;
    }

    public String getCorreo_usuario() {
        return correo_usuario;
    }

    public void setCorreo_usuario(String correo_usuario) {
        this.correo_usuario = correo_usuario;
    }

    public Roles getRol_usuario() {
        return rol_usuario;
    }

    public void setRol_usuario(Roles role_usuario) {
        this.rol_usuario = role_usuario;
    }
    public boolean verificarContraseña(String clave_ingresada) {
        return encoder.matches(clave_ingresada, this.clave_usuario);
    }

}
