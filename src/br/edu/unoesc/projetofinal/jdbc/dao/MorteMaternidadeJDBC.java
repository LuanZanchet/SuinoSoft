package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.CausaDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.MorteMaternidadeDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.MorteLeitaoMaternidade;

public class MorteMaternidadeJDBC implements MorteMaternidadeDAO {
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();
	private CausaDAO causaDao = DaoFactory.get().causaDao();

	public void store(MorteLeitaoMaternidade morte) {
		String sql = "insert into morteleitaomaternidade values(null,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) morte.getData());
			ps.setInt(2, morte.getCausa().getCodigo());
			ps.setInt(3, morte.getMatriz().getCodigo());
			ps.setInt(4, morte.getQuantidade());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(MorteLeitaoMaternidade morte) {
		String sql = "update morteleitaomaternidade set dataMorte=?,idCausa=?,idMatriz=?,quantidade=? where idMorteLeitaoMaternidade=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) morte.getData());
			ps.setInt(2, morte.getCausa().getCodigo());
			ps.setInt(3, morte.getMatriz().getCodigo());
			ps.setInt(4, morte.getQuantidade());
			ps.setInt(5, morte.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(MorteLeitaoMaternidade morte) {
		String sql = "delete from morteleitaomaternidade where idMorteLeitaoMaternidade=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, morte.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<MorteLeitaoMaternidade> listarTodos() {
		List<MorteLeitaoMaternidade> mortes = new ArrayList<>();
		String sql = "select*from morteleitaomaternidade";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MorteLeitaoMaternidade morte = new MorteLeitaoMaternidade();
				morte.setCodigo(rs.getInt("idMorteLeitaoMaternidade"));
				morte.setData(rs.getDate("dataMorte"));
				morte.setCausa(causaDao.get(rs.getInt("idCausa")));
				morte.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
				morte.setQuantidade(rs.getInt("quantidade"));
				mortes.add(morte);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mortes;
	}

	public MorteLeitaoMaternidade get(Integer codigo) {
		MorteLeitaoMaternidade morte = new MorteLeitaoMaternidade();
		String sql = "select*from morteleitaomaternidade where idMorteLeitaoMaternidade=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			morte.setCodigo(rs.getInt("idMorteLeitaoMaternidade"));
			morte.setData(rs.getDate("dataMorte"));
			morte.setCausa(causaDao.get(rs.getInt("idCausa")));
			morte.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
			morte.setQuantidade(rs.getInt("quantidade"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return morte;
	}
}
