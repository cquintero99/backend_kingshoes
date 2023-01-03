package com.sistema.kingshoes.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
@Entity

public class Producto implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="id_tienda")
	private Tienda tienda;
	private String nombre;
	private String color;
	private Integer precio;
	@ManyToOne
	@JoinColumn(name="id_marca")
	private Marca marca;
	@ManyToOne
	@JoinColumn(name="id_categoria")
	private Categoria categoria;
	@JsonIgnore
	@OneToMany(mappedBy = "producto")
	private List<Almacen>listaInventario;
}
