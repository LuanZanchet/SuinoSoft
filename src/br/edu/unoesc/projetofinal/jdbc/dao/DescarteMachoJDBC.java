package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.CausaDAO;
import br.edu.unoesc.projetofinal.dao.DescarteMachoDAO;
import br.edu.unoesc.projetofinal.dao.MachoDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.DescarteMacho;

public class DescarteMachoJDBC implements DescarteMachoDAO {
	private CausaDAO causaDao = DaoFactory.get().causaDao();
	private MachoDAO machoDao = DaoFactory.get().machoDao();

	public void store(DescarteMacho descarte) {
		String sql = "insert into descartemacho values(null,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) descarte.getData());
			ps.setInt(2, descarte.getCausa().getCodigo());
			ps.setInt(3, descarte.getMacho().getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(DescarteMacho descarte) {
		String sql = "update descartemacho set dataDescarte=?,idCausa=?,idMacho=? where idDescarte=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) descarte.getData());
			ps.setInt(2, descarte.getCausa().getCodigo());
			ps.setInt(3, descarte.getMacho().getCodigo());
			ps.setInt(4, descarte.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(DescarteMacho descarte) {
		String slq = "delete from descartemacho where idDescarte=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(slq);
			ps.setInt(1, descarte.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<DescarteMacho> listarTodos() {
		List<DescarteMacho> descartes = new ArrayList<>();
		String sql = "select*from descartemacho";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DescarteMacho descarte = new DescarteMacho();
				descarte.setCodigo(rs.getInt("idDescarte"));
				descarte.setData(rs.getDate("dataDescarte"));
				descarte.setCausa(causaDao.get(rs.getInt("idCausa")));
				descarte.setMacho(machoDao.get(rs.getInt("idMacho")));
				descartes.add(descarte);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return descartes;
	}

	public DescarteMacho get(Integer codigo) {
		DescarteMacho descarte = new DescarteMacho();
		String sql = "select*from descartemacho where idDescarte=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			descarte.setCodigo(rs.getInt("idDescarte"));
			descarte.setData(rs.getDate("dataDescarte"));
			descarte.setCausa(causaDao.get(rs.getInt("idCausa")));
			descarte.setMacho(machoDao.get(rs.getInt("idMacho")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return descarte;
	}

}
