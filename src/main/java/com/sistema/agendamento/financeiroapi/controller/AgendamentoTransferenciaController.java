package com.sistema.agendamento.financeiroapi.controller;

import com.sistema.agendamento.financeiroapi.dto.TransferenciaDTO;
import com.sistema.agendamento.financeiroapi.service.TransferenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agendamento-transferencia")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AgendamentoTransferenciaController {
    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping
    public ResponseEntity<List<TransferenciaDTO>> findAll() {
        final List<TransferenciaDTO> transferencias = this.transferenciaService.findAll();
        return ResponseEntity.ok(transferencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransferenciaDTO> findById(@PathVariable UUID id) {
        final TransferenciaDTO transferencia = this.transferenciaService.findById(id);
        return ResponseEntity.ok(transferencia);
    }

    @PostMapping
    public ResponseEntity<TransferenciaDTO> save(@RequestBody @Valid TransferenciaDTO transferenciaDTO) {
        transferenciaDTO = this.transferenciaService.save(transferenciaDTO);
        return new ResponseEntity<>(transferenciaDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransferenciaDTO> update(@PathVariable UUID id, @RequestBody @Valid TransferenciaDTO transferenciaDTO) {
        TransferenciaDTO transferenciaDTOUpdated = this.transferenciaService.update(id, transferenciaDTO);
        return ResponseEntity.ok(transferenciaDTOUpdated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        this.transferenciaService.delete(id);
    }
}
