package dados;

public class Equipamento implements Comparable<Equipamento> {
	private final int id;
	private final String nome;
	private final double custoDia;
	private Equipe equipe;
	
	
	public Equipamento(int id, String nome, double custoDia) {
		this.id = id;
		this.nome = nome;
		this.custoDia = custoDia;
		this.equipe = null;
	}

	public int getId(){
		return id;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public String getNome(){
		return nome;
	}
	
	public double getCustoDia() {
		return custoDia;
	}

	@Override
	public int compareTo(Equipamento o) {
		return Integer.compare(this.getId(), o.getId());
	}

	@Override
	public String toString() {
		return "Equipamento:\n" + "ID: " + id + "\nNome: " + nome + "\nCusto por Dia: R$" + custoDia + "\n";
	}
}
