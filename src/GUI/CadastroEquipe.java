package GUI;

import app.AppEquipe;
import dados.Equipe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class CadastroEquipe implements ActionListener {
    private JTextField textField1, textField2, textField3, textField4;
    private ArrayList<JTextField> camposDeTexto;
    private JButton confirmar, limpar, mostrarDados, voltar;
    private JTextArea areaTexto;
    private JPanel painel;
    private AppEquipe appEquipe;
    private ACMERescue acmeRescue;

    public CadastroEquipe(ACMERescue acmeRescue) {
        super();
        this.acmeRescue = acmeRescue;
        this.appEquipe = acmeRescue.getAppEquipe();
        camposDeTexto = new ArrayList<>();
        camposDeTexto.add(textField1);
        camposDeTexto.add(textField2);
        camposDeTexto.add(textField3);
        camposDeTexto.add(textField4);
        confirmar.addActionListener(this);
        limpar.addActionListener(this);
        mostrarDados.addActionListener(this);
        voltar.addActionListener(this);
    }

    private void cadastrarEquipe() {
        if(textField1.getText().trim().isEmpty() || textField2.getText().trim().isEmpty() || textField3.getText().trim().isEmpty() || textField4.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Erro! Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String codinome = textField1.getText().trim();
            int quantidade = Integer.parseInt(textField2.getText().trim());
            double latitude = Double.parseDouble(textField3.getText().trim());
            double longitude = Double.parseDouble(textField4.getText().trim());

            Equipe equipe = new Equipe(codinome, quantidade, latitude, longitude);
            if (appEquipe.cadastrarEquipe(equipe)) {
                JOptionPane.showMessageDialog(null, "Equipe cadastrada com sucesso.");
            } else {
                JOptionPane.showMessageDialog(null, "Erro! Já existe uma equipe com esse codinome.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro! Formato inválido para quantidade, latitude ou longitude.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        for (JTextField j : camposDeTexto) {
            j.setText("");
        }
        areaTexto.setText("");
    }

    private void mostrarDados() {
        ArrayList<Equipe> equipes = appEquipe.getEquipes();
        Collections.sort(equipes);

        if (equipes.isEmpty()) {
            areaTexto.append("Erro! Nenhuma equipe cadastrada.\n");
        } else {
            for (Equipe e : equipes) {
                areaTexto.append(e.toString() + "\n");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmar) {
            cadastrarEquipe();
        } else if (e.getSource() == limpar) {
            limparCampos();
        } else if (e.getSource() == mostrarDados) {
            mostrarDados();
        } else if (e.getSource() == voltar) {
            acmeRescue.setContentPane(acmeRescue.getPainel());
            acmeRescue.setSize(800, 600);
        }
    }

    public JPanel getPainel() {
        return painel;
    }
}
