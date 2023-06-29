package ativPooCapII;

import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import model.dao.AdicionalDao;
import model.dao.CartaoAdicDao;
import model.dao.CartaoDao;
import model.dao.ClienteDao;
import model.dao.TransacaoAdicDao;
import model.dao.TransacaoDao;
import model.entity.Adicional;
import model.entity.Cartao;
import model.entity.CartaoAdic;
import model.entity.Cliente;
import model.entity.Transacao;
import model.entity.TransacaoAdic;

public class App {

	public static String leString(String msg) {
		String valor = JOptionPane.showInputDialog(null, msg);
		return valor;
	}

	public static int menuMain() {
		System.out.println("MENU PRINCIPAL");
		System.out.println("1 - Menu cliente");
		System.out.println("2 - Menu cartão");
		System.out.println("3 - Listar estabelecimentos");
		System.out.println("4 - Sair");
		System.out.println(" ");
		System.out.println("Digite uma opção: ");
		Scanner input1 = new Scanner(System.in);
		return input1.nextInt();
	}
	
	public static int menu() {
		System.out.println("MENU CLIENTE");
		System.out.println("1 - Inserir cliente");
		System.out.println("2 - Listar todos");
		System.out.println("3 - Listar por ID");
		System.out.println("4 - Inserir cliente adicional");
		System.out.println("5 - Listar todos adicionais");
		System.out.println("6 - Listar adicional por ID");
		System.out.println("7 - Sair");
		System.out.println(" ");
		System.out.print("Digite uma opção: ");
		Scanner input = new Scanner(System.in);
		return input.nextInt();
	}
	
	public static int menuCard() {
		System.out.println("MENU PRODUTO");
		System.out.println("1 - Inserir cartão");
		System.out.println("2 - Consultar cartões");
		System.out.println("3 - Inserir compra");
		System.out.println("4 - Consultar compras");
		System.out.println("5 - Consultar limite");
		System.out.println("6 - Pagar fatura");
		System.out.println("7 - Inserir cartão adicional");
		System.out.println("8 - Consultar cartões adicionais");
		System.out.println("9 - Inserir compra cartão adicional");
		System.out.println("10 - Consultar compras cartão adicional");
		System.out.println("11 - Consultar limite cartão adicional");
		System.out.println("12 - Pagar fatura");
		System.out.println("13 - Sair");
		System.out.println(" ");
		System.out.print("Digite uma opção: ");
		Scanner input2 = new Scanner(System.in);
		return input2.nextInt();
	}

	
	//----------------------------------------------------------------------------------//
	//                           MÉTODOS CLASSE CLIENTE
	//----------------------------------------------------------------------------------//
	
	public static void metodoInserir() {
		String nome = leString("Digite o nome: ");
		String doc = leString("Digite o cpf: ");
		Cliente cliente = new Cliente(nome, doc);
		ClienteDao clienteDao = new ClienteDao();
		clienteDao.inserir(cliente);
	}
	
	public static void metodoConsultarTodos() {	
		List<Cliente> registros = new ClienteDao().consultarTodos();
		if (!registros.isEmpty()) {
			String saida = "";
			saida += "id\tnome\tcpf\n";
			for (int  i = 0; i < registros.size(); i++) {
				Cliente c = registros.get(i);
				saida += c.getId() + "\t";
				saida += c.getNome() + "\t";
				saida += c.getDoc() + "\n";
			}
			JOptionPane.showMessageDialog(null, new JTextArea(saida));
		} else {
			System.out.println("Não possui registros");
		}
	}
	
	public static Cliente metodoConsultarId() {
		String idStr = leString("Digite o ID");
		// converter de String para int
		int id = Integer.parseInt(idStr);
		ClienteDao dao = new ClienteDao();
		Cliente c = dao.consultar(id);
		return c;
	}
	
	
	//----------------------------------------------------------------------------------//
	//                           MÉTODOS CLASSE ADICIONAL
	//----------------------------------------------------------------------------------//
	
	public static void metodoInserirAdic() {
		String nome = leString("Digite o nome: ");
		String respStr = leString("Digite o id do cliente responsável: ");
		int resp = Integer.parseInt(respStr);
		Adicional adicional = new Adicional(nome, resp);
		AdicionalDao adicionalDao = new AdicionalDao();
		adicionalDao.inserir(adicional);
	}
	
	public static void metodoConsultarTodosAdic() {	
		List<Adicional> registros = new AdicionalDao().consultarTodosAdic();
		if (!registros.isEmpty()) {
			String saida = "";
			saida += "id\tnome\tid_resp\n";
			for (int  i = 0; i < registros.size(); i++) {
				Adicional a = registros.get(i);
				saida += a.getId_adic() + "\t";
				saida += a.getNome_adic() + "\t";
				saida += a.getId_cliente() + "\n";
			}
			JOptionPane.showMessageDialog(null, new JTextArea(saida));
		} else {
			System.out.println("Não possui registros");
		}
	}
	
	public static Adicional metodoConsultarAdic() {
		String idStr = leString("Digite o ID");
		// converter de String para int
		int id = Integer.parseInt(idStr);
		AdicionalDao dao = new AdicionalDao();
		Adicional a = dao.consultarAdic(id);
		return a;
	}
	
	
	//----------------------------------------------------------------------------------//
	//                           MÉTODOS CLASSE CARTAO
	//----------------------------------------------------------------------------------//
	
	public static void inserirCartao() {
		
		String nome_cliente = leString("Digite o primeiro nome do cliente: ");
		String nr_cartao = leString("Digite o número do cartão: ");
		String data = leString("Digite a data de validade do cartão: ");
		String limite = leString("Digite o limite do cartão: ");
		int limite1 = Integer.parseInt(limite);
		int id_cliente = 0;
		CartaoDao cartaoDao = new CartaoDao();
		id_cliente = cartaoDao.pesquisaCliente(nome_cliente);
		//System.out.println(id_cliente);
		Cartao cartao = new Cartao(nr_cartao, data, limite1, id_cliente);
		cartaoDao.inserirCartao(cartao);
	}
	
	public static void metodoConsultarCards() {	
		List<Cartao> registros = new CartaoDao().consultarTodosCard();
		if (!registros.isEmpty()) {
			String saida = "";
			saida += "id\tnumero\tdata val\tlimite\tid cliente\n";
			for (int  i = 0; i < registros.size(); i++) {
				Cartao cr = registros.get(i);
				saida += cr.getId() + "\t";
				saida += cr.getNumCartao() + "\t";
				saida += cr.getDataVal() + "\t";
				saida += cr.getLimite() + "\t";
				saida += cr.getIdCliente() + "\n";
			}
			JOptionPane.showMessageDialog(null, new JTextArea(saida));
		} else {
			System.out.println("Não possui registros");
		}
	}
	
	public static Cartao consultarLimite() {
		String idStr = leString("Digite o ID do cliente");
		// converter de String para int
		int id = Integer.parseInt(idStr);
		CartaoDao dao = new CartaoDao();
		Cartao c = dao.consultaLimite(id);
		return c;
	}
	
	//----------------------------------------------------------------------------------//
	//                           MÉTODOS CLASSE ADICIONAL
	//----------------------------------------------------------------------------------//
	
	public static void inserirCartaoAdic() {
		
		String nome_adic = leString("Digite o primeiro nome do cliente adicional: ");
		String nr_cartao_adic = leString("Digite o número do cartão adicional: ");
		String data_adic = leString("Digite a data de validade do cartão adicional: ");
		String limite = leString("Digite o limite do cartão adicional: ");
		float limite_adic = Float.parseFloat(limite);
		int id_adic = 0;
		CartaoAdicDao cartaoAdicDao = new CartaoAdicDao();
		id_adic = cartaoAdicDao.pesquisaAdic(nome_adic);
		//System.out.println(id_cliente);
		CartaoAdic cartaoAdic = new CartaoAdic(nr_cartao_adic, data_adic, limite_adic, id_adic);
		cartaoAdicDao.inserirCartaoAdic(cartaoAdic);
	}
	
	public static void metodoConsultarCardsAdic() {	
		List<CartaoAdic> registros = new CartaoAdicDao().consultarTodosCardAdic();
		if (!registros.isEmpty()) {
			String saida = "";
			saida += "id\tnumero\tdata val\tlimite\tid adic\n";
			for (int  i = 0; i < registros.size(); i++) {
				CartaoAdic cra = registros.get(i);
				saida += cra.getId_cartao_adic() + "\t";
				saida += cra.getNumCartao_adic() + "\t";
				saida += cra.getDataVal_adic() + "\t";
				saida += cra.getLimiteAdic() + "\t";
				saida += cra.getId_adic() + "\n";
			}
			JOptionPane.showMessageDialog(null, new JTextArea(saida));
		} else {
			System.out.println("Não possui registros");
		}
	}
	
	public static CartaoAdic consultarLimiteAdic() {
		String idStr = leString("Digite o ID do cliente adicional");
		// converter de String para int
		int id = Integer.parseInt(idStr);
		CartaoAdicDao dao = new CartaoAdicDao();
		CartaoAdic ca = dao.consultaLimiteAdic(id);
		return ca;
	}
	
	//----------------------------------------------------------------------------------//
	//                           MÉTODOS CLASSE TRANSACAO
	//----------------------------------------------------------------------------------//
	
	public static void inserirCompra() {
		String nome_estab = leString("Digite o parte do nome do estabelecimento: ");
		String valorStr = leString("Digite o valor da compra: ");
		String idStr = leString("Digite o ID do cartão: ");
		double valor = Double.parseDouble(valorStr);
		int id = Integer.parseInt(idStr);
		TransacaoDao transacaoDao = new TransacaoDao();
		int id_estab = transacaoDao.pesquisaEstab(nome_estab);
		
		Transacao transacao = new Transacao(valor, id);
		int id_transacao = transacaoDao.inserirCompra(transacao);
		
		Transacao transacao1 = new Transacao(id_transacao, id_estab);
		transacaoDao.inserirEstab(transacao1);
	}
	
	public static void consultarCompras() {	
		String idStr = leString("Digite o ID do cartão");
		int id = Integer.parseInt(idStr);
		List<Transacao> registros = new TransacaoDao().consultarCompras(id);
		if (!registros.isEmpty()) {
			String saida = "";
			saida += "id\tvalor\tid_cartao\testabelecimento\n";
			for (int  i = 0; i < registros.size(); i++) {
				Transacao tr = registros.get(i);
				saida += tr.getId_transacao() + "\t";
				saida += tr.getCompra() + "\t";
				saida += tr.getId_cartao() + "\t";
				saida += tr.getNome_estab() + "\n";
			}
			JOptionPane.showMessageDialog(null, new JTextArea(saida));
		} else {
			System.out.println("Não possui registros");
		}
	}
	
	public static Transacao pagarFatura() {
		String idStr = leString("Digite o ID do cartão");
		// converter de String para int
		int id = Integer.parseInt(idStr);
		TransacaoDao dao = new TransacaoDao();
		Transacao tr = dao.pagarFatura(id);
		return tr;
	}
	
	public static void metodoConsultaEstab() {	
		List<Transacao> registros = new TransacaoDao().consultarEstab();
		if (!registros.isEmpty()) {
			String saida = "";
			saida += "id\tnome_estab\n";
			for (int  i = 0; i < registros.size(); i++) {
				Transacao trEstab = registros.get(i);
				saida += trEstab.getId_estab() + "\t";
				saida += trEstab.getNome_estab() + "\n";
			}
			JOptionPane.showMessageDialog(null, new JTextArea(saida));
		} else {
			System.out.println("Não possui registros");
		}
	}
	
	
	//----------------------------------------------------------------------------------//
	//                           MÉTODOS CLASSE TRANSACAO ADICIONAL
	//----------------------------------------------------------------------------------//
	
	public static void inserirCompraAdic() {
		String nome_estab = leString("Digite o parte do nome do estabelecimento: ");
		String valorStr = leString("Digite o valor da compra: ");
		String idStr = leString("Digite o ID do cartão adicional: ");
		double valor = Double.parseDouble(valorStr);
		int id = Integer.parseInt(idStr);
		TransacaoAdicDao transacaoAdicDao = new TransacaoAdicDao();
		int id_estab = transacaoAdicDao.pesquisaEstabAdic(nome_estab);
		
		TransacaoAdic transacaoAdic = new TransacaoAdic(valor, id);
		int id_transacao_adic = transacaoAdicDao.inserirCompraAdic(transacaoAdic);
		
		TransacaoAdic transacaoAdic1 = new TransacaoAdic(id_transacao_adic, id_estab);
		transacaoAdicDao.inserirEstabAdic(transacaoAdic1);
	}
	
	public static void consultarComprasAdic() {	
		String idStr = leString("Digite o ID do cartão adicional");
		int id = Integer.parseInt(idStr);
		List<TransacaoAdic> registros = new TransacaoAdicDao().consultarComprasAdic(id);
		if (!registros.isEmpty()) {
			String saida = "";
			saida += "id\tvalor\tid_cartao\testabelecimento\n";
			for (int  i = 0; i < registros.size(); i++) {
				TransacaoAdic tr = registros.get(i);
				saida += tr.getId_transacao_adic() + "\t";
				saida += tr.getCompra_adic() + "\t";
				saida += tr.getId_cartao_adic() + "\t";
				saida += tr.getNome_estab_adic() + "\n";
			}
			JOptionPane.showMessageDialog(null, new JTextArea(saida));
		} else {
			System.out.println("Não possui registros");
		}
	}
	
	public static TransacaoAdic pagarFaturaAdic() {
		String idStr = leString("Digite o ID do cartão adicional");
		// converter de String para int
		int id = Integer.parseInt(idStr);
		TransacaoAdicDao dao = new TransacaoAdicDao();
		TransacaoAdic tr = dao.pagarFaturaAdic(id);
		return tr;
	}
	

	
	public static void main(String[] args) {
		int opcao;

		do {
			opcao = menuMain();
			switch (opcao) {
			case 1:

				int op;

				do {
					op = menu();
					switch (op) {
					case 1:
						metodoInserir();
						break;
					case 2:
						metodoConsultarTodos();
						break;
					case 3:
						Cliente cli = metodoConsultarId();
						String saida;
						if (cli != null) {
							saida = "id\tnome\tcpf\n";
							saida += cli.getId() + "\t";
							saida += cli.getNome() + "\t";
							saida += cli.getDoc() + "\n";
						} else {
							saida = "Registro não foi localizado!";
						}
						JOptionPane.showMessageDialog(null, new JTextArea(saida));
						break;
					case 4:
						metodoInserirAdic();
						break;
					case 5:
						metodoConsultarTodosAdic();
						break;
					case 6:
						Adicional adic = metodoConsultarAdic();
						String saidaAdic;
						if (adic != null) {
							saidaAdic = "id\tnome\tid_resp\n";
							saidaAdic += adic.getId_adic() + "\t";
							saidaAdic += adic.getNome_adic() + "\t";
							saidaAdic += adic.getId_cliente() + "\n";
						} else {
							saidaAdic = "Registro não foi localizado!";
						}
						JOptionPane.showMessageDialog(null, new JTextArea(saidaAdic));
						break;
					case 7:
						System.out.println("Voltando ao menu principal.");
						break;
					default:
						System.out.println("Opção inválida.");
						break;
					}
				} while (op != 7);

				break;
			case 2:

				int opCartao;

				do {
					opCartao = menuCard();
					switch (opCartao) {
					case 1:
						inserirCartao();
						break;
					case 2:
						metodoConsultarCards();
						break;
					case 3:
						inserirCompra();
						break;
					case 4: 
						consultarCompras();
						break;
					case 5:
						Cartao card1 = consultarLimite();
						String saida1;
						if (card1 != null) {
							saida1 = "id\tnome\tnumero cartao\tlimite\n";
							saida1 += card1.getIdCliente()+"\t";
							saida1 += card1.getNome()+"\t";
							saida1 += card1.getNumCartao()+"\t";
							saida1 += card1.getLimite()+"\n";
						} else {
							saida1 = "Registro não foi localizado!";
						}
						JOptionPane.showMessageDialog(null, new JTextArea(saida1));
						break;
					case 6:
						Transacao total = pagarFatura();
						String saida2;
						if (total != null) {
							saida2 = "id_cartao\tvalor_total\n";
							saida2 += total.getId_cartao()+"\t";
							saida2 += total.getTotal()+"\n";
						} else {
							saida2 = "Registro não foi localizado!";
						}
						JOptionPane.showMessageDialog(null, new JTextArea(saida2));
						break;
					case 7:
						inserirCartaoAdic();
						break;
					case 8:
						metodoConsultarCardsAdic();
						break;
					case 9:
						inserirCompraAdic();
						break;
					case 10:
						consultarComprasAdic();
						break;
					case 11:
						CartaoAdic cardAdic = consultarLimiteAdic();
						String saidaCardAdic;
						if (cardAdic != null) {
							saidaCardAdic = "id\tnome\tnumero cartao\tlimite\n";
							saidaCardAdic += cardAdic.getId_adic()+"\t";
							saidaCardAdic += cardAdic.getNome_adic()+"\t";
							saidaCardAdic += cardAdic.getNumCartao_adic()+"\t";
							saidaCardAdic += cardAdic.getLimiteAdic()+"\n";
						} else {
							saidaCardAdic = "Registro não foi localizado!";
						}
						JOptionPane.showMessageDialog(null, new JTextArea(saidaCardAdic));
						break;
					case 12:
						TransacaoAdic totalAdic = pagarFaturaAdic();
						String saidaFatura;
						if (totalAdic != null) {
							saidaFatura = "id_cartao\tvalor_total\n";
							saidaFatura += totalAdic.getId_cartao_adic()+"\t";
							saidaFatura += totalAdic.getTotal_adic()+"\n";
						} else {
							saidaFatura = "Registro não foi localizado!";
						}
						JOptionPane.showMessageDialog(null, new JTextArea(saidaFatura));
						break;
					case 13:
						System.out.println("Voltando ao menu principal.");
					default:
						System.out.println("Opção inválida.");
						break;
					}
				} while (opCartao != 13);

				break;
			case 3:
				metodoConsultaEstab();
				break;
			case 4:
				System.out.println("Finalizando programa.");
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}
		} while (opcao != 4);

	}

}
