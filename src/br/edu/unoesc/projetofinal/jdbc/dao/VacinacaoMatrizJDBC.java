package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.VacinaDAO;
import br.edu.unoesc.projetofinal.dao.VacinacaoMatrizDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.VacinacaoMatriz;

public class VacinacaoMatrizJDBC implements VacinacaoMatrizDAO {
	private VacinaDAO vacinaDao = DaoFactory.get().vacinaDao();
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();

	public void store(VacinacaoMatriz vacinacao) {
		String sql = "insert into vacinacaomatriz values(null,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, vacinacao.getVacina().getCodigo());
			ps.setDate(2, (Date) vacinacao.getData());
			ps.setDouble(3, vacinacao.getQuantidadeUsada());
			ps.setInt(4, vacinacao.getMatriz().getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(VacinacaoMatriz vacinacao) {
		String sql = "update vacinacaomatriz set idVacina=?,dataVacinacao=?,quantidadeUsada=?,idMatriz=? where idVacinacao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, vacinacao.getVacina().getCodigo());
			ps.setDate(2, (Date) vacinacao.getData());
			ps.setDouble(3, vacinacao.getQuantidadeUsada());
			ps.setInt(4, vacinacao.getMatriz().getCodigo());
			ps.setInt(5, vacinacao.getCodigo());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(VacinacaoMatriz vacinacao) {
		String sql = "delete from vacinacao where idVacinacao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, vacinacao.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<VacinacaoMatriz> listarTodos() {
		List<VacinacaoMatriz> vacinacoes = new ArrayList<>();
		String sql = "select*from vacinacaomatriz";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				VacinacaoMatriz vacinacao = new VacinacaoMatriz();
				vacinacao.setCodigo(rs.getInt("idVacinacao"));
				vacinacao.setVacina(vacinaDao.get(rs.getInt("idVacina")));
				vacinacao.setData(rs.getDate("dataVacina"));
				vacinacao.setQuantidadeUsada(rs.getDouble("quantidadeUsada"));
				vacinacao.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
				vacinacoes.add(vacinacao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vacinacoes;
	}

	public VacinacaoMatriz get(Integer codigo) {
		VacinacaoMatriz vacinacao = new VacinacaoMatriz();
		String sql = "select*from vacinacaomatriz where idVacinacao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vacinacao.setCodigo(rs.getInt("idVacinacao"));
			vacinacao.setVacina(vacinaDao.get(rs.getInt("idVacina")));
			vacinacao.setData(rs.getDate("dataVacina"));
			vacinacao.setQuantidadeUsada(rs.getDouble("quantidadeUsada"));
			vacinacao.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vacinacao;
	}
}