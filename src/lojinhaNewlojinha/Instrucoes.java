package lojinhaNewlojinha;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Instrucoes extends JPanel{
	private JLabel lbInstrucoes, lbCadastrar, lbEditar, lbDeletar, lbPesquisar;
	private JLabel lbCadastrarDesc, lbEditarDesc, lbDeletarDesc;
	private JLabel lbCliente, lbProduto, lbVenda;
	private JLabel lbClienteDesc, lbProdutoDesc, lbVendaDesc;

	public Instrucoes() {
		inicializarComponentes();
	}

	public void inicializarComponentes() {
		lbInstrucoes = new JLabel("<html><font color='white'>INSTRUÇÕES</font></html>");
		lbInstrucoes.setFont(lbInstrucoes.getFont().deriveFont(20.0f));
		lbCadastrar = new JLabel("<html><font color='white'>CADASTRAR</font></html>");
		lbCadastrar.setFont(lbCadastrar.getFont().deriveFont(17.0f));
		lbEditar = new JLabel("<html><font color='white'>EDITAR</font></html>");
		lbEditar.setFont(lbEditar.getFont().deriveFont(17.0f));
		lbDeletar = new JLabel("<html><font color='white'>DELETAR</font></html>");
		lbDeletar.setFont(lbDeletar.getFont().deriveFont(17.0f));		
		lbPesquisar = new JLabel("<html><font color='white'>PESQUISAR:</font></html>");
		lbPesquisar.setFont(lbPesquisar.getFont().deriveFont(17.0f));		
		lbCliente = new JLabel("<html><font color='white'>CLIENTE</font></html>");
		lbCliente.setFont(lbCliente.getFont().deriveFont(17.0f));		
		lbProduto = new JLabel("<html><font color='white'>PRODUTO</font></html>");
		lbProduto.setFont(lbProduto.getFont().deriveFont(17.0f));		
		lbVenda = new JLabel("<html><font color='white'>VENDA</font></html>");
		lbVenda.setFont(lbVenda.getFont().deriveFont(17.0f));		
		
		lbCadastrarDesc = new JLabel("<html><font color='white'>Para cadastrar um cliente ou um produto ou uma venda, "
				+ "preencha os campos corretamente e clique no botão \"Adicionar\".</font></html>");
		lbCadastrarDesc.setFont(lbCadastrarDesc.getFont().deriveFont(12.5f));
		
		lbEditarDesc = new JLabel("<html><font color='white'>Para editar um cliente ou um produto ou uma venda, "
				+ "clique no item na tabela, faça as alterações nos campos e clique em \"Editar\".</font></html>");
		lbEditarDesc.setFont(lbEditarDesc.getFont().deriveFont(12.5f));
		
		lbDeletarDesc = new JLabel("<html><font color='white'>Para deletar um cliente ou um produto ou uma venda, "
				+ "clique no item da tabela e clique em \"Excluir\".</font></html>");
		lbDeletarDesc.setFont(lbDeletarDesc.getFont().deriveFont(12.5f));
		
		lbClienteDesc = new JLabel("<html><font color='white'>Digite o nome do cliente na barra de pesquisa \"Pesquisar nome\" "
				+ "caso queira buscar pelo nome do cliente e clique no botão ao lado da barra. "
				+ "Caso queira uma pesquisa única, preencha o campo \"Cpf\" com o cpf do cliente e clique no botão \"CPF\". "
				+ "Use a caixa de opções para pesquisas diversas.</font></html>");
		lbClienteDesc.setFont(lbClienteDesc.getFont().deriveFont(12.5f));
		
		lbProdutoDesc = new JLabel("<html><font color='white'>Digite a categoria do produto no campo \"Categoria\" "
				+ "e clique no botão \"Categoria\" para pesquisar pela categoria do produto. "
				+ "Use a caixa de opções para pesquisas diversas.</font></html>");
		lbProdutoDesc.setFont(lbProdutoDesc.getFont().deriveFont(12.5f));
		
		lbVendaDesc = new JLabel("<html><font color='white'>Digite o ID do cliente no campo \"ID Cliente\" e clique no botão "
				+ "\"Pesquisar\" para buscar os produtos comprados pelo mesmo. "
				+ "Digite o ID do produto no campo \\\"ID Produto\\\" e clique no botão "
				+ "\"Pesquisar\" para buscar quais clientes compraram o mesmo.</font></html>");
		lbVendaDesc.setFont(lbVendaDesc.getFont().deriveFont(12.5f));
		
		setLayout(null);
		lbInstrucoes.setBounds(340, 20, 150, 20);
		
		lbCadastrar.setBounds(40, 60, 150, 20);
		lbCadastrarDesc.setBounds(40, 80, 200, 100);
		
		lbEditar.setBounds(250, 60, 100, 20);
		lbEditarDesc.setBounds(250, 80, 200, 100);
		
		lbDeletar.setBounds(460, 60, 100, 20);
		lbDeletarDesc.setBounds(460, 70, 200, 100);
		
		lbPesquisar.setBounds(40, 190, 150, 20);
		
		lbCliente.setBounds(40, 220, 150, 20);
		lbClienteDesc.setBounds(40, 230, 200, 220);
		
		lbProduto.setBounds(250, 220, 150, 20);
		lbProdutoDesc.setBounds(250, 240, 200, 130);
		
		lbVenda.setBounds(460, 220, 150, 20);
		lbVendaDesc.setBounds(460, 240, 200, 170);
		
		add(lbInstrucoes);
		add(lbCadastrar);
		add(lbCadastrarDesc);
		add(lbEditar);
		add(lbEditarDesc);
		add(lbDeletar);
		add(lbDeletarDesc);
		add(lbPesquisar);
		add(lbCliente);
		add(lbClienteDesc);
		add(lbProduto);
		add(lbProdutoDesc);
		add(lbVenda);
		add(lbVendaDesc);
	}
	
	@Override
	public void setBackground(Color bg) {
		super.setBackground(new Color(40, 42, 54));
	}
}
