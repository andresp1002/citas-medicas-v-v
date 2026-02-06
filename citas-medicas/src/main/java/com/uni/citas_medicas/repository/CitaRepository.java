package com.uni.citas_medicas.repository;

// Corregimos el import para que coincida con el nombre de tu paquete
import com.uni.citas_medicas.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    // Aquí ya tienes todas las operaciones CRUD básicas
}