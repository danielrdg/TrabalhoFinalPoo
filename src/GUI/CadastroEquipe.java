package GUI;

import app.AppEquipe;
import dados.Equipe;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class CadastroEquipe extends JFrame implements ActionListener {
    private JTextField textField1, textField2, textField3, textField4;
    private ArrayList<JTextField> camposDeTexto;
    private JButton cadastrar, limpar, mostrarDados, voltar;
    private JTextArea textArea1;
    private JPanel painel;
    private AppEquipe appEquipe = new AppEquipe();
    private ACMERescue acmeRescue;

    public CadastroEquipe(ACMERescue acmeRescue, ArrayList<Equipe> equipes){
        super();
        this.acmeRescue = acmeRescue;
        camposDeTexto = new ArrayList<>();
        camposDeTexto.add(textField1);
        camposDeTexto.add(textField2);
        camposDeTexto.add(textField3);
        camposDeTexto.add(textField4);
        cadastrar.addActionListener(this);
        limpar.addActionListener(this);
        mostrarDados.addActionListener(this);
        voltar.addActionListener(this);
    }

    private void cadastrarEquipe() {
        try {
            String codinome = textField1.getText().trim();
            int quantidade = Integer.parseInt(textField2.getText().trim());
            double latitude = Double.parseDouble(textField3.getText().trim());
            double longitude = Double.parseDouble(textField4.getText().trim());

            Equipe equipe = new Equipe(codinome,quantidade,latitude,longitude);
            if (appEquipe.cadastrarEquipe(equipe)) {
                textArea1.append("Equipe cadastrada com sucesso!");
            }
            else {
                textArea1.append("Erro! Já existe uma equipe com esse codinome;");
            }
        }
        catch(NumberFormatException e){
            textArea1.append("Formato inválido! Tente novamente.\n");
        }
    }

    private void limparCampos() {
        for (JTextField j : camposDeTexto) {
            j.setText("");
        }
        textArea1.setText("");
    }

    private void mostrarDados() {

        ArrayList<Equipe> equipes = appEquipe.getEquipes();

        Collections.sort(equipes);

        if (equipes.isEmpty()) {
            textArea1.append("Nenhuma equipe cadastrada no sistema.\n");
        }

        for (Equipe e : equipes){
            textArea1.append("Equipe:\n" + e.toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cadastrar) {
            cadastrarEquipe();
        }
        else if (e.getSource() == limpar) {
            limparCampos();
        }
        else if (e.getSource() == mostrarDados) {
            mostrarDados();
        }
        else if (e.getSource() == voltar) {
            acmeRescue.setContentPane(acmeRescue.getPainel());
            acmeRescue.setSize(600,400);
        }
    }

    public JPanel getPainel() {
        return painel;
    }
}
