package br.edu.unoesc.projetofinal.model;

import java.sql.Date;

public abstract class Monta implements Entidade {
	private Integer codigo;
	private Cobertura cobertura;
	private String tipo;
	private Date data;
	private Funcionario funcionario;

	public Monta() {

	}

	public Monta(Integer codigo, Cobertura cobertura, Macho macho, Semen semen, String tipo, Date data,
			Funcionario funcionario) {
		super();
		this.codigo = codigo;
		this.cobertura = cobertura;
		this.tipo = tipo;
		this.data = data;
		this.funcionario = funcionario;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Cobertura getCobertura() {
		return cobertura;
	}

	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
}