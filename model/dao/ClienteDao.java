package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.entity.Cliente;

public class ClienteDao {

	public void inserir(Cliente cliente) {
		// ABRIR CONEXÃO
		ConectaBD con = new ConectaBD();
		String sql = "INSERT INTO cliente (nome_cliente, cpf) VALUES (?,?)";
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, cliente.getNome());
			pst.setString(2, cliente.getDoc());
			pst.execute();
			System.out.println(cliente.getNome() + " inserido com sucesso.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public List<Cliente> consultarTodos(){
		ConectaBD con = new ConectaBD();
		String sql = "SELECT * FROM cliente";
		List<Cliente> lista = new LinkedList<Cliente>();
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Cliente cliente = new Cliente();
				int id = rs.getInt("id_cliente");
				String nome = rs.getString("nome_cliente");
				String cpf = rs.getString("cpf");
				cliente.setId(id);
				cliente.setNome(nome);
				cliente.setDoc(cpf);
				lista.add(cliente);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	
	public Cliente consultar(int id_cliente) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
		Cliente c = null;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setInt(1, id_cliente);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				String nome = rs.getString("nome_cliente");
				String cpf = rs.getString("cpf");
				c = new Cliente(nome, cpf);
				c.setId(rs.getInt("id_cliente"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return c;
	}
}