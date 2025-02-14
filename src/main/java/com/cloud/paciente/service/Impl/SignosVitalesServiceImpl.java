package com.cloud.paciente.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloud.paciente.model.SignosVitales;
import com.cloud.paciente.repository.SignosVitalesRepository;
import com.cloud.paciente.service.SignosVitalesService;

@Service
public class SignosVitalesServiceImpl implements SignosVitalesService{
    
    @Autowired
    private SignosVitalesRepository signosVitalesRepository;

    @Override
    public SignosVitales registrarSignosVitales(SignosVitales signosVitales) {
        // Guardar signos vitales
        SignosVitales guardado = signosVitalesRepository.save(signosVitales);

        return guardado;
    }

    @Override
    public List<SignosVitales> obtenerSignosVitalesDePaciente(Long pacienteId) {
        return signosVitalesRepository.findByPacienteId(pacienteId);
    }

}
