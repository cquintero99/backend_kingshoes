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

import com.sistema.kingshoes.entities.Almacen;
import com.sistema.kingshoes.entities.Talla;
import com.sistema.kingshoes.repository.ITallaRepository;

@RestController
@RequestMapping("/tallas")
@CrossOrigin
public class TallaController {
	@Autowired
	ITallaRepository tallaRepository;
	
	@GetMapping
	public List<Talla> listaTallas(){
		return tallaRepository.findAll();
	}
	@GetMapping("/{id}")
	public Optional<Talla> findTalla(@PathVariable Integer id){
		Optional<Talla> talla=tallaRepository.findById(id);
		if(talla.isPresent()) {
			return talla;
		}
		return null;
	}
	@GetMapping("/{id}/productos")
	public List<Almacen> listaProducto(@PathVariable Integer id){
		Optional<Talla>talla=tallaRepository.findById(id);
		if(talla.isPresent()) {
			return talla.get().getProductos();
		}
		return null;
		
	}
	@PostMapping()
	public Talla postTalla(@RequestBody Talla talla) {
		tallaRepository.save(talla);
		return talla;
	}
	@PutMapping("/{id}")
	public Talla putTalla( @PathVariable Integer id,@RequestBody Talla talla) {
		Optional<Talla> tallaCurrent=tallaRepository.findById(id);
		if(tallaCurrent.isPresent()) {
			Talla tallaReturn =tallaCurrent.get();
			tallaReturn.setNumero(talla.getNumero());
			tallaRepository.save(tallaReturn);
			return tallaReturn;
		}
		return null;
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		tallaRepository.deleteById(id);
	}

}
