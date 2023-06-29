package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import model.entity.Transacao;
import model.entity.TransacaoAdic;

public class TransacaoAdicDao {

	public int inserirCompraAdic(TransacaoAdic transacaoAdic) {
		// ABRIR CONEXÃO
		ConectaBD con = new ConectaBD();
		String sql_1 = "INSERT INTO transacao (valor, id_cartao, id_cartao_adic) VALUES (?, NULL, ?)";
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql_1, PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setDouble(1, transacaoAdic.getCompra_adic());
			pst.setInt(2, transacaoAdic.getId_cartao_adic());
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
	
	public int pesquisaEstabAdic(String nome_estab) {
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
	
	public void inserirEstabAdic(TransacaoAdic transacaoAdic) {
		
		ConectaBD con = new ConectaBD();
		String sql_2 = "INSERT INTO local_compra (id_transacao, id_estab) VALUES (?, ?)";
		try {
			PreparedStatement pst1 = con.getConexao().prepareStatement(sql_2);
			pst1.setInt(1, transacaoAdic.getId_transacao_adic());
			pst1.setInt(2, transacaoAdic.getId_estab_adic());
			pst1.execute();
			System.out.println("Compra realizada com sucesso.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
/*
	public TransacaoAdic consultarComprasAdic(int id_cartao_adic) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT id_transacao, valor, id_cartao_adic FROM transacao WHERE id_cartao_adic = ?";
		TransacaoAdic cardAdic = null;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setInt(1, id_cartao_adic);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id_transacao");
				double valor = rs.getDouble("valor");
				int id_card_adic = rs.getInt("id_cartao_adic");
				cardAdic = new TransacaoAdic(id, valor, id_card_adic);
				cardAdic.setId_cartao_adic(rs.getInt("id_cartao_adic"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return cardAdic;
	}
*/
	
	public List<TransacaoAdic> consultarComprasAdic(int id_cartao_adic){
		ConectaBD con = new ConectaBD();
		String sql = "SELECT tr.id_transacao, tr.valor, tr.id_cartao_adic, e.nome_estab FROM transacao as tr INNER JOIN local_compra as lc ON tr.id_transacao = lc.id_transacao INNER JOIN estab as e ON lc.id_estab = e.id_estab WHERE id_cartao_adic = ?";
		List<TransacaoAdic> lista = new LinkedList<TransacaoAdic>();
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setInt(1, id_cartao_adic);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				TransacaoAdic transacaoAdic = new TransacaoAdic();
				int id = rs.getInt("id_transacao");
				double valor = rs.getDouble("valor");
				int id_card = rs.getInt("id_cartao_adic");
				String estab = rs.getString("nome_estab");
				transacaoAdic.setId_transacao_adic(id);
				transacaoAdic.setCompra_adic(valor);;
				transacaoAdic.setId_cartao_adic(id_cartao_adic);
				transacaoAdic.setNome_estab_adic(estab);
				lista.add(transacaoAdic);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lista;
	}
	
	public TransacaoAdic pagarFaturaAdic(int id_cartao_adic) {
		ConectaBD con = new ConectaBD();
		String sql = "SELECT ca.id_cartao_adic, SUM(tr.valor) as valor_total FROM cartao_adic as ca INNER JOIN transacao as tr ON ca.id_cartao_adic = tr.id_cartao_adic WHERE ca.id_cartao_adic = ? GROUP BY ca.id_cartao_adic";
		TransacaoAdic totalAdic = null;
		try {
			PreparedStatement pst = con.getConexao().prepareStatement(sql);
			pst.setInt(1, id_cartao_adic);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id_cartao_adic");
				double v_total = rs.getDouble("valor_total");
				totalAdic = new TransacaoAdic(id, v_total);
				totalAdic.setId_cartao_adic(rs.getInt("id_cartao_adic"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return totalAdic;
	}
	
}
