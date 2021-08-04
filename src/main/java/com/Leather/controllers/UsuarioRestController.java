/*package com.Leather.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Leather.models.bean.AuthenticationBean;

@CrossOrigin(origins = { "http://localhost:4200", "*"  })
@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {
	
    @GetMapping(path = "/autenticacion")
    public AuthenticationBean basicauth() {
        return new AuthenticationBean("Autenticada");
    }
	
}
*/