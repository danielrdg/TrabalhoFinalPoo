package app;

import dados.Atendimento;
import java.util.LinkedList;
import java.util.Queue;

public class AppAtendimento {
    private Queue<Atendimento> atendimentosPendentes;

    public AppAtendimento(){
        atendimentosPendentes = new LinkedList<>();
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

    public Queue<Atendimento> getAtendimentosPendentes() {
        return atendimentosPendentes;
    }
}
