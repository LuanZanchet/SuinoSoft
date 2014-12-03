package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.LoteDAO;
import br.edu.unoesc.projetofinal.model.Lote;

public class LoteJDBC implements LoteDAO {

	public void store(Lote lote) {
		String sql = "insert into lote values(null,0,0,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setLong(1, lote.getNumero());
			ps.setString(2, lote.getObservacao());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Lote lote) {
		String sql = "update lote set quantidadeLeitao=?,idade=?,numero=?,observacao=? where idLote=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, lote.getQuantidadeLeitao());
			ps.setInt(2, lote.getIdade());
			ps.setLong(3, lote.getNumero());
			ps.setString(4, lote.getObservacao());
			ps.setInt(5, lote.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Lote lote) {
		String sql = "delete from lote where idLote=?";
		PreparedStatement ps;
		try {
			ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, lote.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Lote> listarTodos() {
		List<Lote> lotes = new ArrayList<>();
		String sql = "select*from lote";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Lote lote = new Lote();
				lote.setCodigo(rs.getInt("idLote"));
				lote.setQuantidadeLeitao(rs.getInt("quantidadeLeitao"));
				lote.setIdade(rs.getInt("idade"));
				lote.setNumero(rs.getLong("numero"));
				lote.setObservacao(rs.getString("observacao"));
				lotes.add(lote);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lotes;
	}

	public Lote get(Integer codigo) {
		Lote lote = new Lote();
		String sql = "select*from lote where idLote=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			lote.setCodigo(rs.getInt("idLote"));
			lote.setQuantidadeLeitao(rs.getInt("quantidadeLeitao"));
			lote.setIdade(rs.getInt("idade"));
			lote.setNumero(rs.getLong("numero"));
			lote.setObservacao(rs.getString("observacao"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lote;
	}
}