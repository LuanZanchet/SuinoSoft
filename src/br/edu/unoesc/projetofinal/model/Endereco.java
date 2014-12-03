package br.edu.unoesc.projetofinal.model;

public class Endereco implements Entidade {
	private Integer codigo;
	private String cidade;
	private String uf;

	public Endereco() {

	}

	public Endereco(Integer codigo, String cidade, String uf) {
		this.codigo = codigo;
		this.cidade = cidade;
		this.uf = uf;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
