package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class CompraRacao implements Entidade {
	private Integer codigo;
	private Racao racao;
	private Nota nota;
	private Fornecedor fornecedor;
	private Date data;
	private Integer quantidade;

	public CompraRacao() {

	}

	public CompraRacao(Integer codigo, Racao racao, Nota nota, Fornecedor fornecedor, Date data, Integer quantidade) {
		this.racao = racao;
		this.nota = nota;
		this.fornecedor = fornecedor;
		this.data = data;
		this.quantidade = quantidade;
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

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
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

}
