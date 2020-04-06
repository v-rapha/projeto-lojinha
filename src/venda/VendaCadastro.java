package venda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import cliente.ClienteProdutoMethods;

public class VendaCadastro extends JPanel {
	// Atributos
	private JLabel lbIdVenda, lbIdCliente, lbIdProduto, lbQtdProd, lbDataVenda;
	private JTextField txIdVenda, txIdCliente, txIdProduto, txQtdProd;
	private JFormattedTextField txfDataVenda;
	private MaskFormatter maskDataVenda;
	private JButton btAdicionar, btEditar, btExcluir, btPesquisa;
	private JTable tabela;
	private JScrollPane jScroll;
	private DefaultTableModel modelo = new DefaultTableModel();
	ClienteProdutoMethods dataMethod = new ClienteProdutoMethods();

	// Construtor
	public VendaCadastro() {
		inicializarComponentes();
		criarEventos();
	}

	// Método para criação e adição de componentes
	public void inicializarComponentes() {
		// JLabel's
		lbIdVenda = new JLabel("ID Venda");
		lbIdCliente = new JLabel("ID Cliente");
		lbIdProduto = new JLabel("ID Produto");
		lbQtdProd = new JLabel("Quantidade");
		lbDataVenda = new JLabel("Data de Venda");

		// JTextFiel's
		txIdVenda = new JTextField();
		txIdVenda.setEditable(false);
		try {
			maskDataVenda = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txIdCliente = new JTextField();
		txIdProduto = new JTextField();
		txQtdProd = new JTextField();
		txfDataVenda = new JFormattedTextField(maskDataVenda);

		// JButton's
		btAdicionar = new JButton("Adicionar");
		btEditar = new JButton("Editar");
		btExcluir = new JButton("Excluir");
		btPesquisa = new JButton("Pesquisar");
		btPesquisa.setIcon(new ImageIcon(this.getClass().getResource("/img/mg.png")));

		// JTable: Criação de uma tabela 'padrão' sem linhas e colunas usando a classe
		// 'DefaultTableModel'
		tabela = new JTable(modelo);
		// Adicionando colunas e linhas a tabela
		modelo.addColumn("Id-venda");
		modelo.addColumn("Id-cliente");
		modelo.addColumn("Id-produto");
		modelo.addColumn("Quantidade");
		modelo.addColumn("Data de Venda");
		tabela.getColumnModel().getColumn(0).setPreferredWidth(40);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(40);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(40);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(40);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(80);

		// Adicionando eventos de mouse para a tabela:
		/*
		 * O evento de clique(mouseClicked) faz com que os dados da tabela vão para os
		 * campos(nome,cpf,dataNasc). O evento mouseReleased faz com que o duplo clique
		 * com o mouse limpe os campos(nome,cpf,dataNasc).
		 */
		tabela.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tabela.getSelectedRow() != -1) {
					txIdVenda.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
					txIdCliente.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
					txIdProduto.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
					txQtdProd.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
					txfDataVenda
							.setText(tabela.getValueAt(tabela.getSelectedRow(), 4).toString().replaceAll("[/\\-]", ""));
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma linha");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				txIdVenda.setText("");
				txIdCliente.setText("");
				txIdProduto.setText("");
				txQtdProd.setText("");
				txfDataVenda.setText("");
			}
		});

		// JScrollPane, utilizado para a renderização da JTable
		jScroll = new JScrollPane(tabela);

		setLayout(null);
		lbIdVenda.setBounds(120, 20, 80, 20);
		txIdVenda.setBounds(180, 20, 30, 20);
		lbIdCliente.setBounds(120, 50, 80, 20);
		txIdCliente.setBounds(180, 50, 30, 20);
		lbIdProduto.setBounds(240, 50, 80, 20);
		txIdProduto.setBounds(310, 50, 30, 20);
		lbQtdProd.setBounds(360, 50, 150, 20);
		txQtdProd.setBounds(440, 50, 30, 20);
		lbDataVenda.setBounds(490, 50, 150, 20);
		txfDataVenda.setBounds(590, 50, 70, 20);
		btAdicionar.setBounds(180, 350, 100, 20);
		btEditar.setBounds(300, 350, 100, 20);
		btExcluir.setBounds(420, 350, 100, 20);
		btPesquisa.setBounds(540, 350, 120, 20);
		jScroll.setBounds(120, 120, 550, 200);

		add(lbIdVenda);
		add(txIdVenda);
		add(lbIdCliente);
		add(txIdCliente);
		add(lbIdProduto);
		add(txIdProduto);
		add(lbQtdProd);
		add(txQtdProd);
		add(lbDataVenda);
		add(txfDataVenda);
		add(btAdicionar);
		add(btEditar);
		add(btExcluir);
		add(btPesquisa);
		add(jScroll);
		listVendas(modelo);
	}

	// Métodos para criação de eventos
	public void criarEventos() {
		btAdicionar.addActionListener(new ActionListener() {

			/**
			 * Caso alguma linha da tabela esteja selecionada uma mensagem é mostrada para
			 * que não selecione uma linhada tabela. Caso não tenha linhas selecionadas, há
			 * uma verificação, caso algum campo esteja vazio uma mensagem é mostrada para
			 * que preencha os campos corretamente.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabela.getSelectedRow() == -1) {
					if (!(txIdCliente.getText().equals("") || txIdProduto.getText().equals("")
							|| txQtdProd.getText().equals("") || txfDataVenda.getText().equals("  /  /    "))) {

						// Caso esteja todo correto um obj da classe Venda e da classe
						// VendaComands é instanciado
						Venda venda = new Venda();
						VendaComands cmd = new VendaComands();

						// Os dados dos campos são armazenados no obj venda
						// Obs: para data é usado um método de a conversão para ser compativel com a
						// data usada pelo banco de dados
						venda.setIdCliente(Integer.parseInt(txIdCliente.getText()));
						venda.setIdProduto(Integer.parseInt(txIdProduto.getText()));
						venda.setQtdProduto(Integer.parseInt(txQtdProd.getText()));
						venda.setDataVenda(java.sql.Date.valueOf(dataMethod.converterData(txfDataVenda.getText())));

						// Com o obj da classe VendaComands é possivel chamar o método create()
						// passando o obj venda
						cmd.create(venda);
						txIdCliente.setText("");
						txIdProduto.setText("");
						txQtdProd.setText("");
						txfDataVenda.setText("");
						listVendas(modelo);
					} else {
						JOptionPane.showMessageDialog(null, "Preencha os campos corretamente!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Não selecione linhas");
					tabela.clearSelection();
					txIdVenda.setText("");
					txIdCliente.setText("");
					txIdProduto.setText("");
					txQtdProd.setText("");
					txfDataVenda.setText("");
				}
			}
		});

		btEditar.addActionListener(new ActionListener() {

			/**
			 * Caso uma linha da tabela não esteja selecionada uma mensagem é mostrada para
			 * que selecione uma linhada tabela. Caso tenha linhas selecionadas, há uma
			 * verificação, caso algum campo esteja vazio uma mensagem é mostrada para que
			 * preencha os campos corretamente.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabela.getSelectedColumn() != -1) {
					if (!(txIdCliente.getText().equals("") || txIdProduto.getText().equals("")
							|| txQtdProd.getText().equals("") || txfDataVenda.getText().equals("  /  /    "))) {
						Venda venda = new Venda();
						VendaComands cmd = new VendaComands();

						venda.setIdCliente(Integer.parseInt(txIdCliente.getText()));
						venda.setIdProduto(Integer.parseInt(txIdProduto.getText()));
						venda.setQtdProduto(Integer.parseInt(txQtdProd.getText()));
						venda.setDataVenda(java.sql.Date.valueOf(dataMethod.converterData(txfDataVenda.getText())));
						venda.setIdVenda((int) tabela.getValueAt(tabela.getSelectedRow(), 0));

						cmd.update(venda);
						txIdVenda.setText("");
						txIdCliente.setText("");
						txIdProduto.setText("");
						txQtdProd.setText("");
						txfDataVenda.setText("");
						listVendas(modelo);
					} else {
						JOptionPane.showMessageDialog(null, "Preencha os campos corretamente!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma linha para editar!");
				}
			}
		});

		btExcluir.addActionListener(new ActionListener() {

			/**
			 * Caso uma linha da tabela não esteja selecionada uma mensagem é mostrada para
			 * que selecione uma linhada tabela. Caso tenha linhas selecionadas e o usuario
			 * clicou em 'Excluir' uma caixa de opção é mostrada e se o botão 'OK' for
			 * clicado a lógica é executada
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tabela.getSelectedRow() != -1) {
					int i = JOptionPane.showConfirmDialog(null, "Deseja continuar?", "Excluir",
							JOptionPane.OK_CANCEL_OPTION);
					if (i == JOptionPane.OK_OPTION) {
						Venda venda = new Venda();
						VendaComands cmd = new VendaComands();

						venda.setIdVenda((int) tabela.getValueAt(tabela.getSelectedRow(), 0));

						cmd.delete(venda);
						txIdVenda.setText("");
						txIdCliente.setText("");
						txIdProduto.setText("");
						txQtdProd.setText("");
						txfDataVenda.setText("");
						listVendas(modelo);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um linha para remover!");
				}
			}
		});

		btPesquisa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txIdProduto.getText().equals("")) {
					listProduto(modelo, Integer.parseInt(txIdProduto.getText()));
				} else if (!txIdCliente.getText().equals("")) {
					listCliente(modelo, Integer.parseInt(txIdCliente.getText()));
				} else {
					JOptionPane.showMessageDialog(null,
							"Preecha o campo \"ID Produto\" ou o campo \"ID Cliente\" para pesquisar!!");
					listVendas(modelo);
				}
			}
		});
	}

	public static void listVendas(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		VendaComands cmd = new VendaComands();

		for (Venda v : cmd.read()) {
			modelo.addRow(new Object[] { v.getIdVenda(), v.getIdCliente(), v.getIdProduto(), v.getQtdProduto(),
					v.getDataVenda() });
		}
	}

	public static void listProduto(DefaultTableModel modelo, int idProduto) {
		modelo.setNumRows(0);
		VendaComands cmd = new VendaComands();

		for (Venda v : cmd.innerJoinProduto(idProduto)) {
			modelo.addRow(new Object[] { v.getIdVenda(), v.getIdCliente(), v.getIdProduto(), v.getQtdProduto(),
					v.getDataVenda() });
		}
	}

	public static void listCliente(DefaultTableModel modelo, int idCliente) {
		modelo.setNumRows(0);
		VendaComands cmd = new VendaComands();

		for (Venda v : cmd.innerJoinCliente(idCliente)) {
			modelo.addRow(new Object[] { v.getIdVenda(), v.getIdCliente(), v.getIdProduto(), v.getQtdProduto(),
					v.getDataVenda() });
		}
	}
}
