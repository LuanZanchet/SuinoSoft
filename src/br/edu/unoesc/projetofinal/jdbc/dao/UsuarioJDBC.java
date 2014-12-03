package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.UsuarioDAO;
import br.edu.unoesc.projetofinal.model.Usuario;

public class UsuarioJDBC implements UsuarioDAO {

	public void store(Usuario usuario) {
		String slq = "insert into usuario values(null,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(slq);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getSenha());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Usuario usuario) {
		String sql = "update usuario set nome=?,senha=? where idUsuario=?";
		PreparedStatement ps;
		try {
			ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getSenha());
			ps.setInt(3, usuario.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Usuario usuario) {
		String sql = "delete from usuario where idUsuario=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, usuario.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Usuario> listarTodos() {
		List<Usuario> usuarios = new ArrayList<>();
		String sql = "select*from usuario";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setCodigo(rs.getInt("idUsuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public Usuario get(Integer codigo) {
		Usuario usuario = new Usuario();
		String sql = "select*from usuario where idUsuario=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			usuario.setCodigo(rs.getInt("idUsuario"));
			usuario.setNome(rs.getString("nome"));
			usuario.setSenha(rs.getString("senha"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
}
