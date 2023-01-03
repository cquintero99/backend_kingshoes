package com.sistema.kingshoes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.kingshoes.entities.Municipio;
import com.sistema.kingshoes.repository.IMunicipioRepository;

@RestController
@RequestMapping("/municipio")
@CrossOrigin
public class MunicipioController {
	@Autowired
	IMunicipioRepository municipioRepository;
	@GetMapping
	public List<Municipio>lista(){
		return municipioRepository.findAll();
	}
	@GetMapping("/{id}")
	public Optional<Municipio>findByIdMunicipio(@PathVariable Integer id){
		Optional<Municipio>munipio=municipioRepository.findById(id);
		if(munipio.isPresent()) {
			return munipio;
			
		}
		return null;
	}
	
	
	
}
