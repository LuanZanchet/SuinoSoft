package br.edu.unoesc.projetofinal.model;

public class Lote implements Entidade {
	private Integer codigo;
	private Long numero;
	private Integer quantidadeLeitao;
	private Integer idade;
	private String observacao;

	public Lote() {

	}

	public Lote(Integer codigo, Long numero, Integer quantidadeLeitao, Integer idade, String observacao) {
		this.codigo = codigo;
		this.numero = numero;
		this.quantidadeLeitao = quantidadeLeitao;
		this.idade = idade;
		this.observacao = observacao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getQuantidadeLeitao() {
		return quantidadeLeitao;
	}

	public void setQuantidadeLeitao(Integer quantidadeLeitao) {
		this.quantidadeLeitao = quantidadeLeitao;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}