package cl.ubiobio_gps.plantilla_gps.usuario;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthController {

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    /*
    Que falta

    Agregar un handler para las respuestas
    Terminar los metodos de registro y logueo
    Añadir Tests con cypress o el de JUnit 
    */

    @PostMapping("/registrar")
    public String registrarUsuario() {

    }
    @PostMapping("/loguear")
    public String loguearUsuario() {

    }
    @PostMapping("/desloguear")
    public void desLoguearse() {

    }

    @PostMapping
    public String token(Authentication authentication) {
        String token = tokenService.generateToken(authentication);
        return token;
    }
}
