package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.LoteDAO;
import br.edu.unoesc.projetofinal.dao.TransferenciaEntreLoteDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.TransferenciaEntreLotes;

public class TransferenciaEntreLotesJDBC implements TransferenciaEntreLoteDAO {
	private LoteDAO loteDao = DaoFactory.get().loteDao();

	public void store(TransferenciaEntreLotes transferencia) {
		String sql = "insert into transferenciaentrelotes values(null,?,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, transferencia.getLoteOrigem().getCodigo());
			ps.setInt(2, transferencia.getLoteDestino().getCodigo());
			ps.setInt(3, transferencia.getQuantidade());
			ps.setDate(4, (Date) transferencia.getData());
			ps.setDouble(5, transferencia.getPesoTotal());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(TransferenciaEntreLotes transferencia) {
		String sql = "update transferenciaentrelotes set idLoteOrigem=?,idLoteDestino=?,quantidade=?,dataTransferencia=?,pesoTotal=? where idTransferencia=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, transferencia.getLoteOrigem().getCodigo());
			ps.setInt(2, transferencia.getLoteDestino().getCodigo());
			ps.setInt(3, transferencia.getQuantidade());
			ps.setDate(4, (Date) transferencia.getData());
			ps.setDouble(5, transferencia.getPesoTotal());
			ps.setInt(6, transferencia.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(TransferenciaEntreLotes transferencia) {
		String sql = "delete from transferenciaentrelotes where idTransferencia=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, transferencia.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<TransferenciaEntreLotes> listarTodos() {
		List<TransferenciaEntreLotes> transferencias = new ArrayList<>();
		String sql = "select*from transferenciaentrelotes";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TransferenciaEntreLotes transferencia = new TransferenciaEntreLotes();
				transferencia.setCodigo(rs.getInt("idTransferencia"));
				transferencia.setLoteOrigem(loteDao.get(rs.getInt("idLoteOrigem")));
				transferencia.setLoteDestino(loteDao.get(rs.getInt("idLoteDestino")));
				transferencia.setQuantidade(rs.getInt("quantidade"));
				transferencia.setData(rs.getDate("dataTransferencia"));
				transferencia.setPesoTotal(rs.getDouble("pesoTotal"));
				transferencias.add(transferencia);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transferencias;
	}

	public TransferenciaEntreLotes get(Integer codigo) {
		TransferenciaEntreLotes transferencia = new TransferenciaEntreLotes();
		String sql = "select*from transferenciaentrelotes where idTransferencia=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			transferencia.setCodigo(rs.getInt("idTransferencia"));
			transferencia.setLoteOrigem(loteDao.get(rs.getInt("idLoteOrigem")));
			transferencia.setLoteDestino(loteDao.get(rs.getInt("idLoteDestino")));
			transferencia.setQuantidade(rs.getInt("quantidade"));
			transferencia.setData(rs.getDate("dataTransferencia"));
			transferencia.setPesoTotal(rs.getDouble("pesoTotal"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transferencia;
	}
}