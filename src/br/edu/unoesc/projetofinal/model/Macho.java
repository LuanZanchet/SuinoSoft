package br.edu.unoesc.projetofinal.model;

import java.util.Date;

public class Macho extends Animal {
	private String tipoUtilizacao;

	public Macho() {

	}

	public Macho(Integer codigo, Long mossa, Long brinco, Nota nota, Date dataEntrada, Date dataNascimento,
			Double peso, Double valor, Raca raca, Fornecedor fornecedor, String observacao, String status,
			Integer idade, String tipoUtilizacao) {
		this.setCodigo(codigo);
		this.setMossa(mossa);
		this.setBrinco(brinco);
		this.setNota(nota);
		this.setDataEntrada(dataEntrada);
		this.setDataNascimento(dataNascimento);
		this.setPeso(peso);
		this.setValor(valor);
		this.setRaca(raca);
		this.setFornecedor(fornecedor);
		this.setObservacao(observacao);
		this.setStatus(status);
		this.setIdade(idade);
		this.tipoUtilizacao = tipoUtilizacao;
	}

	public String getTipoUtilizacao() {
		return tipoUtilizacao;
	}

	public void setTipoUtilizacao(String tipoUtilizacao) {
		this.tipoUtilizacao = tipoUtilizacao;
	}

}
