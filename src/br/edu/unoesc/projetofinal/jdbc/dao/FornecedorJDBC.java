package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.EnderecoDAO;
import br.edu.unoesc.projetofinal.dao.FornecedorDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Fornecedor;

public class FornecedorJDBC implements FornecedorDAO {
	private static EnderecoDAO enderecoDao = DaoFactory.get().enderecoDao();

	public void store(Fornecedor fornecedor) {
		String sql = "insert into fornecedor values(null,?,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setString(1, fornecedor.getNome());
			ps.setString(2, fornecedor.getTipo());
			ps.setLong(3, fornecedor.getCpfCnpj());
			ps.setLong(4, fornecedor.getTelefone());
			ps.setInt(5, fornecedor.getEndereco().getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Fornecedor fornecedor) {
		String sql = "update fornecedor set nome=?,tipo=?,cpfcnpj=?,telefone=?,idEndereco=? where idFornecedor=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setString(1, fornecedor.getNome());
			ps.setString(2, fornecedor.getTipo());
			ps.setLong(3, fornecedor.getCpfCnpj());
			ps.setLong(4, fornecedor.getTelefone());
			ps.setInt(5, fornecedor.getEndereco().getCodigo());
			ps.setInt(6, fornecedor.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(Fornecedor fornecedor) {
		String sql = "delete from fornecedor wher idFornecedor=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, fornecedor.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Fornecedor> listarTodos() {
		List<Fornecedor> fornecedores = new ArrayList<>();
		String sql = "select*from fornecedor";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Fornecedor fornecedor = new Fornecedor();
				fornecedor.setCodigo(rs.getInt("idFornecedor"));
				fornecedor.setNome(rs.getString("nome"));
				fornecedor.setTipo(rs.getString("tipo"));
				fornecedor.setCpfCnpj(rs.getLong("cpfcnpj"));
				fornecedor.setTelefone(rs.getLong("telefone"));
				fornecedor.setEndereco(enderecoDao.get(rs.getInt("idEndereco")));
				fornecedores.add(fornecedor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fornecedores;
	}

	public Fornecedor get(Integer codigo) {
		Fornecedor fornecedor = new Fornecedor();
		String sql = "select*from fornecedor where idFornecedor=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			fornecedor.setCodigo(rs.getInt("idFornecedor"));
			fornecedor.setNome(rs.getString("nome"));
			fornecedor.setTipo(rs.getString("tipo"));
			fornecedor.setCpfCnpj(rs.getLong("cpfcnpj"));
			fornecedor.setTelefone(rs.getLong("telefone"));
			fornecedor.setEndereco(enderecoDao.get(rs.getInt("idEndereco")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fornecedor;
	}
}
