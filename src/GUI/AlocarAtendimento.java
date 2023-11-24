package GUI;

import app.AppAtendimento;
import app.AppEvento;
import dados.Atendimento;
import dados.Evento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlocarAtendimento {

    private JPanel painel;
    private JButton confirmarButton, limparButton, mostrarDadosButton, voltarButton;
    private JTextArea areaTexto;
    private ACMERescue acmeRescue;
    private MostrarEvento mostrarEvento;
    private AppAtendimento appAtendimento;

    public AlocarAtendimento(ACMERescue acmeRescue) {
        this.acmeRescue = acmeRescue;
        this.appAtendimento = acmeRescue.getAppAtendimento();


        painel = new JPanel();
        confirmarButton = new JButton("Confirmar");
        limparButton = new JButton("Limpar");
        mostrarDadosButton = new JButton("Mostrar Dados");
        voltarButton = new JButton("Voltar");
        areaTexto = new JTextArea(10, 30);


        painel.add(confirmarButton);
        painel.add(limparButton);
        painel.add(mostrarDadosButton);
        painel.add(voltarButton);
        painel.add(new JScrollPane(areaTexto));
        painel.setVisible(true);


        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaTexto.append("Atendimento alocado com sucesso!\n");
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaTexto.setText("");
            }
        });

        mostrarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acmeRescue.setSize(600, 400);
                acmeRescue.setContentPane(acmeRescue.getPainel());
            }
        });
    }

    public JPanel getPainel() {
        return painel;
    }
}
