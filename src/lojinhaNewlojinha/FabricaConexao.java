package lojinhaNewlojinha;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FabricaConexao {
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	private final static String DBNAME = "dsa_vinicius_lojinha";
	private final static String URL = "jdbc:mysql://localhost:3306/" + DBNAME;
	private final static String LOGIN = "root";
	private final static String SENHA = "";

	// Método para efetuar a conexão com o banco de dados
	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, LOGIN, SENHA);
		} catch (ClassNotFoundException | SQLException ex) {
			throw new RuntimeException("Erro na conexão " + ex);
		}
	}

	// Método para fechar a conexão com o banco de dados
	public static void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para fechar a conexão com o banco de dados e PrepareStatement
	public static void closeConnection(Connection con, PreparedStatement stnt) {
		closeConnection(con);

		try {
			if (stnt != null) {
				stnt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método para fechar a conexão com o banco de dados e PrepareStatement e ResultSet
	public static void closeConnection(Connection con, PreparedStatement stnt, ResultSet rs) {
		closeConnection(con, stnt);

		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
