package com.sistema.kingshoes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.kingshoes.entities.Direccion;
import com.sistema.kingshoes.repository.IDireccionRepository;

@RestController
@RequestMapping("/direccion")
@CrossOrigin
public class DireccionController {

		@Autowired
		IDireccionRepository direccionRepository;
		
		@GetMapping
		public List<Direccion>lista(){
			return direccionRepository.findAll();
		}
		@GetMapping("/{id}")
		public Optional<Direccion>direccion(@PathVariable Integer id){
			Optional<Direccion>direccion=direccionRepository.findById(id);
			if(direccion.isPresent()) {
				return direccion;
			}
			return null;
		}
		@PostMapping("/save")
		public Direccion postDireccion(@RequestBody Direccion direccion) {
			direccionRepository.save(direccion);
			return direccion;
		}
		
		@PutMapping("/{id}")
		public Direccion putDireccion(@PathVariable Integer id ,@RequestBody Direccion direccion) {
			Optional<Direccion >direccionCurrent=direccionRepository.findById(id);
			if(direccionCurrent.isPresent()) {
				Direccion direccionReturn=direccionCurrent.get();
				direccionReturn.setNombre(direccion.getNombre());
				direccionReturn.setApellido(direccion.getApellido());
				direccionReturn.setDireccion(direccion.getDireccion());
				direccionReturn.setComplemento(direccion.getComplemento());
				direccionReturn.setBarrio(direccion.getBarrio());
				direccionReturn.setMunicipio(direccion.getMunicipio());
				direccionReturn.setTelefono(direccion.getTelefono());
				direccionRepository.save(direccionReturn);
				return direccionReturn;
			}
			return null;
		}
		@DeleteMapping("/{id}")
		public Direccion delete(@PathVariable Integer id) {
			Optional<Direccion>direccion=direccionRepository.findById(id);
			if(direccion.isPresent()) {
				direccionRepository.deleteById(id);
				return direccion.get();
			}
			return null;
		}
}
