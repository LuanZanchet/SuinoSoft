package br.edu.unoesc.projetofinal.model;

import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;

public class Maternidade implements Entidade {
	private List<Matriz> matrizesLactantes;
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();

	public List<Matriz> getMatrizesLactantes() {
		if (this.matrizesLactantes == null) {
			this.matrizesLactantes = new ArrayList<>();
		}
		for (Matriz matriz : matrizDao.listarTodos()) {
			if (matriz.getStatus().equals("Lactante")) {
				this.matrizesLactantes.add(matriz);
			}
		}
		return this.matrizesLactantes;
	}

	public Integer getTotalLeitao() {
		Integer total = 0;
		for (Matriz matriz : this.getMatrizesLactantes()) {
			total = total + matriz.getLactacoes().get(matriz.getLactacoes().size() - 1).getQuantAtual();
		}
		return total;
	}
}
