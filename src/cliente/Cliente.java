package cliente;

import java.sql.Date;

public class Cliente {
	// Atributos
	private int idCliente;
	private String nomeCliente, cpfCliente;
	private Date DataNascCliente;

	// Getters & Setters
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public Date getDataNascCliente() {
		return DataNascCliente;
	}

	public void setDataNascCliente(Date dataNascCliente) {
		DataNascCliente = dataNascCliente;
	}
}
