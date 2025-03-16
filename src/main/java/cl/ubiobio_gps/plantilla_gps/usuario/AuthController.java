package cl.ubiobio_gps.plantilla_gps.usuario;

import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ubiobio_gps.plantilla_gps.helper.ResponseHandler;
import cl.ubiobio_gps.plantilla_gps.helper.Result;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;


@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    private final AuthService authService;

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();
    /*
    Que falta

    Agregar un handler para las respuestas
    Terminar los metodos de registro y logueo
    Añadir Tests con cypress o el de JUnit 
    */

    public AuthController(
        TokenService tokenService, 
        AuthenticationManager authenticationManager,
        AuthService authService
        , UsuarioService usuarioService) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<Map<String, Object>> registrarUsuario(
        @RequestBody UsuarioModel usuario
    ) {
        Set<ConstraintViolation<UsuarioModel>> violations = 
            validator.validate(usuario);
        if(!violations.isEmpty()) {
            String error = "";
            for(ConstraintViolation<UsuarioModel> violation : violations) {
                error += violation.getMessage();
                error += "\n";
            }
            return ResponseHandler.handleErrorClient("Error de validacion", HttpStatus.BAD_REQUEST, error);
        }
        Result<UsuarioModel, String> result = authService.registrarUsuario(usuario);
        if(result.getError() != null) {
            return ResponseHandler.handleErrorServer("Error al guardar usuario", HttpStatus.INTERNAL_SERVER_ERROR, result.getError());
        }
        return ResponseHandler.handleSuccess("Usuario Guardado correctamente", HttpStatus.OK, result.getError());
    }

    @PostMapping("/loguear")
    public ResponseEntity<Map<String, Object>> loguearUsuario(
        @RequestBody LoginForm body
    ) {
        //Check if there exist a user with the username
        //Map<String, Object> response = new HashMap<>();
        //response.put("status", "Server Error");
        //response.put("message", "hola");
        //response.put("data", "data");
        //return ResponseEntity.ok(response);
        boolean isUserValid = authService
            .loguearUsuario(body.nombre_usuario(), body.clave_usuario());
        if(!isUserValid) {
        return ResponseHandler.handleErrorClient(
            "Las credenciales ingresadas son incorrectas", 
            HttpStatus.BAD_REQUEST,
            body);
        }
        Authentication auth = 
            new UsernamePasswordAuthenticationToken(
            body.nombre_usuario(), null);
        String token = tokenService.generateToken(auth);
        return ResponseHandler.handleSuccess("Login Succesfull", 
        HttpStatus.OK,
        token
        );


    }
    //@PostMapping("/desloguear")
    //public void desLoguearse() {

    //}

    //@PostMapping
    //public String token(@RequestBody LoginForm userLogin) {
        ////Authentication authentication = authenticationManager.authenticate(
            ////new UsernamePasswordAuthenticationToken(
                ////userLogin.username(),
                ////userLogin.password()
            ////)
        ////);
        //System.out.println(userLogin.username());
        //if(userLogin.username().equals("x")) {
            //return "no";
        //}
        //Authentication auth = 
            //new UsernamePasswordAuthenticationToken(
            //userLogin.username(), null);
        //return tokenService.generateToken(auth);
        ////return token;
    //}
}
