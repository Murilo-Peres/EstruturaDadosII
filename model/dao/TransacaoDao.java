package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.entity.Cartao;
import model.entity.Transacao;

public class TransacaoDao {

	public int inserirCompra(Transacao transacao) {
		// ABRIR CONEXÃO
		ConectaBD con = new ConectaBD();
		String sql_1 = "INSERT INTO transacao (valor, id_cartao, id_cartao_adic) VALUES (?, ?, NULL)";
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql_1, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setDouble(1, transacao.getCompra());
			pst.setInt(2, transacao.getId_cartao());
			pst.execute();
			System.out.println("Compra realizada com sucesso.");
			
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
	            return rs.getInt(1);
	        }
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	public int pesquisaEstab(String nome_estab) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT id_estab FROM estab WHERE nome_estab LIKE ?";
		int id_estab = 0;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setString(1, "%" + nome_estab + "%");
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				id_estab = rs.getInt("id_estab");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return id_estab;
	}
	
	public void inserirEstab(Transacao transacao) {
		
		ConectaBD con = new ConectaBD();
		String sql_2 = "INSERT INTO local_compra (id_transacao, id_estab) VALUES (?, ?)";
		try {
			PreparedStatement pst1 = con.getConexao().prepareStatement(sql_2);
			pst1.setInt(1, transacao.getId_transacao());
			pst1.setInt(2, transacao.getId_estab());
			pst1.execute();
			System.out.println("Compra realizada com sucesso.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}	
	
	public List<Transacao> consultarCompras(int id_cartao){
		ConectaBD con = new ConectaBD();
		String sql = "SELECT tr.id_transacao, tr.valor, tr.id_cartao, e.nome_estab FROM transacao as tr INNER JOIN local_compra as lc ON tr.id_transacao = lc.id_transacao INNER JOIN estab as e ON lc.id_estab = e.id_estab WHERE id_cartao = ?";
		List<Transacao> lista = new LinkedList<Transacao>();
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setInt(1, id_cartao);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Transacao transacao = new Transacao();
				int id = rs.getInt("id_transacao");
				double valor = rs.getDouble("valor");
				int id_card = rs.getInt("id_cartao");
				String estab = rs.getString("nome_estab");
				transacao.setId_transacao(id);
				transacao.setCompra(valor);;
				transacao.setId_cartao(id_card);
				transacao.setNome_estab(estab);
				lista.add(transacao);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	
	public Transacao pagarFatura(int id_cartao) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT c.id_cartao, SUM(tr.valor) as valor_total FROM cartao as c INNER JOIN transacao as tr ON c.id_cartao = tr.id_cartao WHERE c.id_cartao = ? GROUP BY c.id_cartao";
		Transacao total = null;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setInt(1, id_cartao);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id_cartao");
				double v_total = rs.getDouble("valor_total");
				total = new Transacao(id, v_total);
				total.setId_cartao(rs.getInt("id_cartao"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return total;
	}
	
	
	public List<Transacao> consultarEstab(){
		ConectaBD con = new ConectaBD();
		String sql = "SELECT * FROM estab";
		List<Transacao> lista = new LinkedList<Transacao>();
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				Transacao transacao = new Transacao();
				int id_estab = rs.getInt("id_estab");
				String nome_estab = rs.getString("nome_estab");
				transacao.setId_estab(id_estab);;
				transacao.setNome_estab(nome_estab);
				lista.add(transacao);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	
}
