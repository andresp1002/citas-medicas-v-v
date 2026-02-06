package com.uni.citas_medicas.service;

import com.uni.citas_medicas.model.Cita;
import com.uni.citas_medicas.repository.CitaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.List;

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
    public void cuandoGuardaCita_entoncesRetornaCitaGuardada() {
        // Arrange (Preparar datos)
        Cita cita = new Cita();
        cita.setNombrePaciente("Andres Peralta");
        cita.setNombreDoctor("Dr. House");
        cita.setFechaCita(LocalDateTime.now().plusDays(1));
        cita.setMotivo("Consulta General");

        // Simulamos que el repositorio guarda cualquier cita y devuelve la misma cita
        when(repository.save(any(Cita.class))).thenReturn(cita);

        // Act (Ejecutar)
        Cita guardada = service.guardar(cita);

        // Assert (Verificar)
        assertNotNull(guardada, "La cita guardada no debería ser nula");
        assertEquals("Andres Peralta", guardada.getNombrePaciente());

        // Verificamos que el método save del repositorio se llamó exactamente 1 vez
        verify(repository, times(1)).save(cita);
    }

    @Test
    public void cuandoListaCitas_entoncesRetornaLista() {
        // Simulamos que la base de datos devuelve una lista con 1 cita
        when(repository.findAll()).thenReturn(List.of(new Cita()));

        List<Cita> lista = service.listarTodas();

        assertFalse(lista.isEmpty(), "La lista no debería estar vacía");
    }
}