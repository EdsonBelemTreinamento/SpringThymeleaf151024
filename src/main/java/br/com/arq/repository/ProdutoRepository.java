package br.com.arq.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.arq.model.Produto;


public interface ProdutoRepository extends MongoRepository<Produto,String>{

	 public Produto findByNome(String nome);
}
