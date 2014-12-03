package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class MorteMatriz extends Morte {
	private Matriz matriz;

	public MorteMatriz() {

	}

	public MorteMatriz(Integer codigo, Date data, Causa causa, Matriz matriz) {
		this.setCodigo(codigo);
		this.setData(data);
		this.setCausa(causa);
		this.matriz = matriz;
	}

	public Matriz getMatriz() {
		return matriz;
	}

	public void setMatriz(Matriz matriz) {
		this.matriz = matriz;
	}
}