package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class MorteAnimal extends Morte {
	private Animal animal;

	public MorteAnimal() {

	}

	public MorteAnimal(Integer codigo, Date data, Causa causa, Animal animal) {
		this.setCodigo(codigo);
		this.setData(data);
		this.setCausa(causa);
		this.animal = animal;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

}
