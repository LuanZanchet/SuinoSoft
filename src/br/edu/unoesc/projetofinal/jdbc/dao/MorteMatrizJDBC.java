package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.CausaDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.MorteMatrizDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.MorteMatriz;

public class MorteMatrizJDBC implements MorteMatrizDAO {
	private CausaDAO causaDao = DaoFactory.get().causaDao();
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();

	public void store(MorteMatriz morte) {
		String sql = "insert into mortematriz values(null,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) morte.getData());
			ps.setInt(2, morte.getCausa().getCodigo());
			ps.setInt(3, morte.getMatriz().getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(MorteMatriz morte) {
		String sql = "update mortematriz set dataMorte=?,idCausa=?,idMatriz=? where idMorte=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) morte.getData());
			ps.setInt(2, morte.getCausa().getCodigo());
			ps.setInt(3, morte.getMatriz().getCodigo());
			ps.setInt(4, morte.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(MorteMatriz morte) {
		String sql = "delete from morte where idMorte=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, morte.getCodigo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<MorteMatriz> listarTodos() {
		List<MorteMatriz> mortes = new ArrayList<>();
		String sql = "select*from mortematriz";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MorteMatriz morte = new MorteMatriz();
				morte.setCodigo(rs.getInt("idMorte"));
				morte.setData(rs.getDate("dataMorte"));
				morte.setCausa(causaDao.get(rs.getInt("idCausa")));
				morte.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
				mortes.add(morte);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mortes;
	}

	public MorteMatriz get(Integer codigo) {
		MorteMatriz morte = new MorteMatriz();
		String sql = "select*from mortematriz where idMorte=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			morte.setCodigo(rs.getInt("idMorte"));
			morte.setData(rs.getDate("dataMorte"));
			morte.setCausa(causaDao.get(rs.getInt("idCausa")));
			morte.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}