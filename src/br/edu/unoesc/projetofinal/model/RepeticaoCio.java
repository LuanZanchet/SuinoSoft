package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class RepeticaoCio implements Entidade {
	private Integer codigo;
	private Matriz matriz;
	private Date data;
	private String observacao;

	public RepeticaoCio() {

	}

	public RepeticaoCio(Integer codigo, Matriz matriz, Date data, String observacao) {
		this.codigo = codigo;
		this.matriz = matriz;
		this.data = data;
		this.observacao = observacao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Matriz getMatriz() {
		return matriz;
	}

	public void setMatriz(Matriz matriz) {
		this.matriz = matriz;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
