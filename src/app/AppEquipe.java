package app;

import java.util.ArrayList;
import dados.Equipe;

public class AppEquipe {
    private ArrayList<Equipe> equipes;

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
}
