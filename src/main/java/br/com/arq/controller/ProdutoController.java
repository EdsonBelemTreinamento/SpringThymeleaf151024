package br.com.arq.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.arq.model.Produto;
import br.com.arq.repository.ProdutoRepository;

 

@Controller
public class ProdutoController {

	 @Autowired
	 private ProdutoRepository repository;
	
	@GetMapping("/")
	public String hello() {
		return "novo";
	}
	
	@GetMapping("/cadastro")
	public String cadastroProduto(Model model) {
		 model.addAttribute("produto",new Produto());
		 model.addAttribute("message","Cadastro de Produto");
		return "produto-form";
	}
	
	
	@PostMapping("/cadastrar")
	public String cadastrar(@ModelAttribute("produto") Produto produto,BindResult result, Model model) {
	   try {
		   Produto resp = repository.save(produto);
		   
		   if(resp ==null) {
			    throw new IllegalArgumentException("Produto nao gravado, nome duplicado");
		   }
		   model.addAttribute("produto", new Produto());
		   model.addAttribute("messages","Dados do Produto gravados");
		   return "produto-form";
	   }catch(Exception ex) {
		   model.addAttribute("messages", ex.getMessage());
		   return  "produto-form";
	   }
	}
		
 
	@GetMapping("/listagem")
	public String listagemProduto(Model model) {
		 List<Produto> produtos= repository.findAll();
		 model.addAttribute("produtos",produtos);
		 model.addAttribute("message","Listagem de Produtos");
		return "produto-lista";
	}
	
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") String id,Model model) {
		 Optional<Produto> produto = repository.findById(id);
		 
		 if (produto.isPresent()) {
			 repository.deleteById(id);
		 }
		 model.addAttribute("produtos", repository.findAll());
		 model.addAttribute("message","Listagem de Produtos");
		return "produto-lista";
	}
	
	
}
