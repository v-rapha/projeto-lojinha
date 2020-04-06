package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class ClienteCadastro extends JPanel {
	// Atributos
	private JLabel lbID, lbNome, lbCpf, lbDataNasc, lbTeste, lbPesquisa;
	private JTextField txID, txNome, txQtdClientes, txBarSearch;
	private JFormattedTextField txfCpf, txfDataNasc;
	private MaskFormatter maskCpf, maskDataNasc;
	private JButton btAdicionar, btEditar, btExcluir, btCpfSearch, btBarSearch;
	private JComboBox<String> comboBox;
	private JTable tabela;
	private JScrollPane jScroll;
	private DefaultTableModel modelo = new DefaultTableModel();
	ClienteProdutoMethods clientMeth = new ClienteProdutoMethods();

	// Construtor
	public ClienteCadastro() {
		inicializarComponentes();
		criarEventos();
	}

	// Método para criação e adição de componentes
	public void inicializarComponentes() {
		// JLabel's
		lbID = new JLabel("ID");
		lbNome = new JLabel("Nome");
		lbCpf = new JLabel("Cpf");
		lbDataNasc = new JLabel("Data de nascimento");
		lbTeste = new JLabel("Qtd. Clientes");
		lbPesquisa = new JLabel("Pesquisar nome");

		// JTextFiel's
		txNome = new JTextField();
		try {
			maskCpf = new MaskFormatter("###.###.###-##");
			maskDataNasc = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txID = new JTextField();
		txID.setEditable(false);
		txQtdClientes = new JTextField();
		txQtdClientes.setEditable(false);
		txBarSearch = new JTextField();
		txfCpf = new JFormattedTextField(maskCpf);
		txfDataNasc = new JFormattedTextField(maskDataNasc);
		btAdicionar = new JButton("Adicionar");

		// JButton's
		btEditar = new JButton("Editar");
		btExcluir = new JButton("Excluir");
		btCpfSearch = new JButton("CPF");
		btCpfSearch.setIcon(new ImageIcon(this.getClass().getResource("/img/mg.png")));
		btBarSearch = new JButton(new ImageIcon(getClass().getResource("/img/mg.png")));

		// JComboBox
		comboBox = new JComboBox<String>();
		comboBox.addItem("None");
		comboBox.addItem("Qtd. Clientes");
		comboBox.addItem("A-Z");
		comboBox.addItem("Z-A");
		comboBox.addItem("------------");
		comboBox.addItem("<- 1960");
		comboBox.addItem("1960-1965");
		comboBox.addItem("1966-1970");
		comboBox.addItem("1971-1975");
		comboBox.addItem("1976-1980");
		comboBox.addItem("1981-1985");
		comboBox.addItem("1986-1990");
		comboBox.addItem("1991-1995");
		comboBox.addItem("1996-2000");
		comboBox.addItem("2000 ->");

		// JTable: Criação de uma tabela 'padrão' sem linhas e colunas usando a classe
		// 'DefaultTableModel'
		tabela = new JTable(modelo);
		// Adicionando colunas e linhas a tabela
		modelo.addColumn("Id");
		modelo.addColumn("Nome");
		modelo.addColumn("Cpf");
		modelo.addColumn("Data de Nascimento");
		tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(120);

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
					txID.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
					txNome.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
					txfCpf.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
					txfDataNasc
							.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString().replaceAll("[/\\-]", ""));

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
				txID.setText("");
				txNome.setText("");
				txfCpf.setText("");
				txfDataNasc.setText("");
			}
		});

		// JScrollPane, utilizado para a renderização da JTable
		jScroll = new JScrollPane(tabela);

		setLayout(null);
		lbID.setBounds(120, 20, 30, 20);
		txID.setBounds(150, 20, 30, 20);
		lbNome.setBounds(100, 50, 80, 20);
		txNome.setBounds(150, 50, 150, 20);
		lbCpf.setBounds(320, 50, 80, 20);
		txfCpf.setBounds(360, 50, 100, 20);
		lbDataNasc.setBounds(480, 50, 150, 20);
		txfDataNasc.setBounds(610, 50, 70, 20);
		comboBox.setBounds(690, 50, 100, 20);
		btAdicionar.setBounds(180, 350, 100, 20);
		btEditar.setBounds(300, 350, 100, 20);
		btExcluir.setBounds(420, 350, 100, 20);
		btCpfSearch.setBounds(540, 350, 100, 20);
		btBarSearch.setBounds(500, 20, 30, 19);
		jScroll.setBounds(120, 120, 550, 200);
		lbTeste.setBounds(520, 80, 100, 20);
		txQtdClientes.setBounds(610, 80, 70, 20);
		lbPesquisa.setBounds(200, 20, 100, 20);
		txBarSearch.setBounds(300, 20, 200, 20);

		add(txBarSearch);
		add(lbTeste);
		add(txQtdClientes);
		add(lbID);
		add(txID);
		add(lbNome);
		add(txNome);
		add(lbCpf);
		add(txfCpf);
		add(lbDataNasc);
		add(txfDataNasc);
		add(comboBox);
		add(btAdicionar);
		add(btEditar);
		add(btExcluir);
		add(lbPesquisa);
		add(btCpfSearch);
		add(btBarSearch);
		add(jScroll);
		clientMeth.listClientes(modelo);
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
					if (!(txNome.getText().equals("") || txfCpf.getText().equals("   .   .   -  ")
							|| txfDataNasc.getText().equals("  /  /    "))) {

						// Caso esteja todo correto um obj da classe Cliente e da classe
						// ClienteComands é instanciado
						Cliente cliente = new Cliente();
						ClienteComands CmdAdicionar = new ClienteComands();

						// Os dados dos campos são armazenados no objeto cliente
						// Obs: para data é usado um método de a conversão para ser compativel com a
						// data usada pelo banco de dados
						cliente.setNomeCliente(txNome.getText());
						cliente.setCpfCliente(txfCpf.getText());
						cliente.setDataNascCliente(
								java.sql.Date.valueOf(clientMeth.converterData(txfDataNasc.getText())));

						// Com o obj da classe ClienteComands é possivel chamar o método create()
						// passando o obj cliente
						CmdAdicionar.create(cliente);
						// Após tudo feito os campos são limpos e o método da classe
						// ClienteProdutoMethods é chamado. Este método têm como função listar todos os
						// clientes cadastrados
						txNome.setText("");
						txfCpf.setText("");
						txfDataNasc.setText("");
						clientMeth.listClientes(modelo);
					} else {
						JOptionPane.showMessageDialog(null, "Preencha os campos corretamente!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "NÃ£o selecione linhas");
					tabela.clearSelection();
					txID.setText("");
					txNome.setText("");
					txfCpf.setText("");
					txfDataNasc.setText("");
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
					if (!(txNome.getText().equals("") || txfCpf.getText().equals("   .   .   -  ")
							|| txfDataNasc.getText().equals("  /  /    "))) {
						// Caso esteja todo correto um obj da classe Cliente e da classe
						// ClienteComands é instanciado
						Cliente cliente = new Cliente();
						ClienteComands cmdUpdate = new ClienteComands();

						// Os dados dos campos são armazenados no objeto cliente
						cliente.setNomeCliente(txNome.getText());
						cliente.setCpfCliente(txfCpf.getText());
						cliente.setDataNascCliente(
								java.sql.Date.valueOf(clientMeth.converterData(txfDataNasc.getText())));
						cliente.setIdCliente((int) tabela.getValueAt(tabela.getSelectedRow(), 0));

						// Com o obj da classe ClienteComands é possivel chamar o método update()
						// passando o obj cliente
						cmdUpdate.update(cliente);
						// Após tudo feito os campos são limpos e o método listClientes() é chamado
						txID.setText("");
						txNome.setText("");
						txfCpf.setText("");
						txfDataNasc.setText("");
						clientMeth.listClientes(modelo);
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
						// Um obj da classe Cliente e da classe ClienteComands é instanciado
						Cliente cliente = new Cliente();
						ClienteComands cmdDelete = new ClienteComands();

						// O ID do cliente selecionado é armazenado no objeto cliente
						cliente.setIdCliente((int) tabela.getValueAt(tabela.getSelectedRow(), 0));

						// Com o obj da classe ClienteComands é possivel chamar o método delete()
						// passando o obj cliente
						cmdDelete.delete(cliente);
						// Após tudo feito os campos são limpos e o método listClientes() é chamado
						txID.setText("");
						txNome.setText("");
						txfCpf.setText("");
						txfDataNasc.setText("");
						clientMeth.listClientes(modelo);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um linha para remover!");
				}
			}
		});

		btCpfSearch.addActionListener(new ActionListener() {
			/**
			 * Este evento serve para uma pesquisa única(cpf), quando o botão 'CPF' é
			 * clicado o método listForCpf() é chamado passando como parâmetro o texto
			 * digitado no campo 'cpf'. Este método retorna o cliente dono do cpf digitado.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txfCpf.getText().equals("   .   .   -  ")) {
					// Caso o campo esteja vazio uma mensagem é apresentada e o método
					// listClientes() é chamado retornando a lista de todos os clientes
					JOptionPane.showMessageDialog(null, "Preencha o campo \"Cpf\" para pesquisar");
					clientMeth.listClientes(modelo);
				} else {
					clientMeth.listForCpf(modelo, txfCpf.getText());
					txfCpf.setText("");
				}
			}
		});

		comboBox.addActionListener(new ActionListener() {
			/**
			 * Este evento no JComboBox possibilita alguns tipos de pesquisa como, por
			 * exemplo, a quantidade de clientes cadastrados, organização da tabela por
			 * ordem alfabética ou uma lista de clientes nascidos em uma certa faixa de anos
			 */
			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox<Object> comboBox = (JComboBox<Object>) event.getSource();

				Object selected = comboBox.getSelectedItem();
				ClienteComands cmd = new ClienteComands();
				if (selected.toString().equals("None")) {
					clientMeth.listClientes(modelo);
					txQtdClientes.setText("");
				} else if (selected.toString().equals("Qtd. Clientes")) {
					txQtdClientes.setText(cmd.count());
				} else if (selected.toString().equals("A-Z")) {
					clientMeth.listClientesAsc(modelo);
				} else if (selected.toString().equals("Z-A")) {
					clientMeth.listClientesDesc(modelo);
				} else if (selected.toString().equals("<- 1960")) {
					clientMeth.listClienteNasc(modelo, 1900, 1959);
				} else if (selected.toString().equals("1960-1965")) {
					clientMeth.listClienteNasc(modelo, 1960, 1965);
				} else if (selected.toString().equals("1966-1970")) {
					clientMeth.listClienteNasc(modelo, 1966, 1970);
				} else if (selected.toString().equals("1971-1975")) {
					clientMeth.listClienteNasc(modelo, 1971, 1975);
				} else if (selected.toString().equals("1976-1980")) {
					clientMeth.listClienteNasc(modelo, 1976, 1980);
				} else if (selected.toString().equals("1981-1985")) {
					clientMeth.listClienteNasc(modelo, 1981, 1985);
				} else if (selected.toString().equals("1986-1990")) {
					clientMeth.listClienteNasc(modelo, 1986, 1990);
				} else if (selected.toString().equals("1991-1995")) {
					clientMeth.listClienteNasc(modelo, 1991, 1995);
				} else if (selected.toString().equals("1996-2000")) {
					clientMeth.listClienteNasc(modelo, 1996, 2000);
				} else if (selected.toString().equals("2000 ->")) {
					clientMeth.listClienteNasc(modelo, 2001, 2099);
				}
			}
		});

		btBarSearch.addActionListener(new ActionListener() {

			/**
			 * Este evento serve para pesquisar o nome de um cliente, quando o botão ao lado
			 * do campo de pesquisa é clicado o método listClienteNome() é chamado passando
			 * como parâmetro o texto digitado no campo 'Pesquisar nome'. Este método
			 * retorna o(s) cliente(s) com o nome fornecido
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				clientMeth.listClienteNome(modelo, txBarSearch.getText());
			}
		});
	}

}
