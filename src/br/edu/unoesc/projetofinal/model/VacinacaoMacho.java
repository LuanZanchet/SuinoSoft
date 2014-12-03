package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class VacinacaoMacho implements Entidade {
	private Integer codigo;
	private Vacina vacina;
	private Date data;
	private Double quantidadeUsada;
	private Macho macho;

	public VacinacaoMacho() {

	}

	public VacinacaoMacho(Integer codigo, Vacina vacina, Date data, Double quantidadeUsada, Macho macho) {
		this.codigo = codigo;
		this.vacina = vacina;
		this.data = data;
		this.quantidadeUsada = quantidadeUsada;
		this.macho = macho;
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

	public Macho getMacho() {
		return macho;
	}

	public void setMacho(Macho macho) {
		this.macho = macho;
	}
}