package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.CausaDAO;
import br.edu.unoesc.projetofinal.dao.DescarteMatrizDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.DescarteMatriz;

public class DescarteMatrizJDBC implements DescarteMatrizDAO {
	private CausaDAO causaDao = DaoFactory.get().causaDao();
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();

	public void store(DescarteMatriz descarte) {
		String sql = "insert into descartematriz values(null,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) descarte.getData());
			ps.setInt(2, descarte.getCausa().getCodigo());
			ps.setInt(3, descarte.getMatriz().getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(DescarteMatriz descarte) {
		String sql = "update descartematriz set dataDescarte=?,idCausa=?,idMatriz=? where idDescarte=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) descarte.getData());
			ps.setInt(2, descarte.getCausa().getCodigo());
			ps.setInt(3, descarte.getMatriz().getCodigo());
			ps.setInt(4, descarte.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(DescarteMatriz descarte) {
		String slq = "delete from descartematriz where idDescarte=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(slq);
			ps.setInt(1, descarte.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<DescarteMatriz> listarTodos() {
		List<DescarteMatriz> descartes = new ArrayList<>();
		String sql = "select*from descartematriz";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DescarteMatriz descarte = new DescarteMatriz();
				descarte.setCodigo(rs.getInt("idDescarte"));
				descarte.setData(rs.getDate("dataDescarte"));
				descarte.setCausa(causaDao.get(rs.getInt("idCausa")));
				descarte.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
				descartes.add(descarte);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return descartes;
	}

	public DescarteMatriz get(Integer codigo) {
		DescarteMatriz descarte = new DescarteMatriz();
		String sql = "select*from descartematriz where idDescarte=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			descarte.setCodigo(rs.getInt("idDescarte"));
			descarte.setData(rs.getDate("dataDescarte"));
			descarte.setCausa(causaDao.get(rs.getInt("idCausa")));
			descarte.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return descarte;
	}
}