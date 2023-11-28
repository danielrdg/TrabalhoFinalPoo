package dados;

import java.util.ArrayList;

public class Evento implements Comparable<Evento> {
    private String codigo;
    private String data;
    private double latitude;
    private double longitude;
    private Atendimento atendimento;

    public Evento(String codigo, String data, double latitude, double longitude){
        this.codigo = codigo;
        this.data = data;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getData() {
        return data;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    @Override
    public int compareTo(Evento o) {
        return this.getCodigo().compareTo(o.getCodigo());
    }

    @Override
    public String toString() {
        return "\nEvento:\n" + "Código: " + codigo + "\nData: " + data + "\nLatitude: " + latitude + "\nLongitude: " + longitude + "\n";
    }
}
