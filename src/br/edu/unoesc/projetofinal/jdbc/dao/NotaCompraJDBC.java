package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.FornecedorDAO;
import br.edu.unoesc.projetofinal.dao.NotaCompraDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.NotaCompra;

public class NotaCompraJDBC implements NotaCompraDAO {
	private static FornecedorDAO fornecedorDao = DaoFactory.get().fornecedorDao();

	public void store(NotaCompra nota) {
		String sql = "insert into notacompra values(null,?,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) nota.getData());
			ps.setDouble(2, nota.getValor());
			ps.setString(3, nota.getFormaPagamento());
			ps.setInt(4, nota.getFornecedor().getCodigo());
			ps.setLong(5, nota.getNumero());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void alter(NotaCompra nota) {
		String sql = "update notacompra set dataNota=?,valor=?,formaPagamento=?,idFornecedor=?,numero=? where idNota=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) nota.getData());
			ps.setDouble(2, nota.getValor());
			ps.setString(3, nota.getFormaPagamento());
			ps.setInt(4, nota.getFornecedor().getCodigo());
			ps.setLong(5, nota.getNumero());
			ps.setInt(6, nota.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(NotaCompra nota) {
		String sql = "delete from notacompra where idNota=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, nota.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<NotaCompra> listarTodos() {
		List<NotaCompra> notas = new ArrayList<>();
		String sql = "select*from notacompra";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				NotaCompra nota = new NotaCompra();
				nota.setCodigo(rs.getInt("idNota"));
				nota.setData(rs.getDate("dataNota"));
				nota.setValor(rs.getDouble("valor"));
				nota.setFormaPagamento(rs.getString("formaPagamento"));
				nota.setFornecedor(fornecedorDao.get(rs.getInt("idFornecedor")));
				nota.setNumero(rs.getLong("numero"));
				notas.add(nota);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notas;
	}

	public NotaCompra get(Integer codigo) {
		NotaCompra nota = new NotaCompra();
		String sql = "select*from notacompra where idNota=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			nota.setCodigo(rs.getInt("idNota"));
			nota.setData(rs.getDate("dataNota"));
			nota.setValor(rs.getDouble("valor"));
			nota.setFormaPagamento(rs.getString("formaPagamento"));
			nota.setFornecedor(fornecedorDao.get(rs.getInt("idFornecedor")));
			nota.setNumero(rs.getLong("numero"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nota;
	}
}