package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class MorteMacho extends Morte {
	private Macho macho;

	public MorteMacho() {

	}

	public MorteMacho(Integer codigo, Date data, Causa causa, Macho macho) {
		this.setCodigo(codigo);
		this.setData(data);
		this.setCausa(causa);
		this.macho = macho;
	}

	public Macho getMacho() {
		return macho;
	}

	public void setMacho(Macho macho) {
		this.macho = macho;
	}
}