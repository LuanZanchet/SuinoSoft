package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.CoberturaDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Cobertura;

public class CoberturaJDBC implements CoberturaDAO {
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();

	public void store(Cobertura cobertura) {
		String sql = "insert into cobertura values(null,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, cobertura.getMatriz().getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Cobertura cobertura) {
		String sql = "update cobertura set idMatriz=? where idCobertura=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, cobertura.getMatriz().getCodigo());
			ps.setInt(2, cobertura.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Cobertura cobertura) {
		String sql = "delete from cobertura where idCobertura=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, cobertura.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Cobertura> listarTodos() {
		List<Cobertura> coberturas = new ArrayList<>();
		String sql = "select*from cobertura";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cobertura cobertura = new Cobertura();
				cobertura.setCodigo(rs.getInt("idCobertura"));
				cobertura.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
				coberturas.add(cobertura);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coberturas;
	}

	public Cobertura get(Integer codigo) {
		Cobertura cobertura = new Cobertura();
		String sql = "select*from cobertura where idCobertura=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			cobertura.setCodigo(rs.getInt("idCobertura"));
			cobertura.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cobertura;
	}
}