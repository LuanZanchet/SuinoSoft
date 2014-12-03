package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class Semen implements Entidade {
	private Integer codigo;
	private Long brinco;
	private Date dataEntrada;
	private Date dataNascimento;
	private Raca raca;
	private Fornecedor fornecedor;
	private Nota nota;

	public Semen() {

	}

	public Semen(Integer codigo, Long brinco, Date dataEntrada, Date dataNascimento, Raca raca, Fornecedor fornecedor,
			Nota nota) {
		this.codigo = codigo;
		this.brinco = brinco;
		this.dataEntrada = dataEntrada;
		this.dataNascimento = dataNascimento;
		this.raca = raca;
		this.fornecedor = fornecedor;
		this.nota = nota;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Long getBrinco() {
		return brinco;
	}

	public void setBrinco(Long brinco) {
		this.brinco = brinco;
	}

	public Date getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
	}
}
