package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.CompradorDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.NotaVendaDAO;
import br.edu.unoesc.projetofinal.dao.VendaMatrizDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.VendaMatriz;

public class VendaMatrizJDBC implements VendaMatrizDAO {
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();
	private CompradorDAO compradorDao = DaoFactory.get().compradorDao();
	private NotaVendaDAO notaDao = DaoFactory.get().notaVendaDao();

	public void store(VendaMatriz venda) {
		String sql = "insert into vendamatriz values(null,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) venda.getData());
			ps.setDouble(2, venda.getValor());
			ps.setInt(3, venda.getComprador().getCodigo());
			ps.setLong(4, venda.getGta());
			ps.setInt(5, venda.getNota().getCodigo());
			ps.setDouble(6, venda.getPesoTotal());
			ps.setDouble(7, venda.getPesoMedio());
			ps.setInt(8, venda.getMatriz().getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(VendaMatriz venda) {
		String sql = "update vendamatriz set dataVenda=?,valor=?,idComprador=?,gta=?,idNota=?,pesoTotal=?,pesoMedio=?,idMatriz=? where idVenda=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) venda.getData());
			ps.setDouble(2, venda.getValor());
			ps.setInt(3, venda.getComprador().getCodigo());
			ps.setLong(4, venda.getGta());
			ps.setInt(5, venda.getNota().getCodigo());
			ps.setDouble(6, venda.getPesoTotal());
			ps.setDouble(7, venda.getPesoMedio());
			ps.setInt(8, venda.getMatriz().getCodigo());
			ps.setInt(9, venda.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(VendaMatriz venda) {
		String sql = "delete from vendamatriz where idVenda=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, venda.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<VendaMatriz> listarTodos() {
		List<VendaMatriz> vendas = new ArrayList<>();
		String sql = "select*from vendamatriz";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				VendaMatriz venda = new VendaMatriz();
				venda.setCodigo(rs.getInt("idVenda"));
				venda.setData(rs.getDate("dataVenda"));
				venda.setValor(rs.getDouble("valor"));
				venda.setComprador(compradorDao.get(rs.getInt("idComprador")));
				venda.setGta(rs.getLong("gta"));
				venda.setNota(notaDao.get(rs.getInt("idNota")));
				venda.setPesoTotal(rs.getDouble("pesoTotal"));
				venda.setPesoMedio(rs.getDouble("pesoMedio"));
				venda.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
				vendas.add(venda);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vendas;
	}

	public VendaMatriz get(Integer codigo) {
		VendaMatriz venda = new VendaMatriz();
		String sql = "select*from vendamatriz where idVenda=?";
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
			venda.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return venda;
	}
}