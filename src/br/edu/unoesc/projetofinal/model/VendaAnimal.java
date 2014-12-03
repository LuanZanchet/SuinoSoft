package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class VendaAnimal extends Venda {
	private Animal animal;

	public VendaAnimal() {

	}

	public VendaAnimal(Integer codigo, Date data, Double valor, Comprador comprador, Long gta, Nota nota,
			Double pesoTotal, Double pesoMedio, Animal animal) {
		this.setCodigo(codigo);
		this.setData(data);
		this.setValor(valor);
		this.setComprador(comprador);
		this.setGta(gta);
		this.setNota(nota);
		this.setPesoTotal(pesoTotal);
		this.setPesoMedio(pesoMedio);
		this.animal = animal;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
}
