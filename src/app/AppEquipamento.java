package app;

import dados.Equipamento;
import dados.Equipe;
import dados.Evento;

import java.util.ArrayList;

public class AppEquipamento {

    private final ArrayList<Equipamento> equipamentos;
    private Equipe equipe;

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public AppEquipamento() {
        equipamentos = new ArrayList<>();
    }

    public boolean cadastrarEquipamento(Equipamento equipamento) {
        for (Equipamento e : equipamentos) {
            if (e.getId() == equipamento.getId()) {
                return false;
            }
        }
        equipamentos.add(equipamento);
        return true;
    }
    public boolean existeEquipamento(int id) {
        for (Equipamento equipamento : equipamentos) {
            if (equipamento.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Equipamento> getEquipamentos() {
        return equipamentos;
    }

}
