package GUI;

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
    private JButton finalizarButton;
    private ArrayList<Equipamento>equipamentos = new ArrayList<>();

    public CadastroEquipamento(){

        JFrame frame = new JFrame();
        frame.setContentPane(EquipamentoCad);
        frame.setSize(600,400);
        frame.setTitle("ACMERescue");
        ImageIcon imageIcon = new ImageIcon("icon.png");
        frame.setLocationRelativeTo(null);
        frame.setIconImage(imageIcon.getImage());

        confirmarButton.addActionListener(this);
        limparButton.addActionListener(this);
        mostrarDadosButton.addActionListener(this);
        finalizarButton.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private boolean existeID(int id){
        for(Equipamento equipamento: equipamentos){
            if (equipamento.getId()==id){
                return true;
            }
        }
        return false;
    }


    private void cadastrarEquipamento() {
        try {
            int idEquipamento = Integer.parseInt(textField1.getText());
            String nomeEquipamento =textField2.getText();
            double custoDia = Double.parseDouble(textField3.getText());
            if (existeID(idEquipamento)) {
                textArea1.append("Erro! Já existe um equipamento com esse ID."+"\n");
            }
            else {
                equipamentos.add(new Equipamento(idEquipamento, nomeEquipamento, custoDia));
                textArea1.append("Equipamento cadastrado!"+"\n");
            }
        }
        catch (NumberFormatException e){
            textArea1.append("Formato inválido! Tente novamente."+"\n");
        }
    }


    private void mostrarEquipamentos() {
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
        System.exit(0);
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
        else if (e.getSource()== finalizarButton){
            sairDoPrograma();
        }
    }
}


