package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.ProprietarioDAO;
import br.edu.unoesc.projetofinal.model.Proprietario;

public class ProprietarioJDBC implements ProprietarioDAO {

	public void store(Proprietario proprietario) {
		String sql = "insert into proprietario values(null,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setString(1, proprietario.getNome());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Proprietario proprietario) {
		String sql = "update proprietario set nome=? where idProprietario=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setString(1, proprietario.getNome());
			ps.setInt(2, proprietario.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Proprietario proprietario) {
		String sql = "delete from proprietario where idProprietario=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, proprietario.getCodigo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Proprietario> listarTodos() {
		List<Proprietario> proprietarios = new ArrayList<>();
		String sql = "select*from proprietario";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Proprietario proprietario = new Proprietario();
				proprietario.setCodigo(rs.getInt("idProprietario"));
				proprietario.setNome(rs.getString("nome"));
				proprietarios.add(proprietario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proprietarios;
	}

	public Proprietario get(Integer codigo) {
		Proprietario proprietario = new Proprietario();
		String sql = "select*from proprietario where idProprietario=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			proprietario.setCodigo(rs.getInt("idProprietario"));
			proprietario.setNome(rs.getString("nome"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return proprietario;
	}
}
