package service;

import model.usuario;
import model.usuarioDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class usuarioService {

    public String addUsuario(usuario user, usuarioDTO usuarioDTO){
        List<usuario> listaUsuarios = usuarioDTO.getListaUsuarios();
        user.setId(listaUsuarios.size()+1);
        listaUsuarios.add(user);
        return "Nuevo Usuario Creado Correctamente";
    }



}
