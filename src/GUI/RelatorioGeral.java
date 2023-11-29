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
    private final AppAtendimento appAtendimento;
    private final AppEvento appEvento;
    private final AppEquipe appEquipe;
    private final AppEquipamento appEquipamento;
    private final ACMERescue acmeRescue;

    public RelatorioGeral(ACMERescue acmeRescue){
        this.acmeRescue = acmeRescue;
        this.appAtendimento = acmeRescue.getAppAtendimento();
        this.appEvento = acmeRescue.getAppEvento();
        this.appEquipe = acmeRescue.getAppEquipe();
        this.appEquipamento = acmeRescue.getAppEquipamento();
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean algumDadoCadastrado = false;

                if (!appAtendimento.getAtendimentosPendentes().isEmpty()) {
                    for (Atendimento a : appAtendimento.getAtendimentosPendentes()) {
                        areaTexto.append(a.toString());
                    }
                    algumDadoCadastrado = true;
                }

                if (!appEvento.getEventos().isEmpty()) {
                    for (Evento evento : appEvento.getEventos()) {
                        areaTexto.append(evento.toString());
                    }
                    algumDadoCadastrado = true;
                }

                if (!appEquipe.getEquipes().isEmpty()) {
                    for (Equipe equipe : appEquipe.getEquipes()) {
                        areaTexto.append(equipe.toString());
                    }
                    algumDadoCadastrado = true;
                }

                if (!appEquipamento.getEquipamentos().isEmpty()) {
                    for (Equipamento equipamento : appEquipamento.getEquipamentos()) {
                        areaTexto.append(equipamento.toString());
                    }
                    algumDadoCadastrado = true;
                }

                if (!algumDadoCadastrado) {
                    JOptionPane.showMessageDialog(null, "Erro! Nenhum dado cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
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
