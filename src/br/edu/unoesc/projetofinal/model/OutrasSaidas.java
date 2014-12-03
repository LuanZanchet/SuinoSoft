package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class OutrasSaidas implements Entidade {
	private Integer codigo;
	private Lote lote;
	private String tipoSaida;
	private Date data;
	private Integer quantidade;
	private Double pesoTotal;

	public OutrasSaidas() {

	}

	public OutrasSaidas(Integer codigo, Lote lote, String tipoSaida, Date data, Integer quantidade, Double pesoTotal) {
		this.codigo = codigo;
		this.lote = lote;
		this.tipoSaida = tipoSaida;
		this.data = data;
		this.quantidade = quantidade;
		this.pesoTotal = pesoTotal;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public String getTipoSaida() {
		return tipoSaida;
	}

	public void setTipoSaida(String tipoSaida) {
		this.tipoSaida = tipoSaida;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPesoTotal() {
		return pesoTotal;
	}

	public void setPesoTotal(Double pesoTotal) {
		this.pesoTotal = pesoTotal;
	}

}
