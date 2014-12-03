package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.MovimentacaoDeLeitaoDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.MovimentacaoDeLeitao;

public class MovimentacaoDeLeitaoJDBC implements MovimentacaoDeLeitaoDAO {
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();

	public void store(MovimentacaoDeLeitao movimentacao) {
		String sql = "insert into movimentacaoleitao values(null,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, movimentacao.getMatrizDoadora().getCodigo());
			ps.setInt(2, movimentacao.getMatrizReceptora().getCodigo());
			ps.setDate(3, (Date) movimentacao.getData());
			ps.setInt(4, movimentacao.getQuantidadeLeitao());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(MovimentacaoDeLeitao movimentacao) {
		String sql = "update movimentacaoleitao set idMatrizDoadora=?,idMatrizReceptora=?,dataMovimentacao=?,quantidade=? where idMovimentacaoLeitao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, movimentacao.getMatrizDoadora().getCodigo());
			ps.setInt(2, movimentacao.getMatrizReceptora().getCodigo());
			ps.setDate(3, (Date) movimentacao.getData());
			ps.setInt(4, movimentacao.getQuantidadeLeitao());
			ps.setInt(5, movimentacao.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(MovimentacaoDeLeitao movimentacao) {
		String sql = "delete from movimentacaoleitao where idMovimentacaoLeitao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, movimentacao.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<MovimentacaoDeLeitao> listarTodos() {
		List<MovimentacaoDeLeitao> movimentacoes = new ArrayList<>();
		String sql = "select*from movimentacaoleitao";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MovimentacaoDeLeitao movimentacao = new MovimentacaoDeLeitao();
				movimentacao.setCodigo(rs.getInt("idMovimentacaoLeitao"));
				movimentacao.setMatrizDoadora(matrizDao.get(rs.getInt("idMatrizDoadora")));
				movimentacao.setMatrizReceptora(matrizDao.get(rs.getInt("idMatrizReceptora")));
				movimentacao.setData(rs.getDate("dataMovimentacao"));
				movimentacao.setQuantidadeLeitao(rs.getInt("quantidade"));
				movimentacoes.add(movimentacao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movimentacoes;
	}

	public MovimentacaoDeLeitao get(Integer codigo) {
		MovimentacaoDeLeitao movimentacao = new MovimentacaoDeLeitao();
		String sql = "select*from movimentacaoleitao where idMovimentacaoLeitao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			movimentacao.setCodigo(rs.getInt("idMovimentacaoLeitao"));
			movimentacao.setMatrizDoadora(matrizDao.get(rs.getInt("idMatrizDoadora")));
			movimentacao.setMatrizReceptora(matrizDao.get(rs.getInt("idMatrizReceptora")));
			movimentacao.setData(rs.getDate("dataMovimentacao"));
			movimentacao.setQuantidadeLeitao(rs.getInt("quantidade"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movimentacao;
	}
}