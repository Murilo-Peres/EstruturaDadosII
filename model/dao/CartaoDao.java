package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.entity.Cartao;
import model.entity.Cliente;

public class CartaoDao {
	
	public int pesquisaCliente(String nome_cliente) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT id_cliente FROM cliente WHERE nome_cliente LIKE ?";
		int id_cliente = 0;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, nome_cliente + "%");
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				id_cliente = rs.getInt("id_cliente");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return id_cliente;
	}

	public void inserirCartao(Cartao cartao) {
		// ABRIR CONEXÃO
		ConectaBD con = new ConectaBD();
		
		String sql_1 = "INSERT INTO cartao (nr_cartao, data_val, limite, id_cliente) VALUES (?,?,?,?)";
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql_1);
			pst.setString(1, cartao.getNumCartao());
			pst.setString(2, cartao.getDataVal());
			pst.setDouble(3, cartao.getLimite());
			pst.setInt(4, cartao.getIdCliente());
			pst.execute();
			System.out.println("Cartão inserido com sucesso.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Cartao> consultarTodosCard(){
		ConectaBD con = new ConectaBD();
		String sql = "SELECT * FROM cartao";
		List<Cartao> lista = new LinkedList<Cartao>();
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Cartao cartao = new Cartao();
				int id = rs.getInt("id_cartao");
				String numero = rs.getString("nr_cartao");
				String data = rs.getString("data_val");
				int limite = rs.getInt("limite");
				int id_cliente = rs.getInt("id_cliente");
				cartao.setId(id);
				cartao.setNumCartao(numero);
				cartao.setDataVal(data);
				cartao.setLimite(limite);
				cartao.setIdCliente(id_cliente);
				lista.add(cartao);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

	public Cartao consultaLimite(int id_cliente) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT c.id_cliente, c.nome_cliente, cr.nr_cartao, cr.limite FROM cartao as cr INNER JOIN cliente as c ON c.id_cliente = cr.id_cliente WHERE c.id_cliente = ?";
		Cartao card = null;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setInt(1, id_cliente);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id_cliente");
				String nome = rs.getString("nome_cliente");
				String numero = rs.getString("nr_cartao");
				double limite = rs.getDouble("limite");
				card = new Cartao(id, nome, numero, limite);
				card.setLimite(rs.getDouble("limite"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return card;
	}

}
