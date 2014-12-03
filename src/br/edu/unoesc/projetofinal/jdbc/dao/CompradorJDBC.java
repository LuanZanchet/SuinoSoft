package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;








import br.edu.unoesc.projetofinal.dao.CompradorDAO;
import br.edu.unoesc.projetofinal.dao.EnderecoDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Comprador;

public class CompradorJDBC implements CompradorDAO {
	private EnderecoDAO enderecoDao=DaoFactory.get().enderecoDao();
	
	public void store(Comprador comprador) {
		String sql="insert into comprador values(null,?,?,?,?,?)";
		try {
			PreparedStatement ps=ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setString(1, comprador.getNome());
			ps.setString(2, comprador.getTipo());
			ps.setLong(3, comprador.getCpfCnpj());
			ps.setInt(4, comprador.getEndereco().getCodigo());
			ps.setLong(5, comprador.getTelefone());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Comprador comprador) {
		String sql="update comprador set nome=?,tipo=?,cpfcnpj=?,idEndereco=?,telefone=? where idComprador=?";
		try {
			PreparedStatement ps=ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setString(1, comprador.getNome());
			ps.setString(2, comprador.getTipo());
			ps.setLong(3, comprador.getCpfCnpj());
			ps.setInt(4, comprador.getEndereco().getCodigo());
			ps.setLong(5, comprador.getTelefone());
			ps.setInt(6, comprador.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Comprador comprador) {
		String sql="delete from comprador where idComprador=?";
		try {
			PreparedStatement ps=ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, comprador.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Comprador> listarTodos() {
		List<Comprador>compradores=new ArrayList<Comprador>();
		String sql="select*from comprador";
		try {
			PreparedStatement ps=ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Comprador comprador=new Comprador();
				comprador.setCodigo(rs.getInt("idComprador"));
				comprador.setNome(rs.getString("nome"));
				comprador.setTipo(rs.getString("tipo"));
				comprador.setCpfCnpj(rs.getLong("cpfcnpj"));
				comprador.setEndereco(enderecoDao.get(rs.getInt("idEndereco")));
				comprador.setTelefone(rs.getLong("telefone"));
				compradores.add(comprador);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return compradores;
	}

	public Comprador get(Integer codigo) {
		Comprador comprador=new Comprador();
		String sql="select*from comprador where idComprador=?";
		try {
			PreparedStatement ps=ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs=ps.executeQuery();
			rs.next();
			comprador.setCodigo(rs.getInt("idComprador"));
			comprador.setNome(rs.getString("nome"));
			comprador.setTipo(rs.getString("tipo"));
			comprador.setCpfCnpj(rs.getLong("cpfcnpj"));
			comprador.setEndereco(enderecoDao.get(rs.getInt("idEndereco")));
			comprador.setTelefone(rs.getLong("telefone"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comprador;
	}
}