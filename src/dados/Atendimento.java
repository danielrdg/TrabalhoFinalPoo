package dados;

public class Atendimento {
	private int cod;
	private String dataInicio;
	private int duracao;
	private String status;
	private Equipe equipe;
	private Evento evento;
	
	public Atendimento(int cod, String dataInicio, int duracao, String status, Evento evento) {
		this.cod = cod;
		this.dataInicio = dataInicio;
		this.duracao = duracao;
		this.status = "PENDENTE";
		this.evento = evento;
	}
	
	public double calculaCusto() {
		double custoEquipe = duracao * 250 * equipe.getQuantidade();
		double custoEquipamentos = duracao * equipe.somatorioCustosEquipamento();
		double custoDeslocamento = calculoDistancia() * (100 * equipe.getQuantidade()) + 1.10 * equipe.somatorioCustosEquipamento();
		return custoEquipe + custoEquipamentos + custoDeslocamento;
	}
	
	public double calculoDistancia() {
		
		//Formula de Haversine
		
		final double RAIO_TERRA = 6371;
		
		double latEquipe = Math.toRadians(equipe.getLatitude());
		double longEquipe = Math.toRadians(equipe.getLongitude());
		double latEvento = Math.toRadians(evento.getLatitude());
		double longEvento = Math.toRadians(evento.getLongitude());
		
		double distanciaLatitude = latEquipe - latEvento;
		double distanciaLongitude = longEquipe - longEvento;
		
		double a = Math.sin(distanciaLatitude / 2) * Math.sin(distanciaLatitude / 2) + 
				   Math.cos(latEquipe) * Math.cos(latEvento) * 
				   Math.sin(distanciaLongitude / 2) * Math.sin(distanciaLongitude / 2);
		
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		
		return RAIO_TERRA * c;
		
	}

	public int getCod() {
		return cod;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public String getStatus() {
		return status;
	}

	public int getDuracao() {
		return duracao;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
