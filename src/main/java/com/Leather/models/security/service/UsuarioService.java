package com.Leather.models.security.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Leather.models.security.entity.Usuario;
import com.Leather.models.security.repository.UsuarioRepository;

@Service
@Transactional// Coherencia de la BD si falla una operacion vuelve al estado anterior
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
		return usuarioRepository.findByNombreUsuario(nombreUsuario);
	}
	
	public boolean existsByNombreUsuario(String nombreUsuario) {
		return usuarioRepository.existsByNombreUsuario(nombreUsuario);
	}
	
	public boolean existsByCorreo(String correo) {
		return usuarioRepository.existsByCorreo(correo);
	}
	
	// Para guardar
	public void save(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
}
