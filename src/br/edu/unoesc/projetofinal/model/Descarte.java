package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class Descarte implements Entidade {
	private Integer codigo;
	private Date data;
	private Causa causa;
	private Animal animal;
	
	public Descarte(){
		
	}

	public Descarte(Integer codigo, Date data, Causa causa, Animal animal) {
		this.codigo = codigo;
		this.data = data;
		this.causa = causa;
		this.animal = animal;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Causa getCausa() {
		return causa;
	}

	public void setCausa(Causa causa) {
		this.causa = causa;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
}
