package cl.ubiobio_gps.plantilla_gps.helper;

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
        //UsuarioModel masterAdmin = new UsuarioModel(
           //"Master Admin",
           ////TODO: replace salt
           //"admin1234",
            //"master@admin.org",
            //UsuarioModel.roles.ADMIN
        //);
        UsuarioModel masterAdmin = new UsuarioModel();
        masterAdmin.setNombre_usuario("MasterAdmin");
        //TODO: encrypt
        
        masterAdmin.setClave_usuario("admin1234");
        masterAdmin.setCorreo_usuario("master@admin.org");
        masterAdmin.setRol_usuario(UsuarioModel.Roles.ADMIN);
        System.out.println(masterAdmin);
        usuarioRepository.save(masterAdmin);
    }
}
