package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class VendaLeitao extends Venda {
	private Lote lote;
	private Integer quantidade;

	public VendaLeitao() {

	}

	public VendaLeitao(Integer codigo, Date data, Double valor, Comprador comprador, Long gta, Nota nota,
			Double pesoTotal, Double pesoMedio, Lote lote, Integer quantidade) {
		this.setCodigo(codigo);
		this.setData(data);
		this.setValor(valor);
		this.setComprador(comprador);
		this.setGta(gta);
		this.setNota(nota);
		this.setPesoTotal(pesoTotal);
		this.setPesoMedio(pesoMedio);
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
