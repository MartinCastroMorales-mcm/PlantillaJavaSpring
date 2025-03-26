package cl.ubiobio_gps.plantilla_gps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.ubiobio_gps.plantilla_gps.alumno.AlumnoModel;
import cl.ubiobio_gps.plantilla_gps.alumno.AlumnoRepository;

//@ContextConfiguration(classes = PlantillaGpsApplication.class) // Replace with your main app class
//@SpringBootTest
@SpringBootTest(classes = cl.ubiobio_gps.plantilla_gps.PlantillaGpsApplication.class)
//@WebMvcTest(AlumnoController.class)
@AutoConfigureMockMvc
public class AlumnoTest {
   
    @Autowired
    MockMvc MockMvc;

    @MockitoBean
    AlumnoRepository repositorio;

    private String token;

    @BeforeEach
    void setUp() throws Exception {
        // Code here runs before each test

        //body
        String jsonBody = """
        {
            "nombre_usuario": "MasterAdmin",
            "clave_usuario": "admin1234"
        }
        """;

        System.out.println("Setting up before each test");
        MvcResult result = MockMvc.perform(MockMvcRequestBuilders
            .post("/api/v1/auth/loguear")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonBody)) //<-- ) importante
            .andReturn();

         MockHttpServletResponse response = 
            result.getResponse();
        
        String sResponse = response.getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(sResponse);
        
        int status = response.getStatus();
        assertEquals(200, status);
        token = rootNode.get("data").asText();
        assertEquals(true, token instanceof String);
        
    }

    @Test
    void shouldGetAlumnos() throws Exception {
        //body

        MvcResult result = MockMvc.perform(MockMvcRequestBuilders
            .get("/api/v1/alumno")
            .header("Authorization", "Bearer " + token))
            .andReturn();

        MockHttpServletResponse response = result.getResponse();
        //check status
        int status = response.getStatus();
        assertEquals(200, status);
        //MockHttpServletResponse response = result.getResponse();
        String jsonResponse = response.getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<AlumnoModel> Alumnos = objectMapper.readValue(
            //Las llaves al final significa que List<AlumnosModel>> esta definido
            //como un tipo anonimo, es decir no esta definido como un archivo.
            jsonResponse, new TypeReference<List<AlumnoModel>>() {});

        //Se usa assert True y no assert ya que assert solo funciona en runtime y
        //es dependiente a las configuraciones del jvm a diferencia de assertTrue
        assertTrue(Alumnos.size() >= 0);
    }
    //TODO ruta sin autentificacion 
    //TODO ruta con rol incorrecto

    @Test
    void postSuccessCase() throws Exception {

        String jsonBody = """
        {
            "rut_alumno": "20.941.502-K",
            "nombre_completo_alumno": "MARTIN CASTRO MORALES",
            "fecha_nacimiento_alumno":"2001-12-21",
            "correo_alumno": "martin.castro2101@alumnos.ubiobio.cl"
        }
        """;
        MockHttpServletResponse response = postAlumno(jsonBody);
        int status = response.getStatus();
        assertEquals(200, status);
    }
    @Test
    void postErrorInvalidRut() throws Exception {

        String jsonBody = """
        {
            "rut_alumno": "20.941.502-1",
            "nombre_completo_alumno": "MARTIN CASTRO MORALES",
            "fecha_nacimiento_alumno":"2001-12-21",
            "correo_alumno": "martin.castro2101@alumnos.ubiobio.cl"
        }
        """;
        MockHttpServletResponse response = postAlumno(jsonBody);
        int status = response.getStatus();
        assertEquals(400, status);
    }
    @Test
    void postErrorPorlyFormatedRut() throws Exception {

        String jsonBody = """
        {
            "rut_alumno": "2-1",
            "nombre_completo_alumno": "MARTIN CASTRO MORALES",
            "fecha_nacimiento_alumno":"2001-12-21",
            "correo_alumno": "martin.castro2101@alumnos.ubiobio.cl"
        }
        """;
        MockHttpServletResponse response = postAlumno(jsonBody);
        int status = response.getStatus();
        assertEquals(400, status);
    }
    @Test
    void postNoRutValue() throws Exception {

        String jsonBody = """
        {
            "rut_alumno": "",
            "nombre_completo_alumno": "MARTIN CASTRO MORALES",
            "fecha_nacimiento_alumno":"2001-12-21",
            "correo_alumno": "martin.castro2101@alumnos.ubiobio.cl"
        }
        """;
        MockHttpServletResponse response = postAlumno(jsonBody);
        int status = response.getStatus();
        assertEquals(400, status);
    }
    @Test
    void postNombreInvalido() throws Exception {

        String jsonBody = """
        {
            "rut_alumno": "20.941.502-1",
            "nombre_completo_alumno": "1ñl23jk12MARTIN CASTRO MORALES",
            "fecha_nacimiento_alumno":"2001-12-21",
            "correo_alumno": "martin.castro2101@alumnos.ubiobio.cl"
        }
        """;
        MockHttpServletResponse response = postAlumno(jsonBody);
        int status = response.getStatus();
        assertEquals(400, status);
    }
    @Test
    void postFechaInvalida() throws Exception {

        String jsonBody = """
        {
            "rut_alumno": "20.941.502-1",
            "nombre_completo_alumno": "MARTIN CASTRO MORALES",
            "fecha_nacimiento_alumno":"2030-12-21",
            "correo_alumno": "martin.castro2101@alumnos.ubiobio.cl"
        }
        """;
        MockHttpServletResponse response = postAlumno(jsonBody);
        int status = response.getStatus();
        assertEquals(400, status);
    }
    @Test
    void postCorreoFormatoInvalido() throws Exception {

        String jsonBody = """
        {
            "rut_alumno": "20.941.502-1",
            "nombre_completo_alumno": "MARTIN CASTRO MORALES",
            "fecha_nacimiento_alumno":"2001-12-21",
            "correo_alumno": "martin.castro2101-alumnos.ubiobio.cl"
        }
        """;
        MockHttpServletResponse response = postAlumno(jsonBody);
        int status = response.getStatus();
        assertEquals(400, status);
    }

    private MockHttpServletResponse postAlumno(String jsonBody) {
        MvcResult result = null;
        try {
            result = MockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/alumno")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBody)) //<-- ) importante
                .andReturn();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(result == null) {
            return null;
        }
        MockHttpServletResponse response = result.getResponse();
        //check status
        return response;
    }
}
