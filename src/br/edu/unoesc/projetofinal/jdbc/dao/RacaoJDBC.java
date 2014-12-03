package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.RacaoDAO;
import br.edu.unoesc.projetofinal.model.Racao;

public class RacaoJDBC implements RacaoDAO {

	public void store(Racao racao) {
		String sql = "insert into racao values(null,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setString(1, racao.getNome());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Racao racao) {
		String sql = "update racao set nome=? where idRacao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setString(1, racao.getNome());
			ps.setInt(2, racao.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Racao racao) {
		String sql = "delete from racao where idRacao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, racao.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Racao> listarTodos() {
		List<Racao> racoes = new ArrayList<>();
		String sql = "select*from racao";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Racao racao = new Racao();
				racao.setCodigo(rs.getInt("idRacao"));
				racao.setNome(rs.getString("nome"));
				racoes.add(racao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return racoes;
	}

	public Racao get(Integer codigo) {
		Racao racao = new Racao();
		String sql = "select*from racao where idRacao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			racao.setCodigo(rs.getInt("idRacao"));
			racao.setNome(rs.getString("nome"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return racao;
	}
}
