package app;

import dados.Atendimento;
import dados.Equipe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AppAtendimento {
    private Queue<Atendimento> atendimentosPendentes;

    public AppAtendimento(){
        atendimentosPendentes = new LinkedList();
    }

    public boolean cadastrarAtendimento(Atendimento atendimento){
        for(Atendimento a : atendimentosPendentes){
            if(a.getCod() == atendimento.getCod()){
                return false;
            }
        }
        atendimentosPendentes.add(atendimento);
        return true;
    }
    public boolean existeAtendimento(int cod) {
        for (Atendimento atendimento : atendimentosPendentes) {
            if (atendimento.getCod() == cod) {
                return true;
            }
        }
        return false;
    }

    public Queue<Atendimento> getAtendimentosPendentes() {
        return atendimentosPendentes;
    }
}
