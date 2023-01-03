package com.sistema.kingshoes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.kingshoes.entities.Departamento;
import com.sistema.kingshoes.entities.Municipio;
import com.sistema.kingshoes.repository.IDepartamentoRepository;

@RestController
@RequestMapping("/departamentos")
@CrossOrigin
public class DepartamentoController {
	@Autowired
	IDepartamentoRepository departamentoRepository;
	
	@GetMapping
	public List<Departamento>listaDepartamentos(){
		return departamentoRepository.findAll();
	}
	@GetMapping("/{id}")
	public Optional<Departamento> findByIdDepartamento(@PathVariable Integer id){
		Optional<Departamento>departamento=departamentoRepository.findById(id);
		if(departamento.isPresent()) {
			return departamento;
		}
		return null;
	}
	@GetMapping("/{id}/municipios")
	public List<Municipio>municipios(@PathVariable Integer id){
		Optional<Departamento>departamento=departamentoRepository.findById(id);
		if(departamento.isPresent()) {
			return departamento.get().getMunicipios();
		}
		return null;
	}
	
	

}
