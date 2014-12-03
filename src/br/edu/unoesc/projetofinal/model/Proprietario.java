package br.edu.unoesc.projetofinal.model;

public class Proprietario implements Entidade {
	private Integer codigo;
	private String nome;

	public Proprietario() {

	}

	public Proprietario(String nome, Integer codigo) {
		this.codigo = codigo;
		this.nome = nome;
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
}