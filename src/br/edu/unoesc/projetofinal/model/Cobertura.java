package br.edu.unoesc.projetofinal.model;

import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.MontaMachoDao;
import br.edu.unoesc.projetofinal.dao.MontaSemenDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;

public class Cobertura implements Entidade {
	private Integer codigo;
	private Matriz matriz;
	private List<Monta> montas;
	private MontaMachoDao montaMachoDao=DaoFactory.get().montaMachoDao();
	private MontaSemenDAO montaSemenDao=DaoFactory.get().montaSemenDao();

	public Cobertura() {

	}

	public Cobertura(Integer codigo, Matriz matriz) {
		this.codigo = codigo;
		this.matriz = matriz;
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

	public List<Monta> getMontas() {
		if (this.montas == null) {
			this.montas = new ArrayList<>();
		}
		for(MontaMacho montaMacho:montaMachoDao.listarTodos()){
			if(montaMacho.getCobertura().getCodigo().equals(this.getCodigo())){
				this.montas.add(montaMacho);
			}
		}
		for(MontaSemen montaSemen:montaSemenDao.listarTodos()){
			if(montaSemen.getCobertura().getCodigo().equals(this.getCodigo())){
				this.montas.add(montaSemen);
			}
		}
		return montas;
	}

	public void setMontas(List<Monta> montas) {
		this.montas = montas;
	}
}