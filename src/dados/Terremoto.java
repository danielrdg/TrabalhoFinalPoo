package dados;

public class Terremoto extends Evento {
	private final double magnitude;

	public Terremoto(String codigo, String data, double latitude, double longitude, double magnitude) {
		super(codigo, data, latitude, longitude);
		this.magnitude = magnitude;
	}

	public double getMagnitude() {
		return magnitude;
	}

	@Override
	public String toString() {
		return super.toString() + "Tipo de evento: Terremoto\n" + String.format(" Magnitude: %.2f", magnitude).replace(",", ".") + "\n\n";
	}
}
