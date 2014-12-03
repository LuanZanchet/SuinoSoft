package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.DesmameDAO;
import br.edu.unoesc.projetofinal.dao.LoteDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Desmame;

public class DesmameJDBC implements DesmameDAO {
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();
	private LoteDAO loteDao = DaoFactory.get().loteDao();

	public void store(Desmame desmame) {
		String sql = "insert into desmame values(null,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, desmame.getMatriz().getCodigo());
			ps.setDate(2, (Date) desmame.getData());
			ps.setDate(3, (Date) desmame.getPrevisaoDesmame());
			ps.setInt(4, desmame.getQuantidade());
			ps.setDouble(5, desmame.getPesoTotal());
			ps.setDouble(6, desmame.getPesoMedio());
			ps.setInt(7, desmame.getLote().getCodigo());
			ps.setString(8, desmame.getObsDesmame());
			ps.setString(9, desmame.getObsLote());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Desmame desmame) {
		String sql = "update desmame set idMatriz=?,dataDesmame=?,dataPrevista=?,quantidade=?,pesoTotal=?,pesoMedio=?,idLote=?,observacaoDesmame=?,observacaoLote=? where idDesmame=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, desmame.getMatriz().getCodigo());
			ps.setDate(2, (Date) desmame.getData());
			ps.setDate(3, (Date) desmame.getPrevisaoDesmame());
			ps.setInt(4, desmame.getQuantidade());
			ps.setDouble(5, desmame.getPesoTotal());
			ps.setDouble(6, desmame.getPesoMedio());
			ps.setInt(7, desmame.getLote().getCodigo());
			ps.setString(8, desmame.getObsDesmame());
			ps.setString(9, desmame.getObsLote());
			ps.setInt(10, desmame.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Desmame desmame) {
		String sql = "delete from desmame where idDesmame=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, desmame.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Desmame> listarTodos() {
		List<Desmame> desmames = new ArrayList<>();
		String sql = "select*from desmame";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Desmame desmame = new Desmame();
				desmame.setCodigo(rs.getInt("idDesmame"));
				desmame.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
				desmame.setData(rs.getDate("dataDesmame"));
				desmame.setPrevisaoDesmame(rs.getDate("dataPrevista"));
				desmame.setPesoTotal(rs.getDouble("pesoTotal"));
				desmame.setPesoMedio(rs.getDouble("pesoMedio"));
				desmame.setLote(loteDao.get(rs.getInt("idLote")));
				desmame.setObsDesmame(rs.getString("observacaoDesmame"));
				desmame.setObsLote(rs.getString("observacaoLote"));
				desmame.setQuantidade(rs.getInt("quantidade"));
				desmames.add(desmame);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return desmames;
	}

	public Desmame get(Integer codigo) {
		Desmame desmame = new Desmame();
		String sql = "select*from desmame where idDesmame=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			desmame.setCodigo(rs.getInt("idDesmame"));
			desmame.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
			desmame.setData(rs.getDate("dataDesmame"));
			desmame.setPrevisaoDesmame(rs.getDate("dataPrevista"));
			desmame.setPesoTotal(rs.getDouble("pesoTotal"));
			desmame.setPesoMedio(rs.getDouble("pesoMedio"));
			desmame.setLote(loteDao.get(rs.getInt("idLote")));
			desmame.setObsDesmame(rs.getString("observacaoDesmame"));
			desmame.setObsLote(rs.getString("observacaoLote"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return desmame;
	}
}
