package GUI;

import app.AppAtendimento;
import dados.Atendimento;
import dados.Equipamento;
import dados.Equipe;
import dados.Evento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Queue;

public class ConsultarAtendimento implements ActionListener {
    private JTextArea areaTexto;
    private JPanel painel;
    private JButton consultarAtendimentosButton;
    private JButton limparButton;
    private JButton voltarButton;
    private ACMERescue acmeRescue;
    private AppAtendimento appAtendimento;

    public ConsultarAtendimento(ACMERescue acmeRescue) {
        super();
        this.acmeRescue = acmeRescue;
        this.appAtendimento = acmeRescue.getAppAtendimento();
        consultarAtendimentosButton.addActionListener(this);
        limparButton.addActionListener(this);
        voltarButton.addActionListener(this);
    }

    public JPanel getPainel() {
        return painel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == consultarAtendimentosButton) {
            consultarAtendimentos();
        }
        else if (e.getSource() == limparButton) {
            areaTexto.setText("");
        }
        else if (e.getSource() == voltarButton) {
            acmeRescue.setContentPane(acmeRescue.getPainel());
            acmeRescue.setSize(800, 600);
        }
    }

    private void consultarAtendimentos() {
        if (appAtendimento.getAtendimentosPendentes().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há atendimentos cadastrados.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (Atendimento atendimento : appAtendimento.getAtendimentosPendentes()) {
            areaTexto.append(atendimento.toString());

            Evento evento = atendimento.getEvento();
            if (evento != null) {
                areaTexto.append("\n" + evento);
            }

            Equipe equipe = atendimento.getEquipe();
            if (equipe != null) {
                areaTexto.append("\n" + equipe);

                ArrayList<Equipamento> equipamentos = equipe.getEquipamentos();
                if (!equipamentos.isEmpty()) {
                    for (Equipamento equipamento : equipamentos) {
                        areaTexto.append("\n" + equipamento);
                    }
                }
            }
            double custoAtendimento = atendimento.calculaCusto();
            String custoFormatado = String.format("Custo do Atendimento: R$ %.2f", custoAtendimento);
            areaTexto.append("\n" + custoFormatado);
            areaTexto.append("\n--------------------------\n");
        }
        JOptionPane.showMessageDialog(null, "Atendimentos consultados com sucesso.");
    }

}
