package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.EnderecoDAO;
import br.edu.unoesc.projetofinal.dao.GranjaDAO;
import br.edu.unoesc.projetofinal.dao.ProprietarioDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Granja;

public class GranjaJDBC implements GranjaDAO {
	private static EnderecoDAO enderecoDao = DaoFactory.get().enderecoDao();
	private static ProprietarioDAO proprietarioDao = DaoFactory.get().proprietarioDao();

	public void store(Granja granja) {
		String sql = "insert into granja values(null,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, granja.getProprietario().getCodigo());
			ps.setInt(2, granja.getEndereco().getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Granja granja) {
		String sql = "update granja set idProprietario=?,idEndereco=? where idGranja=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, granja.getProprietario().getCodigo());
			ps.setInt(2, granja.getEndereco().getCodigo());
			ps.setInt(3, granja.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Granja granja) {
		String sql = "delete from granja where idGranja=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, granja.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Granja> listarTodos() {
		List<Granja> granjas = new ArrayList<>();
		String sql = "select*from granja";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Granja granja = new Granja();
				granja.setCodigo(rs.getInt("idGranja"));
				granja.setProprietario(proprietarioDao.get(rs.getInt("idProprietario")));
				granja.setEndereco(enderecoDao.get(rs.getInt("idEndereco")));
				granjas.add(granja);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return granjas;
	}

	public Granja get(Integer codigo) {
		Granja granja = new Granja();
		String sql = "select*from granja where idGranja=?";
		PreparedStatement ps;
		try {
			ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			granja.setCodigo(rs.getInt("idGranja"));
			granja.setProprietario(proprietarioDao.get(rs.getInt("idProprietario")));
			granja.setEndereco(enderecoDao.get(rs.getInt("idEndereco")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return granja;
	}
}