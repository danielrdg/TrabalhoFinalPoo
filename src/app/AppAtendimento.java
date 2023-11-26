package app;

import dados.Atendimento;
import dados.Equipe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class AppAtendimento {
    private ArrayList<Atendimento> atendimentosPendentes;
    private ArrayList<Equipe>equipesDisponiveis;

    public AppAtendimento(){
        atendimentosPendentes = new ArrayList<>();
        equipesDisponiveis = new ArrayList<>();
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

    public ArrayList<Atendimento> getAtendimentosPendentes() {
        return atendimentosPendentes;
    }
    public void alocarAtendimentos() {
        for (Atendimento atendimento : atendimentosPendentes) {
            boolean equipeAlocada = false;

            for (Equipe equipe : equipesDisponiveis) {
                if (equipe.podeAtender(atendimento)) {
                    atendimento.setEquipe(equipe);
                    equipe.adicionarAtendimento(atendimento);
                    atendimento.setStatus("ALOCADO");
                    equipeAlocada = true;
                    break;
                }
            }
            if (!equipeAlocada) {
                atendimento.setStatus("CANCELADO");
                atendimento.setEquipe(null);
            }
        }
}
}
