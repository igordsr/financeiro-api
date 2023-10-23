package com.sistema.agendamento.financeiroapi.controller;

import com.sistema.agendamento.financeiroapi.dto.TaxaDTO;
import com.sistema.agendamento.financeiroapi.service.TaxaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/taxa")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaxaController {
    @Autowired
    private TaxaService taxaService;
    @GetMapping
    public ResponseEntity<List<TaxaDTO>> findAll() {
        List<TaxaDTO> taxas = this.taxaService.findAll();
        return ResponseEntity.ok(taxas);
    }
}
