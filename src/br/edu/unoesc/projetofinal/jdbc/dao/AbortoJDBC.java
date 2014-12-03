package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.AbortoDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Aborto;

public class AbortoJDBC implements AbortoDAO {

	private static MatrizDAO matrizDao = DaoFactory.get().matrizDao();

	public void store(Aborto aborto) {
		String sql = "insert into aborto values(null,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, aborto.getMatriz().getCodigo());
			ps.setDate(2, (Date) aborto.getData());
			ps.setString(3, aborto.getObservacao());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Aborto aborto) {
		String sql = "update aborto set idMatriz=?,dataAborto=?,observacao=? where idAborto=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, aborto.getMatriz().getCodigo());
			ps.setDate(2, (Date) aborto.getData());
			ps.setString(3, aborto.getObservacao());
			ps.setInt(4, aborto.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Aborto aborto) {
		String sql = "delete from aborto where idAborto=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, aborto.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Aborto> listarTodos() {
		List<Aborto> abortos = new ArrayList<>();
		String sql = "select*from aborto";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Aborto aborto = new Aborto();
				aborto.setCodigo(rs.getInt("idAborto"));
				aborto.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
				aborto.setData(rs.getDate("dataAborto"));
				aborto.setObservacao(rs.getString("observacao"));
				abortos.add(aborto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return abortos;
	}

	public Aborto get(Integer codigo) {
		Aborto aborto = new Aborto();
		String sql = "select*from aborto where idAborto=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			aborto.setCodigo(rs.getInt("idAborto"));
			aborto.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
			aborto.setData(rs.getDate("dataAborto"));
			aborto.setObservacao(rs.getString("observacao"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aborto;
	}

}
