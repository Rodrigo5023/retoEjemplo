package service;

import model.usuario;
import model.usuarioDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class usuarioServiceTest {

    // Test jUnit
    @Autowired
    usuarioService usuarioService;

    @Test
    public void usuarioAgregado(){
        usuario user1 = new usuario(1,"Juan","Perez","gmail","avatar1");
        usuario user2 = new usuario(2,"Jose","Ugarte", "hotmail" , "avatar2");
        usuarioDTO usuarioDTO = new usuarioDTO();
        usuarioDTO.getListaUsuarios().add(user1);
        usuarioDTO.getListaUsuarios().add(user2);
        usuario user3 = new usuario(6,"Pedro","Ramirez", "amazon" , "avatar6");
        Assert.assertEquals("Nuevo Usuario Creado Correctamente", usuarioService.addUsuario(user3,usuarioDTO));

    }

}