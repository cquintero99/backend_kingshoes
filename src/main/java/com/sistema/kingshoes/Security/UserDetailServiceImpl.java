package com.sistema.kingshoes.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sistema.kingshoes.entities.Usuario;
import com.sistema.kingshoes.repository.IUsuarioRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	IUsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario=usuarioRepository.findByCorreo(email)
				.orElseThrow(()-> new UsernameNotFoundException("El usuario con email"+email+" no existe"));
		
		return new UserDetailsImpl(usuario);
	}
	

}
