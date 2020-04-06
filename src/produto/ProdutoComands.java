package produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import lojinhaNewlojinha.FabricaConexao;

public class ProdutoComands {
	// Variáveis constantes do tipo String armazenado comandos SQL
	private final String INSERT = "INSERT INTO tb_produto(nome_produto, categoria_produto, preco_produto) VALUES (?, ?, ?) ";
	private final String UPDATE = "UPDATE tb_produto SET nome_produto = ?, categoria_produto = ?, preco_produto = ? WHERE id_produto = ?";
	private final String DELETE = "DELETE FROM tb_produto WHERE id_produto = ?";
	private final String LIST = "SELECT * FROM tb_produto";
	private final String COUNT = "SELECT COUNT(*) FROM tb_produto";
	private final String ASC = "SELECT * FROM tb_produto ORDER BY nome_produto ASC";
	private final String SUM = "SELECT SUM(preco_produto) FROM tb_produto";
	private final String MAX = "SELECT MAX(preco_produto) FROM tb_produto";
	private final String MIN = "SELECT MIN(preco_produto) FROM tb_produto";
	private final String DESC = "SELECT * FROM tb_produto ORDER BY nome_produto DESC";
	private final String PESQUISA_CATEGORIA = "SELECT * FROM tb_produto WHERE categoria_produto LIKE ?";

	// Método create() faz a inserção de dados no banco
	public boolean create(Produto produto) {
		// A conexão com o banco é aberta utilizando o método .getConnection() da classe
		// FabricaConexao
		Connection con = FabricaConexao.getConnection();
		// Uma variável do tipo PreparedStatement é declarada
		PreparedStatement stnt = null;

		try {
			stnt = con.prepareStatement(INSERT);
			stnt.setString(1, produto.getNome());
			stnt.setString(2, produto.getCategoria());
			stnt.setFloat(3, produto.getPreco());

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
	public List<Produto> read() {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;
		ResultSet rs = null;

		List<Produto> produtos = new ArrayList<>();

		try {
			stnt = con.prepareStatement(LIST);
			rs = stnt.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto();

				produto.setId(rs.getInt("id_produto"));
				produto.setNome(rs.getString("nome_produto"));
				produto.setCategoria(rs.getString("categoria_produto"));
				produto.setPreco(rs.getFloat("preco_produto"));
				produto.setPrecoDesc(rs.getFloat("preco_desconto"));

				produtos.add(produto);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar " + e.getMessage());
		} finally {
			FabricaConexao.closeConnection(con, stnt, rs);
		}

		return produtos;
	}

	// Método update() faz a edição de dados no banco
	public boolean update(Produto produto) {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;

		try {
			stnt = con.prepareStatement(UPDATE);
			stnt.setString(1, produto.getNome());
			stnt.setString(2, produto.getCategoria());
			stnt.setFloat(3, produto.getPreco());
			stnt.setInt(4, produto.getId());

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
	public boolean delete(Produto produto) {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;

		try {
			stnt = con.prepareStatement(DELETE);
			stnt.setInt(1, produto.getId());

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
	public List<Produto> readCategoria(String search) {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;
		ResultSet rs = null;

		List<Produto> produtos = new ArrayList<>();

		try {
			stnt = con.prepareStatement(PESQUISA_CATEGORIA);
			stnt.setString(1, search + "%");
			rs = stnt.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto();

				produto.setId(rs.getInt("id_produto"));
				produto.setNome(rs.getString("nome_produto"));
				produto.setCategoria(rs.getString("categoria_produto"));
				produto.setPreco(rs.getFloat("preco_produto"));
				produto.setPrecoDesc(rs.getFloat("preco_desconto"));

				produtos.add(produto);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar " + e.getMessage());
		} finally {
			FabricaConexao.closeConnection(con, stnt, rs);
		}

		return produtos;
	}

	// Método readAsc() faz a listagem de dados de A-Z
	public List<Produto> readAsc() {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;
		ResultSet rs = null;

		List<Produto> produtos = new ArrayList<>();

		try {
			stnt = con.prepareStatement(ASC);
			rs = stnt.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto();

				produto.setId(rs.getInt("id_produto"));
				produto.setNome(rs.getString("nome_produto"));
				produto.setCategoria(rs.getString("categoria_produto"));
				produto.setPreco(rs.getFloat("preco_produto"));
				produto.setPrecoDesc(rs.getFloat("preco_desconto"));

				produtos.add(produto);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar " + e.getMessage());
		} finally {
			FabricaConexao.closeConnection(con, stnt, rs);
		}

		return produtos;
	}

	// Método readDesc() faz a listagem de dados de Z-A
	public List<Produto> readDesc() {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;
		ResultSet rs = null;

		List<Produto> produtos = new ArrayList<>();

		try {
			stnt = con.prepareStatement(DESC);
			rs = stnt.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto();

				produto.setId(rs.getInt("id_produto"));
				produto.setNome(rs.getString("nome_produto"));
				produto.setCategoria(rs.getString("categoria_produto"));
				produto.setPreco(rs.getFloat("preco_produto"));
				produto.setPrecoDesc(rs.getFloat("preco_desconto"));

				produtos.add(produto);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar " + e.getMessage());
		} finally {
			FabricaConexao.closeConnection(con, stnt, rs);
		}

		return produtos;
	}

	// Método sum() retorna a soma dos preços dos produtos
	public String sum() {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;
		ResultSet rs = null;
		String sum = "";

		try {
			stnt = con.prepareStatement(SUM);
			rs = stnt.executeQuery();
			rs.next();
			sum = rs.getString(1);
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			FabricaConexao.closeConnection(con, stnt, rs);
		}
		return "R$ " + sum;
	}

	// Método count() retorna a quantidade de produtos cadastrados
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
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			FabricaConexao.closeConnection(con, stnt, rs);
		}
		return count;
	}

	// Método max() retorna o preço do produto mais caro
	public String max() {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;
		ResultSet rs = null;
		String max = "";

		try {
			stnt = con.prepareStatement(MAX);
			rs = stnt.executeQuery();
			rs.next();
			max = rs.getString(1);
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			FabricaConexao.closeConnection(con, stnt, rs);
		}
		return "R$ " + max;
	}

	// Método min() retorna o preço do produto mais barato
	public String min() {
		Connection con = FabricaConexao.getConnection();
		PreparedStatement stnt = null;
		ResultSet rs = null;
		String min = "";

		try {
			stnt = con.prepareStatement(MIN);
			rs = stnt.executeQuery();
			rs.next();
			min = rs.getString(1);
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			FabricaConexao.closeConnection(con, stnt, rs);
		}
		return "R$ " + min;
	}
}
