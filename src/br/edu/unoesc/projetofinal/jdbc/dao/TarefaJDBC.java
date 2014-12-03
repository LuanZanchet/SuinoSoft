package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.edu.unoesc.projetofinal.dao.TarefaDAO;
import br.edu.unoesc.projetofinal.model.Tarefa;

public class TarefaJDBC implements TarefaDAO {

	public void store(Tarefa tarefa) {
		String sql="insert into tarefa values(null,?,?)";
		try {
			PreparedStatement ps=ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setString(1, tarefa.getDescricao());
			ps.setDate(2, (Date) tarefa.getDataTarefa());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void alter(Tarefa tarefa) {
		String sql="update tarefa set descricao=?, dataTarefa=? where idTarefa=?";
		try {
			PreparedStatement ps=ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setString(1, tarefa.getDescricao());
			ps.setDate(2, (Date) tarefa.getDataTarefa());
			ps.setInt(3, tarefa.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Tarefa tarefa) {
		String sql="delete from tarefa where idTarefa=?";
		try {
			PreparedStatement ps=ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, tarefa.getCodigo());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Tarefa> listarTodos() {
		List<Tarefa>tarefas=new ArrayList<>();
		String sql="select*from tarefa";
		try {
			PreparedStatement ps=ConexaoUtil.getConexao().prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Tarefa tarefa=new Tarefa();
				tarefa.setCodigo(rs.getInt("idTarefa"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setDataTarefa(rs.getDate("dataTarefa"));
				tarefas.add(tarefa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tarefas;
	}

	public Tarefa get(Integer codigo) {
		Tarefa tarefa=new Tarefa();
		String sql="select*from tarefa where idTarefa=?";
		try {
			PreparedStatement ps=ConexaoUtil.getConexao().prepareStatement(sql);
			ps.setInt(1, codigo);
			ResultSet rs=ps.executeQuery();
			rs.next();
			tarefa.setCodigo(rs.getInt("idTarefa"));
			tarefa.setDescricao(rs.getString("descricao"));
			tarefa.setDataTarefa(rs.getDate("dataTarefa"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tarefa;
	}
}