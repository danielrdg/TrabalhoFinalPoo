package GUI;

import dados.Equipe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class CadastroEquipe extends JFrame implements ActionListener {

    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton cadastrarButton;
    private JButton limparButton;
    private JButton mostrarDadosButton;
    private JButton finalizarButton;
    private JTextArea textArea1;
    private JPanel cadastroEquipe;
    private ArrayList<Equipe> equipes=new ArrayList<>();

    public CadastroEquipe(){
        cadastrarButton.addActionListener(this);
        limparButton.addActionListener(this);
        mostrarDadosButton.addActionListener(this);
        finalizarButton.addActionListener(this);

        JFrame frame=new JFrame();
        frame.setContentPane(cadastroEquipe);
        frame.setTitle("Cadastro de Equipes");
        frame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        frame.setSize(800,400);
        frame.pack();
        frame.setVisible(true);
    }

    private void cadastrarEquipe(){
        try {
            String codinome=textField1.getText();
            int quantidade=Integer.parseInt(textField2.getText().trim());
            double latitude=Double.parseDouble(textField3.getText().trim());
            double longitude=Double.parseDouble(textField4.getText().trim());
            Equipe equipe=new Equipe(codinome,quantidade,latitude,longitude);
            for(Equipe e:equipes){
                if(e.getCodinome().equals(equipe.getCodinome())){
                    textArea1.append("Já existe uma equipe com esse codinome, tente novamente.\n");
                }
            }
            equipes.add(equipe);
            limparCampos();
            textArea1.append("Equipe cadastrada com sucesso.\n");
        }
        catch(NumberFormatException e){
            textArea1.append("Formato inválido, tente novamente!\n");
        }
    }

    private void limparCampos(){
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textArea1.setText("");
    }

    private void finalizar(){
        System.exit(0);
    }

    private void mostrarDados(){

        Collections.sort(equipes);

        if(equipes.isEmpty()){
            textArea1.append("Não há equipes cadastradas!\n");
        }
        for(Equipe e:equipes){
            textArea1.append("Codinome: " + e.getCodinome()+"\n" + "Quantidade de membros: " + e.getQuantidade()+"\n" + "Latitude: " + e.getLatitude()+"\n" + "Longitude: " + e.getLongitude()+"\n\n");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cadastrarButton){
            cadastrarEquipe();
        }
        else if(e.getSource()==limparButton){
            limparCampos();
        }
        else if(e.getSource()==finalizarButton){
            finalizar();
        }
        else if(e.getSource()==mostrarDadosButton){
            mostrarDados();
        }
    }
}
