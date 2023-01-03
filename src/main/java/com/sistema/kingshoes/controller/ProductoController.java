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
import com.sistema.kingshoes.entities.Producto;
import com.sistema.kingshoes.repository.IProductoRepository;

@RestController
@RequestMapping("/productos")
@CrossOrigin
public class ProductoController {
	@Autowired
	IProductoRepository productoRepository;
	
	@GetMapping
	public List<Producto> listaProductos(){
		return productoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Producto> findProductoById(@PathVariable Integer id){
		Optional<Producto> producto=productoRepository.findById(id);
		if(producto.isPresent()) {
			return producto;
		}
		return null;
	}
	@PostMapping("/save")
	public Producto postProducto(@RequestBody Producto producto) {
		productoRepository.save(producto);
		return producto;
	}
	@PutMapping("/{id}")
	public Producto putProducto(@PathVariable Integer id,@RequestBody Producto producto) {
		Optional<Producto>productoCurren=productoRepository.findById(id);
		if(productoCurren.isPresent()) {
			Producto productoReturn=productoCurren.get();
			productoReturn.setNombre(producto.getNombre());
			productoReturn.setColor(producto.getColor());
			productoReturn.setPrecio(producto.getPrecio());
			productoReturn.setCategoria(producto.getCategoria());
			productoRepository.save(productoReturn);
			return productoReturn;
		}
		return null;
		
		
		
	}
	@DeleteMapping("/{id}")
	public Producto deleteProducto(@PathVariable Integer id) {
		Optional<Producto>producto=productoRepository.findById(id);
		if(producto.isPresent()) {
			productoRepository.deleteById(id);
			return producto.get();
		}
		return null;
	}
	
	@GetMapping("/{id}/almacen")
	public List<Almacen> listaDeTalla(@PathVariable Integer id){
		Optional<Producto>producto=productoRepository.findById(id);
		if(!producto.isEmpty()) {
			
			return producto.get().getListaInventario();
		}
		return null;
	}
	
	

}
