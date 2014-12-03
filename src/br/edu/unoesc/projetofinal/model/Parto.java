package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class Parto implements Entidade {
	private Integer codigo;
	private Matriz matriz;
	private String tipoParto;
	private Date data;
	private Integer quantVivos;
	private Integer quantMortos;
	private Integer quantMumificados;
	private Integer quantNatimortos;
	private Double pesoTotal;
	private Double pesoMedio;
	private Funcionario funcionario;

	public Parto() {

	}

	public Parto(Integer codigo, Matriz matriz, String tipoParto, Date data, Integer quantVivos, Integer quantMortos,
			Integer quantMumificados, Integer quantNatimortos, Double pesoTotal, Double pesoMedio,
			Funcionario funcionario) {
		this.codigo = codigo;
		this.matriz = matriz;
		this.tipoParto = tipoParto;
		this.data = data;
		this.quantVivos = quantVivos;
		this.quantMortos = quantMortos;
		this.quantMumificados = quantMumificados;
		this.quantNatimortos = quantNatimortos;
		this.pesoTotal = pesoTotal;
		this.pesoMedio = pesoMedio;
		this.funcionario = funcionario;
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

	public String getTipoParto() {
		return tipoParto;
	}

	public void setTipoParto(String tipoParto) {
		this.tipoParto = tipoParto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getQuantVivos() {
		return quantVivos;
	}

	public void setQuantVivos(Integer quantVivos) {
		this.quantVivos = quantVivos;
	}

	public Integer getQuantMortos() {
		return quantMortos;
	}

	public void setQuantMortos(Integer quantMortos) {
		this.quantMortos = quantMortos;
	}

	public Integer getQuantMumificados() {
		return quantMumificados;
	}

	public void setQuantMumificados(Integer quantMumificados) {
		this.quantMumificados = quantMumificados;
	}

	public Integer getQuantNatimortos() {
		return quantNatimortos;
	}

	public void setQuantNatimortos(Integer quantNatimortos) {
		this.quantNatimortos = quantNatimortos;
	}

	public Double getPesoTotal() {
		return pesoTotal;
	}

	public void setPesoTotal(Double pesoTotal) {
		this.pesoTotal = pesoTotal;
	}

	public Double getPesoMedio() {
		return pesoMedio;
	}

	public void setPesoMedio(Double pesoMedio) {
		this.pesoMedio = pesoMedio;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
