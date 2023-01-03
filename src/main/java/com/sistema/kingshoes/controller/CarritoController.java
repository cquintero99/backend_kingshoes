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

import com.sistema.kingshoes.entities.Carrito;
import com.sistema.kingshoes.entities.CarritoProducto;
import com.sistema.kingshoes.entities.Producto;
import com.sistema.kingshoes.repository.ICarritoRepository;

@RestController
@CrossOrigin
@RequestMapping("/carrito")
public class CarritoController {
	@Autowired
	ICarritoRepository carritoRepository;
	
	@GetMapping
	public List<Carrito> listaCarritos(){
		return carritoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Carrito findCarritoById(@PathVariable Integer id) {
		Optional<Carrito>carrito=carritoRepository.findById(id);
		if(carrito.isPresent()) {
			return carrito.get();
		}
		return null;
		
	}
	@GetMapping("/{id}/productos")
	public List<CarritoProducto> productoCarrito(@PathVariable Integer id){
		Optional<Carrito> carrito=carritoRepository.findById(id);
		if(carrito.isPresent()) {
			return carrito.get().getProductos();
		}
		return null;
	}
	
	@PostMapping("/save")
	public Carrito postCarrito(@RequestBody Carrito carrito) {
		carritoRepository.save(carrito);
		return carrito;
	}
	
	@PutMapping("/{id}")
	public Carrito putCarrito(@PathVariable Integer id ,@RequestBody Carrito carrito) {
		Optional<Carrito >carritoCurren=carritoRepository.findById(id);
		if(carritoCurren.isPresent()) {
			Carrito carritoReturn=carritoCurren.get();
			carritoReturn.setTotal(carrito.getTotal());
			carritoRepository.save(carritoReturn);
			return carritoReturn;
		}
		return null;
		
	}
	@DeleteMapping("/{id}")
	public Carrito deleteCarrito(@PathVariable Integer id) {
		Optional<Carrito>carrito=carritoRepository.findById(id);
		if(carrito.isPresent()) {
			carritoRepository.deleteById(id);
			return carrito.get();
		}
		return null;
		
		
	}
	/*
	@GetMapping("/{id}/productos")
	public List<CarritoProducto> listaProductos(@PathVariable Integer id){
		Optional<Carrito>carrito=carritoRepository.findById(id);
		if(carrito.isPresent()) {
			return carrito.get().getProductos();
		}
		return null;
		
	}
	*/

}
