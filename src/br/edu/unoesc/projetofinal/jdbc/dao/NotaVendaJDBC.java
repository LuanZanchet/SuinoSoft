package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.CompradorDAO;
import br.edu.unoesc.projetofinal.dao.FornecedorDAO;
import br.edu.unoesc.projetofinal.dao.NotaVendaDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.NotaVenda;

public class NotaVendaJDBC implements NotaVendaDAO {
	private CompradorDAO compradorDao = DaoFactory.get().compradorDao();

	public void store(NotaVenda nota) {
		String sql = "insert into notavenda values(null,?,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) nota.getData());
			ps.setDouble(2, nota.getValor());
			ps.setString(3, nota.getFormaPagamento());
			ps.setInt(4, nota.getComprador().getCodigo());
			ps.setLong(5, nota.getNumero());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(NotaVenda nota) {
		String sql = "update notavenda set dataNota=?,valor=?,formaPagamento=?,idComprador=?,numero=? where idNotaVenda=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) nota.getData());
			ps.setDouble(2, nota.getValor());
			ps.setString(3, nota.getFormaPagamento());
			ps.setInt(4, nota.getComprador().getCodigo());
			ps.setLong(5, nota.getNumero());
			ps.setInt(6, nota.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void delete(NotaVenda nota) {
		String sql = "delete from notavenda where idNotaVenda=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, nota.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<NotaVenda> listarTodos() {
		List<NotaVenda> notas = new ArrayList<>();
		String sql = "select*from notavenda";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				NotaVenda nota = new NotaVenda();
				nota.setCodigo(rs.getInt("idNotaVenda"));
				nota.setData(rs.getDate("dataNota"));
				nota.setValor(rs.getDouble("valor"));
				nota.setFormaPagamento(rs.getString("formaPagamento"));
				nota.setComprador(compradorDao.get(rs.getInt("idComprador")));
				nota.setNumero(rs.getLong("numero"));
				notas.add(nota);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notas;
	}

	public NotaVenda get(Integer codigo) {
		NotaVenda nota = new NotaVenda();
		String sql = "select*from notavenda where idNotaVenda=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			nota.setCodigo(rs.getInt("idNotaVenda"));
			nota.setData(rs.getDate("dataNota"));
			nota.setValor(rs.getDouble("valor"));
			nota.setFormaPagamento(rs.getString("formaPagamento"));
			nota.setComprador(compradorDao.get(rs.getInt("idComprador")));
			nota.setNumero(rs.getLong("numero"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nota;
	}
}