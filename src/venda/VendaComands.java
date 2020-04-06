package venda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import lojinhaNewlojinha.FabricaConexao;

public class VendaComands {
	// Vari�veis constantes do tipo String armazenado comandos SQL
	private final String INSERT = "INSERT INTO tb_venda(quantidade_prod, data_venda, id_cliente, id_produto) VALUES (?, ?, ?, ?) ";
	private final String UPDATE = "UPDATE tb_venda SET quantidade_prod = ?, data_venda = ?, id_cliente = ?, id_produto = ? WHERE id_venda = ?";
	private final String DELETE = "DELETE FROM tb_venda WHERE id_venda = ?";
	private final String LIST = "SELECT * FROM tb_venda";
	private final String INNERJOIN_PRODUTO = "SELECT * FROM tb_venda "
			+ "INNER JOIN tb_produto ON tb_venda.id_produto = tb_produto.id_produto "
			+ "INNER JOIN tb_cliente ON tb_venda.id_cliente = tb_cliente.id_cliente "
			+ "WHERE tb_produto.id_produto = ?;";
	private final String INNERJOIN_CLIENTE = "SELECT * FROM tb_venda "
			+ "INNER JOIN tb_produto ON tb_venda.id_produto = tb_produto.id_produto "
			+ "INNER JOIN tb_cliente ON tb_venda.id_cliente = tb_cliente.id_cliente "
			+ "WHERE tb_cliente.id_cliente = ?;";

	// M�todo create() faz a inser��o de dados no banco
	public boolean create(Venda venda) {
		// A conex�o com o banco � aberta utilizando o m�todo .getConnection() da classe
		// FabricaConexao
		Connection con = FabricaConexao.getConnection();
		// Uma vari�vel do tipo PreparedStatement � declarada
		PreparedStatement stnt = null;

		try {
			stnt = con.prepareStatement(INSERT);
			stnt.setInt(1, venda.getQtdProduto());
			stnt.setDate(2, venda.getDataVenda());
			stnt.setInt(3, venda.getIdCliente());
			stnt.setInt(4, venda.getIdProduto());

			if (stnt.executeUpdate() != 0) {
				// Se alterou pelo menos 1 linha, ent�o o comando deu certo
				JOptionPane.showMessageDialog(null, "Salvo com sucesso!!!");
				return true;
			}
		} catch (SQLException e) {
			// Caso ocorra algum erro uma mensagem � mostrada ao usu�rio
			JOptionPane.showMessageDialog(null, "Erro ao salvar " + e.getMessage());
		} finally {
			// Caso o comando seja executado ou ocorra algum erro, a conex�o com o banco �
			// fechada
			FabricaConexao.closeConnection(con, stnt);
		}
		return false;
	}

	// M�todo read() faz a listagem de dados
	public List<Venda> read() {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;
		ResultSet rs = null;

		List<Venda> vendas = new ArrayList<>();

		try {
			stnt = con.prepareStatement(LIST);
			rs = stnt.executeQuery();

			while (rs.next()) {
				Venda venda = new Venda();

				venda.setIdVenda(rs.getInt("id_venda"));
				venda.setQtdProduto(rs.getInt("quantidade_prod"));
				venda.setDataVenda(rs.getDate("data_venda"));
				venda.setIdCliente(rs.getInt("id_cliente"));
				venda.setIdProduto(rs.getInt("id_produto"));

				vendas.add(venda);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar " + e.getMessage());
		} finally {
			FabricaConexao.closeConnection(con, stnt, rs);
		}

		return vendas;
	}

	// M�todo update() faz a edi��o de dados no banco
	public boolean update(Venda venda) {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;

		try {
			stnt = con.prepareStatement(UPDATE);
			stnt.setInt(1, venda.getQtdProduto());
			stnt.setDate(2, venda.getDataVenda());
			stnt.setInt(3, venda.getIdCliente());
			stnt.setInt(4, venda.getIdProduto());
			stnt.setInt(5, venda.getIdVenda());

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

	// M�todo delete() faz a exclus�o de dados no banco
	public boolean delete(Venda venda) {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;

		try {
			stnt = con.prepareStatement(DELETE);
			stnt.setInt(1, venda.getIdVenda());

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

	// M�todo innerJoinProduto() faz a listagem de clientes que compraram certo
	// produto
	public List<Venda> innerJoinProduto(int id) {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;
		ResultSet rs = null;

		List<Venda> vendas = new ArrayList<>();

		try {
			stnt = con.prepareStatement(INNERJOIN_PRODUTO);
			stnt.setInt(1, id);
			rs = stnt.executeQuery();

			while (rs.next()) {
				Venda venda = new Venda();

				venda.setQtdProduto(rs.getInt("quantidade_prod"));
				venda.setDataVenda(rs.getDate("data_venda"));
				venda.setIdCliente(rs.getInt("id_cliente"));
				venda.setIdProduto(rs.getInt("id_produto"));

				vendas.add(venda);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar " + e.getMessage());
		} finally {
			FabricaConexao.closeConnection(con, stnt, rs);
		}

		return vendas;
	}

	// M�todo innerJoinProduto() faz a listagem de produtos comprados por clientes
	public List<Venda> innerJoinCliente(int id) {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;
		ResultSet rs = null;

		List<Venda> vendas = new ArrayList<>();

		try {
			stnt = con.prepareStatement(INNERJOIN_CLIENTE);
			stnt.setInt(1, id);
			rs = stnt.executeQuery();

			while (rs.next()) {
				Venda venda = new Venda();

				venda.setQtdProduto(rs.getInt("quantidade_prod"));
				venda.setDataVenda(rs.getDate("data_venda"));
				venda.setIdCliente(rs.getInt("id_cliente"));
				venda.setIdProduto(rs.getInt("id_produto"));

				vendas.add(venda);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar " + e.getMessage());
		} finally {
			FabricaConexao.closeConnection(con, stnt, rs);
		}

		return vendas;
	}
}
