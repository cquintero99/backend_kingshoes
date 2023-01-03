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
public class Marca implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="id_tienda")
	private Integer idTienda;
	private String nombre;
	private String descripcion;
	@JsonIgnore
	@OneToMany(mappedBy = "marca")
	private List<Producto>listaProductos;
	

}
