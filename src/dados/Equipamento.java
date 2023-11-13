package dados;

public class Equipamento implements Comparable<Equipamento> {
	private int id;
	private String nome;
	private double custoDia;
	private Equipe equipe;
	
	
	public Equipamento(int id, String nome, double custoDia) {
		this.id = id;
		this.nome = nome;
		this.custoDia = custoDia;
	}

	public int getId(){
		return id;
	}

	public String getNome(){
		return nome;
	}
	
	public double getCustoDia() {
		return custoDia;
	}

	@Override
    public String toString() {
        return "ID: " + id + ", Equipamento: " + nome + ", Custo por Dia: R$" + custoDia;
    }

	@Override
	public int compareTo(Equipamento o) {
		return Integer.compare(this.getId(), o.getId());
	}
}
