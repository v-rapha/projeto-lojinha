package produto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import cliente.ClienteProdutoMethods;

public class ProdutoCadastro extends JPanel {
	// Atributos
	private JLabel lbID, lbNome, lbCategoria, lbPreco, lbResultados;
	private JTextField txID, txNome, txCategoria, txPreco, txResultados;
	private JButton btAdicionar, btEditar, btExcluir, btCatSearch;
	private JComboBox<String> comboBox;
	private JTable tabela;
	private JScrollPane jScroll;
	private DefaultTableModel modelo = new DefaultTableModel();
	ClienteProdutoMethods productMethod = new ClienteProdutoMethods();

	// Construtor
	public ProdutoCadastro() {
		inicializarComponentes();
		criarEventos();
	}

	// Método para criação e adição de componentes
	public void inicializarComponentes() {
		// JLabel's
		lbID = new JLabel("ID");
		lbNome = new JLabel("Nome");
		lbCategoria = new JLabel("Categoria");
		lbPreco = new JLabel("Preço $");
		lbResultados = new JLabel("Resultados");

		// JTextFiel's
		txNome = new JTextField();
		txID = new JTextField();
		txID.setEditable(false);
		txCategoria = new JTextField();
		txPreco = new JTextField();
		txCategoria = new JTextField();
		txResultados = new JTextField();
		txResultados.setEditable(false);
		
		// JButton's
		btAdicionar = new JButton("Adicionar");
		btEditar = new JButton("Editar");
		btExcluir = new JButton("Excluir");
		btCatSearch = new JButton("Categoria");
		btCatSearch.setIcon(new ImageIcon(this.getClass().getResource("/img/mg.png")));
		btCatSearch.setHorizontalAlignment(SwingConstants.LEFT);
		
		// JComboBox
		comboBox = new JComboBox<String>();
		comboBox.addItem("None");
		comboBox.addItem("Maior Preço");
		comboBox.addItem("Menor Preço");
		comboBox.addItem("Soma dos Valores");
		comboBox.addItem("A-Z");
		comboBox.addItem("Z-A");
		comboBox.addItem("Qtd. de Produtos");

		// JTable: Criação de uma tabela 'padrão' sem linhas e colunas usando a classe
				// 'DefaultTableModel'
		tabela = new JTable(modelo);
		// Adicionando colunas e linhas a tabela
		modelo.addColumn("Id");
		modelo.addColumn("Nome");
		modelo.addColumn("Categoria");
		modelo.addColumn("Preco $");
		modelo.addColumn("À Vista");
		tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(120);
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
					txID.setText(tabela.getValueAt(tabela.getSelectedRow(), 0).toString());
					txNome.setText(tabela.getValueAt(tabela.getSelectedRow(), 1).toString());
					txCategoria.setText(tabela.getValueAt(tabela.getSelectedRow(), 2).toString());
					txPreco.setText(tabela.getValueAt(tabela.getSelectedRow(), 3).toString());
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma linha");
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {
				txID.setText("");
				txNome.setText("");
				txCategoria.setText("");
				txPreco.setText("");
			}
		});

		// JScrollPane, utilizado para a renderização da JTable
		jScroll = new JScrollPane(tabela);

		setLayout(null);
		lbID.setBounds(120, 20, 30, 20);
		txID.setBounds(150, 20, 30, 20);
		lbNome.setBounds(100, 50, 80, 20);
		txNome.setBounds(150, 50, 150, 20);
		lbCategoria.setBounds(320, 50, 80, 20);
		txCategoria.setBounds(395, 50, 100, 20);
		lbPreco.setBounds(520, 50, 150, 20);
		txPreco.setBounds(570, 50, 70, 20);
		lbResultados.setBounds(570, 80, 100, 20);
		txResultados.setBounds(650, 80, 100, 20);
		comboBox.setBounds(650, 50, 100, 20);
		btAdicionar.setBounds(180, 350, 100, 20);
		btEditar.setBounds(300, 350, 100, 20);
		btExcluir.setBounds(420, 350, 100, 20);
		btCatSearch.setBounds(540, 350, 110, 20);
		jScroll.setBounds(120, 120, 550, 200);
		
		add(lbID);
		add(txID);
		add(lbNome);
		add(txNome);
		add(lbCategoria);
		add(txCategoria);
		add(lbPreco);
		add(txPreco);
		add(lbResultados);
		add(txResultados);
		add(comboBox);
		add(btAdicionar);
		add(btEditar);
		add(btExcluir);
		add(btCatSearch);
		add(jScroll);
		productMethod.listProdutos(modelo);
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
				if(tabela.getSelectedRow() == -1) {
					if(!(txNome.getText().equals("") || txCategoria.getText().equals("") || txPreco.getText().equals(""))) {
						
						// Caso esteja todo correto um obj da classe Produto e da classe
						// ProdutoComands é instanciado
						Produto produto = new Produto();
						ProdutoComands cmd = new ProdutoComands();
						
						// Os dados dos campos são armazenados no obj produto
						// Obs: para data é usado um método de a conversão para ser compativel com a
						// data usada pelo banco de dados
						produto.setNome(txNome.getText());
						produto.setCategoria(txCategoria.getText());
						produto.setPreco(Float.parseFloat(txPreco.getText().replaceAll("[,]", ".")));
						
						// Com o obj da classe ProdutoComands é possivel chamar o método create()
						// passando o obj produto
						cmd.create(produto);
						// Após tudo feito os campos são limpos e o método da classe
						// ClienteProdutoMethods é chamado. Este método têm como função listar todos os
						// produtos cadastrados
						txNome.setText("");
						txCategoria.setText("");
						txPreco.setText("");
						productMethod.listProdutos(modelo);
					} else {
						JOptionPane.showMessageDialog(null, "Preencha os campos corretamente!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Não selecione linhas");
					tabela.clearSelection();
					txID.setText("");
					txNome.setText("");
					txCategoria.setText("");
					txPreco.setText("");
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
					if (!(txNome.getText().equals("") || txCategoria.getText().equals("") || txPreco.getText().equals(""))) {
						// Caso esteja todo correto um obj da classe Produto e da classe
						// ProdutoComands é instanciado
						Produto produto = new Produto();
					ProdutoComands cmd = new ProdutoComands();

					// Os dados dos campos são armazenados no obj produto
					produto.setNome(txNome.getText());
					produto.setCategoria(txCategoria.getText());
					produto.setPreco(Float.parseFloat(txPreco.getText().replaceAll("[,]", ".")));
					produto.setId((int) tabela.getValueAt(tabela.getSelectedRow(), 0));

					// Com o obj da classe ProdutoComands é possivel chamar o método update()
					// passando o obj produto
					cmd.update(produto);
					// Após tudo feito os campos são limpos e o método listProdutos() é chamado
					txID.setText("");
					txNome.setText("");
					txCategoria.setText("");
					txPreco.setText("");
					productMethod.listProdutos(modelo);
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
						// Um obj da classe Produto e da classe ProdutoComans é instanciado
						Produto produto = new Produto();
						ProdutoComands cmd = new ProdutoComands();

						// O ID do produto selecionado é armazenado no obj produto
						produto.setId((int) tabela.getValueAt(tabela.getSelectedRow(), 0));

						// Com o obj da classe ProdutoComands é possivel chamar o método delete()
						// passando o obj produto
						cmd.delete(produto);
						// Após tudo feito os campos são limpos e o método listProduto() é chamado
						txID.setText("");
						txNome.setText("");
						txCategoria.setText("");
						txPreco.setText("");
						productMethod.listProdutos(modelo);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um linha para remover!");
				}
			}
		});
		
		btCatSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				productMethod.listCategoria(modelo, txCategoria.getText());
			}
		});
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				JComboBox<Object> comboBox = (JComboBox<Object>) event.getSource();

                Object selected = comboBox.getSelectedItem();
                ProdutoComands cmd = new ProdutoComands();
                if (selected.toString().equals("None")) {
                	txResultados.setText("");
                	productMethod.listProdutos(modelo);
                } else 
                if(selected.toString().equals("Maior Preço")) {
                	txResultados.setText(cmd.max());                	
                }else if(selected.toString().equals("Menor Preço")) {
                	txResultados.setText(cmd.min());
                } else if (selected.toString().equals("Soma dos Valores")) {
                	txResultados.setText(cmd.sum());
                } else if (selected.toString().equals("A-Z")) {
                	productMethod.listProdutoAsc(modelo);
                } else if (selected.toString().equals("Z-A")) {
                	productMethod.listProdutoDesc(modelo);
                } else if (selected.toString().equals("Qtd. de Produtos")) {
                	txResultados.setText(cmd.count());
                }
			}
		});
	}
}
