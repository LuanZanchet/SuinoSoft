package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class MorteLeitaoMaternidade extends Morte {
	private Matriz matriz;
	private Integer quantidade;

	public MorteLeitaoMaternidade() {

	}

	public MorteLeitaoMaternidade(Integer codigo, Date data, Causa causa, Matriz matriz, Integer quantidade) {
		this.setCodigo(codigo);
		this.setData(data);
		this.setCausa(causa);
		this.matriz = matriz;
		this.quantidade = quantidade;
	}

	public Matriz getMatriz() {
		return matriz;
	}

	public void setMatriz(Matriz matriz) {
		this.matriz = matriz;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
