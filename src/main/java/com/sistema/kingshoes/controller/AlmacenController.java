package com.sistema.kingshoes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.kingshoes.entities.Almacen;
import com.sistema.kingshoes.entities.Producto;
import com.sistema.kingshoes.repository.IAlmacenRepository;

@RestController
@RequestMapping("/almacen")
@CrossOrigin
public class AlmacenController {
	@Autowired
	IAlmacenRepository almacenRepository;
	
	@GetMapping
	public List<Almacen> listarInventario(){
		return almacenRepository.findAll();
		
	}
	@GetMapping("/{id}")
	public Optional<Almacen> findAlmacenById(@PathVariable Integer id){
		Optional<Almacen> almacen=almacenRepository.findById(id);
		if(almacen.isPresent()) {
			return almacen;
		}
		return null;
	}
	
	@PostMapping("/save")
	public Almacen postAlmacen(@RequestBody Almacen almacen) {
		almacenRepository.save(almacen);
		return almacen;
	}
	@PostMapping("/{id}")
	public Almacen putAlmacen(@PathVariable Integer id ,@RequestBody Almacen almacen) {
		Optional<Almacen>almacenCurren=almacenRepository.findById(id);
		if(almacenCurren.isPresent()) {
			Almacen almacenReturn =almacenCurren.get();
			almacenReturn.setStock(almacen.getStock());
		}
		return null;
	}
	@DeleteMapping("/id")
	public Almacen deleteAlmacen(@PathVariable Integer id) {
		Optional<Almacen>almacen=almacenRepository.findById(id);
		if(almacen.isPresent()) {
			almacenRepository.deleteById(id);
			return almacen.get();
		}
		return null;
		
	}
	

}
