package dados;

import app.AppEquipamento;
import app.AppEquipe;
import dados.Atendimento;
import dados.Equipamento;
import java.util.ArrayList;

public class Equipe implements Comparable<Equipe> {
	private String codinome;
	private int quantidade;
	private double latitude;
	private double longitude;
	private ArrayList<Equipamento> equipamentos;

	public Equipe(String codinome, int quantidade, double latitude, double longitude) {
		this.codinome = codinome;
		this.quantidade = quantidade;
		this.latitude = latitude;
		this.longitude = longitude;
		this.equipamentos = new ArrayList<>();
	}

	public void addEquipamento (Equipamento e) {
		equipamentos.add(e);
	}

	public int getQuantidade() {
		return quantidade;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}


	public String getCodinome() {
		return codinome;
	}

	public double somatorioCustosEquipamento() {
		double somatorio = 0;
		for (Equipamento e : equipamentos) {
			somatorio += e.getCustoDia();
		}
		return somatorio;
	}

	@Override
	public int compareTo(Equipe o) {
		return this.getCodinome().compareTo(o.getCodinome());
	}

	@Override
	public String toString() {
		return "Equipe:\n" + "Codinome: " + getCodinome() + "\nQuantidade: " + getQuantidade() + "\nLatitude: " + getLatitude() + "\nLongitude: " + getLongitude() + "\n\n";
	}
}
