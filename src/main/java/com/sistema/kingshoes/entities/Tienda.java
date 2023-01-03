package com.sistema.kingshoes.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Tienda implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="id_usuario")
	private Integer idUsuario;
	private String nombre;
	private String descripcion;
	@Column(name="fecha_registro")
	private String fechaRegistro;
	@JsonIgnore
	@OneToMany(mappedBy = "tienda")
	private List<Producto>productos;
	@JsonIgnore
	@OneToMany(mappedBy = "tienda_id")
	private List<PedidoAlmacen>pedidos;
	
	
	

}
