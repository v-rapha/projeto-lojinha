package produto;

public class Produto {
	// Atributos
	private int id;
	private String nome, categoria;
	private float preco, precoDesc;
	
	// Getters & Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public float getPreco() {
		return preco;
	}
	public void setPreco(float preco) {
		this.preco = preco;
	}
	public float getPrecoDesc() {
		return precoDesc;
	}
	public void setPrecoDesc(float precoDesc) {
		this.precoDesc = precoDesc;
	}

}
