package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class NotaCompra extends Nota implements Entidade {
	private Fornecedor fornecedor;

	public NotaCompra() {

	}

	public NotaCompra(Integer codigo, Date data, Double valor, String formaPagamento, Fornecedor fornecedor, Long numero) {
		this.setCodigo(codigo);
		this.setData(data);
		this.setValor(valor);
		this.setFormaPagamento(formaPagamento);
		this.fornecedor = fornecedor;
		this.setNumero(numero);
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
