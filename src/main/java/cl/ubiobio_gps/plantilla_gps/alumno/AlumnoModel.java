package cl.ubiobio_gps.plantilla_gps.alumno;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class AlumnoModel {

    @Id
    private long id_alumno;
    //Las anotaciones no tienen ;
    @Size(max=12, min=11, message = "Rut solo puede tener 11 o 12 caracteres")
    private String rut_alumno;

    @NotNull
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", 
    message = "El nombre solo puede contener letras y espacios")
    private String nombre_completo_alumno;

    @Transient
    //@Positive(message = "La edad debe ser un numero positivo")
    private int edad_alumno;

    @Past(message = "La fecha de nacimiento debe ser positiva")
    private LocalDate fecha_nacimiento_alumno;

    @NotNull
    @NotBlank
    @Email
    private String correo_alumno;




    public long getId_alumno() {
        return id_alumno;
    }
    public void setId_alumno(long id_alumno) {
        this.id_alumno = id_alumno;
    }
    public String getRut_alumno() {
        return rut_alumno;
    }
    public void setRut_alumno(String rut_alumno) {
        this.rut_alumno = rut_alumno;
    }
    public String getNombre_completo_alumno() {
        return nombre_completo_alumno;
    }
    public void setNombre_completo_alumno(String nombre_completo_alumno) {
        this.nombre_completo_alumno = nombre_completo_alumno;
    }
    public int getEdad_alumno() {
        return edad_alumno;
    }
    public void setEdad_alumno(int edad_alumno) {
        //TODO crear edad basandose en la fecha de nacimiento
        this.edad_alumno = edad_alumno;
    }
    public LocalDate getFecha_nacimiento_alumno() {
        return fecha_nacimiento_alumno;
    }
    public void setFecha_nacimiento_alumno(LocalDate fecha_nacimiento_alumno) {
        this.fecha_nacimiento_alumno = fecha_nacimiento_alumno;
    }
    public String getCorreo_alumno() {
        return correo_alumno;
    }
    public void setCorreo_alumno(String correo_alumno) {
        this.correo_alumno = correo_alumno;
    }
}
