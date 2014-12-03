package br.edu.unoesc.projetofinal.dao;

import java.util.List;

import br.edu.unoesc.projetofinal.model.Entidade;

public interface CrudDAO<T extends Entidade> {
	void store(T entidade);
	void alter(T entidade);
	void delete(T entidade);
	List<T> listarTodos();
	T get(Integer codigo);
}	
