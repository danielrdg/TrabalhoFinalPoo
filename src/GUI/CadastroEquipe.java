package GUI;

import app.AppEquipe;
import dados.Equipe;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class CadastroEquipe implements ActionListener {

    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton cadastrar;
    private JButton limpar;
    private JButton mostrarDados;
    private JButton voltar;
    private JTextArea textArea1;
    private JPanel painel;
    private AppEquipe appEquipe;
    private ACMERescue acmeRescue;

    public CadastroEquipe(ACMERescue acmeRescue){
        super();
        this.acmeRescue = acmeRescue;
        appEquipe = new AppEquipe();
        cadastrar.addActionListener(this);
        limpar.addActionListener(this);
        mostrarDados.addActionListener(this);
        voltar.addActionListener(this);
    }

    private void cadastrarEquipe() {
        try {
            String codinome = textField1.getText();
            int quantidade = Integer.parseInt(textField2.getText().trim());
            double latitude = Double.parseDouble(textField3.getText().trim());
            double longitude = Double.parseDouble(textField4.getText().trim());
            Equipe equipe = new Equipe(codinome,quantidade,latitude,longitude);
            if(appEquipe.cadastrarEquipe(equipe)) {
                textArea1.append("Equipe cadastrada com sucesso.");
            }
            else {
                textArea1.append("Já existe uma equipe com esse codinome;");
            }
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

    private void mostrarDados() {
        ArrayList<Equipe> equipes = appEquipe.getEquipes();

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
        else if(e.getSource() == mostrarDados){
            mostrarDados();
        }
    }

    public JPanel getPainel() {
        return painel;
    }
}
