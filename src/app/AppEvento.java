package app;

import java.util.ArrayList;
import java.util.Queue;

import GUI.ACMERescue;
import dados.Atendimento;
import dados.Evento;

public class AppEvento {
    private final ArrayList<Evento> eventos;


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
    public Evento buscarEvento(String codigo) {
        for (Evento e : eventos) {
            if (e.getCodigo().equals(codigo)) {
                return e;
            }
        }
        return null;
    }
    public boolean existeCodigo(String codigo) {
        for (Evento evento : eventos) {
            if (evento.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }


    public ArrayList<Evento> getEventos() {
        return eventos;
    }


}
