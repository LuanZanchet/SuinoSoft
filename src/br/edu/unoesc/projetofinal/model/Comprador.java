package br.edu.unoesc.projetofinal.model;

public class Comprador implements Entidade {
	private Integer codigo;
	private String nome;
	private String tipo;
	private Long cpfCnpj;
	private Endereco endereco;
	private Long telefone;

	public Comprador() {

	}

	public Comprador(Integer codigo, String nome, String tipo, Long cpfCnpj, Endereco endereco, Long telefone) {
		this.codigo = codigo;
		this.nome = nome;
		this.tipo = tipo;
		this.cpfCnpj = cpfCnpj;
		this.endereco = endereco;
		this.telefone = telefone;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(Long cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

}
