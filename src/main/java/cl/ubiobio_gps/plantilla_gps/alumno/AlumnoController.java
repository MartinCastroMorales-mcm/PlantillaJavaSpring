package cl.ubiobio_gps.plantilla_gps.alumno;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.ubiobio_gps.plantilla_gps.PlantillaGpsApplication;
import jakarta.validation.Valid;


@RestController
@RequestMapping(path = "/api/v1/alumno")
public class AlumnoController {


  private final AlumnoService alumnoService;

  public AlumnoController(AlumnoService alumnoService, AlumnoRepository alumnoRepository, PlantillaGpsApplication plantillaGpsApplication) {
    this.alumnoService = alumnoService;
  }  

  @GetMapping
  public List<AlumnoModel> getAlumnos() {
      return alumnoService.getAlumnos();
  }
  @PostMapping
  public void addAlumno(@RequestBody @Valid AlumnoModel alumno) {
    alumnoService.addAlumno(alumno);
  }

  @DeleteMapping(path = "{id_alumno}")
  public void deleteAlumno(@PathVariable("id_alumno") Long id_alumno) {
     alumnoService.deleteAlumno(id_alumno); 
  }

  @PutMapping(path = "id_alumno")
  public void updateAlumno(@PathVariable("id_alumno") Long id_alumno,
    @RequestParam(required = false) String nombre_completo_alumno,
    @RequestParam(required = false) String correo_alumno
    ) {
    
    alumnoService.updateAlumno(id_alumno, nombre_completo_alumno, correo_alumno);
    }
}
