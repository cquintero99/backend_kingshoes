package com.sistema.kingshoes.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="usuario")
public class Usuario  implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Integer cedula;
	private String nombre;
	private String apellido;
	
	private String fecha_nacimiento;
	private String correo;
	private String contraseña;
	private String sexo;
	private Integer id_estado;
	private Integer id_rol;
	@Column(name="fecha_registro")
	private String fechaRegistro;
	@JsonIgnore
	@OneToMany(mappedBy = "usuario")
	private List<Carrito> productos;
	@JsonIgnore
	@OneToMany(mappedBy = "usuario_id")
	private List<Pedido>pedidos;
	@JsonIgnore
	@OneToMany(mappedBy = "usuario_id")
	private List<Direccion> direccion;
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", fecha_nacimiento=" + fecha_nacimiento + ", correo=" + correo + ", sexo=" + sexo + ", id_estado="
				+ id_estado + ", id_rol=" + id_rol + ", fechaRegistro=" + fechaRegistro + "]";
	}
	
	
	
	
	
	

}
