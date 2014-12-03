package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public abstract class Animal implements Entidade {
	private Integer codigo;
	private Long mossa;
	private Long brinco;
	private Nota nota;
	private Date dataEntrada;
	private Date dataNascimento;
	private Double peso;
	private Double valor;
	private Raca raca;
	private Fornecedor fornecedor;
	private String observacao;
	private String status;
	private Integer idade;
	private Integer tetasDireitas;
	private Integer tetasEsquerdas;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Long getMossa() {
		return mossa;
	}

	public void setMossa(Long mossa) {
		this.mossa = mossa;
	}

	public Long getBrinco() {
		return brinco;
	}

	public void setBrinco(Long brinco) {
		this.brinco = brinco;
	}

	public Nota getNota() {
		return nota;
	}

	public void setNota(Nota nota) {
		this.nota = nota;
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

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
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

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Integer getTetasDireitas() {
		return tetasDireitas;
	}

	public void setTetasDireitas(Integer tetasDireitas) {
		this.tetasDireitas = tetasDireitas;
	}

	public Integer getTetasEsquerdas() {
		return tetasEsquerdas;
	}

	public void setTetasEsquerdas(Integer tetasEsquerdas) {
		this.tetasEsquerdas = tetasEsquerdas;
	}
}
