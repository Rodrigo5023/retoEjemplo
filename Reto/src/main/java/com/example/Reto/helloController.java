package com.example.Reto;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

    // Obtención url_link desde properties
    @Value("${direction.url_link}")
    private String url_link;

    @RequestMapping("/")
    public String paginaPrincipal(){

        return "Inicio del Programa Reto" + "\n" + "URL utilizada: " + url_link;
    }



}
