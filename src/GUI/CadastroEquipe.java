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
    private JButton cadastrar;
    private JButton limpar;
    private JButton mostrarDados;
    private JButton voltar;
    private JTextArea textArea1;
    private JPanel cadastroEquipe;
    private ArrayList<Equipe> equipes;

    public CadastroEquipe(){
        super();
        cadastrar.addActionListener(this);
        limpar.addActionListener(this);
        mostrarDados.addActionListener(this);
        voltar.addActionListener(this);
        equipes = new ArrayList<>();

        setContentPane(cadastroEquipe);
        setTitle("ACMERescue");
        ImageIcon imageIcon = new ImageIcon("icon.png");
        setLocationRelativeTo(null);
        setIconImage(imageIcon.getImage());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
    }

    private void cadastrarEquipe() {
        try {
            String codinome = textField1.getText();
            int quantidade = Integer.parseInt(textField2.getText().trim());
            double latitude = Double.parseDouble(textField3.getText().trim());
            double longitude = Double.parseDouble(textField4.getText().trim());
            Equipe equipe = new Equipe(codinome,quantidade,latitude,longitude);

            for(Equipe e:equipes){
                if(e.getCodinome().equals(equipe.getCodinome())){
                    textArea1.append("Já existe uma equipe com esse codinome.\n");
                }
            }
            equipes.add(equipe);
            textArea1.append("Equipe cadastrada com sucesso.\n");
        }
        catch(NumberFormatException e){
            textArea1.append("Formato inválido, tente novamente!\n");
        }
    }

    private void limparCampos() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textArea1.setText("");
    }

    private void voltar(){
        dispose();
    }

    private void mostrarDados() {

        Collections.sort(equipes);

        if(equipes.isEmpty()){
            textArea1.append("Nenhuma equipe cadastrada.\n");
        }
        for(Equipe e : equipes){
            textArea1.append("Codinome: " + e.getCodinome()+"\n" + "Quantidade de membros: " + e.getQuantidade()+"\n" + "Latitude: " + e.getLatitude()+"\n" + "Longitude: " + e.getLongitude()+"\n\n");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cadastrar){
            cadastrarEquipe();
        }
        else if(e.getSource() == limpar){
            limparCampos();
        }
        else if(e.getSource() == voltar){
            dispose();
        }
        else if(e.getSource() == mostrarDados){
            mostrarDados();
        }
    }
}
