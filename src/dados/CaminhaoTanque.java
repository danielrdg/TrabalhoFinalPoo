package dados;

public class CaminhaoTanque extends Equipamento {
	private double capacidade;

	public CaminhaoTanque(int id, String nome, double custoDia, double capacidade) {
		super(id, nome, custoDia);
		this.capacidade = capacidade;
	}

	public double getCapacidadeTanque() {
		return capacidade;
	}

	@Override
	public String toString() {
		return String.format("%sCapacidade do Tanque: %.2f", super.toString(), capacidade).replaceAll(",", ".") + "\n\n";
	}
}
