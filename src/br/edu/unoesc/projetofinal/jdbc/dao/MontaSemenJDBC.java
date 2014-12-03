package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import br.edu.unoesc.projetofinal.dao.CoberturaDAO;
import br.edu.unoesc.projetofinal.dao.FuncionarioDAO;
import br.edu.unoesc.projetofinal.dao.MontaSemenDAO;
import br.edu.unoesc.projetofinal.dao.SemenDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.MontaSemen;

public class MontaSemenJDBC implements MontaSemenDAO{
	private CoberturaDAO coberturaDao=DaoFactory.get().coberturaDao();
	private FuncionarioDAO funcionarioDao=DaoFactory.get().funcionarioDao();
	private SemenDAO semenDao=DaoFactory.get().semenDao();
	
	public void store(MontaSemen monta) {
		String sql="insert into montaSemen values(null,?,?,?,?,?)";
		try {
			PreparedStatement ps=ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, monta.getData());
			ps.setInt(2, monta.getCobertura().getCodigo());
			ps.setInt(3, monta.getSemen().getCodigo());
			ps.setInt(4, monta.getFuncionario().getCodigo());
			ps.setString(5, monta.getTipo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(MontaSemen monta) {
		String sql="update montaSemen set dataMonta=?,idCobertura=?,idSemen=?,idFuncionario=?,tipo=? where idMontaSemen=?";
		try {
			PreparedStatement ps=ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setDate(1, monta.getData());
			ps.setInt(2, monta.getCobertura().getCodigo());
			ps.setInt(3, monta.getSemen().getCodigo());
			ps.setInt(4, monta.getFuncionario().getCodigo());
			ps.setString(5, monta.getTipo());
			ps.setInt(6, monta.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(MontaSemen monta) {
		String sql="delete from montaSemen where idMontaSemen=?";
		try {
			PreparedStatement ps=ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, monta.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<MontaSemen> listarTodos() {
		List<MontaSemen>montas=new ArrayList<>();
		String sql="select*from montaSemen";
		try {
			PreparedStatement ps=ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				MontaSemen monta=new MontaSemen();
				monta.setCodigo(rs.getInt("idMontaSemen"));
				monta.setData(rs.getDate("dataMonta"));
				monta.setCobertura(coberturaDao.get(rs.getInt("idCobertura")));
				monta.setSemen(semenDao.get(rs.getInt("idSemen")));
				monta.setFuncionario(funcionarioDao.get(rs.getInt("idFuncionario")));
				monta.setTipo(rs.getString("tipo"));
				montas.add(monta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return montas;
	}

	public MontaSemen get(Integer codigo) {
		MontaSemen monta=new MontaSemen();
		String sql="select*from montaSemen where idMontaSemen=?";
		try {
			PreparedStatement ps=ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs=ps.executeQuery();
			rs.next();
			monta.setCodigo(rs.getInt("idMontaSemen"));
			monta.setData(rs.getDate("dataMonta"));
			monta.setCobertura(coberturaDao.get(rs.getInt("idCobertura")));
			monta.setSemen(semenDao.get(rs.getInt("idSemen")));
			monta.setFuncionario(funcionarioDao.get(rs.getInt("idFuncionario")));
			monta.setTipo(rs.getString("tipo"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return monta;
	}
}