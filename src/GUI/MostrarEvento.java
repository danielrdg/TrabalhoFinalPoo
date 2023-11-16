package GUI;

import app.AppEvento;
import dados.Evento;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MostrarEvento {
    private JPanel painel;
    private JTextArea areaEventos;
    private JButton voltarButton;
    private JButton confirmarButton;
    private JTextField campoCodigo;
    private JButton limparButton;
    private ACMERescue acmeRescue;
    private MostrarEvento mostrarEvento;
    private Evento eventoSelecionado;
    private CadastrarAtendimento cadastrarAtendimento;
    private AppEvento appEvento;

    public MostrarEvento(ACMERescue acmeRescue) {
        super();
        this.acmeRescue = acmeRescue;
        this.mostrarEvento = mostrarEvento;
        this.cadastrarAtendimento = new CadastrarAtendimento(acmeRescue, this);
        this.appEvento = acmeRescue.getAppEvento();

        if (appEvento.getEventos().isEmpty()) {
            areaEventos.append("Nenhum evento cadastrado.\n");
        } else {
            for (Evento evento : appEvento.getEventos()) {
                areaEventos.append(evento.toString());
            }
        }

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean eventoEncontrado = false;
                try {
                    for (Evento evento : appEvento.getEventos()) {
                        if (evento.getCodigo().equals(campoCodigo.getText().trim())) {
                            eventoSelecionado = evento;
                            eventoEncontrado = true;
                            acmeRescue.setContentPane(cadastrarAtendimento.getPainel());
                            acmeRescue.setSize(600,400);
                            break;
                        }
                    }

                    if (eventoEncontrado) {
                        areaEventos.append("Evento selecionado com sucesso!\n");
                    } else {
                        areaEventos.append("Não existe evento com o código fornecido.\n");
                    }
                } catch (NumberFormatException ex) {
                    areaEventos.append("Erro! Digite um valor inteiro no campo código.\n");
                }
            }
        });
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acmeRescue.setSize(600, 400);
                acmeRescue.setContentPane(acmeRescue.getPainel());
            }
        });
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaEventos.setText("");
                campoCodigo.setText("");
            }
        });
    }

    public JPanel getPainel() {
        return painel;
    }

    public Evento getEventoSelecionado() {
        return eventoSelecionado;
    }

    public void atualizarListaEventos() {
        SwingUtilities.invokeLater(() -> {
            areaEventos.setText("");
            if (appEvento.getEventos().isEmpty()) {
                areaEventos.append("Nenhum evento cadastrado.\n");
            } else {
                for (Evento evento : appEvento.getEventos()) {
                    areaEventos.append(evento.toString());
                }
            }
        });
    }

}
