package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.unoesc.projetofinal.dao.FuncionarioDAO;
import br.edu.unoesc.projetofinal.dao.MatrizDAO;
import br.edu.unoesc.projetofinal.dao.PartoDAO;
import br.edu.unoesc.projetofinal.dao.factory.DaoFactory;
import br.edu.unoesc.projetofinal.model.Parto;

public class PartoJDBC implements PartoDAO {
	private MatrizDAO matrizDao = DaoFactory.get().matrizDao();
	private FuncionarioDAO funcionarioDao = DaoFactory.get().funcionarioDao();

	public void store(Parto parto) {
		String sql = "insert into parto values (null,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, parto.getMatriz().getCodigo());
			ps.setString(2, parto.getTipoParto());
			ps.setDate(3, (Date) parto.getData());
			ps.setInt(4, parto.getQuantVivos());
			ps.setInt(5, parto.getQuantMortos());
			ps.setInt(6, parto.getQuantMumificados());
			ps.setInt(7, parto.getQuantNatimortos());
			ps.setDouble(8, parto.getPesoTotal());
			ps.setDouble(9, parto.getPesoMedio());
			ps.setInt(10, parto.getFuncionario().getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Parto parto) {
		String sql = "update parto set idMatriz=?,tipoParto=?,dataParto=?,quantVivos=?,quantMortos=?,quantMumificados=?,quantNatimortos=?,pesoTotal=?,pesoMedio=?,idFuncionario=? where idParto=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, parto.getMatriz().getCodigo());
			ps.setString(2, parto.getTipoParto());
			ps.setDate(3, (Date) parto.getData());
			ps.setInt(4, parto.getQuantVivos());
			ps.setInt(5, parto.getQuantMortos());
			ps.setInt(6, parto.getQuantMumificados());
			ps.setInt(7, parto.getQuantNatimortos());
			ps.setDouble(8, parto.getPesoTotal());
			ps.setDouble(9, parto.getPesoMedio());
			ps.setInt(10, parto.getFuncionario().getCodigo());
			ps.setInt(11, parto.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Parto parto) {
		String sql = "delete from parto where idParto=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, parto.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Parto> listarTodos() {
		List<Parto> partos = new ArrayList<>();
		String sql = "select*from parto";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Parto parto = new Parto();
				parto.setCodigo(rs.getInt("idParto"));
				parto.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
				parto.setTipoParto(rs.getString("tipoParto"));
				parto.setData(rs.getDate("dataParto"));
				parto.setQuantVivos(rs.getInt("quantVivos"));
				parto.setQuantMortos(rs.getInt("quantMortos"));
				parto.setQuantMumificados(rs.getInt("quantMumificados"));
				parto.setQuantNatimortos(rs.getInt("quantNatimortos"));
				parto.setPesoTotal(rs.getDouble("pesoTotal"));
				parto.setPesoMedio(rs.getDouble("pesoMedio"));
				parto.setFuncionario(funcionarioDao.get(rs.getInt("idFuncionario")));
				partos.add(parto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return partos;
	}

	public Parto get(Integer codigo) {
		Parto parto = new Parto();
		String sql = "select*from parto where idParto=?";
		try {
			PreparedStatement ps = ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			rs.next();
			parto.setCodigo(rs.getInt("idParto"));
			parto.setMatriz(matrizDao.get(rs.getInt("idMatriz")));
			parto.setTipoParto(rs.getString("tipoParto"));
			parto.setData(rs.getDate("dataParto"));
			parto.setQuantVivos(rs.getInt("quantVivos"));
			parto.setQuantMortos(rs.getInt("quantMortos"));
			parto.setQuantMumificados(rs.getInt("quantMumificados"));
			parto.setQuantNatimortos(rs.getInt("quantNatimortos"));
			parto.setPesoTotal(rs.getDouble("pesoTotal"));
			parto.setPesoMedio(rs.getDouble("pesoMedio"));
			parto.setFuncionario(funcionarioDao.get(rs.getInt("idFuncionario")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return parto;
	}
}