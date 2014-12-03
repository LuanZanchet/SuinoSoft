package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class Vacinacao implements Entidade {
	private Integer codigo;
	private Vacina vacina;
	private Date data;
	private Integer quantidadeUsada;
	private Animal animal;

	public Vacinacao() {

	}

	public Vacinacao(Integer codigo, Vacina vacina, Date data, Integer quantidadeUsada, Animal animal) {
		this.codigo = codigo;
		this.vacina = vacina;
		this.data = data;
		this.quantidadeUsada = quantidadeUsada;
		this.animal = animal;
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

	public Integer getQuantidadeUsada() {
		return quantidadeUsada;
	}

	public void setQuantidadeUsada(Integer quantidadeUsada) {
		this.quantidadeUsada = quantidadeUsada;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

}
