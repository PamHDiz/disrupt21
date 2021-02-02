package br.com.fiap.loja.domain;

import br.com.fiap.loja.endereco.Endereco;

public class Cliente {
	
	private long id;
	private String cpf;
	private String nome;
	private String email;
	private String telefone;
	private Endereco endereco;
	
	
	public Cliente(long id, String nome, String cpf, String email, 
			String telefone, Endereco endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	
}
