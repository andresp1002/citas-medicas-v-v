package com.uni.citas_medicas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity // Indica que esto es una tabla en la BD
@Data   // De Lombok: Crea automáticamente Getters, Setters y toString
@Table(name = "citas")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del paciente es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre del paciente debe tener entre 3 y 50 caracteres")
    private String nombrePaciente;

    @NotBlank(message = "El nombre del doctor es obligatorio")
    private String nombreDoctor;

    @NotNull(message = "La fecha de la cita es obligatoria")
    @Future(message = "La cita debe ser en una fecha futura") // Validación de lógica de negocio
    private LocalDateTime fechaCita;

    @NotBlank(message = "El motivo de la consulta es obligatorio")
    @Size(min = 10, max = 250, message = "El motivo debe tener al menos 10 caracteres")
    private String motivo;
}