package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class MovimentacaoDeLeitao implements Entidade {
	private Integer codigo;
	private Matriz matrizDoadora;
	private Matriz matrizReceptora;
	private Date data;
	private Integer quantidadeLeitao;

	public MovimentacaoDeLeitao() {

	}

	public MovimentacaoDeLeitao(Integer codigo, Matriz matrizDoadora, Matriz matrizReceptora, Date data,
			Integer quantidadeLeitao) {
		this.codigo = codigo;
		this.matrizDoadora = matrizDoadora;
		this.matrizReceptora = matrizReceptora;
		this.data = data;
		this.quantidadeLeitao = quantidadeLeitao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Matriz getMatrizDoadora() {
		return matrizDoadora;
	}

	public void setMatrizDoadora(Matriz matrizDoadora) {
		this.matrizDoadora = matrizDoadora;
	}

	public Matriz getMatrizReceptora() {
		return matrizReceptora;
	}

	public void setMatrizReceptora(Matriz matrizReceptora) {
		this.matrizReceptora = matrizReceptora;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getQuantidadeLeitao() {
		return quantidadeLeitao;
	}

	public void setQuantidadeLeitao(Integer quantidadeLeitao) {
		this.quantidadeLeitao = quantidadeLeitao;
	}

}
