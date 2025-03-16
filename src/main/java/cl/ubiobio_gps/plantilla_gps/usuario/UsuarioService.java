package cl.ubiobio_gps.plantilla_gps.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public List<UsuarioModel> getUsuarios() {
        return usuarioRepository.findAll();
    }
    public Optional<UsuarioModel> getUsuario(String nombre_usuario) {
        Optional<UsuarioModel> optional = 
            usuarioRepository.findByNombreUsuario(nombre_usuario);
        return optional;
    }

    //Debera ser el register
    //public void addUsuario();

    //Solo se deberia permitir al Admin
    public void deleteUsuario(Long id_usuario) {
        boolean exists = usuarioRepository.existsById(id_usuario);
        if(!exists) {
            throw new IllegalStateException(
                "Alumno con id: " + id_usuario + " no existe"
            );
        }
    }
    //Para despues
    //public void updateUsuario(Long id_usuario);


}
