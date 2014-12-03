package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class Tarefa implements Entidade {
	private Integer codigo;
	private String descricao;
	private Date dataTarefa;

	public Tarefa() {

	}

	public Tarefa(Integer codigo, String descricao, Date dataTarefa) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.dataTarefa = dataTarefa;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataTarefa() {
		return dataTarefa;
	}

	public void setDataTarefa(Date dataTarefa) {
		this.dataTarefa = dataTarefa;
	}

}