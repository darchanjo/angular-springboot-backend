package com.candidatos.service.impl;

import java.util.List;

import com.candidatos.exception.BadRequestException;
import com.candidatos.model.Candidato;
import com.candidatos.model.dto.CandidatoDto;
import com.candidatos.repository.CandidatoRepository;
import com.candidatos.service.CandidatoService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.val;

@Service(value = "candidatoService")
public class CandidatoServiceImpl implements CandidatoService {
    @Autowired
    private CandidatoRepository candidatoRepository;

    @Override
    public Candidato create(final CandidatoDto candidato) {
        val newCandidato = new Candidato();
        newCandidato.setNome(candidato.getNome());
        newCandidato.setSobrenome(candidato.getSobrenome());
        newCandidato.setEmail(candidato.getEmail());
        return candidatoRepository.save(newCandidato);
    }

    @Override
    public List<Candidato> findAll() {
        return candidatoRepository.findAll();
    }

    @Override
    public Candidato findById(final Long id) {
        return candidatoRepository.findById(id).orElseThrow(() -> {
            throw new BadRequestException("Not register found :: " + id);
        });
    }

    @Override
    public CandidatoDto update(final CandidatoDto candidatoDto) {
        candidatoRepository.findById(candidatoDto.getId()).ifPresentOrElse(candidato -> {
            BeanUtils.copyProperties(candidatoDto, candidato);
            candidatoRepository.save(candidato);
        }, () -> {
            throw new BadRequestException("Not register found :: " + candidatoDto.getId());
        });
        return candidatoDto;
    }

    @Override
    public void delete(final Long id) {
        candidatoRepository.findById(id).ifPresentOrElse((value) -> {
            candidatoRepository.deleteById(id);
        }, () -> {
            throw new BadRequestException("Not register found :: " + id);
        });
    }
}