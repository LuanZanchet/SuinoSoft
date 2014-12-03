package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.CausaDAO;
import br.edu.unoesc.projetofinal.dao.MachoDAO;
import br.edu.unoesc.projetofinal.dao.MorteMachoDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.MorteMacho;

public class MorteMachoJDBC implements MorteMachoDAO {
	private MachoDAO machoDao = DaoFactory.get().machoDao();
	private CausaDAO causaDao = DaoFactory.get().causaDao();

	public void store(MorteMacho morte) {
		String sql = "insert into mortemacho values(null,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) morte.getData());
			ps.setInt(2, morte.getCausa().getCodigo());
			ps.setInt(3, morte.getMacho().getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(MorteMacho morte) {
		String sql = "update mortemacho set dataMorte=?,idCausa=?,idMacho=? where idMorte=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) morte.getData());
			ps.setInt(2, morte.getCausa().getCodigo());
			ps.setInt(3, morte.getMacho().getCodigo());
			ps.setInt(4, morte.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(MorteMacho morte) {
		String sql = "delete from mortemacho where idMorte=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, morte.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<MorteMacho> listarTodos() {
		List<MorteMacho> mortes = new ArrayList<>();
		String sql = "select*from mortemacho";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MorteMacho morte = new MorteMacho();
				morte.setCodigo(rs.getInt("idMorte"));
				morte.setData(rs.getDate("dataMorte"));
				morte.setCausa(causaDao.get(rs.getInt("idCausa")));
				morte.setMacho(machoDao.get(rs.getInt("idMacho")));
				mortes.add(morte);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mortes;
	}

	public MorteMacho get(Integer codigo) {
		MorteMacho morte = new MorteMacho();
		String sql = "select*from mortemacho where idMorte=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			morte.setCodigo(rs.getInt("idMorte"));
			morte.setData(rs.getDate("dataMorte"));
			morte.setCausa(causaDao.get(rs.getInt("idCausa")));
			morte.setMacho(machoDao.get(rs.getInt("idMacho")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return morte;
	}
}