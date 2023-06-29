package model.entity;

public class Cartao extends Cliente{
	protected Integer id_cartao;
	private Integer id_cliente;
	private String numCartao, dataVal;
	protected double limite;
	public boolean geraCartao;
	
	public Cartao() {
		
	}
	
	public Cartao(String nome, String doc, String numCartao, String dataVal, double limite) {
		super(nome, doc);
		this.numCartao = numCartao;
		this.dataVal = dataVal;
		this.limite = limite;
	}

	public Cartao(int id, String nome, String numero, double limite) {
		this.id_cliente = id;
		this.nome = nome;
		this.numCartao = numero;
		this.limite = limite;
	}

	public Cartao(String nr_cartao, String data, double limite) {
		this.numCartao = nr_cartao;
		this.dataVal = data;
		this.limite = limite;
	}

	public Cartao(String nr_cartao, String data, int limite1, int id_cliente) {
		this.numCartao = nr_cartao;
		this.dataVal = data;
		this.limite = limite1;
		this.id_cliente = id_cliente;
	}

	public Cartao(int id_cartao) {
		this.id_cartao = id_cartao;
	}

	public String getNumCartao() {
		return numCartao;
	}

	public void setNumCartao(String numCartao) {
		this.numCartao = numCartao;
	}

	public String getDataVal() {
		return dataVal;
	}

	public void setDataVal(String dataVal) {
		this.dataVal = dataVal;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public void geraCartao() {
		geraCartao = true;
		System.out.println("Novo cartão de crédito gerado.");
	}

	public int getId_cartao() {
		return id_cartao;
	}

	public void setId_cartao(int id_cartao) {
		this.id_cartao = id_cartao;
	}

	public void setIdCliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Integer getIdCliente() {
		return id_cliente;
	}

	public void setNomeCliente(String nomeCliente) {
		// TODO Auto-generated method stub	
	}

}

