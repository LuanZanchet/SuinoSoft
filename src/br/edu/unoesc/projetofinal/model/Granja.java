package br.edu.unoesc.projetofinal.model;

import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.FuncionarioDAO;
import br.edu.unoesc.projetofinal.dao.MachoDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.UsuarioDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;

public class Granja implements Entidade {
	private Integer codigo;
	private Proprietario proprietario;
	private Endereco endereco;
	private List<Funcionario> funcionarios;
	private List<Usuario> usuarios;
	private List<Animal> animais;
	private MatrizDAO matrizDao=DaoFactory.get().matrizDao();
	private MachoDAO machoDao=DaoFactory.get().machoDao();
	private UsuarioDAO usuarioDao=DaoFactory.get().usuarioDao();
	private FuncionarioDAO funcionario=DaoFactory.get().funcionarioDao();
	
	public Granja() {

	}

	public Granja(Integer codigo, Proprietario proprietario, Endereco endereco) {
		this.codigo = codigo;
		this.proprietario = proprietario;
		this.endereco = endereco;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Funcionario> getFuncionarios() {
		if(this.funcionarios==null){
			this.funcionarios=new ArrayList<Funcionario>();
		}
		for(Funcionario funcionario:this.funcionario.listarTodos()){
			this.funcionarios.add(funcionario);
		}
		return this.funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Usuario> getUsuarios() {
		if(this.usuarios==null){
			this.usuarios=new ArrayList<>();
		}
		for(Usuario usuario: usuarioDao.listarTodos()){
			this.usuarios.add(usuario);
		}
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Animal> getAnimais() {
		if(this.animais==null){
			this.animais=new ArrayList<>();
		}
		for(Matriz matriz:matrizDao.listarTodos()){
			this.animais.add(matriz);
		}
		for(Macho macho:machoDao.listarTodos()){
			this.animais.add(macho);
		}
		return this.animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}
	
	public Integer totalFuncionarios(){
		Integer total=0;
		for(Funcionario funcionario:this.getFuncionarios()){
			total++;
		}
		return total;
	}
	
	public Integer totalUsuarios(){
		Integer total=0;
		for(Usuario usuario: this.getUsuarios()){
			total++;
		}
		return total;
	}
	
	public Integer totalAnimais(){
		Integer total=0;
		for(Animal animal:this.getAnimais()){
			total++;
		}
		return total;
	}
}
