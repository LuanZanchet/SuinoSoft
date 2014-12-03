package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.CausaDAO;
import br.edu.unoesc.projetofinal.dao.LoteDAO;
import br.edu.unoesc.projetofinal.dao.MorteLeitaoCrecheDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.MorteLeitaoCreche;

public class MorteLeitaoCrecheJDBC implements MorteLeitaoCrecheDAO {
	private CausaDAO causaDao = DaoFactory.get().causaDao();
	private LoteDAO loteDao = DaoFactory.get().loteDao();

	public void store(MorteLeitaoCreche morte) {
		String sql = "insert into morteleitaocreche values(null,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) morte.getData());
			ps.setInt(2, morte.getCausa().getCodigo());
			ps.setInt(3, morte.getLote().getCodigo());
			ps.setInt(4, morte.getQuantidade());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(MorteLeitaoCreche morte) {
		String sql = "update morteleitaocreche set dataMorte=?,idCausa=?,idLote=?,quantidade=? where idMorteLeitaoCreche=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, (Date) morte.getData());
			ps.setInt(2, morte.getCausa().getCodigo());
			ps.setInt(3, morte.getLote().getCodigo());
			ps.setInt(4, morte.getQuantidade());
			ps.setInt(5, morte.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(MorteLeitaoCreche morte) {
		String sql = "delete from morteleitaocreche where idMorteLeitaoCreche=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, morte.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<MorteLeitaoCreche> listarTodos() {
		List<MorteLeitaoCreche> mortes = new ArrayList<>();
		String sql = "select*from morteleitaocreche";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				MorteLeitaoCreche morte = new MorteLeitaoCreche();
				morte.setCodigo(rs.getInt("idMorteLeitaoCreche"));
				morte.setData(rs.getDate("dataMorte"));
				morte.setCausa(causaDao.get(rs.getInt("idCausa")));
				morte.setLote(loteDao.get(rs.getInt("idLote")));
				morte.setQuantidade(rs.getInt("quantidade"));
				mortes.add(morte);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mortes;
	}

	public MorteLeitaoCreche get(Integer codigo) {
		MorteLeitaoCreche morte = new MorteLeitaoCreche();
		String sql = "select*from morteleitaocreche where idMorteLeitaoCreche=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			morte.setCodigo(rs.getInt("idMorteLeitaoCreche"));
			morte.setData(rs.getDate("dataMorte"));
			morte.setCausa(causaDao.get(rs.getInt("idCausa")));
			morte.setLote(loteDao.get(rs.getInt("idLote")));
			morte.setQuantidade(rs.getInt("quantidade"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return morte;
	}
}