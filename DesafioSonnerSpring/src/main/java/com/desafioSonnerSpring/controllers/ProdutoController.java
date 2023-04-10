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
import org.springframework.web.bind.annotation.RestController;

import com.desafioSonnerSpring.entities.Produto;
import com.desafioSonnerSpring.repositories.ProdutoRepository;

@RestController
@RequestMapping(value = "/produtos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProdutoController { 
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping
	public List<Produto> listarProdutos() {
	    return produtoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarProduto(@PathVariable Long id) {
	    Optional<Produto> produtoOptional = produtoRepository.findById(id);

	    if (!produtoOptional.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    Produto produto = produtoOptional.get();
	    return ResponseEntity.ok(produto);
	}
	
	@PostMapping
	public Produto cadastrarProduto(@RequestBody Produto produto) {
	    return produtoRepository.save(produto);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
	    Optional<Produto> produtoOptional = produtoRepository.findById(id);

	    if (!produtoOptional.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    produto.setId(id);
	    produtoRepository.save(produto);
	    return ResponseEntity.ok(produto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
	    produtoRepository.deleteById(id);
	    return ResponseEntity.noContent().build();
	}
}