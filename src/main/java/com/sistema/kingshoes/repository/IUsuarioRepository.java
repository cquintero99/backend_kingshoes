package com.sistema.kingshoes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistema.kingshoes.entities.Usuario;
@Repository
public interface IUsuarioRepository  extends JpaRepository<Usuario, Integer>{
	Optional<Usuario> findByCorreo(String correo);
	Optional<Usuario> findByCedula(Integer cedula);
	boolean existsByCorreo(String correo);
}
