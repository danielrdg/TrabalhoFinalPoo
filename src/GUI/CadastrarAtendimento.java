package GUI;

import dados.Atendimento;
import dados.Evento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarAtendimento extends JFrame{
    private JPanel painel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextArea textArea1;
    private JButton cadastrarButton;
    private JButton limparButton;
    private JButton mostrarDadosButton;
    private JButton finalizarButton;
    private ACMERescue acmeRescue;
    private Evento eventoSelecionado;

    public CadastrarAtendimento(ACMERescue acmeRescue, Evento eventoSelecionado){
        this.acmeRescue = acmeRescue;
        this.eventoSelecionado = eventoSelecionado;

        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try {
                   int cod = Integer.parseInt(textField1.getText().trim());
                   String dataInicio = textField2.getText().trim();
                   int duracao = Integer.parseInt(textField3.getText().trim());
                   String status = "PENDENTE";
                   Evento evento = eventoSelecionado;
                   Atendimento atendimento = new Atendimento(cod,dataInicio,duracao,status,eventoSelecionado);
               }
               catch(NumberFormatException ex){
                   textArea1.append("Formato invalido, tente novamente.");
               }
            }
        });
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
                textArea1.setText("");
            }
        });
        mostrarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        finalizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acmeRescue.setContentPane(acmeRescue.getPainel());
                acmeRescue.setSize(800,400);
            }
        });
    }

    public JPanel getPainel() {
        return painel;
    }
}
