package com.cloud.paciente.service.Impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.paciente.model.Alerta;
import com.cloud.paciente.model.SignosVitales;
import com.cloud.paciente.repository.AlertaRepository;
import com.cloud.paciente.service.AlertaService;

@Service
public class AlertaServiceImpl implements AlertaService{
    @Autowired
    private AlertaRepository alertaRepository;

    @Override
    public Alerta evaluarCriticidad(SignosVitales signosVitales) {
        String mensaje = null;
        String tipo = null;

        if (signosVitales.getFrecuenciaCardiaca() > 120) {
            mensaje = "Frecuencia cardíaca alta: " + signosVitales.getFrecuenciaCardiaca() + " bpm";
            tipo = "frecuencia cardiaca crítica";
        } else if (signosVitales.getSaturacionOxigeno() < 90) {
            mensaje = "Saturación de oxígeno baja: " + signosVitales.getSaturacionOxigeno() + "%";
            tipo = "hipoxia";
        }

        if (mensaje != null) {
            Alerta alerta = new Alerta();
            alerta.setMensaje(mensaje);
            alerta.setTipo(tipo);
            alerta.setFechaGeneracion(LocalDateTime.now());
            alerta.setAtendida(false);
            alerta.setPaciente(signosVitales.getPaciente());
            alerta.setSignosVitales(signosVitales);
            return alerta;
        }

        return null;
    }

    @Override
    public Alerta guardarAlerta(Alerta alerta) {
        return alertaRepository.save(alerta);
    }

    @Override
    public List<Alerta> obtenerAlertasNoAtendidas() {
        return alertaRepository.findByAtendidaFalse();
    }

    @Override
    public List<Alerta> obtenerAlertasByPacienteID(Long pacienteID){
        return alertaRepository.findByPacienteId(pacienteID);
    }

    @Override
    public Alerta marcarAlertaComoAtendida(Long alertaId) {
        // Buscar la alerta por ID
        Optional<Alerta> alertaOptional = alertaRepository.findById(alertaId);
        if (alertaOptional.isEmpty()) {
            return null;
        }

        // Marcar como atendida
        Alerta alerta = alertaOptional.get();
        alerta.setAtendida(true);

        // Guardar la alerta actualizada
        return alertaRepository.save(alerta);
    }
}
