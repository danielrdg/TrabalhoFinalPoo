import java.util.ArrayList;

public class Equipe {
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
	
	public double somatorioCustosEquipamento() {
		double somatorio = 0;
		
		for(Equipamento e : equipamentos) {
			somatorio += e.getCustoDia();
		}
		
		return somatorio;
	}
	
	
}
