package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class VendaMatriz extends Venda {
	private Matriz matriz;

	public VendaMatriz() {

	}

	public VendaMatriz(Integer codigo, Date data, Double valor, Comprador comprador, Long gta, Nota nota,
			Double pesoTotal, Double pesoMedio, Matriz matriz) {
		this.setCodigo(codigo);
		this.setData(data);
		this.setValor(valor);
		this.setComprador(comprador);
		this.setGta(gta);
		this.setNota(nota);
		this.setPesoTotal(pesoTotal);
		this.setPesoMedio(pesoMedio);
		this.matriz = matriz;
	}

	public Matriz getMatriz() {
		return matriz;
	}

	public void setMatriz(Matriz matriz) {
		this.matriz = matriz;
	}
}