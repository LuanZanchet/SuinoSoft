package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.FornecedorDAO;
import br.edu.unoesc.projetofinal.dao.MachoDAO;
import br.edu.unoesc.projetofinal.dao.NotaCompraDAO;
import br.edu.unoesc.projetofinal.dao.RacaDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Macho;

public class MachoJDBC implements MachoDAO {
	private static RacaDAO racaDao = DaoFactory.get().racaDao();
	private static FornecedorDAO fornecedorDao = DaoFactory.get().fornecedorDao();
	private static NotaCompraDAO notaCompraDao = DaoFactory.get().notaCompraDao();

	public void store(Macho macho) {
		String sql = "insert into macho values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setLong(1, macho.getMossa());
			ps.setLong(2, macho.getBrinco());
			ps.setInt(3, macho.getNota().getCodigo());
			ps.setDate(4, (Date) macho.getDataEntrada());
			ps.setDate(5, (Date) macho.getDataNascimento());
			ps.setDouble(6, macho.getPeso());
			ps.setDouble(7, macho.getValor());
			ps.setInt(8, macho.getRaca().getCodigo());
			ps.setInt(9, macho.getFornecedor().getCodigo());
			ps.setString(10, macho.getObservacao());
			ps.setString(11, macho.getStatus());
			ps.setInt(12, macho.getIdade());
			ps.setInt(13, macho.getTetasDireitas());
			ps.setInt(14, macho.getTetasEsquerdas());
			ps.setString(15, macho.getTipoUtilizacao());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Macho macho) {
		String sql = "update macho set mossa=?,brinco=?,idNota=?,dataEntrada=?,dataNascimento=?,peso=?,valor=?,idRaca=?,idFornecedor=?,observacao=?,statusAtual=?,idade=?,tetasDireitas=?,tetasEsquerdas=?,tipoUtilizacao=? where idMacho=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setLong(1, macho.getMossa());
			ps.setLong(2, macho.getBrinco());
			ps.setLong(3, macho.getNota().getCodigo());
			ps.setDate(4, (Date) macho.getDataEntrada());
			ps.setDate(5, (Date) macho.getDataNascimento());
			ps.setDouble(6, macho.getPeso());
			ps.setDouble(7, macho.getValor());
			ps.setInt(8, macho.getRaca().getCodigo());
			ps.setInt(9, macho.getFornecedor().getCodigo());
			ps.setString(10, macho.getObservacao());
			ps.setString(11, macho.getStatus());
			ps.setInt(12, macho.getIdade());
			ps.setInt(13, macho.getTetasDireitas());
			ps.setInt(14, macho.getTetasEsquerdas());
			ps.setString(15, macho.getTipoUtilizacao());
			ps.setInt(16, macho.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Macho macho) {
		String sql = "delete from macho where idMacho=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, macho.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Macho> listarTodos() {
		List<Macho> machos = new ArrayList<>();
		String sql = "select*from macho";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Macho macho = new Macho();
				macho.setCodigo(rs.getInt("idMacho"));
				macho.setMossa(rs.getLong("mossa"));
				macho.setBrinco(rs.getLong("brinco"));
				macho.setNota(notaCompraDao.get(rs.getInt("idNota")));
				macho.setDataEntrada(rs.getDate("dataEntrada"));
				macho.setDataNascimento(rs.getDate("dataNascimento"));
				macho.setPeso(rs.getDouble("peso"));
				macho.setValor(rs.getDouble("valor"));
				macho.setRaca(racaDao.get(rs.getInt("idRaca")));
				macho.setFornecedor(fornecedorDao.get(rs.getInt("idFornecedor")));
				macho.setObservacao(rs.getString("observacao"));
				macho.setStatus(rs.getString("statusAtual"));
				macho.setIdade(rs.getInt("idade"));
				macho.setTetasDireitas(rs.getInt("tetasDireitas"));
				macho.setTetasEsquerdas(rs.getInt("tetasEsquerdas"));
				macho.setTipoUtilizacao(rs.getString("tipoUtilizacao"));
				machos.add(macho);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return machos;
	}

	public Macho get(Integer codigo) {
		Macho macho = new Macho();
		String sql = "select*from macho where idMacho=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			macho.setCodigo(rs.getInt("idMacho"));
			macho.setMossa(rs.getLong("mossa"));
			macho.setBrinco(rs.getLong("brinco"));
			macho.setNota(notaCompraDao.get(rs.getInt("idNota")));
			macho.setDataEntrada(rs.getDate("dataEntrada"));
			macho.setDataNascimento(rs.getDate("dataNascimento"));
			macho.setPeso(rs.getDouble("peso"));
			macho.setValor(rs.getDouble("valor"));
			macho.setRaca(racaDao.get(rs.getInt("idRaca")));
			macho.setFornecedor(fornecedorDao.get(rs.getInt("idFornecedor")));
			macho.setObservacao(rs.getString("observacao"));
			macho.setStatus(rs.getString("statusAtual"));
			macho.setIdade(rs.getInt("idade"));
			macho.setTetasDireitas(rs.getInt("tetasDireitas"));
			macho.setTetasEsquerdas(rs.getInt("tetasEsquerdas"));
			macho.setTipoUtilizacao(rs.getString("tipoUtilizacao"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return macho;
	}
}
