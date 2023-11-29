package dados;

public class Seca extends Evento {
	private final int estiagem;
	
	public Seca(String codigo, String data, double latitude, double longitude, int estiagem) {
		super(codigo, data, latitude, longitude);
		this.estiagem = estiagem;
	}
	public int getEstiagem(){
		return estiagem;
	}

	@Override
	public String toString() {
		return super.toString() + "Tipo de evento: Seca\n" + "Estiagem: " + estiagem + "\n\n";
	}
}
