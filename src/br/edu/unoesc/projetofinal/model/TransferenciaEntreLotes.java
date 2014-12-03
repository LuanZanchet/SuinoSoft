package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class TransferenciaEntreLotes implements Entidade {
	private Integer codigo;
	private Lote loteOrigem;
	private Lote loteDestino;
	private Integer quantidade;
	private Date data;
	private Double pesoTotal;

	public TransferenciaEntreLotes() {

	}

	public TransferenciaEntreLotes(Integer codigo, Lote loteOrigem, Lote loteDestino, Integer quantidade, Date data,
			Double pesoTotal) {
		this.codigo = codigo;
		this.loteOrigem = loteOrigem;
		this.loteDestino = loteDestino;
		this.quantidade = quantidade;
		this.data = data;
		this.pesoTotal = pesoTotal;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Lote getLoteOrigem() {
		return loteOrigem;
	}

	public void setLoteOrigem(Lote loteOrigem) {
		this.loteOrigem = loteOrigem;
	}

	public Lote getLoteDestino() {
		return loteDestino;
	}

	public void setLoteDestino(Lote loteDestino) {
		this.loteDestino = loteDestino;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getPesoTotal() {
		return pesoTotal;
	}

	public void setPesoTotal(Double pesoTotal) {
		this.pesoTotal = pesoTotal;
	}
}
