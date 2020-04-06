package cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import lojinhaNewlojinha.FabricaConexao;

public class ClienteComands {
	// Variáveis constantes do tipo String armazenado comandos SQL
	private final String INSERT = "INSERT INTO tb_cliente(nome_cliente, cpf_cliente, data_nasc) VALUES (?, ?, ?) ";
	private final String UPDATE = "UPDATE tb_cliente SET nome_cliente = ?, cpf_cliente = ?, data_nasc = ? WHERE id_cliente = ?";
	private final String DELETE = "DELETE FROM tb_cliente WHERE id_cliente = ?";
	private final String COUNT = "SELECT COUNT(*) FROM tb_cliente";
	private final String ASC = "SELECT * FROM tb_cliente ORDER BY nome_cliente ASC";
	private final String DESC = "SELECT * FROM tb_cliente ORDER BY nome_cliente DESC";
	private final String LIST = "SELECT * FROM tb_cliente";
	private final String LIKE = "SELECT * FROM tb_cliente WHERE nome_cliente LIKE ?";
	private final String PESQUISA_UNICA = "SELECT * FROM tb_cliente WHERE cpf_cliente = ?";
	private final String PESQUISA_DATA_NASC = "SELECT * FROM tb_cliente  WHERE YEAR(data_nasc) >= ? AND YEAR(data_nasc) <= ?";

	// Método create() faz a inserção de dados no banco
	public boolean create(Cliente cliente) {
		// A conexão com o banco é aberta utilizando o método .getConnection() da classe
		// FabricaConexao
		Connection con = FabricaConexao.getConnection();
		// Uma variável do tipo PreparedStatement é declarada
		PreparedStatement stnt = null;

		try {
			stnt = con.prepareStatement(INSERT);
			stnt.setString(1, cliente.getNomeCliente());
			stnt.setString(2, cliente.getCpfCliente());
			stnt.setDate(3, cliente.getDataNascCliente());

			if (stnt.executeUpdate() != 0) {
				// Se alterou pelo menos 1 linha, então o comando deu certo
				JOptionPane.showMessageDialog(null, "Salvo com sucesso!!!");
				return true;
			}
		} catch (SQLException e) {
			// Caso ocorra algum erro uma mensagem é mostrada ao usuário
			JOptionPane.showMessageDialog(null, "Erro ao salvar " + e.getMessage());
		} finally {
			// Caso o comando seja executado ou ocorra algum erro, a conexão com o banco é
			// fechada
			FabricaConexao.closeConnection(con, stnt);
		}
		return false;
	}

	// Método read() faz a listagem de dados 
	public List<Cliente> read() {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;
		ResultSet rs = null;

		List<Cliente> clientes = new ArrayList<>();

		try {
			stnt = con.prepareStatement(LIST);
			rs = stnt.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();

				cliente.setIdCliente(rs.getInt("id_cliente"));
				cliente.setNomeCliente(rs.getString("nome_cliente"));
				cliente.setCpfCliente(rs.getString("cpf_cliente"));
				cliente.setDataNascCliente(rs.getDate("data_nasc"));

				clientes.add(cliente);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar " + e.getMessage());
		} finally {
			FabricaConexao.closeConnection(con, stnt, rs);
		}

		return clientes;
	}

	// Método update() faz a edição de dados no banco
	public boolean update(Cliente cliente) {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;

		try {
			stnt = con.prepareStatement(UPDATE);
			stnt.setString(1, cliente.getNomeCliente());
			stnt.setString(2, cliente.getCpfCliente());
			stnt.setDate(3, cliente.getDataNascCliente());
			stnt.setInt(4, cliente.getIdCliente());

			if (stnt.executeUpdate() != 0) {
				JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar " + e.getMessage());
		} finally {
			FabricaConexao.closeConnection(con, stnt);
		}

		return false;
	}

	// Método delete() faz a exclusão de dados no banco
	public boolean delete(Cliente cliente) {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;

		try {
			stnt = con.prepareStatement(DELETE);
			stnt.setInt(1, cliente.getIdCliente());

			if (stnt.executeUpdate() != 0) {
				JOptionPane.showMessageDialog(null, "Excluido com sucesso");
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir " + e.getMessage());
		} finally {
			FabricaConexao.closeConnection(con, stnt);
		}

		return false;
	}

	// Método readAsc() faz a listagem de dados de A-Z
	public List<Cliente> readAsc() {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;
		ResultSet rs = null;

		List<Cliente> clientes = new ArrayList<>();

		try {
			stnt = con.prepareStatement(ASC);
			rs = stnt.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();

				cliente.setIdCliente(rs.getInt("id_cliente"));
				cliente.setNomeCliente(rs.getString("nome_cliente"));
				cliente.setCpfCliente(rs.getString("cpf_cliente"));
				cliente.setDataNascCliente(rs.getDate("data_nasc"));

				clientes.add(cliente);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar " + e.getMessage());
		} finally {
			FabricaConexao.closeConnection(con, stnt, rs);
		}

		return clientes;
	}

	// Método readDesc() faz a listagem de dados de Z-A
	public List<Cliente> readDesc() {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;
		ResultSet rs = null;

		List<Cliente> clientes = new ArrayList<>();

		try {
			stnt = con.prepareStatement(DESC);
			rs = stnt.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();

				cliente.setIdCliente(rs.getInt("id_cliente"));
				cliente.setNomeCliente(rs.getString("nome_cliente"));
				cliente.setCpfCliente(rs.getString("cpf_cliente"));
				cliente.setDataNascCliente(rs.getDate("data_nasc"));

				clientes.add(cliente);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar " + e.getMessage());
		} finally {
			FabricaConexao.closeConnection(con, stnt, rs);
		}

		return clientes;
	}

	// Método readCpf() faz a listagem do cliente com o cpf fornecido
	public List<Cliente> readCpf(String cpf) {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;
		ResultSet rs = null;

		List<Cliente> clientes = new ArrayList<>();

		try {
			stnt = con.prepareStatement(PESQUISA_UNICA);
			stnt.setString(1, cpf);
			rs = stnt.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();

				cliente.setIdCliente(rs.getInt("id_cliente"));
				cliente.setNomeCliente(rs.getString("nome_cliente"));
				cliente.setCpfCliente(rs.getString("cpf_cliente"));
				cliente.setDataNascCliente(rs.getDate("data_nasc"));

				clientes.add(cliente);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar " + e.getMessage());
		} finally {
			FabricaConexao.closeConnection(con, stnt, rs);
		}

		return clientes;
	}

	// Método readName() faz a listagem de clientes com o nome fornecido
	public List<Cliente> readName(String pesquisa) {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;
		ResultSet rs = null;

		List<Cliente> clientes = new ArrayList<>();

		try {
			stnt = con.prepareStatement(LIKE);
			stnt.setString(1, pesquisa + "%");
			rs = stnt.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();

				cliente.setIdCliente(rs.getInt("id_cliente"));
				cliente.setNomeCliente(rs.getString("nome_cliente"));
				cliente.setCpfCliente(rs.getString("cpf_cliente"));
				cliente.setDataNascCliente(rs.getDate("data_nasc"));

				clientes.add(cliente);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar " + e.getMessage());
		} finally {
			FabricaConexao.closeConnection(con, stnt, rs);
		}

		return clientes;
	}

	// Método readDataNasc() faz a listagem de clientes com a faixa de anos fornecido
	public List<Cliente> readDataNasc(int ano1, int ano2) {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;
		ResultSet rs = null;

		List<Cliente> clientes = new ArrayList<>();

		try {
			stnt = con.prepareStatement(PESQUISA_DATA_NASC);
			stnt.setInt(1, ano1);
			stnt.setInt(2, ano2);
			rs = stnt.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();

				cliente.setIdCliente(rs.getInt("id_cliente"));
				cliente.setNomeCliente(rs.getString("nome_cliente"));
				cliente.setCpfCliente(rs.getString("cpf_cliente"));
				cliente.setDataNascCliente(rs.getDate("data_nasc"));

				clientes.add(cliente);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar " + e.getMessage());
		} finally {
			FabricaConexao.closeConnection(con, stnt, rs);
		}

		return clientes;
	}

	// Método count() faz a contagem de clientes cadastrados no banco
	public String count() {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;
		ResultSet rs = null;
		String count = "";

		try {
			stnt = con.prepareStatement(COUNT);
			rs = stnt.executeQuery();
			rs.next();
			count = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			FabricaConexao.closeConnection(con, stnt, rs);
		}
		return count;
	}
}
