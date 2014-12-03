package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class CompraMedicamento implements Entidade {
	private Integer codigo;
	private Vacina vacina;
	private Date data;
	private Nota nota;
	private Fornecedor fornecedor;
	private Integer quantidade;

	public CompraMedicamento() {

	}

	public CompraMedicamento(Integer codigo, Vacina vacina, Date data, Nota nota, Fornecedor fornecedor,
			Integer quantidade) {
		this.codigo = codigo;
		this.vacina = vacina;
		this.data = data;
		this.nota = nota;
		this.fornecedor = fornecedor;
		this.quantidade = quantidade;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
