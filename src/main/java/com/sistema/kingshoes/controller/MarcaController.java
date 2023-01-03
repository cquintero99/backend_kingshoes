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

import com.sistema.kingshoes.entities.Marca;
import com.sistema.kingshoes.entities.Producto;
import com.sistema.kingshoes.repository.IMarcaRepository;

@CrossOrigin
@RestController
@RequestMapping("/marca")
public class MarcaController {
	@Autowired
	IMarcaRepository marcaRepository;
	@GetMapping
	public List<Marca> listaMarca(){
		return marcaRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Marca> findMarcaById(@PathVariable Integer id){
		Optional<Marca> marca=marcaRepository.findById(id);
		if(marca.isPresent()) {
			return marca;
		}
		
		return null;
	}
	
	@PostMapping("/save")
	public Marca postMarca(@RequestBody Marca marca ) {
		marcaRepository.save(marca);
		return marca;
		
	}
	@PutMapping("/{id}")
	public Marca putMarcaById(@PathVariable Integer id , @RequestBody Marca marca) {
		Optional<Marca> marcaCurrent=marcaRepository.findById(id);
		if(marcaCurrent.isPresent()) {
				Marca marcaReturn=marcaCurrent.get();
				marcaReturn.setNombre(marca.getNombre());
				marcaReturn.setDescripcion(marca.getDescripcion());
				marcaRepository.save(marcaReturn);
				return marcaReturn;
				
		}
		return null;
	}
	@DeleteMapping
	public Marca deleteMarca(@PathVariable Integer id ) {
		Optional<Marca> marca=marcaRepository.findById(id);
		if(marca.isPresent()) {
			marcaRepository.deleteById(id);
			return marca.get();
		}
		return null;
	}
	@GetMapping("/{id}/productos")
	public List<Producto>listaProductos(@PathVariable Integer id ){
		Optional<Marca>marca=marcaRepository.findById(id);
		if(marca.isPresent()) {
			return marca.get().getListaProductos();
		}
		return null;
		
	}

}
