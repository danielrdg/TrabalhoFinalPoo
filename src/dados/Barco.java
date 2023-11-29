package dados;

public class Barco extends Equipamento {
	private final int capacidade;

	public Barco(int id, String nome, double custoDia, int capacidade) {
		super(id, nome, custoDia);
		this.capacidade = capacidade;
	}

	public int getCapacidade() {
		return capacidade;
	}

	@Override
	public String toString() {
		return super.toString() + "Tipo: Barco\n" +
				"Capacidade: " + capacidade + "\n\n";
	}
}
