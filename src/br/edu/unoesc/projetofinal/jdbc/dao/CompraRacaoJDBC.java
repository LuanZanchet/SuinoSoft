package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.CompraRacaoDAO;
import br.edu.unoesc.projetofinal.dao.FornecedorDAO;
import br.edu.unoesc.projetofinal.dao.NotaCompraDAO;
import br.edu.unoesc.projetofinal.dao.RacaoDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.CompraRacao;

public class CompraRacaoJDBC implements CompraRacaoDAO {
	private RacaoDAO racaoDao = DaoFactory.get().racaoDao();
	private NotaCompraDAO notaDao = DaoFactory.get().notaCompraDao();
	private FornecedorDAO fornecedorDao = DaoFactory.get().fornecedorDao();

	public void store(CompraRacao compraRacao) {
		String sql = "insert into compraracao values(null,?,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, compraRacao.getRacao().getCodigo());
			ps.setInt(2, compraRacao.getNota().getCodigo());
			ps.setInt(3, compraRacao.getFornecedor().getCodigo());
			ps.setDate(4, (Date) compraRacao.getData());
			ps.setInt(5, compraRacao.getQuantidade());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void alter(CompraRacao compraRacao) {
		String sql = "update compraracao set idRacao=?,idNota=?,idFornecedor=?,dataCompra=?,quantidade=? where idCompraRacao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, compraRacao.getRacao().getCodigo());
			ps.setInt(2, compraRacao.getNota().getCodigo());
			ps.setInt(3, compraRacao.getFornecedor().getCodigo());
			ps.setDate(4, (Date) compraRacao.getData());
			ps.setInt(5, compraRacao.getQuantidade());
			ps.setInt(6, compraRacao.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(CompraRacao compraRacao) {
		String sql = "delete from compraracao where idCompraRacao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, compraRacao.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<CompraRacao> listarTodos() {
		List<CompraRacao> compraRacoes = new ArrayList<>();
		String sql = "select*from compraracao";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CompraRacao compraRacao = new CompraRacao();
				compraRacao.setCodigo(rs.getInt("idCompraRacao"));
				compraRacao.setRacao(racaoDao.get(rs.getInt("idRacao")));
				compraRacao.setNota(notaDao.get(rs.getInt("idNota")));
				compraRacao.setFornecedor(fornecedorDao.get(rs.getInt("idFornecedor")));
				compraRacao.setData(rs.getDate("dataCompra"));
				compraRacao.setQuantidade(rs.getInt("quantidade"));
				compraRacoes.add(compraRacao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return compraRacoes;
	}

	public CompraRacao get(Integer codigo) {
		CompraRacao compraRacao = new CompraRacao();
		String sql = "select*from compraracao where idCompraRacao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			compraRacao.setCodigo(rs.getInt("idCompraRacao"));
			compraRacao.setRacao(racaoDao.get(rs.getInt("idRacao")));
			compraRacao.setNota(notaDao.get(rs.getInt("idNota")));
			compraRacao.setFornecedor(fornecedorDao.get(rs.getInt("idFornecedor")));
			compraRacao.setData(rs.getDate("dataCompra"));
			compraRacao.setQuantidade(rs.getInt("quantidade"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return compraRacao;
	}

}
