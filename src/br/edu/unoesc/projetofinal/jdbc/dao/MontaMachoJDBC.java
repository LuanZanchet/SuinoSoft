package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.CoberturaDAO;
import br.edu.unoesc.projetofinal.dao.FuncionarioDAO;
import br.edu.unoesc.projetofinal.dao.MachoDAO;
import br.edu.unoesc.projetofinal.dao.MontaMachoDao;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.MontaMacho;

public class MontaMachoJDBC implements MontaMachoDao {
	private CoberturaDAO coberturaDao=DaoFactory.get().coberturaDao();
	private MachoDAO machoDao=DaoFactory.get().machoDao();
	private FuncionarioDAO funcionarioDao=DaoFactory.get().funcionarioDao();
	
	public void store(MontaMacho monta) {
		String sql = "insert into montaMacho values(null,?,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, monta.getData());
			ps.setInt(2, monta.getCobertura().getCodigo());
			ps.setInt(3, monta.getMacho().getCodigo());
			ps.setInt(4, monta.getFuncionario().getCodigo());
			ps.setString(5, monta.getTipo());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(MontaMacho monta) {
		String sql = "update montaMacho set dataMonta=?,idCobertura=?,idMacho=?,idFuncionario=?,tipo=? where idMontaMacho=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, monta.getData());
			ps.setInt(2, monta.getCobertura().getCodigo());
			ps.setInt(3, monta.getMacho().getCodigo());
			ps.setInt(4, monta.getFuncionario().getCodigo());
			ps.setString(5, monta.getTipo());
			ps.setInt(6, monta.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(MontaMacho monta) {
		String sql = "delete from montaMacho where idMontaMacho=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, monta.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<MontaMacho> listarTodos() {
		List<MontaMacho>montas=new ArrayList<>();
		String sql="select*from montaMacho";
		try {
			PreparedStatement ps=ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				MontaMacho monta=new MontaMacho();
				monta.setCodigo(rs.getInt("idMontaMacho"));
				monta.setData(rs.getDate("dataMonta"));
				monta.setCobertura(coberturaDao.get(rs.getInt("idCobertura")));
				monta.setMacho(machoDao.get(rs.getInt("idMacho")));
				monta.setFuncionario(funcionarioDao.get(rs.getInt("idFuncionario")));
				monta.setTipo(rs.getString("tipo"));
				montas.add(monta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return montas;
	}

	public MontaMacho get(Integer codigo) {
		MontaMacho monta=new MontaMacho();
		String sql="select*from montaMacho where idMontaMacho=?";
		try {
			PreparedStatement ps=ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs=ps.executeQuery();
			rs.next();
			monta.setCodigo(rs.getInt("idMontaMacho"));
			monta.setData(rs.getDate("dataMonta"));
			monta.setCobertura(coberturaDao.get(rs.getInt("idCobertura")));
			monta.setMacho(machoDao.get(rs.getInt("idMacho")));
			monta.setFuncionario(funcionarioDao.get(rs.getInt("idFuncionario")));
			monta.setTipo(rs.getString("tipo"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return monta;
	}
}