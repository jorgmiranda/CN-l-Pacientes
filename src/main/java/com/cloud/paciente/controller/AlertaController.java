package com.cloud.paciente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.paciente.model.Alerta;
import com.cloud.paciente.service.AlertaService;

@RestController
@CrossOrigin
@RequestMapping("/api/alertas")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @GetMapping("/no-atendidas")
    public List<Alerta> obtenerAlertasNoAtendidas() {
        return alertaService.obtenerAlertasNoAtendidas();
    }

}
