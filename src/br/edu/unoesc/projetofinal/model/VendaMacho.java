package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class VendaMacho extends Venda {
	private Macho macho;

	public VendaMacho() {

	}

	public VendaMacho(Integer codigo, Date data, Double valor, Comprador comprador, Long gta, Nota nota,
			Double pesoTotal, Double pesoMedio, Macho macho) {
		this.setCodigo(codigo);
		this.setData(data);
		this.setValor(valor);
		this.setComprador(comprador);
		this.setGta(gta);
		this.setNota(nota);
		this.setPesoTotal(pesoTotal);
		this.setPesoMedio(pesoMedio);
		this.macho = macho;
	}

	public Macho getMacho() {
		return macho;
	}

	public void setMacho(Macho macho) {
		this.macho = macho;
	}
}