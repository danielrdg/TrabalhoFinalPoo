package GUI;

import app.AppAtendimento;
import app.AppEquipamento;
import app.AppEquipe;
import app.AppEvento;
import dados.Atendimento;
import dados.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RelatorioGeral {
    private JTextArea areaTexto;
    private JPanel painel;
    private JButton limparButton, voltarButton, mostrarButton;
    private AppAtendimento appAtendimento;
    private AppEvento appEvento;
    private AppEquipe appEquipe;
    private AppEquipamento appEquipamento;
    private ACMERescue acmeRescue;

    public RelatorioGeral(ACMERescue acmeRescue){
        this.acmeRescue = acmeRescue;
        this.appAtendimento = acmeRescue.getAppAtendimento();
        this.appEvento = acmeRescue.getAppEvento();
        this.appEquipe = acmeRescue.getAppEquipe();
        this.appEquipamento = acmeRescue.getAppEquipamento();
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (appAtendimento.getAtendimentosPendentes().isEmpty() && appEvento.getEventos().isEmpty() && appEquipe.getEquipes().isEmpty()) {
                    areaTexto.append("Erro! Nenhum dado cadastrado.\n");
                }
                else {
                    for (Atendimento a : appAtendimento.getAtendimentosPendentes()) {
                        areaTexto.append(a.toString() + "\n");
                    }
                    for (Evento evento : appEvento.getEventos()) {
                        areaTexto.append(evento.toString() + "\n");
                    }
                    for (Equipe equipe : appEquipe.getEquipes()) {
                        areaTexto.append(equipe.toString() + "\n");
                    }
                    for (Equipamento equipamento : appEquipamento.getEquipamentos()) {
                        areaTexto.append(equipamento.toString() + "\n");
                    }
                }
            }
        });
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaTexto.setText("");
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acmeRescue.setContentPane(acmeRescue.getPainel());
                acmeRescue.setSize(800, 600);
            }
        });
    }

    public JPanel getPainel() {
        return painel;
    }
}
