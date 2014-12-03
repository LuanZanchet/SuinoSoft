package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.FornecedorDAO;
import br.edu.unoesc.projetofinal.dao.NotaCompraDAO;
import br.edu.unoesc.projetofinal.dao.RacaDAO;
import br.edu.unoesc.projetofinal.dao.SemenDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Semen;

public class SemenJDBC implements SemenDAO {
	private RacaDAO racaDao = DaoFactory.get().racaDao();
	private FornecedorDAO fornecedorDao = DaoFactory.get().fornecedorDao();
	private NotaCompraDAO notaDao = DaoFactory.get().notaCompraDao();

	public void store(Semen semen) {
		String sql = "insert into semen values(null,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setLong(1, semen.getBrinco());
			ps.setDate(2, (Date) semen.getDataEntrada());
			ps.setDate(3, (Date) semen.getDataNascimento());
			ps.setInt(4, semen.getRaca().getCodigo());
			ps.setInt(5, semen.getFornecedor().getCodigo());
			ps.setInt(6, semen.getNota().getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Semen semen) {
		String sql = "update semen set brinco=?,dataEntrada=?,dataNascimento=?,idRaca=?,idFornecedor=?,idNota=? where idSemen=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setLong(1, semen.getBrinco());
			ps.setDate(2, (Date) semen.getDataEntrada());
			ps.setDate(3, (Date) semen.getDataNascimento());
			ps.setInt(4, semen.getRaca().getCodigo());
			ps.setInt(5, semen.getFornecedor().getCodigo());
			ps.setInt(6, semen.getNota().getCodigo());
			ps.setInt(7, semen.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Semen semen) {
		String sql = "delete from semen where idSemen=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, semen.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Semen> listarTodos() {
		List<Semen> semens = new ArrayList<>();
		String sql = "select*from semen";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Semen semen = new Semen();
				semen.setCodigo(rs.getInt("idSemen"));
				semen.setBrinco(rs.getLong("brinco"));
				semen.setDataEntrada(rs.getDate("dataEntrada"));
				semen.setDataNascimento(rs.getDate("dataNascimento"));
				semen.setRaca(racaDao.get(rs.getInt("idRaca")));
				semen.setFornecedor(fornecedorDao.get(rs.getInt("idFornecedor")));
				semen.setNota(notaDao.get(rs.getInt("idNota")));
				semens.add(semen);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return semens;
	}

	public Semen get(Integer codigo) {
		Semen semen = new Semen();
		String sql = "select*from semen where idSemen=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			semen.setCodigo(rs.getInt("idSemen"));
			semen.setBrinco(rs.getLong("brinco"));
			semen.setDataEntrada(rs.getDate("dataEntrada"));
			semen.setDataNascimento(rs.getDate("dataNascimento"));
			semen.setRaca(racaDao.get(rs.getInt("idRaca")));
			semen.setFornecedor(fornecedorDao.get(rs.getInt("idFornecedor")));
			semen.setNota(notaDao.get(rs.getInt("idNota")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return semen;
	}
}
