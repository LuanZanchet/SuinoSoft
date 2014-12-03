package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class DescarteMacho implements Entidade {
	private Integer codigo;
	private Date data;
	private Causa causa;
	private Macho macho;

	public DescarteMacho() {

	}

	public DescarteMacho(Integer codigo, Date data, Causa causa, Macho macho) {
		this.codigo = codigo;
		this.data = data;
		this.causa = causa;
		this.macho = macho;
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

	public Macho getMacho() {
		return macho;
	}

	public void setMacho(Macho macho) {
		this.macho = macho;
	}
}
