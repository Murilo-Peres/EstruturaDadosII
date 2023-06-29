package model.entity;

import model.dao.TransacaoDao;

public class Transacao extends Cartao{
	private int id_transacao, id_estab;
	private String nome_estab;
	private double compra, total;

	public Transacao() {
		
	}
	
	public Transacao(int id_transacao, int id_estab) {
		this.id_transacao = id_transacao;
		this.id_estab = id_estab;
	}

	public Transacao(double valor, int id_cartao) {
		super(id_cartao);
		this.compra = valor;
	}

	public Transacao(int id, double valor, int id_cartao) {
		super(id_cartao);
		this.id_transacao = id;
		this.compra = valor;
	}
	
	public Transacao(int id_cartao, double v_total) {
		super(id_cartao);
		this.total = v_total;
	}

	public Transacao(int id, double valor, int id_cartao, String estab) {
		super(id_cartao);
		this.id_transacao = id;
		this.compra = valor;
		this.nome_estab = estab;
	}

	public int getId_transacao() {
		return id_transacao;
	}

	public void setId_transacao(int id_transacao) {
		this.id_transacao = id_transacao;
	}

	public void estab(String nome_estab) {
		this.nome_estab = nome_estab;
	}
	
	public double getCompra() {
		return compra;
	}

	public void setCompra(double compra) {
		this.compra = compra;
	}
	
	public String getNome_estab() {
		return nome_estab;
	}

	public void setNome_estab(String nome_estab) {
		this.nome_estab = nome_estab;
	}

	public int getId_estab() {
		return id_estab;
	}

	public void setId_estab(int id_estab) {
		this.id_estab = id_estab;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void compra (double compra) {
		if (compra > limite) {
			System.out.println("Compra não realizada. Limite excedido!");
		} else {
			limite -= compra;
			total += compra;
			System.out.println("Compra realizada!");
		}
	}
	
	public double pagarCartao() {
		return total;
	}
	
	public void inserir() {
		TransacaoDao transacaoDao = new TransacaoDao();
		transacaoDao.inserirCompra(this);
	}
}
