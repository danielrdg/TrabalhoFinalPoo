package GUI;

import app.AppEquipamento;
import app.AppEquipe;
import dados.Equipamento;
import dados.Equipe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VincularEquipamento {
    private ACMERescue acmeRescue;
    private AppEquipamento appEquipamento;
    private AppEquipe appEquipe;
    private JPanel painel;
    private JTextArea equipamentos;
    private JTextArea equipes;
    private JButton vincularButton;
    private JButton voltarButton;
    private JButton limparButton;
    private JTextField codinomeEquipe;
    private JTextField codigoEquipamento;
    private Equipe equipeSelecionada;
    private Equipamento equipamentoSelecionado;


    public VincularEquipamento(ACMERescue acmeRescue) {
        this.acmeRescue = acmeRescue;
        this.appEquipamento = acmeRescue.getAppEquipamento();
        this.appEquipe = acmeRescue.getAppEquipe();

        if (appEquipe.getEquipes().isEmpty()) {
            equipes.append("Não há equipes cadastradas no momento.\n");
        }
        else {
            equipes.append("Equipes cadastradas:\n");
            for (Equipe equipe : appEquipe.getEquipes()) {
                equipes.append(equipe.toString());
            }
        }

        if (appEquipamento.getEquipamentos().isEmpty()) {
            equipamentos.append("Não há equipamentos cadastrados no momento.\n");
        }
        else {
            equipamentos.append("Equipamentos cadastrados:\n");
            for (Equipamento equipamento : appEquipamento.getEquipamentos()) {
                equipamentos.append(equipamento.toString());
            }
        }

        vincularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    String codinome = codinomeEquipe.getText().toLowerCase().trim();
                    int codEquipamento = Integer.parseInt(codigoEquipamento.getText().trim());

                    for (Equipe equipe : appEquipe.getEquipes()) {
                        if (equipe.getCodinome().toLowerCase().equals(codinome)) {
                            equipeSelecionada = equipe;
                            equipes.append("Equipe selecionada com sucesso!\n");
                            for (Equipamento equipamento : appEquipamento.getEquipamentos()) {
                                if (equipamento.getId() == codEquipamento) {
                                    equipamentoSelecionado = equipamento;
                                    equipamentos.append("Equipamento selecionado com sucesso!\n");

                                    if (equipamento.getEquipe() != null) {
                                        equipamentos.append("Erro! O equipamento já está vinculado a outra equipe.\n");
                                    } else {
                                        equipamento.setEquipe(equipeSelecionada);
                                        equipeSelecionada.addEquipamento(equipamentoSelecionado);
                                        equipamentos.append("Equipamento vinculado à equipe com sucesso!\n");
                                    }
                                    break;
                                }
                            }

                            if (equipamentoSelecionado == null) {
                                equipamentos.append("Erro! Não há um equipamento com esse código na lista.\n");
                            }
                            break;
                        }
                    }
                    if (equipeSelecionada == null) {
                        equipes.append("Erro! Não há uma equipe com esse codinome na lista. \n");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro! Certifique-se de inserir valores válidos nos campos.");
                }
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                equipamentos.setText("");
                equipes.setText("");
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acmeRescue.setContentPane(acmeRescue.getPainel());
                acmeRescue.setSize(800,600);
            }
        });
    }

    public void atualizarExibicao() {
        equipamentos.setText("");
        equipes.setText("");

        if (appEquipe.getEquipes().isEmpty()) {
            equipes.append("Não há equipes cadastradas no momento.\n");
        } else {
            equipes.append("Equipes cadastradas:\n");
            for (Equipe equipe : appEquipe.getEquipes()) {
                equipes.append(equipe.toString());
            }
        }

        if (appEquipamento.getEquipamentos().isEmpty()) {
            equipamentos.append("Não há equipamentos cadastrados no momento.\n");
        } else {
            equipamentos.append("Equipamentos cadastrados:\n");
            for (Equipamento equipamento : appEquipamento.getEquipamentos()) {
                equipamentos.append(equipamento.toString());
            }
        }
    }


    public JPanel getPainel() {
        return painel;
    }
}
