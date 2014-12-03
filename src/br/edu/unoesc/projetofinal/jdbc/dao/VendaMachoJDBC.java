package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.edu.unoesc.projetofinal.dao.CompradorDAO;
import br.edu.unoesc.projetofinal.dao.MachoDAO;
import br.edu.unoesc.projetofinal.dao.NotaVendaDAO;
import br.edu.unoesc.projetofinal.dao.VendaMachoDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.VendaMacho;

public class VendaMachoJDBC implements VendaMachoDAO {
	private CompradorDAO compradorDao = DaoFactory.get().compradorDao();
	private MachoDAO machoDao = DaoFactory.get().machoDao();
	private NotaVendaDAO notaDao = DaoFactory.get().notaVendaDao();

	public void store(VendaMacho venda) {
		String sql = "insert into vendamacho values(null,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) venda.getData());
			ps.setDouble(2, venda.getValor());
			ps.setInt(3, venda.getComprador().getCodigo());
			ps.setLong(4, venda.getGta());
			ps.setInt(5, venda.getNota().getCodigo());
			ps.setDouble(6, venda.getPesoTotal());
			ps.setDouble(7, venda.getPesoMedio());
			ps.setInt(8, venda.getMacho().getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(VendaMacho venda) {
		String sql = "update vendamacho set dataVenda=?,valor=?,idComprador=?,gta=?,idNota=?,pesoTotal=?,pesoMedio=?,idMacho=? where idVenda=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) venda.getData());
			ps.setDouble(2, venda.getValor());
			ps.setInt(3, venda.getComprador().getCodigo());
			ps.setLong(4, venda.getGta());
			ps.setInt(5, venda.getNota().getCodigo());
			ps.setDouble(6, venda.getPesoTotal());
			ps.setDouble(7, venda.getPesoMedio());
			ps.setInt(8, venda.getMacho().getCodigo());
			ps.setInt(9, venda.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(VendaMacho venda) {
		String sql = "delete from vendamacho where idVenda=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, venda.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<VendaMacho> listarTodos() {
		List<VendaMacho> vendas = new ArrayList<>();
		String sql = "select*from vendamacho";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				VendaMacho venda = new VendaMacho();
				venda.setCodigo(rs.getInt("idVenda"));
				venda.setData(rs.getDate("dataVenda"));
				venda.setValor(rs.getDouble("valor"));
				venda.setComprador(compradorDao.get(rs.getInt("idComprador")));
				venda.setGta(rs.getLong("gta"));
				venda.setNota(notaDao.get(rs.getInt("idNota")));
				venda.setPesoTotal(rs.getDouble("pesoTotal"));
				venda.setPesoMedio(rs.getDouble("pesoMedio"));
				venda.setMacho(machoDao.get(rs.getInt("idMacho")));
				vendas.add(venda);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vendas;
	}

	public VendaMacho get(Integer codigo) {
		VendaMacho venda = new VendaMacho();
		String sql = "select*from vendamacho where idVenda=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			venda.setCodigo(rs.getInt("idVenda"));
			venda.setData(rs.getDate("dataVenda"));
			venda.setValor(rs.getDouble("valor"));
			venda.setComprador(compradorDao.get(rs.getInt("idComprador")));
			venda.setGta(rs.getLong("gta"));
			venda.setNota(notaDao.get(rs.getInt("idNota")));
			venda.setPesoTotal(rs.getDouble("pesoTotal"));
			venda.setPesoMedio(rs.getDouble("pesoMedio"));
			venda.setMacho(machoDao.get(rs.getInt("idMacho")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return venda;
	}
}