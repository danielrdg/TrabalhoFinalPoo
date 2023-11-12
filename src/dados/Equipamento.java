package dados;

public class Equipamento {
	private int id;
	private String nome;
	private double custoDia;
	private Equipe equipe;
	
	
	public Equipamento(int id, String nome, double custoDia) {
		this.id = id;
		this.nome = nome;
		this.custoDia = custoDia;
	}
	
	
	public double getCustoDia() {
		return custoDia;
	}

	@Override
    public String toString() {
        return "ID: " + id + ", Equipamento: " + nome + ", Custo por Dia: R$" + custoDia;
    }
	
}
