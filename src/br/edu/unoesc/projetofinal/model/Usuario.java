package br.edu.unoesc.projetofinal.model;

public class Usuario implements AcessaSistema, Entidade {
	private Integer codigo;
	private String nome;
	private String senha;

	public Usuario() {

	}

	public Usuario(String nome, String senha, Integer codigo) {
		this.codigo = codigo;
		this.nome = nome;
		this.senha = senha;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
