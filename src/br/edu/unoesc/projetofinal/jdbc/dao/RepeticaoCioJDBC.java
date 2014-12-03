package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.RepeticaoCioDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.RepeticaoCio;

public class RepeticaoCioJDBC implements RepeticaoCioDAO {
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();

	public void store(RepeticaoCio repeticao) {
		String sql = "insert into repeticaocio values (null,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, repeticao.getMatriz().getCodigo());
			ps.setDate(2, (Date) repeticao.getData());
			ps.setString(3, repeticao.getObservacao());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(RepeticaoCio repeticao) {
		String sql = "update repeticaocio set idMatriz=?,dataRepeticao=?,observacao=?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, repeticao.getMatriz().getCodigo());
			ps.setDate(2, (Date) repeticao.getData());
			ps.setString(3, repeticao.getObservacao());
			ps.setInt(4, repeticao.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(RepeticaoCio repeticao) {
		String sql = "delete from repeticaocio where idRepeticaoCio=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, repeticao.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<RepeticaoCio> listarTodos() {
		List<RepeticaoCio> repeticoes = new ArrayList<>();
		String sql = "select*from repeticaocio";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RepeticaoCio repeticao = new RepeticaoCio();
				repeticao.setCodigo(rs.getInt("idRepeticaoCio"));
				repeticao.setData(rs.getDate("dataRepeticao"));
				repeticao.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
				repeticao.setObservacao(rs.getString("observacao"));
				repeticoes.add(repeticao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return repeticoes;
	}

	public RepeticaoCio get(Integer codigo) {
		RepeticaoCio repeticao = new RepeticaoCio();
		String sql = "select*from repeticaocio where idRepeticaoCio=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			repeticao.setCodigo(rs.getInt("idRepeticaoCio"));
			repeticao.setData(rs.getDate("dataRepeticao"));
			repeticao.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
			repeticao.setObservacao(rs.getString("observacao"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return repeticao;
	}

}
