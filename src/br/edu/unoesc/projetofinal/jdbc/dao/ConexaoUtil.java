package br.edu.unoesc.projetofinal.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoUtil {
	private static Connection conexao;

	static {
		String url = "jdbc:mysql://localhost/projetofinal";
		String user = "root";
		String senha = "123456789abcde";
		try {
			conexao = DriverManager.getConnection(url, user, senha);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConexao() {
		return conexao;
	}
}