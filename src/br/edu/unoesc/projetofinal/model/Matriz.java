package br.edu.unoesc.projetofinal.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.AbortoDAO;
import br.edu.unoesc.projetofinal.dao.CoberturaDAO;
import br.edu.unoesc.projetofinal.dao.DesmameDAO;
import br.edu.unoesc.projetofinal.dao.LactacaoDAO;
import br.edu.unoesc.projetofinal.dao.PartoDAO;
import br.edu.unoesc.projetofinal.dao.RepeticaoCioDAO;
import br.edu.unoesc.projetofinal.dao.VacinacaoMatrizDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;

public class Matriz extends Animal {
	private List<Parto> partos;
	private List<Cobertura> coberturas;
	private List<RepeticaoCio> repeticoes;
	private List<Aborto> abortos;
	private List<Lactacao> lactacoes;
	private List<Desmame> desmames;
	private List<VacinacaoMatriz> vacinacoes;
	private Integer numeroCiclos;
	private VacinacaoMatrizDAO vacinacadao = DaoFactory.get().vacinacaoMatrizDao();
	private DesmameDAO desmamedao = DaoFactory.get().desmameDao();
	private AbortoDAO abortodao = DaoFactory.get().abortoDao();
	private PartoDAO partoDao = DaoFactory.get().partoDao();
	private CoberturaDAO coberturadao = DaoFactory.get().coberturaDao();
	private RepeticaoCioDAO repeticaoCioDao = DaoFactory.get().repeticaoCioDao();
	private LactacaoDAO lactacaoDao = DaoFactory.get().lactacaoDao();

	public Matriz() {

	}

	public Matriz(Integer codigo, Long mossa, Long brinco, Nota nota, Date dataEntrada, Date dataNascimento,
			Double peso, Double valor, Raca raca, Fornecedor fornecedor, String observacao, String status,
			Integer idade, Integer numeroCiclos) {
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
		this.numeroCiclos = numeroCiclos;
	}

	public List<Parto> getPartos() {
		if (this.partos == null) {
			this.partos = new ArrayList<>();
		}
		for (Parto parto : partoDao.listarTodos()) {
			if (parto.getMatriz().getCodigo().equals(this.getCodigo())) {
				this.partos.add(parto);
			}
		}
		return this.partos;
	}

	public void setPartos(List<Parto> partos) {
		this.partos = partos;
	}

	public List<Cobertura> getCoberturas() {
		if (this.coberturas == null) {
			this.coberturas = new ArrayList<>();
		}
		for (Cobertura cobertura : coberturadao.listarTodos()) {
			if (cobertura.getMatriz().getCodigo().equals(this.getCodigo())) {
				this.coberturas.add(cobertura);
			}
		}
		return this.coberturas;
	}

	public void setCoberturas(List<Cobertura> coberturas) {
		this.coberturas = coberturas;
	}

	public List<RepeticaoCio> getRepeticoes() {
		if (this.repeticoes == null) {
			this.repeticoes = new ArrayList<>();
		}
		for (RepeticaoCio repeticao : repeticaoCioDao.listarTodos()) {
			if (repeticao.getMatriz().getCodigo().equals(this.getCodigo())) {
				this.repeticoes.add(repeticao);
			}
		}
		return this.repeticoes;
	}

	public void setRepeticoes(List<RepeticaoCio> repeticoes) {
		this.repeticoes = repeticoes;
	}

	public List<Aborto> getAbortos() {
		if (this.abortos == null) {
			this.abortos = new ArrayList<>();
		}
		for (Aborto aborto : abortodao.listarTodos()) {
			if (aborto.getMatriz().getCodigo().equals(this.getCodigo())) {
				this.abortos.add(aborto);
			}
		}
		return abortos;
	}

	public void setAbortos(List<Aborto> abortos) {
		this.abortos = abortos;
	}

	public List<Lactacao> getLactacoes() {
		if (this.lactacoes == null) {
			this.lactacoes = new ArrayList<>();
		}
		for (Lactacao lactacao : lactacaoDao.listarTodos()) {
			if (lactacao.getMatriz().getCodigo().equals(this.getCodigo())) {
				this.lactacoes.add(lactacao);
			}
		}
		return this.lactacoes;
	}

	public void setLactacoes(List<Lactacao> lactacoes) {
		this.lactacoes = lactacoes;
	}

	public List<Desmame> getDesmames() {
		if (this.desmames == null) {
			this.desmames = new ArrayList<>();
		}
		for (Desmame desmame : desmamedao.listarTodos()) {
			if (desmame.getMatriz().getCodigo().equals(this.getCodigo())) {
				this.desmames.add(desmame);
			}
		}
		return this.desmames;
	}

	public void setDesmames(List<Desmame> desmames) {
		this.desmames = desmames;
	}

	public Integer getNumeroCiclos() {
		return numeroCiclos;
	}

	public void setNumeroCiclos(Integer numeroCiclos) {
		this.numeroCiclos = numeroCiclos;
	}

	public List<VacinacaoMatriz> getVacinacoes() {
		if (this.vacinacoes == null) {
			this.vacinacoes = new ArrayList<>();
		}
		for (VacinacaoMatriz vacinacao : vacinacadao.listarTodos()) {
			if (vacinacao.getMatriz().getCodigo().equals(this.getCodigo())) {
				this.vacinacoes.add(vacinacao);
			}
		}
		return this.vacinacoes;
	}

	public void setVacinacoes(List<VacinacaoMatriz> vacinacoes) {
		this.vacinacoes = vacinacoes;
	}
}
