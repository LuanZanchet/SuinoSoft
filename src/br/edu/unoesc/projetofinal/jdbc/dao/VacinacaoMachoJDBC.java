package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.MachoDAO;
import br.edu.unoesc.projetofinal.dao.VacinaDAO;
import br.edu.unoesc.projetofinal.dao.VacinacaoMachoDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.VacinacaoMacho;

public class VacinacaoMachoJDBC implements VacinacaoMachoDAO {
	private VacinaDAO vacinaDao = DaoFactory.get().vacinaDao();
	private MachoDAO machoDao = DaoFactory.get().machoDao();

	public void store(VacinacaoMacho vacinacao) {
		String sql = "insert into vacinacaomacho values(null,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, vacinacao.getVacina().getCodigo());
			ps.setDate(2, (Date) vacinacao.getData());
			ps.setDouble(3, vacinacao.getQuantidadeUsada());
			ps.setInt(4, vacinacao.getMacho().getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(VacinacaoMacho vacinacao) {
		String sql = "update vacinacaomacho set idVacina=?,dataVacinacao=?,quantidadeUsada=?,idMacho=? where idVacinacao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, vacinacao.getVacina().getCodigo());
			ps.setDate(2, (Date) vacinacao.getData());
			ps.setDouble(3, vacinacao.getQuantidadeUsada());
			ps.setInt(4, vacinacao.getMacho().getCodigo());
			ps.setInt(5, vacinacao.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(VacinacaoMacho vacinacao) {
		String sql = "delete from vacinacaomacho where idVacinacao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, vacinacao.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<VacinacaoMacho> listarTodos() {
		List<VacinacaoMacho> vacinacoes = new ArrayList<>();
		String sql = "select*from vacinacaomacho";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				VacinacaoMacho vacinacao = new VacinacaoMacho();
				vacinacao.setCodigo(rs.getInt("idVacinacao"));
				vacinacao.setVacina(vacinaDao.get(rs.getInt("idVacina")));
				vacinacao.setData(rs.getDate("dataVacinacao"));
				vacinacao.setQuantidadeUsada(rs.getDouble("quantidadeUsada"));
				vacinacao.setMacho(machoDao.get(rs.getInt("idMacho")));
				vacinacoes.add(vacinacao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vacinacoes;
	}

	public VacinacaoMacho get(Integer codigo) {
		VacinacaoMacho vacinacao = new VacinacaoMacho();
		String sql = "select*from vacinacaomacho where idVacinacao=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vacinacao.setCodigo(rs.getInt("idVacinacao"));
			vacinacao.setVacina(vacinaDao.get(rs.getInt("idVacina")));
			vacinacao.setData(rs.getDate("dataVacinacao"));
			vacinacao.setQuantidadeUsada(rs.getDouble("quantidadeUsada"));
			vacinacao.setMacho(machoDao.get(rs.getInt("idMacho")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vacinacao;
	}
}