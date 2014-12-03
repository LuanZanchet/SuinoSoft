package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public abstract class Morte implements Entidade {
	private Integer codigo;
	private Date data;
	private Causa causa;

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
}
