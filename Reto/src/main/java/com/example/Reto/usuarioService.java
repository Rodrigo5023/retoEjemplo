package com.example.Reto;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class usuarioService {

    private List<usuario> listaUsuarios;


    public List<usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public void addUsuario(usuario user){
        user.setId(listaUsuarios.size()+1);
        listaUsuarios.add(user);
    }
}
