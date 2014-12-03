package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.AcessoDAO;
import br.edu.unoesc.projetofinal.model.Acesso;

public class AcessoJDBC implements AcessoDAO {

	public void store(Acesso entidade) {
		String sql = "insert into acesso values(?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, entidade.getCodigo());
			ps.setInt(2, entidade.getQuantidadeAcesso());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Acesso entidade) {
		String sql = "update acesso set quantidadeAcesso=? where idAcesso=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, entidade.getQuantidadeAcesso());
			ps.setInt(2, entidade.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(Acesso entidade) {
		String sql = "delete from acesso where idAcesso=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, entidade.getCodigo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Acesso> listarTodos() {
		List<Acesso> acessos = new ArrayList<>();
		String sql = "select*from acesso";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Acesso acesso = new Acesso();
				acesso.setCodigo(rs.getInt("idAcesso"));
				acesso.setQuantidadeAcesso(rs.getInt("quantidadeAcesso"));
				acessos.add(acesso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acessos;
	}

	public Acesso get(Integer codigo) {
		Acesso acesso = new Acesso();
		String sql = "select*from acesso where idAcesso=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			acesso.setCodigo(rs.getInt("idAcesso"));
			acesso.setQuantidadeAcesso(rs.getInt("quantidadeAcesso"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acesso;
	}

}
