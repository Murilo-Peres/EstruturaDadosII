package model.entity;

public class CartaoAdic extends Adicional{
	protected int id_cartao_adic;
	private int id_adic;
	private String numCartao_adic, dataVal_adic;
	protected double limiteAdic;

	public CartaoAdic() {
		
	}

	public CartaoAdic(int id, String nome_adic, String numero, double limite) {
		super(nome_adic);
		this.id_adic = id;
		this.numCartao_adic = numero;
		this.limiteAdic = limite;
	}

	public CartaoAdic(String nr_cartao_adic, String data_adic, float limite_adic, int id_adic) {
		this.numCartao_adic = nr_cartao_adic;
		this.dataVal_adic = data_adic;
		this.limiteAdic = limite_adic;
		this.id_adic = id_adic;
	}

	public CartaoAdic(int id_cartao_adic) {
		this.id_cartao_adic = id_cartao_adic;
	}

	public int getId_cartao_adic() {
		return id_cartao_adic;
	}

	public void setId_cartao_adic(int id_cartao_adic) {
		this.id_cartao_adic = id_cartao_adic;
	}

	public int getId_adic() {
		return id_adic;
	}

	public void setId_adic(int id_adic) {
		this.id_adic = id_adic;
	}

	public String getNumCartao_adic() {
		return numCartao_adic;
	}

	public void setNumCartao_adic(String numCartao_adic) {
		this.numCartao_adic = numCartao_adic;
	}

	public String getDataVal_adic() {
		return dataVal_adic;
	}

	public void setDataVal_adic(String dataVal_adic) {
		this.dataVal_adic = dataVal_adic;
	}

	public double getLimiteAdic() {
		return limiteAdic;
	}

	public void setLimiteAdic(double limiteAdic) {
		this.limiteAdic = limiteAdic;
	}
	
	

}
