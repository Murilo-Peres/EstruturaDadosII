package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaBD {
	private Connection conexao;

	public ConectaBD() {
		String url = "jdbc:mariadb://localhost:8081/ativpoocap";
		String user = "root";
		String senha = "270590";
		
		try {
			conexao = DriverManager.getConnection(url, user, senha);
			System.out.println("Conexão realizada com sucesso!");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Connection getConexao() {
		return conexao;
	}
}
