package com.sistema.agendamento.financeiroapi.repository;

import com.sistema.agendamento.financeiroapi.entities.Taxa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxaRepository extends JpaRepository<Taxa, Long> {

}
