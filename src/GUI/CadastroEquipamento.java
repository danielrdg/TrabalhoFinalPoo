package GUI;

import app.AppEquipamento;
import dados.Equipamento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class CadastroEquipamento extends JFrame implements ActionListener {
    private JPanel painel;
    private JTextField textField1, textField2, textField3;
    private JButton confirmarButton, limparButton, mostrarDadosButton, voltarButton;
    private JTextArea textArea1;
    private JTextField textField4;
    private ACMERescue acmeRescue;
    private AppEquipamento appEquipamento;

    public CadastroEquipamento(ACMERescue acmeRescue) {
        super();
        this.acmeRescue = acmeRescue;
        confirmarButton.addActionListener(this);
        limparButton.addActionListener(this);
        mostrarDadosButton.addActionListener(this);
        voltarButton.addActionListener(this);
        appEquipamento = new AppEquipamento();
    }


    private void cadastrarEquipamento() {
        try {
            int idEquipamento = Integer.parseInt(textField1.getText().trim());
            String nomeEquipamento =textField2.getText().trim();
            double custoDia = Double.parseDouble(textField3.getText().trim());
            int tipo = Integer.parseInt(textField4.getText().trim());
            Equipamento equipamento = new Equipamento(idEquipamento, nomeEquipamento, custoDia);

            switch(tipo) {
                case 1:

            }

        }
        catch (NumberFormatException e){
            textArea1.append("Formato inválido! Tente novamente."+"\n");
        }
    }


    private void mostrarEquipamentos() {


        if (appEquipamento.getEquipamentos().isEmpty()){
            textArea1.append("Nenhum equipamento cadastrado."+"\n");
        }
        else {
            Collections.sort(appEquipamento.getEquipamentos());
            for (Equipamento equipamento: appEquipamento.getEquipamentos()){
                textArea1.append("ID: " + equipamento.getId()+"\n");
                textArea1.append("Nome: " + equipamento.getNome()+"\n");
                textArea1.append("Custo do Dia: " +"R$"+ equipamento.getCustoDia()+"\n");
            }}
    }

    private void limparCampos() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textArea1.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== confirmarButton){
            cadastrarEquipamento();
        }
        else if (e.getSource() == limparButton){
            limparCampos();
        }
        else if (e.getSource()==mostrarDadosButton){
            mostrarEquipamentos();
        }
        else if (e.getSource()== voltarButton){
            acmeRescue.setContentPane(acmeRescue.getPainel());
            acmeRescue.setSize(800, 600);
        }
    }

    public JPanel getPainel() {
        return painel;
    }
}


