package model.entity;

import model.dao.AdicionalDao;

public class Adicional {
	protected int id_adic, id_cliente;
	protected String nome_adic;

	public Adicional() {
		
	}
	
	public Adicional(int id_adic, String nome_adic, int id_cliente) {
		this.id_adic = id_adic;
		this.nome_adic = nome_adic;
		this.id_cliente = id_cliente;
	}

	public Adicional(String nome_adic, int id_cliente) {
		this.nome_adic = nome_adic;
		this.id_cliente = id_cliente;
	}

	public Adicional(String nome_adic) {
		this.nome_adic = nome_adic;
	}

	public int getId_adic() {
		return id_adic;
	}

	public void setId_adic(int id_adic) {
		this.id_adic = id_adic;
	}

	public String getNome_adic() {
		return nome_adic;
	}

	public void setNome_adic(String nome_adic) {
		this.nome_adic = nome_adic;
	}
	
	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public void inserir() {
		AdicionalDao adicionalDao = new AdicionalDao();
		adicionalDao.inserir(this);
	}
}
