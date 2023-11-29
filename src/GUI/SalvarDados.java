package GUI;

import app.AppAtendimento;
import app.AppEquipamento;
import app.AppEquipe;
import app.AppEvento;
import dados.Atendimento;
import dados.Equipamento;
import dados.Equipe;
import dados.Evento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SalvarDados implements ActionListener {
    private AppEvento appEvento;
    private AppEquipe appEquipe;
    private AppEquipamento appEquipamento;
    private AppAtendimento appAtendimento;
    private ACMERescue acmeRescue;
    private JTextField textField1;
    private JButton voltarButton;
    private JButton confirmarButton;
    private JPanel painel;
    private String nomeArquivo;

    public JPanel getPainel() {
        return painel;
    }

    public SalvarDados(ACMERescue acmeRescue){
        this.acmeRescue = acmeRescue;
        this.appEvento = acmeRescue.getAppEvento();
        this.appEquipe = acmeRescue.getAppEquipe();
        this.appEquipamento = acmeRescue.getAppEquipamento();
        this.appAtendimento = acmeRescue.getAppAtendimento();
        confirmarButton.addActionListener(this);
        voltarButton.addActionListener(this);
    }


    public void salvarTodosOsDados(String nomeArquivo) {
        try (BufferedWriter escritorEvento = new BufferedWriter(new FileWriter(nomeArquivo + "-EVENTOS.CSV"));
             BufferedWriter escritorEquipe = new BufferedWriter(new FileWriter(nomeArquivo + "-EQUIPES.CSV"));
             BufferedWriter escritorEquipamento = new BufferedWriter(new FileWriter(nomeArquivo + "-EQUIPAMENTOS.CSV"));
             BufferedWriter escritorAtendimento = new BufferedWriter(new FileWriter(nomeArquivo + "-ATENDIMENTOS.CSV"))) {

            for (Evento evento : appEvento.getEventos()) {
                escritorEvento.write(evento.toString());
                escritorEvento.newLine();
            }

            for (Equipe equipe : appEquipe.getEquipes()) {
                escritorEquipe.write(equipe.toString());
                escritorEquipe.newLine();
            }

            for (Equipamento equipamento : appEquipamento.getEquipamentos()) {
                escritorEquipamento.write(equipamento.toString());
                escritorEquipamento.newLine();
            }

            for (Atendimento atendimento : appAtendimento.getAtendimentosPendentes()) {
                escritorAtendimento.write(atendimento.toString());
                escritorAtendimento.newLine();
            }

            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso.");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar dados. Verifique o nome do arquivo e tente novamente.");
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmarButton) {
            salvarTodosOsDados("EXEMPLO2");
        } else if(e.getSource() == voltarButton){
            acmeRescue.setContentPane(acmeRescue.getPainel());
            acmeRescue.setSize(800, 600);
        }
    }
}

