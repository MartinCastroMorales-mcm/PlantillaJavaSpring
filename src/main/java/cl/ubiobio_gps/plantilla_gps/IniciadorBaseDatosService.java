package cl.ubiobio_gps.plantilla_gps;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import cl.ubiobio_gps.plantilla_gps.usuario.UsuarioModel;
import cl.ubiobio_gps.plantilla_gps.usuario.UsuarioRepository;
import jakarta.annotation.PostConstruct;

@Service
public class IniciadorBaseDatosService {
    
    private final UsuarioRepository usuarioRepository;

    public IniciadorBaseDatosService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    //Inicia automaticamente despues de todos los beans
    @PostConstruct
    public void inicializarBaseDatos() { 
        UsuarioModel masterAdmin = new UsuarioModel(
           "Master Admin",
           //TODO: replace salt
           "admin1234",
            "master@admin.org",
            UsuarioModel.roles.ADMIN
        );
        usuarioRepository.save(masterAdmin);
    }
}
