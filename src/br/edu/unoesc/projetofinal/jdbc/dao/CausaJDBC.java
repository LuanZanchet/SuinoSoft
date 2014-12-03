package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.CausaDAO;
import br.edu.unoesc.projetofinal.model.Causa;

public class CausaJDBC implements CausaDAO {

	public void store(Causa causa) {
		String sql = "insert into causa values(null,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setString(1, causa.getNome());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Causa causa) {
		String sql = "update causa set nome=? where idCausa=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setString(1, causa.getNome());
			ps.setInt(2, causa.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Causa causa) {
		String sql = "delete from causa where idCausa=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, causa.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Causa> listarTodos() {
		List<Causa> causas = new ArrayList<>();
		String sql = "select*from causa";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Causa causa = new Causa();
				causa.setCodigo(rs.getInt("idCausa"));
				causa.setNome(rs.getString("nome"));
				causas.add(causa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return causas;
	}

	public Causa get(Integer codigo) {
		Causa causa = new Causa();
		String sql = "select*from causa where idCausa=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			causa.setCodigo(rs.getInt("idCausa"));
			causa.setNome(rs.getString("nome"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return causa;
	}

}
