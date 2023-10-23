package com.sistema.agendamento.financeiroapi.entities;

import com.sistema.agendamento.financeiroapi.dto.TransferenciaDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "transferencias")
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Setter
    @Column(nullable = false, name = "conta_origem")
    private String contaOrigem;
    @Setter
    @Column(nullable = false, name = "conta_destino")
    private String contaDestino;
    @Setter
    @Column(nullable = false, name = "valor_da_tranferencia")
    @Min(value = 0, message = "Valor não pode ser menor que 0")
    private Double valorDaTranferencia;

    @Column(nullable = false)
    @Min(value = 0, message = "Valor não pode ser menor que 0")
    private Double taxa;
    @Setter
    @Column(nullable = false, name = "data_da_transferencia")
    private LocalDate dataDaTransferencia;

    @Column(nullable = false, name = "data_da_agendamento")
    private LocalDate dataDeAgendamento;

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }

    public Transferencia(String contaOrigem, String contaDestino, Double valorDaTranferencia, LocalDate dataDaTransferencia) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valorDaTranferencia = valorDaTranferencia;
        this.dataDaTransferencia = dataDaTransferencia;
        this.dataDeAgendamento = LocalDate.now();
    }

    public TransferenciaDTO toDTO(){
        return new TransferenciaDTO(id, contaOrigem, contaDestino, valorDaTranferencia, taxa, dataDaTransferencia, dataDeAgendamento);
    }

    @Override
    public String toString() {
        return "Transferencia{" +
                "id=" + id +
                ", contaOrigem='" + contaOrigem + '\'' +
                ", contaDestino='" + contaDestino + '\'' +
                ", valorDaTranferencia=" + valorDaTranferencia +
                ", taxa=" + taxa +
                ", dataDaTransferencia=" + dataDaTransferencia +
                ", dataDeAgendamento=" + dataDeAgendamento +
                '}';
    }
}
