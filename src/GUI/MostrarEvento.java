package GUI;

import app.AppEvento;
import dados.Evento;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MostrarEvento extends JFrame {
    private JPanel painel;
    private JComboBox<Evento> comboBox1;
    private JTextArea textArea1;
    private JButton voltarButton;
    private JButton confirmarButton;
    private ACMERescue acmeRescue;
    private ArrayList<Evento> eventos;
    private AppEvento appEvento = new AppEvento();
    private Evento eventoSelecionado;
    private CadastrarAtendimento cadastrarAtendimento = new CadastrarAtendimento(acmeRescue, eventoSelecionado);

    public MostrarEvento(ACMERescue acmeRescue, ArrayList<Evento> eventos) {
        this.acmeRescue = acmeRescue;
        this.eventos = eventos;
        DefaultComboBoxModel<Evento> comboBoxModel = new DefaultComboBoxModel<>();

        for (Evento e : appEvento.getEventos()){
            comboBoxModel.addElement(e);
        }
        comboBox1.setModel(comboBoxModel);

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eventoSelecionado = (Evento) comboBox1.getSelectedItem();
                setContentPane(cadastrarAtendimento.getPainel());
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acmeRescue.setContentPane(acmeRescue.getPainel());
                acmeRescue.setSize(600,400);
            }
        });
    }

    public JPanel getPainel() {
        return painel;
    }
}
