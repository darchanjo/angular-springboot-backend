package com.candidatos.service.impl;

import java.util.List;

import com.candidatos.model.Candidato;
import com.candidatos.model.dto.CandidatoDto;
import com.candidatos.repository.CandidatoRepository;
import com.candidatos.service.CandidatoService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "candidatoService")
public class CandidatoServiceImpl implements CandidatoService {
    @Autowired
    private CandidatoRepository candidatoRepository;

    @Override
    public Candidato create(CandidatoDto candidato) {
        Candidato newCandidato = new Candidato();
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
    public Candidato findById(Long id) {
        return candidatoRepository.findById(id)
            .orElse(null);
    }

    @Override
    public CandidatoDto update(CandidatoDto candidatoDto) {
        candidatoRepository.findById(candidatoDto.getId())
            .ifPresent(candidato -> {
                BeanUtils.copyProperties(candidatoDto, candidato);
                candidatoRepository.save(candidato);
            });
        return candidatoDto;
    }

    @Override
    public void delete(Long id) {
        candidatoRepository.deleteById(id);
    }
}