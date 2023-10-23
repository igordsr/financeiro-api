package com.sistema.agendamento.financeiroapi.service;

import com.sistema.agendamento.financeiroapi.controller.exception.ControllerNotFoundException;
import com.sistema.agendamento.financeiroapi.dto.TransferenciaDTO;
import com.sistema.agendamento.financeiroapi.entities.Transferencia;
import com.sistema.agendamento.financeiroapi.repository.TransferenciaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
public class TransferenciaService {
    @Autowired
    private TransferenciaRepository transferenciaRepository;
    @Autowired
    private TaxaService taxaService;

    public List<TransferenciaDTO> findAll() {
        return this.transferenciaRepository.findAll().stream().map(Transferencia::toDTO).toList();
    }

    public TransferenciaDTO save(TransferenciaDTO transferenciaDTO) {
        final Transferencia transferencia = transferenciaDTO.toTransferencia();
        this.taxaService.calcularTaxa(transferencia);
        return this.transferenciaRepository.save(transferencia).toDTO();
    }

    public TransferenciaDTO update(UUID id, TransferenciaDTO transferenciaDTO) {
        try {
            final Transferencia transferencia = this.transferenciaRepository.getReferenceById(id);
            transferencia.setContaOrigem(transferenciaDTO.contaOrigem());
            transferencia.setContaDestino(transferenciaDTO.contaDestino());
            transferencia.setValorDaTranferencia(transferenciaDTO.valorDaTranferencia());
            transferencia.setDataDaTransferencia(transferenciaDTO.dataDaTransferencia());
            this.taxaService.calcularTaxa(transferencia);
            transferenciaDTO = this.transferenciaRepository.saveAndFlush(transferencia).toDTO();
        }
        catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Transferencia não Encontrada");
        }
        return transferenciaDTO;
    }

    public void delete(UUID id) {
        this.transferenciaRepository.deleteById(id);
    }

    public TransferenciaDTO findById(UUID id) {
        try {
            final Transferencia transferencia = this.transferenciaRepository.getReferenceById(id);
            return transferencia.toDTO();
        }
        catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Transferencia não Encontrada");
        }
    }
}
