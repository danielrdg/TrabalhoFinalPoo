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
	
	public void addEquipamentos() {
		equipamentos = new ArrayList<Equipamento>();
	}
	
	public void addAtendimentos() {
		atendimentos = new ArrayList<Atendimento>();
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
}
