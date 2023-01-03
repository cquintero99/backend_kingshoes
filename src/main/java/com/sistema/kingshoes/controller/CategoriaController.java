package com.sistema.kingshoes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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
import com.sistema.kingshoes.entities.Categoria;
import com.sistema.kingshoes.entities.Producto;
import com.sistema.kingshoes.repository.ICategoriarRepository;

@RestController
@RequestMapping("/categoria")
@CrossOrigin
public class CategoriaController {
	@Autowired
	ICategoriarRepository categoriarRepository;

	@GetMapping
	public List<Categoria> listaCategoria() {
		return categoriarRepository.findAll();
	}

	@GetMapping("/{id}")
	public Optional<Categoria> getCategoriaById(@PathVariable Integer id) {
		Optional<Categoria> categoria = categoriarRepository.findById(id);
		if (categoria.isPresent()) {
			return categoria;
		}
		return null;
	}

	@PostMapping("/save")
	public Categoria postCategoria(@RequestBody Categoria categoria) {
		categoriarRepository.save(categoria);
		return categoria;
	}

	@PutMapping("/{id}")
	public Categoria putCategoria(@RequestBody Categoria categoria, @PathVariable Integer id) {
		Optional<Categoria> categoriaCurren = categoriarRepository.findById(id);
		if (categoriaCurren.isPresent()) {
			Categoria categoriaReturn = categoriaCurren.get();
			categoriaReturn.setDescripcion(categoria.getDescripcion());
			categoriarRepository.save(categoriaReturn);
			return categoriaReturn;
		}
		return null;
	}

	@DeleteMapping("/{id}")
	public Categoria deleteCategoria(@PathVariable Integer id) {
		Optional<Categoria> c = categoriarRepository.findById(id);
		if (c.isPresent()) {
			categoriarRepository.deleteById(id);
			return c.get();
		}
		return null;
	}

	@CrossOrigin(origins = "http://localhost:8080/login")
	@GetMapping("/{descripcion}/productos")
	public List<Producto> getProductosbyDescripcion(@PathVariable String descripcion) {

		Optional<Categoria> categoria = categoriarRepository.findByDescripcion(descripcion);

		if (!categoria.isEmpty()) {
			return categoria.get().getProductos();
		}

		return null;

	}

	@GetMapping("/{descripcion}/productos/{marca}/marca")
	public List<Producto> getProductosMarca(@PathVariable String descripcion, @PathVariable String marca) {
		Optional<Categoria> categoria = categoriarRepository.findByDescripcion(descripcion);
		List<Producto> listaReturn = new ArrayList<>();
		if (categoria.isPresent()) {
			List<Producto> lista = categoria.get().getProductos();
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).getMarca().getNombre().equals(marca)) {
					listaReturn.add(lista.get(i));
				}
			}

			return listaReturn;
		}
		return null;

	}

	@GetMapping("/{descripcion}/productos/{color}/color")
	public List<Producto> getProductosColor(@PathVariable String descripcion, @PathVariable String color) {
		Optional<Categoria> categoria = categoriarRepository.findByDescripcion(descripcion);
		List<Producto> listaReturn = new ArrayList<>();
		if (categoria.isPresent()) {
			List<Producto> lista = categoria.get().getProductos();
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).getColor().equals(color)) {
					listaReturn.add(lista.get(i));
				}
			}

			return listaReturn;
		}
		return null;

	}

	@GetMapping("/{descripcion}/productos/{talla}/talla")
	public List<Producto> listaProductosTalla(@PathVariable String descripcion, @PathVariable Integer talla) {
		Optional<Categoria> categoria = categoriarRepository.findByDescripcion(descripcion);
		List<Producto> productosReturn = new ArrayList<>();
		if (categoria.isPresent()) {
			List<Producto> productos = categoria.get().getProductos();
			for (int i = 0; i < productos.size(); i++) {
				List<Almacen> tallas = productos.get(i).getListaInventario();
				for (int x = 0; x < tallas.size(); x++) {
					if (tallas.get(x).getTalla().getNumero() == talla) {
						productosReturn.add(productos.get(i));
					}
				}

			}
			return productosReturn;

		}
		return null;
	}

	@GetMapping("/{descripcion}/productos/{precio}/precio")
	public List<Producto> listaProductosPrecio(@PathVariable String descripcion, @PathVariable String precio) {
		Optional<Categoria> categoria = categoriarRepository.findByDescripcion(descripcion);
		if (categoria.isPresent()) {
			if (precio.equals("menor")) {
				
				List<Producto> data = categoria.get().getProductos();
				for (int i = 0; i < data.size(); i++) {
					for (int j = i; j < data.size(); j++) {
						if (data.get(i).getPrecio() > data.get(j).getPrecio()) {
							Producto p = data.get(j);
							data.set(j, data.get(i));
							data.set(i, p);
							

						}
					}
				}
				return data;
			}else if(precio.equals("mayor")) {
				List<Producto> data = categoria.get().getProductos();
				for (int i = 0; i < data.size(); i++) {
					for (int j = i; j < data.size(); j++) {
						if (data.get(i).getPrecio() < data.get(j).getPrecio()) {
							Producto p = data.get(j);
							data.set(j, data.get(i));
							data.set(i, p);
							

						}
					}
				}
				return data;
				
			}
		}

		return null;
	}

}
