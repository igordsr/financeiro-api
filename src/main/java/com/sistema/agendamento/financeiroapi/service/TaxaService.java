package com.sistema.agendamento.financeiroapi.service;

import com.sistema.agendamento.financeiroapi.controller.exception.ControllerNotFoundException;
import com.sistema.agendamento.financeiroapi.dto.TaxaDTO;
import com.sistema.agendamento.financeiroapi.entities.Taxa;
import com.sistema.agendamento.financeiroapi.entities.Transferencia;
import com.sistema.agendamento.financeiroapi.repository.TaxaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;

@Service
public class TaxaService {
    private static int count = 0;
    @Autowired
    private TaxaRepository taxaRepository;

    public List<TaxaDTO> findAll(){
        return this.taxaRepository.findAll().stream().map(Taxa::toDTO).toList();
    }

    public void calcularTaxa(Transferencia transferencia) throws ControllerNotFoundException{
        List<TaxaDTO> taxas = this.findAll();
        final long quantidadeDeDias = Duration.between(transferencia.getDataDeAgendamento().atStartOfDay(), transferencia.getDataDaTransferencia().atStartOfDay()).toDays();
        TaxaDTO taxaDTO = taxas.stream().filter(obj -> quantidadeDeDias >= obj.inicio() && quantidadeDeDias <= obj.fim()).findFirst().orElseThrow(() -> new ControllerNotFoundException("Operação Cancelada por não haver taxas aplicaveis para esse agendamento"));

        double valorDaTaxa = (transferencia.getValorDaTranferencia() * taxaDTO.aliquota()) / 100;
        valorDaTaxa = new BigDecimal(valorDaTaxa).setScale(2, RoundingMode.HALF_UP).doubleValue();
        if(valorDaTaxa < taxaDTO.valor()){
            valorDaTaxa = taxaDTO.valor();
        }
        transferencia.setTaxa(valorDaTaxa);
    }
}
