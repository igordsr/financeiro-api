package com.sistema.agendamento.financeiroapi.repository;

import com.sistema.agendamento.financeiroapi.entities.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, UUID> {
}
