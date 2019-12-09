package com.candidatos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.candidatos.model.Candidato;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
}
