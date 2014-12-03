package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class MorteLeitaoCreche extends Morte {
	private Lote lote;
	private Integer quantidade;

	public MorteLeitaoCreche() {

	}

	public MorteLeitaoCreche(Integer codigo, Date data, Causa causa, Lote lote, Integer quantidade) {
		this.setCodigo(codigo);
		this.setData(data);
		this.setCausa(causa);
		this.lote = lote;
		this.quantidade = quantidade;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
