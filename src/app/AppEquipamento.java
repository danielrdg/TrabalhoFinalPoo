package app;

import dados.Equipamento;

import java.util.ArrayList;

public class AppEquipamento {

    private ArrayList<Equipamento> equipamentos;

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
