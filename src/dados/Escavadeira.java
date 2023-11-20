package dados;

import dados.Equipamento;

public class Escavadeira extends Equipamento {
	private String combustivel;
	private double carga;
	
	public Escavadeira(int id, String nome, double custoDia, String combustivel, double carga) {
		super(id, nome, custoDia);
		this.combustivel = combustivel;
		this.carga = carga;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public double getCarga() {
		return carga;
	}

	@Override
	public String toString() {
		return "Tipo: Escavadeira" + "\n" + String.format("%sCombustível: %s\n, Carga: %.2f", super.toString(), combustivel, carga).replaceAll(",", ".") + "\n\n";
	}
}
