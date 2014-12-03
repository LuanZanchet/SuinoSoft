package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class VacinacaoMatriz implements Entidade {
	private Integer codigo;
	private Vacina vacina;
	private Date data;
	private Double quantidadeUsada;
	private Matriz matriz;

	public VacinacaoMatriz() {

	}

	public VacinacaoMatriz(Integer codigo, Vacina vacina, Date data, Double quantidadeUsada, Matriz matriz) {
		this.codigo = codigo;
		this.vacina = vacina;
		this.data = data;
		this.quantidadeUsada = quantidadeUsada;
		this.matriz = matriz;
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

	public Double getQuantidadeUsada() {
		return quantidadeUsada;
	}

	public void setQuantidadeUsada(Double quantidadeUsada) {
		this.quantidadeUsada = quantidadeUsada;
	}

	public Matriz getMatriz() {
		return matriz;
	}

	public void setMatriz(Matriz matriz) {
		this.matriz = matriz;
	}
}
