package GUI;

import app.AppAtendimento;
import app.AppEquipe;
import dados.Atendimento;
import dados.Equipe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlocarAtendimentos {
    private JPanel painel;
    private JButton alocarAtendimentosButton;
    private JTextArea textArea1;
    private JButton finalizarButton;
    AppAtendimento appAtendimento = new AppAtendimento();
    AppEquipe appEquipe = new AppEquipe();
    ACMERescue acmeRescue;

    public AlocarAtendimentos(ACMERescue acmeRescue){
        super();
        this.acmeRescue = acmeRescue;

    }

    public JPanel getPainel() {
        return painel;
    }

    public void alocarAtendimentos() {
        AppAtendimento appAtendimento = acmeRescue.getAppAtendimento();
        AppEquipe appEquipe = acmeRescue.getAppEquipe();

        if (appAtendimento.getAtendimentosPendentes().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não há atendimentos pendentes para alocar.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Atendimento atendimento : appAtendimento.getAtendimentosPendentes()) {
            if (atendimento.getStatus().equals("PENDENTE")) {
                boolean equipeAlocada = false;

                for (Equipe equipe : appEquipe.getEquipes()) {
                    if (equipeEstaDisponivel(equipe) && equipe.podeAtender(atendimento)) {
                        atendimento.setEquipe(equipe);
                        equipe.adicionarAtendimento(atendimento);
                        atendimento.setStatus("EXECUTANDO");
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
        JOptionPane.showMessageDialog(null, "Atendimentos alocados com sucesso!");
    }

    private boolean equipeEstaDisponivel(Equipe equipe) {
        AppAtendimento appAtendimento = acmeRescue.getAppAtendimento();

        for (Atendimento atendimento : appAtendimento.getAtendimentosPendentes()) {
            if (atendimento.getEquipe() != null && atendimento.getEquipe().equals(equipe)) {
                return false;
            }
        }

        return true;
    }

    private void atualizarTextArea() {
        AppAtendimento appAtendimento = acmeRescue.getAppAtendimento();
        StringBuilder resultado = new StringBuilder();

        for (Atendimento atendimento : appAtendimento.getAtendimentosPendentes()) {
            resultado.append(atendimento.toString());
        }

        textArea1.setText(resultado.toString());
    }
}
