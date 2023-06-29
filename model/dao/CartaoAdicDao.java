package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.entity.Cartao;
import model.entity.CartaoAdic;

public class CartaoAdicDao {
	
	public int pesquisaAdic(String nome_adic) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT id_adic FROM adicional WHERE nome_adic LIKE ?";
		int id_adic = 0;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, nome_adic + "%");
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				id_adic = rs.getInt("id_adic");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return id_adic;
	}

	public void inserirCartaoAdic(CartaoAdic cartaoadic) {
		// ABRIR CONEXÃO
		ConectaBD con = new ConectaBD();
		
		String sql_1 = "INSERT INTO cartao_adic (nr_cartao_adic, data_val_adic, limite_adic, id_adic) VALUES (?,?,?,?)";
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql_1);
			pst.setString(1, cartaoadic.getNumCartao_adic());
			pst.setString(2, cartaoadic.getDataVal_adic());
			pst.setDouble(3, cartaoadic.getLimiteAdic());
			pst.setInt(4, cartaoadic.getId_adic());
			pst.execute();
			System.out.println("Cartão adicional inserido com sucesso.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<CartaoAdic> consultarTodosCardAdic(){
		ConectaBD con = new ConectaBD();
		String sql = "SELECT * FROM cartao_adic";
		List<CartaoAdic> lista = new LinkedList<CartaoAdic>();
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				CartaoAdic cartaoadic = new CartaoAdic();
				int id = rs.getInt("id_cartao_adic");
				String numero = rs.getString("nr_cartao_adic");
				String data = rs.getString("data_val_adic");
				int limite = rs.getInt("limite_adic");
				int id_adic = rs.getInt("id_adic");
				cartaoadic.setId_cartao_adic(id);
				cartaoadic.setNumCartao_adic(numero);
				cartaoadic.setDataVal_adic(data);
				cartaoadic.setLimiteAdic(limite);
				cartaoadic.setId_adic(id_adic);
				lista.add(cartaoadic);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}

	public CartaoAdic consultaLimiteAdic(int id_adic) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT a.id_adic, a.nome_adic, cra.nr_cartao_adic, cra.limite_adic FROM cartao_adic as cra INNER JOIN adicional as a ON a.id_adic = cra.id_adic WHERE a.id_adic = ?";
		CartaoAdic cardadic = null;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setInt(1, id_adic);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id_adic");
				String nome_adic = rs.getString("nome_adic");
				String numero = rs.getString("nr_cartao_adic");
				double limite = rs.getDouble("limite_adic");
				cardadic = new CartaoAdic(id, nome_adic, numero, limite);
				cardadic.setLimiteAdic(rs.getDouble("limite"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return cardadic;
	}

}
