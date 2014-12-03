package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class Desmame implements Entidade {
	private Integer codigo;
	private Matriz matriz;
	private Date data;
	private Date previsaoDesmame;
	private Integer quantidade;
	private Double pesoTotal;
	private Double pesoMedio;
	private Lote lote;
	private String obsDesmame;
	private String obsLote;

	public Desmame() {

	}

	public Desmame(Integer codigo, Matriz matriz, Date data, Date previsaoDesmame, Integer quantidade,
			Double pesoTotal, Double pesoMedio, Lote lote, String obsDesmame, String obsLote) {
		this.codigo = codigo;
		this.matriz = matriz;
		this.data = data;
		this.previsaoDesmame = previsaoDesmame;
		this.quantidade = quantidade;
		this.pesoTotal = pesoTotal;
		this.pesoMedio = pesoMedio;
		this.lote = lote;
		this.obsDesmame = obsDesmame;
		this.obsLote = obsLote;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Matriz getMatriz() {
		return matriz;
	}

	public void setMatriz(Matriz matriz) {
		this.matriz = matriz;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getPrevisaoDesmame() {
		return previsaoDesmame;
	}

	public void setPrevisaoDesmame(Date previsaoDesmame) {
		this.previsaoDesmame = previsaoDesmame;
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

	public Double getPesoMedio() {
		return pesoMedio;
	}

	public void setPesoMedio(Double pesoMedio) {
		this.pesoMedio = pesoMedio;
	}

	public Lote getLote() {
		return lote;
	}

	public void setLote(Lote lote) {
		this.lote = lote;
	}

	public String getObsDesmame() {
		return obsDesmame;
	}

	public void setObsDesmame(String obsDesmame) {
		this.obsDesmame = obsDesmame;
	}

	public String getObsLote() {
		return obsLote;
	}

	public void setObsLote(String obsLote) {
		this.obsLote = obsLote;
	}
}
