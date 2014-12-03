package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class DescarteMatriz implements Entidade {
	private Integer codigo;
	private Date data;
	private Causa causa;
	private Matriz matriz;

	public DescarteMatriz() {

	}

	public DescarteMatriz(Integer codigo, Date data, Causa causa, Matriz matriz) {
		this.codigo = codigo;
		this.data = data;
		this.causa = causa;
		this.matriz = matriz;
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

	public Matriz getMatriz() {
		return matriz;
	}

	public void setMatriz(Matriz matriz) {
		this.matriz = matriz;
	}
}
