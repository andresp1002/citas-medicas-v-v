package com.uni.citas_medicas.service;

import com.uni.citas_medicas.model.Cita;
import com.uni.citas_medicas.repository.CitaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CitaServiceTest {

    @Mock
    private CitaRepository repository;

    @InjectMocks
    private CitaService service;

    @Test
    void cuandoGuardaCita_entoncesRetornaCitaGuardada() {
        Cita cita = new Cita();
        cita.setNombrePaciente("Andres Peralta");
        when(repository.save(any(Cita.class))).thenReturn(cita);

        Cita guardada = service.guardar(cita);

        assertNotNull(guardada);
        assertEquals("Andres Peralta", guardada.getNombrePaciente());
        verify(repository, times(1)).save(cita);
    }

    @Test
    void cuandoListaCitas_entoncesRetornaLista() {
        when(repository.findAll()).thenReturn(List.of(new Cita()));
        List<Cita> lista = service.listarTodas();
        assertFalse(lista.isEmpty());
    }

    // --- NUEVOS TESTS PARA COMPLETAR EL 100% DEL CRUD ---

    @Test
    void cuandoEliminaCita_entoncesLlamaRepository() {
        Long id = 1L;
        // Simulamos que el repositorio no hace nada (void) al borrar
        doNothing().when(repository).deleteById(id);

        service.eliminar(id);

        // Verificamos que el repositorio recibió la orden de borrar exactamente 1 vez
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void cuandoActualizaCita_entoncesRetornaCitaActualizada() {
        Long id = 1L;
        Cita citaExistente = new Cita();
        citaExistente.setNombrePaciente("Paciente Antiguo");

        Cita citaNueva = new Cita();
        citaNueva.setNombrePaciente("Paciente Actualizado");

        // Simulamos: 1. Encuentra la cita, 2. Guarda los cambios
        when(repository.findById(id)).thenReturn(Optional.of(citaExistente));
        when(repository.save(any(Cita.class))).thenReturn(citaNueva);

        Cita resultado = service.actualizar(id, citaNueva);

        assertEquals("Paciente Actualizado", resultado.getNombrePaciente());
        verify(repository).save(any(Cita.class));
    }
}