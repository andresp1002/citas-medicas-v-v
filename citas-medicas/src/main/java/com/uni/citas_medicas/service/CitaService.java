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

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Cita actualizar(Long id, Cita citaDetalles) {
        Cita cita = repository.findById(id).orElseThrow();
        cita.setNombrePaciente(citaDetalles.getNombrePaciente());
        cita.setNombreDoctor(citaDetalles.getNombreDoctor());
        cita.setFechaCita(citaDetalles.getFechaCita());
        cita.setMotivo(citaDetalles.getMotivo());
        return repository.save(cita);
    }
}