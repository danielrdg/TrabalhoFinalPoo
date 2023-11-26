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
	private ArrayList<Atendimento>atendimentos;

	public Equipe(String codinome, int quantidade, double latitude, double longitude) {
		this.codinome = codinome;
		this.quantidade = quantidade;
		this.latitude = latitude;
		this.longitude = longitude;
		this.equipamentos = new ArrayList<>();
		atendimentos = new ArrayList<>();
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
	public boolean podeAtender(Atendimento atendimento) {
		double distancia = calcularDistancia(atendimento.getEvento().getLatitude(), atendimento.getEvento().getLongitude());
		final double distaciaMaxima=5000.0;
		if (distancia > distaciaMaxima) {
			return false;
		}
		if (!atendimentos.isEmpty()) {
			return false;
		}

		// Adicione outras verificações conforme necessário

		return true;
	}

	private double calcularDistancia(double latitudeEvento, double longitudeEvento) {
		return Math.sqrt(Math.pow(latitude - latitudeEvento, 2) + Math.pow(longitude - longitudeEvento, 2));
	}

	public void adicionarAtendimento(Atendimento atendimento) {
		atendimentos.add(atendimento);
	}
}
