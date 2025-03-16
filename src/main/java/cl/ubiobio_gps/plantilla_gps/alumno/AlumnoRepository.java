package cl.ubiobio_gps.plantilla_gps.alumno;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AlumnoRepository 
    extends JpaRepository<AlumnoModel, Long> {
    
    //jpql != criteria != sql
    //Esto es jpql
    @Query("SELECT a FROM AlumnoModel a WHERE a.rut_alumno = ?1")
    Optional<AlumnoModel> findAlumnoByRut(String rut_alumno);
}
