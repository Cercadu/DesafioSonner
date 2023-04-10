package com.desafioSonnerSpring.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafioSonnerSpring.entities.Usuario;
import com.desafioSonnerSpring.repositories.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuario")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController { 
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public List<Usuario> listarUsuarios() {
	    return usuarioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id) {
		
	    Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

	    if (!usuarioOptional.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    Usuario usuario= usuarioOptional.get();
	    return ResponseEntity.ok(usuario);
	}	
	
	@GetMapping(params = {"email", "senha"})
	public ResponseEntity<Usuario> validarUsuario(@RequestParam String email, @RequestParam String senha) {
	    Usuario usuario = usuarioRepository.findByEmail(email);
	    if (usuario == null || !usuario.getSenha().equals(senha)) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok(usuario);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
		Usuario usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		if(usuarioExistente != null) {
			return ResponseEntity.badRequest().build();
		}
	    Usuario usuarioCriado = usuarioRepository.save(usuario);
	    return ResponseEntity.ok(usuarioCriado);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
	    Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

	    if (!usuarioOptional.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    usuario.setId(id);
	    usuarioRepository.save(usuario);
	    return ResponseEntity.ok(usuario);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
	    usuarioRepository.deleteById(id);
	    return ResponseEntity.noContent().build();
	}
	

}
