package GUI;

import app.AppEvento;
import dados.Evento;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MostrarEvento {
    private JPanel painel;
    private JComboBox<Evento> comboBox1;
    private JTextArea textArea1;
    private JButton voltarButton;
    private JButton confirmarButton;
    private ACMERescue acmeRescue;
    private Evento eventoSelecionado;
    private AppEvento appEvento = new AppEvento();

    public MostrarEvento(ACMERescue acmeRescue) {
        super();
        this.acmeRescue = acmeRescue;
        DefaultComboBoxModel<Evento> comboBoxModel = new DefaultComboBoxModel<>();

        for (Evento evento : appEvento.getEventos()) { //Para cada evento na lista de eventos, adiciona-o à combo box.
            comboBoxModel.addElement(evento);
        }
        comboBox1.setModel(comboBoxModel);


        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Evento eventoSelecionado = (Evento) comboBox1.getSelectedItem();
                CadastrarAtendimento cadastrarAtendimento = new CadastrarAtendimento(acmeRescue, eventoSelecionado);
                acmeRescue.setSize(800,400);
                acmeRescue.setContentPane(cadastrarAtendimento.getPainel());
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acmeRescue.setSize(600,400);
                acmeRescue.setContentPane(acmeRescue.getPainel());
            }
        });
    }

    public JPanel getPainel() {
        return painel;
    }

    public Evento getEventoSelecionado() {
        return eventoSelecionado;
    }
}
