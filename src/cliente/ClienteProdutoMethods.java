package cliente;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import produto.Produto;
import produto.ProdutoComands;

public class ClienteProdutoMethods {

	/* Cliente Methods */
	public void listClientes(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		ClienteComands cmdCliente = new ClienteComands();

		for (Cliente c : cmdCliente.read()) {
			modelo.addRow(
					new Object[] { 
							c.getIdCliente(),
							c.getNomeCliente(),
							c.getCpfCliente(),
							c.getDataNascCliente() 
							});
		}
	}

	public void listForCpf(DefaultTableModel modelo, String cpf) {
		modelo.setNumRows(0);
		ClienteComands cmdCliente = new ClienteComands();

		for (Cliente c : cmdCliente.readCpf(cpf)) {
			modelo.addRow(
					new Object[] {
							c.getIdCliente(),
							c.getNomeCliente(),
							c.getCpfCliente(), 
							c.getDataNascCliente() 
							});
		}
	}

	public void listClienteNome(DefaultTableModel modelo, String pesquisa) {
		modelo.setNumRows(0);
		ClienteComands cmdCliente = new ClienteComands();

		for (Cliente c : cmdCliente.readName(pesquisa)) {
			modelo.addRow(
					new Object[] {
							c.getIdCliente(),
							c.getNomeCliente(),
							c.getCpfCliente(),
							c.getDataNascCliente() 
							});
		}
	}

	public void listClientesAsc(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		ClienteComands cmd = new ClienteComands();

		for (Cliente c : cmd.readAsc()) {
			modelo.addRow(
					new Object[] { 
							c.getIdCliente(), 
							c.getNomeCliente(),
							c.getCpfCliente(),
							c.getDataNascCliente()
							});
		}
	}

	public void listClientesDesc(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		ClienteComands cmd = new ClienteComands();

		for (Cliente c : cmd.readDesc()) {
			modelo.addRow(
					new Object[] {
							c.getIdCliente(),
							c.getNomeCliente(), 
							c.getCpfCliente(), 
							c.getDataNascCliente() 
							});
		}
	}

	public void listClienteNasc(DefaultTableModel modelo, int ano1, int ano2) {
		modelo.setNumRows(0);
		ClienteComands cmdCliente = new ClienteComands();

		for (Cliente c : cmdCliente.readDataNasc(ano1, ano2)) {
			modelo.addRow(
					new Object[] { 
							c.getIdCliente(),
							c.getNomeCliente(),
							c.getCpfCliente(),
							c.getDataNascCliente() 
							});
		}
	}

	/* Produtos Methods */
	public void listProdutos(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		ProdutoComands cmd = new ProdutoComands();

		for (Produto p : cmd.read()) {
			modelo.addRow(new Object[] {
							p.getId(),
							p.getNome(),
							p.getCategoria(),
							p.getPreco(), 
							p.getPrecoDesc()
							});
		}
	}

	public void listCategoria(DefaultTableModel modelo, String search) {
		modelo.setNumRows(0);
		ProdutoComands cmd = new ProdutoComands();

		for (Produto p : cmd.readCategoria(search)) {
			modelo.addRow(new Object[] {
							p.getId(),
							p.getNome(),
							p.getCategoria(),
							p.getPreco(),
							p.getPrecoDesc() 
							});
		}
	}

	public void listProdutoAsc(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		ProdutoComands cmd = new ProdutoComands();

		for (Produto p : cmd.readAsc()) {
			modelo.addRow(new Object[] {
							p.getId(),
							p.getNome(),
							p.getCategoria(),
							p.getPreco(),
							p.getPrecoDesc()
							});
		}
	}

	public void listProdutoDesc(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		ProdutoComands cmd = new ProdutoComands();

		for (Produto p : cmd.readDesc()) {
			modelo.addRow(new Object[] {
							p.getId(),
							p.getNome(),
							p.getCategoria(),
							p.getPreco(),
							p.getPrecoDesc()
							});
		}
	}

	/* Data Method */
	public String converterData(String data) {
		// Este metodo recebe uma String como dia/mes/ano e retorna
		// ano/mes/dia
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date d;
		try {
			d = df.parse(data);
			df = new SimpleDateFormat("yyyy-MM-dd");
			String s = df.format(d);
			return s;
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "OLHA LÁ " + e.getMessage());
		}
		return "IH rapaz";
	}
}
