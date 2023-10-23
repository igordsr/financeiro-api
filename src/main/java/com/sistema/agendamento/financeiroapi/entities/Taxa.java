package com.sistema.agendamento.financeiroapi.entities;

import com.sistema.agendamento.financeiroapi.dto.TaxaDTO;
import com.sistema.agendamento.financeiroapi.service.TaxaService;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "taxas")
public class Taxa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Min(value = 0, message = "Inicio não pode ser menor que 0")
    private Integer inicio;

    @Column(nullable = false)
    @Min(value = 0, message = "Fim não pode ser menor que 0")
    private Integer fim;

    @Column(nullable = false)
    @Min(value = 0, message = "Valor não pode ser menor que 0")
    private Double valor;

    @Column(nullable = false)
    @Min(value = 0, message = "Valor não pode ser menor que 0")
    private Double aliquota;

    public Taxa(Integer inicio, Integer fim, Double valor, Double aliquota) {
        this.inicio = inicio;
        this.fim = fim;
        this.valor = valor;
        this.aliquota = aliquota;
    }

    public TaxaDTO toDTO(){
        return new TaxaDTO(this.inicio, this.fim, this.valor, this.aliquota);
    }

    public static Double calcularTaxa(long quantidadeDeDias, Double valorDaTranferencia){
        TaxaDTO taxaDTO = new TaxaService().findAll().stream().filter(obj -> obj.inicio() >= quantidadeDeDias && obj.fim() <= quantidadeDeDias).findFirst().get();
        Double valorDaTaxa = (valorDaTranferencia * taxaDTO.aliquota()) / 100;
        if(valorDaTaxa < taxaDTO.valor()){
            valorDaTaxa = taxaDTO.valor();
        }
        return valorDaTaxa;
    }
}
