package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.VacinaDAO;
import br.edu.unoesc.projetofinal.model.Vacina;

public class VacinaJDBC implements VacinaDAO {

	public void store(Vacina vacina) {
		String sql = "insert into vacina values(null,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setString(1, vacina.getNome());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Vacina vacina) {
		String sql = "update vacina set nome=? where idVacina=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setString(1, vacina.getNome());
			ps.setInt(2, vacina.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Vacina vacina) {
		String sql = "delete from vacina where idVacina=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, vacina.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Vacina> listarTodos() {
		List<Vacina> vacinas = new ArrayList<>();
		String sql = "select*from vacina";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Vacina vacina = new Vacina();
				vacina.setCodigo(rs.getInt("idVacina"));
				vacina.setNome(rs.getString("nome"));
				vacinas.add(vacina);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vacinas;
	}

	public Vacina get(Integer codigo) {
		Vacina vacina = new Vacina();
		String sql = "select*from vacina where idVacina=?";
		PreparedStatement ps;
		try {
			ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vacina.setCodigo(rs.getInt("idVacina"));
			vacina.setNome(rs.getString("nome"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vacina;
	}
}
