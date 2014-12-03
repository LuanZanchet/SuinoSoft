package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.EnderecoDAO;
import br.edu.unoesc.projetofinal.model.Endereco;

public class EnderecoJDBC implements EnderecoDAO {

	public void store(Endereco endereco) {
		String sql = "insert into endereco values(null,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setString(1, endereco.getUf());
			ps.setString(2, endereco.getCidade());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Endereco endereco) {
		String sql = "update endereco set uf=?,cidade=? where idEndereco=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setString(1, endereco.getUf());
			ps.setString(2, endereco.getCidade());
			ps.setInt(3, endereco.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Endereco endereco) {
		String sql = "delete from endereco where idEndereco=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, endereco.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Endereco> listarTodos() {
		List<Endereco> enderecos = new ArrayList<>();
		String sql = "select*from endereco";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Endereco endereco = new Endereco();
				endereco.setCodigo(rs.getInt("idEndereco"));
				endereco.setUf(rs.getString("uf"));
				endereco.setCidade(rs.getString("cidade"));
				enderecos.add(endereco);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return enderecos;
	}

	public Endereco get(Integer codigo) {
		Endereco endereco = new Endereco();
		String sql = "select*from endereco where idEndereco=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			endereco.setCodigo(rs.getInt("idEndereco"));
			endereco.setUf(rs.getString("uf"));
			endereco.setCidade(rs.getString("cidade"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return endereco;
	}
}
