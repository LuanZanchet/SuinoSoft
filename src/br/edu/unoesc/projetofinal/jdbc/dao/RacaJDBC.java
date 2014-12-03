package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.RacaDAO;
import br.edu.unoesc.projetofinal.model.Raca;

public class RacaJDBC implements RacaDAO {

	public void store(Raca raca) {
		String sql = "insert into raca values(null,?)";
		PreparedStatement ps;
		try {
			ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setString(1, raca.getNome());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Raca raca) {
		String sql = "update raca set nome=? where idRaca=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setString(1, raca.getNome());
			ps.setInt(2, raca.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Raca raca) {
		String sql = "delete from raca where idRaca=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, raca.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Raca> listarTodos() {
		List<Raca> racas = new ArrayList<>();
		String sql = "select*from raca";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Raca raca = new Raca();
				raca.setCodigo(rs.getInt("idRaca"));
				raca.setNome(rs.getString("nome"));
				racas.add(raca);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return racas;
	}

	public Raca get(Integer codigo) {
		Raca raca = new Raca();
		String sql = "select*from raca where idRaca=?";
		PreparedStatement ps;
		try {
			ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			raca.setCodigo(rs.getInt("idRaca"));
			raca.setNome(rs.getString("nome"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return raca;
	}

}
