package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.entity.Adicional;

public class AdicionalDao {

	public void inserir(Adicional adicional) {
		// ABRIR CONEXÃO
		ConectaBD con = new ConectaBD();
		String sql = "INSERT INTO adicional (nome_adic, id_cliente) VALUES (?, ?)";
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, adicional.getNome_adic());
			pst.setInt(2, adicional.getId_cliente());
			pst.execute();
			System.out.println(adicional.getNome_adic() + " inserido com sucesso.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Adicional> consultarTodosAdic(){
		ConectaBD con = new ConectaBD();
		String sql = "SELECT * FROM adicional";
		List<Adicional> lista = new LinkedList<Adicional>();
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Adicional adic = new Adicional();
				int id = rs.getInt("id_adic");
				String nome = rs.getString("nome_adic");
				int resp = rs.getInt("id_cliente");
				adic.setId_adic(id);
				adic.setNome_adic(nome);
				adic.setId_cliente(resp);;
				lista.add(adic);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	
	public Adicional consultarAdic(int id_adic) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT * FROM adicional WHERE id_adic = ?";
		Adicional a = null;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setInt(1, id_adic);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				String nome = rs.getString("nome_adic");
				int id_cliente = rs.getInt("id_cliente");
				a = new Adicional(nome, id_cliente);
				a.setId_adic(rs.getInt("id_adic"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return a;
	}

}
