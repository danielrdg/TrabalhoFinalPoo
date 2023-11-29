package dados;

public class Ciclone extends Evento {
	private final double velocidade;
	private final double precipitacao;
	
	public Ciclone(String codigo, String data, double latitude, double longitude, double velocidade, double precipitacao) {
		super(codigo, data, latitude, longitude);
		this.velocidade = velocidade;
		this.precipitacao = precipitacao;
	}

	public double getPrecipitacao() {
		return precipitacao;
	}

	public double getVelocidade() {
		return velocidade;
	}

	@Override
	public String toString() {
		return super.toString() + "Tipo de evento: Ciclone\n" + String.format(" Velocidade: %.2f\nPrecipitação: %.2f", velocidade, precipitacao).replace(",", ".");
	}
}
