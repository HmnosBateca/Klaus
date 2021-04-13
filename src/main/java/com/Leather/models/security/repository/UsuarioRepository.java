package com.Leather.models.security.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.Leather.models.security.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	// Tener un usuario apartir de nombre usuario
	Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	// Como nombreUsuario y correro son unicos se ponen 2 booleanos para saber si existe por nombreUsuario o por correo
	boolean existsByNombreUsuario(String nombreUsuario);
	boolean existsByCorreo(String correo);
	
	//@Query("select u from usuario u  where u.nombreUsuario = ?1")
	//Usuario UsuarioPorNombreUsuario(String nombreusuario);
}
