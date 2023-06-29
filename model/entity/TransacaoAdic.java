package model.entity;

import model.dao.TransacaoAdicDao;

public class TransacaoAdic extends CartaoAdic{
	private Integer id_transacao_adic, id_estab_adic;
	private String nome_estab_adic;
	private double compra_adic, total_adic;

	public TransacaoAdic() {
		
	}

	public TransacaoAdic(int id, double valor, int id_cartao_adic) {
		super(id_cartao_adic);
		this.id_transacao_adic = id;
		this.compra_adic = valor;
	}

	public TransacaoAdic(int id, double v_total) {
		this.id_transacao_adic = id;
		this.total_adic = v_total;
	}

	public TransacaoAdic(double valor, int id) {
		this.compra_adic = valor;
		this.id_cartao_adic = id;
	}
	
	public TransacaoAdic(int id_transacao_adic, int id_estab_adic) {
		this.id_transacao_adic = id_transacao_adic;
		this.id_estab_adic = id_estab_adic;
	}

	public int getId_transacao_adic() {
		return id_transacao_adic;
	}

	public void setId_transacao_adic(int id_transacao_adic) {
		this.id_transacao_adic = id_transacao_adic;
	}

	public int getId_estab_adic() {
		return id_estab_adic;
	}

	public void setId_estab_adic(int id_estab_adic) {
		this.id_estab_adic = id_estab_adic;
	}

	public String getNome_estab_adic() {
		return nome_estab_adic;
	}

	public void setNome_estab_adic(String nome_estab_adic) {
		this.nome_estab_adic = nome_estab_adic;
	}

	public double getCompra_adic() {
		return compra_adic;
	}

	public void setCompra_adic(double compra_adic) {
		this.compra_adic = compra_adic;
	}

	public double getTotal_adic() {
		return total_adic;
	}

	public void setTotal_adic(double total_adic) {
		this.total_adic = total_adic;
	}

	public void compraAdic (double compra_adic) {
		if (compra_adic > limiteAdic) {
			System.out.println("Compra não realizada. Limite excedido!");
		} else {
			limiteAdic -= compra_adic;
			total_adic += compra_adic;
			System.out.println("Compra realizada!");
		}
	}
	
	public double pagarCartaoAdic() {
		return total_adic;
	}
	
	public void inserirAdic() {
		TransacaoAdicDao transacaoAdicDao = new TransacaoAdicDao();
		transacaoAdicDao.inserirCompraAdic(this);
	}
}
