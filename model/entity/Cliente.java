package model.entity;

import model.dao.ClienteDao;

public class Cliente {
	protected int id;
	protected String nome;
	private String doc;

	public Cliente(String doc) {
		this.doc = doc;
	}
	
	public Cliente(String nome, String doc) {
		this.nome = nome;
		this.doc = doc;
	}
	
	public Cliente() {
		
	}

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

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}
	
	public void inserir() {
		ClienteDao clienteDao = new ClienteDao();
		clienteDao.inserir(this);
	}
}
