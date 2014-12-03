package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.CompradorDAO;
import br.edu.unoesc.projetofinal.dao.LoteDAO;
import br.edu.unoesc.projetofinal.dao.NotaVendaDAO;
import br.edu.unoesc.projetofinal.dao.VendaLeitaoDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.VendaLeitao;

public class VendaLeitaoJDBC implements VendaLeitaoDAO {
	private CompradorDAO compradorDao = DaoFactory.get().compradorDao();
	private NotaVendaDAO notaDao = DaoFactory.get().notaVendaDao();
	private LoteDAO loteDao = DaoFactory.get().loteDao();

	public void store(VendaLeitao venda) {
		String sql = "insert into vendaleitao values(null,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) venda.getData());
			ps.setDouble(2, venda.getValor());
			ps.setInt(3, venda.getComprador().getCodigo());
			ps.setLong(4, venda.getGta());
			ps.setInt(5, venda.getNota().getCodigo());
			ps.setDouble(6, venda.getPesoTotal());
			ps.setDouble(7, venda.getPesoMedio());
			ps.setInt(8, venda.getLote().getCodigo());
			ps.setInt(9, venda.getQuantidade());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(VendaLeitao venda) {
		String sql = "update vendaleitao set dataVenda=?,valor=?,idComprador=?,gta=?,idNota=?,pesoTotal=?,pesoMedio=?,idLote=?,quantidade=? where idVendaLeitao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) venda.getData());
			ps.setDouble(2, venda.getValor());
			ps.setInt(3, venda.getComprador().getCodigo());
			ps.setLong(4, venda.getGta());
			ps.setInt(5, venda.getNota().getCodigo());
			ps.setDouble(6, venda.getPesoTotal());
			ps.setDouble(7, venda.getPesoMedio());
			ps.setInt(8, venda.getLote().getCodigo());
			ps.setInt(9, venda.getQuantidade());
			ps.setInt(10, venda.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(VendaLeitao venda) {
		String sql = "delete from vendaleitao where idVendaLeitao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, venda.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<VendaLeitao> listarTodos() {
		List<VendaLeitao> vendas = new ArrayList<>();
		String sql = "select*from vendaleitao";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				VendaLeitao venda = new VendaLeitao();
				venda.setCodigo(rs.getInt("idVendaLeitao"));
				venda.setData(rs.getDate("dataVenda"));
				venda.setValor(rs.getDouble("valor"));
				venda.setComprador(compradorDao.get(rs.getInt("idComprador")));
				venda.setGta(rs.getLong("gta"));
				venda.setNota(notaDao.get(rs.getInt("idNota")));
				venda.setPesoMedio(rs.getDouble("pesoMedio"));
				venda.setPesoTotal(rs.getDouble("pesoTotal"));
				venda.setLote(loteDao.get(rs.getInt("idLote")));
				venda.setQuantidade(rs.getInt("quantidade"));
				vendas.add(venda);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vendas;
	}

	public VendaLeitao get(Integer codigo) {
		VendaLeitao venda = new VendaLeitao();
		String sql = "select*from vendaleitao where idVendaLeitao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			venda.setCodigo(rs.getInt("idVendaLeitao"));
			venda.setData(rs.getDate("dataVenda"));
			venda.setValor(rs.getDouble("valor"));
			venda.setComprador(compradorDao.get(rs.getInt("idComprador")));
			venda.setGta(rs.getLong("gta"));
			venda.setNota(notaDao.get(rs.getInt("idNota")));
			venda.setPesoMedio(rs.getDouble("pesoMedio"));
			venda.setPesoTotal(rs.getDouble("pesoTotal"));
			venda.setLote(loteDao.get(rs.getInt("idLote")));
			venda.setQuantidade(rs.getInt("quantidade"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return venda;
	}
}