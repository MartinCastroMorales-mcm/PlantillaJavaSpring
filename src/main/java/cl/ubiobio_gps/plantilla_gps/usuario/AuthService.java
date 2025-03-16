package cl.ubiobio_gps.plantilla_gps.usuario;

import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.ubiobio_gps.plantilla_gps.helper.Result;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    
    private final UsuarioService usuarioService;

    public AuthService(UsuarioService usuarioService, UsuarioRepository usuarioRepository) {
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    public boolean loguearUsuario(String nombre_usuario,
        //TODO usar Result para que los mensajes de los errores se muestren
        String clave_usuario) {
        Optional<UsuarioModel> optional = usuarioService.getUsuario(nombre_usuario);
        if(!optional.isPresent()) {
            return false;
        }
        //check password
        UsuarioModel usuario = optional.get();
        return usuario.verificarContraseña(clave_usuario);
    }
    public Result<UsuarioModel, String> registrarUsuario(UsuarioModel usuario) {
        Result<UsuarioModel, String> result = new Result<>();
        Optional<UsuarioModel> optional = usuarioService.getUsuario(usuario.getNombre_usuario());
        if(optional.isPresent()) {
            result.setError("Ya existe un usuario con ese nombre de usuario");
            return result;
        }
        //Insertar el usuario
        try {
            usuarioRepository.save(usuario);
        }catch(Exception e) {
            result.setError(e.getMessage());
            return result;
        }
        result.setValue(usuario);
        return result;
    }
}
