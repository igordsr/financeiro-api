package com.sistema.agendamento.financeiroapi.dto;

import com.sistema.agendamento.financeiroapi.entities.Transferencia;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.UUID;

public record TransferenciaDTO(
        UUID id,
        @NotBlank(message = "Conta de Origem não pode estar em Branco.")
        String contaOrigem,
        @NotBlank(message = "Conta de Destino não pode estar em Branco.")
        String contaDestino,
        @NotNull(message = "Valor da Transferência não pode estar ser null.")
        @Positive(message = "Valor da Transferência não pode ser um valor Negativo")
        Double valorDaTranferencia,
        Double taxa,
        @NotNull(message = "Data da Transferência não pode estar ser null.")
        LocalDate dataDaTransferencia,
        LocalDate dataDeAgendamento
) {
    public Transferencia toTransferencia(){
        return new Transferencia(contaOrigem, contaDestino, valorDaTranferencia, dataDaTransferencia);
    }
}
