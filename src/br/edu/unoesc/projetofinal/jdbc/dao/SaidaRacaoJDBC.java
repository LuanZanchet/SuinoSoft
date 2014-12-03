package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.RacaoDAO;
import br.edu.unoesc.projetofinal.dao.SaidaRacaoDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.SaidaRacao;

public class SaidaRacaoJDBC implements SaidaRacaoDAO {
	private RacaoDAO racaoDao = DaoFactory.get().racaoDao();

	public void store(SaidaRacao saida) {
		String sql = "insert into saidaracao values(null,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, saida.getRacao().getCodigo());
			ps.setInt(2, saida.getQuantidade());
			ps.setDate(3, (Date) saida.getData());
			ps.setString(4, saida.getDestino());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(SaidaRacao saida) {
		String sql = "update saidaracao set idRacao=?,quantidade=?,dataSaida=?,destino=? where idSaidaRacao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, saida.getRacao().getCodigo());
			ps.setInt(2, saida.getQuantidade());
			ps.setDate(3, (Date) saida.getData());
			ps.setString(4, saida.getDestino());
			ps.setInt(5, saida.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(SaidaRacao saida) {
		String sql = "delete from saidaracao where idSaidaRacao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, saida.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<SaidaRacao> listarTodos() {
		List<SaidaRacao> saidas = new ArrayList<>();
		String slq = "select*from saidaracao";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(slq);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SaidaRacao saida = new SaidaRacao();
				saida.setCodigo(rs.getInt("idSaidaRacao"));
				saida.setRacao(racaoDao.get(rs.getInt("idRacao")));
				saida.setQuantidade(rs.getInt("quantidade"));
				saida.setData(rs.getDate("dataSaida"));
				saida.setDestino(rs.getString("destino"));
				saidas.add(saida);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return saidas;
	}

	public SaidaRacao get(Integer codigo) {
		SaidaRacao saida = new SaidaRacao();
		String sql = "select*from saidaracao where idSaidaRacao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			saida.setCodigo(rs.getInt("idSaidaRacao"));
			saida.setRacao(racaoDao.get(rs.getInt("idRacao")));
			saida.setQuantidade(rs.getInt("quantidade"));
			saida.setData(rs.getDate("dataSaida"));
			saida.setDestino(rs.getString("destino"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return saida;
	}
}
