package dados;

import dados.Atendimento;
import dados.Equipamento;

import java.util.ArrayList;

public class Equipe implements Comparable<Equipe> {
	private String codinome;
	private int quantidade;
	private double latitude;
	private double longitude;
	private ArrayList<Equipamento> equipamentos;
	private ArrayList<Atendimento> atendimentos;
	
	public Equipe(String codinome, int quantidade, double latitude, double longitude) {
		this.codinome = codinome;
		this.quantidade = quantidade;
		this.latitude = latitude;
		this.longitude = longitude;
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

	public void setEquipamentos(ArrayList<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	public void setAtendimentos(ArrayList<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public String getCodinome() {
		return codinome;
	}

	public double somatorioCustosEquipamento() {
		double somatorio = 0;
		
		for(Equipamento e : equipamentos) {
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
