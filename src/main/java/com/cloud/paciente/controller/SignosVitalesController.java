package com.cloud.paciente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.paciente.model.SignosVitales;
import com.cloud.paciente.service.SignosVitalesService;

@RestController
@CrossOrigin
@RequestMapping("/api/signos-vitales")
public class SignosVitalesController {
    
    @Autowired
    private SignosVitalesService signosVitalesService;

    @PostMapping
    public SignosVitales registrarSignosVitales(@RequestBody SignosVitales signosVitales) {
        return signosVitalesService.registrarSignosVitales(signosVitales);
    }
}
