package app;

import java.util.ArrayList;
import dados.Evento;

public class AppEvento {
    private ArrayList<Evento> eventos;

    public AppEvento() {
        eventos = new ArrayList<>();
    }

    public boolean cadastrarEvento(Evento evento) {
        for (Evento e : eventos) {
            if (e.getCodigo().equals(evento.getCodigo())) {
                return false;
            }
        }
        eventos.add(evento);
        return true;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }


}
