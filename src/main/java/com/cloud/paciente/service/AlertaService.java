package com.cloud.paciente.service;

import java.util.List;

import com.cloud.paciente.model.Alerta;
import com.cloud.paciente.model.SignosVitales;

public interface AlertaService {
    Alerta evaluarCriticidad(SignosVitales signosVitales);
    Alerta guardarAlerta(Alerta alerta);
    List<Alerta> obtenerAlertasNoAtendidas();
}
