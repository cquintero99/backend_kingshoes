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
import com.sistema.kingshoes.entities.PedidoAlmacen;
import com.sistema.kingshoes.entities.Producto;
import com.sistema.kingshoes.entities.Tienda;
import com.sistema.kingshoes.repository.ITiendaRepository;

@RestController
@RequestMapping("/tiendas")
@CrossOrigin
public class TiendaController {
	@Autowired
	ITiendaRepository tiendaRepository;
	
	@GetMapping
	public List<Tienda>listaTiendas(){
		List<Tienda>tiendas=tiendaRepository.findAll();
		return tiendas;
		
	}
	
	@GetMapping("/{id}")
	public Optional<Tienda> findTiendaById(@PathVariable Integer id) {
		Optional<Tienda>tienda=tiendaRepository.findById(id);
		if(tienda.isPresent()) {
			return tienda;
		}
		return null;
	}
	@GetMapping("/usuario/{id}")
	public Optional<Tienda> findTiendaIdUsuario(@PathVariable Integer id){
		Optional<Tienda>tienda=tiendaRepository.findByIdUsuario(id);
		if(tienda.isPresent()) {
			return tienda;
		}
		return null;
		
	}
	@GetMapping("/{id}/productos")
	public List<Producto> listaProductoTienda(@PathVariable Integer id){
		Optional<Tienda>tienda=tiendaRepository.findById(id);
		if(tienda.isPresent()) {
			return tienda.get().getProductos();
		}
		return null;
	}
	@GetMapping("/{id}/pedidos")
	public List<PedidoAlmacen>pedidos(@PathVariable Integer id){
		Optional<Tienda>tienda=tiendaRepository.findById(id);
		if(tienda.isPresent()) {
			return tienda.get().getPedidos();
			
		}
		return null;
	}
	
	
	
	@PostMapping("/save")
	public Tienda postTienda(@RequestBody Tienda tienda) {
		tiendaRepository.save(tienda);
		return tienda;
	}
	@PutMapping("/update/{id}")
	public Tienda putTienda(@RequestBody Tienda tienda,@PathVariable Integer id) {
		Optional<Tienda>tiendaCurrent=tiendaRepository.findById(id);
		if(tiendaCurrent.isPresent()) {
			Tienda tiendaReturn=tiendaCurrent.get();
			tiendaReturn.setNombre(tienda.getNombre());
			tiendaReturn.setDescripcion(tienda.getDescripcion());
			
			tiendaRepository.save(tiendaReturn);
			return tiendaReturn;
		}
		return null;
	}
	@DeleteMapping("/{id}")
	public void deleteTienda(@PathVariable Integer id) {
		tiendaRepository.deleteById(id);
		
	}
}
