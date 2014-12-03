package br.edu.unoesc.projetofinal.model;

public class Lactacao implements Entidade {
	private Integer codigo;
	private Matriz matriz;
	private Integer quantDoados;
	private Integer quantRecebidos;
	private Integer quantMortos;
	private Integer quantAtual;

	public Lactacao() {

	}

	public Lactacao(Integer codigo, Matriz matriz, Integer quantDoados, Integer quantRecebidos, Integer quantMortos,
			Integer quantAtual) {
		this.codigo = codigo;
		this.matriz = matriz;
		this.quantDoados = quantDoados;
		this.quantRecebidos = quantRecebidos;
		this.quantMortos = quantMortos;
		this.quantAtual = quantAtual;
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

	public Integer getQuantDoados() {
		return quantDoados;
	}

	public void setQuantDoados(Integer quantDoados) {
		this.quantDoados = quantDoados;
	}

	public Integer getQuantRecebidos() {
		return quantRecebidos;
	}

	public void setQuantRecebidos(Integer quantRecebidos) {
		this.quantRecebidos = quantRecebidos;
	}

	public Integer getQuantMortos() {
		return quantMortos;
	}

	public void setQuantMortos(Integer quantMortos) {
		this.quantMortos = quantMortos;
	}

	public Integer getQuantAtual() {
		return quantAtual;
	}

	public void setQuantAtual(Integer quantAtual) {
		this.quantAtual = quantAtual;
	}
}
