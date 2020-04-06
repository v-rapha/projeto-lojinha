package lojinhaNewlojinha;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import cliente.ClienteCadastro;
import produto.ProdutoCadastro;
import venda.VendaCadastro;

public class ViewPrincipal extends JFrame {
	// Attributes
	private Container contentPane;
	private JPanel jpainel;
	private JLabel titulo;
	private JMenuBar mnbBarra;
	private JMenu mnArquivo, mnCadastro, mnSobre;
	private JMenuItem miSair, miMain, miCliente, miProduto, miVenda, miInstrucoes;

	// Constructor
	public ViewPrincipal() {
		inicializarComponentes();
		definirEventos();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void inicializarComponentes() {
		setTitle("Lojinha");
		setBounds(0, 0, 800, 500);

		// Creating Objects
		// Screen
		contentPane = getContentPane();
		titulo = new JLabel("<html><font color='white'>Lojinha new lojinha</font></html>");
		titulo.setFont(titulo.getFont().deriveFont(30.0f));
		jpainel = new JPanel();
		jpainel.setBackground(new Color(40, 42, 54));
		jpainel.add(titulo);
		contentPane.add(jpainel);

		// MenuBarItens
		mnbBarra = new JMenuBar();
		mnbBarra.setBackground(new Color(28, 29, 38));
		// mnbBarra.setForeground(Color.WHITE);
		mnArquivo = new JMenu("<html><font color='white'>Arquivo</font></html>");
		mnCadastro = new JMenu("<html><font color='white'>Cadastro</font></html>");
		mnSobre = new JMenu("<html><font color='white'>Sobre</font></html>");
		miSair = new JMenuItem("Sair");
		miMain = new JMenuItem("Tela Principal");

		// MenuBar SubItens
		miCliente = new JMenuItem("Cliente");
		miProduto = new JMenuItem("Produto");
		miVenda = new JMenuItem("Venda");
		miInstrucoes = new JMenuItem("Instruções");

		// Add Objects
		mnArquivo.add(miSair);
		mnArquivo.add(miMain);
		mnCadastro.add(miCliente);
		mnCadastro.add(miProduto);
		mnCadastro.add(miVenda);
		mnSobre.add(miInstrucoes);
		mnbBarra.add(mnArquivo);
		mnbBarra.add(mnCadastro);
		mnbBarra.add(mnSobre);
		setJMenuBar(mnbBarra);
	}

	private void definirEventos() {
		miSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		miMain.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ViewPrincipal mainView = new ViewPrincipal();
				mainView.setVisible(true);
				mainView.setLocationRelativeTo(null);
			}
		});

		miCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ClienteCadastro cliente = new ClienteCadastro();
				contentPane.removeAll();
				contentPane.add(cliente);
				contentPane.validate();
				setTitle("Cadastro de clientes");
			}
		});

		miProduto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProdutoCadastro produto = new ProdutoCadastro();
				contentPane.removeAll();
				contentPane.add(produto);
				contentPane.validate();
				setTitle("Cadastro de Produtos");
			}
		});

		miVenda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VendaCadastro venda = new VendaCadastro();
				contentPane.removeAll();
				contentPane.add(venda);
				contentPane.validate();
				setTitle("Cadastro de Vendas");
			}
		});

		miInstrucoes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Instrucoes instrucao = new Instrucoes();
				contentPane.removeAll();
				contentPane.add(instrucao);
				contentPane.validate();
				setTitle("Cadastro de Vendas");
			}
		});
	}

}
