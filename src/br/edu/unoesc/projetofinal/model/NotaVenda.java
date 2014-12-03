package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class NotaVenda extends Nota implements Entidade {
	private Comprador comprador;

	public NotaVenda() {

	}

	public NotaVenda(Integer codigo, Date data, Double valor, String formaPagamento, Comprador comprador, Long numero) {
		this.setCodigo(codigo);
		this.setData(data);
		this.setValor(valor);
		this.setFormaPagamento(formaPagamento);
		this.comprador = comprador;
		this.setNumero(numero);
	}

	public Comprador getComprador() {
		return comprador;
	}

	public void setComprador(Comprador comprador) {
		this.comprador = comprador;
	}
}