package br.edu.unoesc.projetofinal.model;

public class Raca implements Entidade {
	private Integer codigo;
	private String nome;

	public Raca() {

	}

	public Raca(Integer codigo, String nome) {
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
