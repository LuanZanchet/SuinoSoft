package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class SaidaRacao implements Entidade {
	private Integer codigo;
	private Racao racao;
	private Integer quantidade;
	private Date data;
	private String destino;

	public SaidaRacao() {

	}

	public SaidaRacao(Integer codigo, Racao racao, Integer quantidade, Date data, String destino) {
		this.codigo = codigo;
		this.racao = racao;
		this.quantidade = quantidade;
		this.data = data;
		this.destino = destino;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Racao getRacao() {
		return racao;
	}

	public void setRacao(Racao racao) {
		this.racao = racao;
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

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}
}