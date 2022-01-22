package controller;

import model.usuario;
import model.usuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import service.usuarioService;

import java.util.List;

@RestController
public class retoController {



    // Obtención url_link desde properties
    @Value("${direction.url_link}")
    private String url_link;

    // Declaración restTemplate
    private final RestTemplate restTemplate;
    @Autowired
    public retoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //GET - lista de usuarios recuperada - cambio de formato
    usuarioDTO listaUsuarios = new usuarioDTO();
    @GetMapping(value="usuarios")
    public String getUsuarios(){
        String url = "url_link";
        listaUsuarios = restTemplate.getForObject(url, usuarioDTO.class);
        List<usuario> lista = listaUsuarios.getListaUsuarios();

        String respuesta = "data: [";
        for (int index = 0; index<lista.size(); index++){
            usuario user = lista.get(index);
            String complementoString = "<" + user.getId() + ">|<" + user.getLast_name() +
                    ">|<" + user.getEmail() + ">, ";
            respuesta = respuesta + complementoString;
        }
        return respuesta;
    }

    //POST - agregar usuarios a /usuariosJson - derivar a /usuarios
    usuarioService usuarioService = new usuarioService();
    @RequestMapping(method = RequestMethod.POST, value ="usuarios")
    public void crearUsuario (@RequestBody usuario usuarioNuevo){
        usuarioService.addUsuario(usuarioNuevo, (usuarioDTO) listaUsuarios.getListaUsuarios());
    }

}
