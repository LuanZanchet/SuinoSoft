package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.FornecedorDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.NotaCompraDAO;
import br.edu.unoesc.projetofinal.dao.RacaDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Matriz;

public class MatrizJDBC implements MatrizDAO {
	private static RacaDAO racaDao = DaoFactory.get().racaDao();
	private static FornecedorDAO fornecedorDao = DaoFactory.get().fornecedorDao();
	private static NotaCompraDAO notaCompraDao = DaoFactory.get().notaCompraDao();

	public void store(Matriz matriz) {
		String sql = "insert into matriz values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setLong(1, matriz.getMossa());
			ps.setLong(2, matriz.getBrinco());
			ps.setInt(3, matriz.getNota().getCodigo());
			ps.setDate(4, (Date) matriz.getDataEntrada());
			ps.setDate(5, (Date) matriz.getDataNascimento());
			ps.setDouble(6, matriz.getPeso());
			ps.setDouble(7, matriz.getValor());
			ps.setInt(8, matriz.getRaca().getCodigo());
			ps.setInt(9, matriz.getFornecedor().getCodigo());
			ps.setString(10, matriz.getObservacao());
			ps.setString(11, matriz.getStatus());
			ps.setInt(12, matriz.getIdade());
			ps.setInt(13, matriz.getTetasDireitas());
			ps.setInt(14, matriz.getTetasEsquerdas());
			ps.setInt(15, matriz.getNumeroCiclos());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Matriz matriz) {
		String sql = "update matriz set mossa=?,brinco=?,idNota=?,dataEntrada=?,dataNascimento=?,peso=?,valor=?,idRaca=?,idFornecedor=?,observacao=?,statusAtual=?,idade=?,tetasDireitas=?,tetasEsquerdas=?,numeroCiclos=? where idMatriz=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setLong(1, matriz.getMossa());
			ps.setLong(2, matriz.getBrinco());
			ps.setLong(3, matriz.getNota().getCodigo());
			ps.setDate(4, (Date) matriz.getDataEntrada());
			ps.setDate(5, (Date) matriz.getDataNascimento());
			ps.setDouble(6, matriz.getPeso());
			ps.setDouble(7, matriz.getValor());
			ps.setInt(8, matriz.getRaca().getCodigo());
			ps.setInt(9, matriz.getFornecedor().getCodigo());
			ps.setString(10, matriz.getObservacao());
			ps.setString(11, matriz.getStatus());
			ps.setInt(12, matriz.getIdade());
			ps.setInt(13, matriz.getTetasDireitas());
			ps.setInt(14, matriz.getTetasEsquerdas());
			ps.setInt(15, matriz.getNumeroCiclos());
			ps.setInt(16, matriz.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Matriz matriz) {
		String sql = "delete from matriz where idMatriz=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, matriz.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Matriz> listarTodos() {
		List<Matriz> matrizes = new ArrayList<>();
		String sql = "select*from matriz";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Matriz matriz = new Matriz();
				matriz.setCodigo(rs.getInt("idMatriz"));
				matriz.setMossa(rs.getLong("mossa"));
				matriz.setBrinco(rs.getLong("brinco"));
				matriz.setNota(notaCompraDao.get(rs.getInt("idNota")));
				matriz.setDataEntrada(rs.getDate("dataEntrada"));
				matriz.setDataNascimento(rs.getDate("dataNascimento"));
				matriz.setPeso(rs.getDouble("peso"));
				matriz.setValor(rs.getDouble("valor"));
				matriz.setRaca(racaDao.get(rs.getInt("idRaca")));
				matriz.setFornecedor(fornecedorDao.get(rs.getInt("idFornecedor")));
				matriz.setObservacao(rs.getString("observacao"));
				matriz.setStatus(rs.getString("statusAtual"));
				matriz.setIdade(rs.getInt("idade"));
				matriz.setTetasDireitas(rs.getInt("tetasDireitas"));
				matriz.setTetasEsquerdas(rs.getInt("tetasEsquerdas"));
				matriz.setNumeroCiclos(rs.getInt("numeroCiclos"));
				matrizes.add(matriz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matrizes;
	}

	public Matriz get(Integer codigo) {
		Matriz matriz = new Matriz();
		String sql = "select*from matriz where idMatriz=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			matriz.setCodigo(rs.getInt("idMatriz"));
			matriz.setMossa(rs.getLong("mossa"));
			matriz.setBrinco(rs.getLong("brinco"));
			matriz.setNota(notaCompraDao.get(rs.getInt("idNota")));
			matriz.setDataEntrada(rs.getDate("dataEntrada"));
			matriz.setDataNascimento(rs.getDate("dataNascimento"));
			matriz.setPeso(rs.getDouble("peso"));
			matriz.setValor(rs.getDouble("valor"));
			matriz.setRaca(racaDao.get(rs.getInt("idRaca")));
			matriz.setFornecedor(fornecedorDao.get(rs.getInt("idFornecedor")));
			matriz.setObservacao(rs.getString("observacao"));
			matriz.setStatus(rs.getString("statusAtual"));
			matriz.setIdade(rs.getInt("idade"));
			matriz.setTetasDireitas(rs.getInt("tetasDireitas"));
			matriz.setTetasEsquerdas(rs.getInt("tetasEsquerdas"));
			matriz.setNumeroCiclos(rs.getInt("numeroCiclos"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return matriz;
	}
}