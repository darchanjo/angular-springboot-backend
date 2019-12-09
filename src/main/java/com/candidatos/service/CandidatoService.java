package com.candidatos.service;

import java.util.List;

import com.candidatos.model.Candidato;
import com.candidatos.model.dto.CandidatoDto;

public interface CandidatoService {
    Candidato create(CandidatoDto user);

    List<Candidato> findAll();

    Candidato findById(Long id);

    CandidatoDto update(CandidatoDto userDto);

    void delete(Long id);
}