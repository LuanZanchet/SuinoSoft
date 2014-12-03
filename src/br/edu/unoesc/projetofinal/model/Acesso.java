package br.edu.unoesc.projetofinal.model;

public class Acesso implements Entidade {
	private Integer codigo;
	private Integer quantidadeAcesso;

	public Acesso() {

	}

	public Acesso(Integer codigo, Integer quantidadeAcesso) {
		this.codigo = codigo;
		this.quantidadeAcesso = quantidadeAcesso;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getQuantidadeAcesso() {
		return quantidadeAcesso;
	}

	public void setQuantidadeAcesso(Integer quantidadeAcesso) {
		this.quantidadeAcesso = quantidadeAcesso;
	}

}
