package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.LoteDAO;
import br.edu.unoesc.projetofinal.dao.OutrasSaidasDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.OutrasSaidas;

public class OutrasSaidasJDBC implements OutrasSaidasDAO {
	private LoteDAO loteDao = DaoFactory.get().loteDao();

	public void store(OutrasSaidas saida) {
		String sql = "insert into outrassaidas values(null,?,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, saida.getLote().getCodigo());
			ps.setString(2, saida.getTipoSaida());
			ps.setDate(3, (Date) saida.getData());
			ps.setInt(4, saida.getQuantidade());
			ps.setDouble(5, saida.getPesoTotal());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(OutrasSaidas saida) {
		String sql = "update outrassaidas set idLote=?,tipoSaida=?,dataSaida=?,quantidade=?,pesoTotal=? where idOutrasSaidas=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, saida.getLote().getCodigo());
			ps.setString(2, saida.getTipoSaida());
			ps.setDate(3, (Date) saida.getData());
			ps.setInt(4, saida.getQuantidade());
			ps.setDouble(5, saida.getPesoTotal());
			ps.setInt(6, saida.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(OutrasSaidas saida) {
		String sql = "delete from outrassaidas where idOutraSaidas=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, saida.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<OutrasSaidas> listarTodos() {
		List<OutrasSaidas> saidas = new ArrayList<>();
		String sql = "select*from outrassaidas";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				OutrasSaidas saida = new OutrasSaidas();
				saida.setCodigo(rs.getInt("idOutrasSaidas"));
				saida.setLote(loteDao.get(rs.getInt("idLote")));
				saida.setTipoSaida(rs.getString("tipoSaida"));
				saida.setData(rs.getDate("dataSaida"));
				saida.setQuantidade(rs.getInt("quantidade"));
				saida.setPesoTotal(rs.getDouble("pesoTotal"));
				saidas.add(saida);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return saidas;
	}

	public OutrasSaidas get(Integer codigo) {
		OutrasSaidas saida = new OutrasSaidas();
		String sql = "select*from outrassaidas where idOutrasSaidas=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			saida.setCodigo(rs.getInt("idOutrasSaidas"));
			saida.setLote(loteDao.get(rs.getInt("idLote")));
			saida.setTipoSaida(rs.getString("tipoSaida"));
			saida.setData(rs.getDate("dataSaida"));
			saida.setQuantidade(rs.getInt("quantidade"));
			saida.setPesoTotal(rs.getDouble("pesoTotal"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return saida;
	}
}
