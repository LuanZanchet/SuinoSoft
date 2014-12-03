package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.CompraMedicamentoDAO;
import br.edu.unoesc.projetofinal.dao.FornecedorDAO;
import br.edu.unoesc.projetofinal.dao.NotaCompraDAO;
import br.edu.unoesc.projetofinal.dao.VacinaDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.CompraMedicamento;

public class CompraMedicamentoJDBC implements CompraMedicamentoDAO {
	private VacinaDAO vacinaDao = DaoFactory.get().vacinaDao();
	private NotaCompraDAO notaDao = DaoFactory.get().notaCompraDao();
	private FornecedorDAO fornecedorDao = DaoFactory.get().fornecedorDao();

	public void store(CompraMedicamento compraMedicamento) {
		String sql = "insert into compramedicamento values(null,?,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, compraMedicamento.getVacina().getCodigo());
			ps.setDate(2, (Date) compraMedicamento.getData());
			ps.setInt(3, compraMedicamento.getNota().getCodigo());
			ps.setInt(4, compraMedicamento.getFornecedor().getCodigo());
			ps.setInt(5, compraMedicamento.getQuantidade());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(CompraMedicamento compraMedicamento) {
		String sql = "update compramedicamento set idVacina=?,dataCompra=?,idNota=?,idFornecedor=?,quantidade=? where idCompraMedicamento=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, compraMedicamento.getVacina().getCodigo());
			ps.setDate(2, (Date) compraMedicamento.getData());
			ps.setInt(3, compraMedicamento.getNota().getCodigo());
			ps.setInt(4, compraMedicamento.getFornecedor().getCodigo());
			ps.setInt(5, compraMedicamento.getQuantidade());
			ps.setInt(6, compraMedicamento.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(CompraMedicamento compraMedicamento) {
		String sql = "delete from compramedicamento where idCompraMedicamento=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, compraMedicamento.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<CompraMedicamento> listarTodos() {
		List<CompraMedicamento> compraMedicamentos = new ArrayList<>();
		String sql = "select*from compramedicamento";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CompraMedicamento compraMedicamento = new CompraMedicamento();
				compraMedicamento.setCodigo(rs.getInt("idCompraMedicamento"));
				compraMedicamento.setVacina(vacinaDao.get(rs.getInt("idVacina")));
				compraMedicamento.setData(rs.getDate("dataCompra"));
				compraMedicamento.setNota(notaDao.get(rs.getInt("idNota")));
				compraMedicamento.setFornecedor(fornecedorDao.get(rs.getInt("idFornecedor")));
				compraMedicamento.setQuantidade(rs.getInt("quantidade"));
				compraMedicamentos.add(compraMedicamento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return compraMedicamentos;
	}

	public CompraMedicamento get(Integer codigo) {
		CompraMedicamento compraMedicamento = new CompraMedicamento();
		String sql = "select*from compramedicamento where idCompraMedicamento=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			compraMedicamento.setCodigo(rs.getInt("idCompraMedicamento"));
			compraMedicamento.setVacina(vacinaDao.get(rs.getInt("idVacina")));
			compraMedicamento.setData(rs.getDate("dataCompra"));
			compraMedicamento.setNota(notaDao.get(rs.getInt("idNota")));
			compraMedicamento.setFornecedor(fornecedorDao.get(rs.getInt("idFornecedor")));
			compraMedicamento.setQuantidade(rs.getInt("quantidade"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return compraMedicamento;
	}

}
