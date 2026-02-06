package com.uni.citas_medicas.controller;

import com.uni.citas_medicas.model.Cita;
import com.uni.citas_medicas.service.CitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas") // Esta ruta DEBE coincidir con Postman
public class CitaController {

    @Autowired
    private CitaService service;

    @GetMapping
    public List<Cita> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public ResponseEntity<Cita> crear(@Valid @RequestBody Cita cita) {
        // Retorna 201 Created según pide la rúbrica [cite: 14]
        return new ResponseEntity<>(service.guardar(cita), HttpStatus.CREATED);
    }
}