package com.uni.citas_medicas.service;

import com.uni.citas_medicas.model.Cita;
import com.uni.citas_medicas.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Indica a Spring que esta es la capa de servicio
public class CitaService {

    @Autowired
    private CitaRepository repository;

    public List<Cita> listarTodas() {
        return repository.findAll();
    }

    public Cita guardar(Cita cita) {
        return repository.save(cita);
    }
}