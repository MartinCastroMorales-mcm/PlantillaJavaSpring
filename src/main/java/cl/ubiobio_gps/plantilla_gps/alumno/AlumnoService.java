package cl.ubiobio_gps.plantilla_gps.alumno;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Service
public class AlumnoService {
   private final AlumnoRepository alumnoRepository;
   private Validator validator;

    public AlumnoService(AlumnoRepository alumnoRepository,
        Validator validator) {
        this.alumnoRepository = alumnoRepository;
        this.validator = validator;
    } 

    public List<AlumnoModel> getAlumnos() {
        return alumnoRepository.findAll();
    }

    public void addAlumno(AlumnoModel alumno) {
        Optional<AlumnoModel> alumnoOptional = 
            alumnoRepository.findAlumnoByRut(alumno.getRut_alumno());
        
        if(alumnoOptional.isPresent()) {
            throw new IllegalStateException("Ya se ingreso un alumno con este rut");
        }
        alumnoRepository.save(alumno);
    }

    public void deleteAlumno(Long id_alumno) {
        boolean exists = alumnoRepository.existsById(id_alumno);

        if(!exists) {
            throw new IllegalStateException(
                "alumno con id: " + id_alumno + " no existe"
            );
        }
        alumnoRepository.deleteById(id_alumno);
    }

    public void updateAlumno(Long id_alumno,
        //Aqui van los atributos que son editables
        String nombre_completo_alumno,
        String correo_alumno
        ) {
        Optional<AlumnoModel> alumnoOptional = 
            alumnoRepository.findById(id_alumno);
        if(!alumnoOptional.isPresent()) {
            throw new IllegalStateException("No existe el alumno que se busca editar");
        }
        AlumnoModel alumno = alumnoOptional.get();
        //Revisar que al menos uno de estos sea no nulo
        if(nombre_completo_alumno == null
        &&  correo_alumno == null) {
            throw new IllegalStateException("No existe el alumno que se busca editar");
        }
        if(correo_alumno != null) {
            alumno.setCorreo_alumno(correo_alumno);
        }
        if(nombre_completo_alumno != null) {
            alumno.setNombre_completo_alumno(nombre_completo_alumno);
        }
        Set<ConstraintViolation<AlumnoModel>> violations = validator.validate(alumno);
        if(!violations.isEmpty()) {
            //TODO: usar excepciones mas descriptivas
            throw new IllegalStateException();
        }
        alumnoRepository.save(alumno);
    }
}
