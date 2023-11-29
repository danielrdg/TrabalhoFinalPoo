package app;

import java.util.ArrayList;

import dados.Atendimento;
import dados.Equipe;
import dados.Evento;

public class AppEquipe {
    private final ArrayList<Equipe> equipes;
    AppAtendimento appAtendimento=new AppAtendimento();

    public AppEquipe() {
        equipes = new ArrayList<>();
    }

    public boolean cadastrarEquipe(Equipe equipe) {
        for(Equipe e : equipes) {
            if(e.getCodinome().equals(equipe.getCodinome())) {
                return false;
            }
        }
        equipes.add(equipe);
        return true;
    }

    public ArrayList<Equipe> getEquipes() {
        return equipes;
    }
    public boolean equipeExiste(String codinome) {
        for (Equipe equipe : equipes) {
            if (equipe.getCodinome().equals(codinome)) {
                return true;
            }
        }
        return false;
    }
    public Equipe buscarEquipe(String codinome) {
        for (Equipe e : equipes) {
            if (e.getCodinome().equals(codinome)) {
                return e;
            }
        }
        return null;
    }

}
