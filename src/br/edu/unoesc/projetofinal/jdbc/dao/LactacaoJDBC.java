package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.LactacaoDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Lactacao;

public class LactacaoJDBC implements LactacaoDAO {
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();

	public void store(Lactacao lactacao) {
		String sql = "insert into lactacao values(null,?,0,0,0,?)";
		PreparedStatement ps;
		try {
			ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, lactacao.getMatriz().getCodigo());
			ps.setInt(2, lactacao.getQuantAtual());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Lactacao lactacao) {
		String sql = "update lactacao set idMatriz=?,quantDoados=?,quantRecebidos=?,quantMortos=?,quantAtual=? where idLactacao=?";
		PreparedStatement ps;
		try {
			ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, lactacao.getMatriz().getCodigo());
			ps.setInt(2, lactacao.getQuantDoados());
			ps.setInt(3, lactacao.getQuantRecebidos());
			ps.setInt(4, lactacao.getQuantMortos());
			ps.setInt(5, lactacao.getQuantAtual());
			ps.setInt(6, lactacao.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Lactacao lactacao) {
		String sql = "delete from lactacao where idLactacao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, lactacao.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Lactacao> listarTodos() {
		List<Lactacao> lactacoes = new ArrayList<>();
		String sql = "select*from lactacao";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Lactacao lactacao = new Lactacao();
				lactacao.setCodigo(rs.getInt("idLactacao"));
				lactacao.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
				lactacao.setQuantDoados(rs.getInt("quantDoados"));
				lactacao.setQuantRecebidos(rs.getInt("quantRecebidos"));
				lactacao.setQuantMortos(rs.getInt("quantMortos"));
				lactacao.setQuantAtual(rs.getInt("quantAtual"));
				lactacoes.add(lactacao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lactacoes;
	}

	public Lactacao get(Integer codigo) {
		Lactacao lactacao = new Lactacao();
		String sql = "select*from lactacao where idLactacao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			lactacao.setCodigo(rs.getInt("idLactacao"));
			lactacao.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
			lactacao.setQuantDoados(rs.getInt("quantDoados"));
			lactacao.setQuantRecebidos(rs.getInt("quantRecebidos"));
			lactacao.setQuantMortos(rs.getInt("quantMortos"));
			lactacao.setQuantAtual(rs.getInt("quantAtual"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lactacao;
	}
}
