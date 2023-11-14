package GUI;

import app.AppEquipamento;
import dados.Equipamento;
import dados.Evento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class CadastroEquipamento extends JFrame implements ActionListener {
    private JPanel EquipamentoCad;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton confirmarButton;
    private JButton limparButton;
    private JButton mostrarDadosButton;
    private JTextArea textArea1;
    private JButton voltarButton;
    private AppEquipamento appEquipamento;

    public CadastroEquipamento() {

        confirmarButton.addActionListener(this);
        limparButton.addActionListener(this);
        mostrarDadosButton.addActionListener(this);
        voltarButton.addActionListener(this);
        appEquipamento = new AppEquipamento();

        setContentPane(EquipamentoCad);
        setSize(600,400);
        setTitle("ACMERescue");
        ImageIcon imageIcon = new ImageIcon("icon.png");
        setLocationRelativeTo(null);
        setIconImage(imageIcon.getImage());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }


    private void cadastrarEquipamento() {
        try {
            int idEquipamento = Integer.parseInt(textField1.getText().trim());
            String nomeEquipamento =textField2.getText().trim();
            double custoDia = Double.parseDouble(textField3.getText().trim());
            Equipamento equipamento = new Equipamento(idEquipamento, nomeEquipamento, custoDia);
            if(appEquipamento.cadastrarEquipamento(equipamento)) {
                textArea1.append("Equipamento cadastrado com sucesso.");
            }
            else {
                textArea1.append("Erro! Já existe um equipamento com esse ID.");
            }
        }
        catch (NumberFormatException e){
            textArea1.append("Formato inválido! Tente novamente."+"\n");
        }
    }


    private void mostrarEquipamentos() {
        ArrayList<Equipamento> equipamentos = appEquipamento.getEquipamentos();

        if (equipamentos.isEmpty()){
            textArea1.append("Nenhum equipamento cadastrado."+"\n");
        }
        else {
            Collections.sort(equipamentos);
            for (Equipamento equipamento:equipamentos){
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


    private void sairDoPrograma() {
        dispose();
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
            sairDoPrograma();
        }
    }
}


