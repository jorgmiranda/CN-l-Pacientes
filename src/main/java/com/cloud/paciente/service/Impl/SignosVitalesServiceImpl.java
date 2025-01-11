package com.cloud.paciente.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.paciente.model.Alerta;
import com.cloud.paciente.model.Paciente;
import com.cloud.paciente.model.SignosVitales;
import com.cloud.paciente.repository.SignosVitalesRepository;
import com.cloud.paciente.service.AlertaService;
import com.cloud.paciente.service.PacienteService;
import com.cloud.paciente.service.SignosVitalesService;

@Service
public class SignosVitalesServiceImpl implements SignosVitalesService{
    
    @Autowired
    private SignosVitalesRepository signosVitalesRepository;

    @Autowired
    private AlertaService alertaService;

    @Autowired
    private PacienteService pacienteService;

    @Override
    public SignosVitales registrarSignosVitales(SignosVitales signosVitales) {
        // Guardar signos vitales
        SignosVitales guardado = signosVitalesRepository.save(signosVitales);

        // Evaluar criticidad y generar alerta si es necesario
        Alerta alerta = alertaService.evaluarCriticidad(guardado);
        if (alerta != null) {
            alertaService.guardarAlerta(alerta);
        }

        return guardado;
    }

    @Override
    public List<SignosVitales> obtenerSignosVitalesDePaciente(Long pacienteId) {
        return signosVitalesRepository.findByPacienteId(pacienteId);
    }

    
}
