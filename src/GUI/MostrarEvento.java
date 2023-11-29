package GUI;

import app.AppEvento;
import dados.Evento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MostrarEvento {
    private JPanel painel;
    private JTextArea areaTexto;
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
            areaTexto.append("Nenhum evento cadastrado.\n");
        } else {
            for (Evento evento : appEvento.getEventos()) {
                areaTexto.append(evento.toString());
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

                            if (evento.getAtendimento() != null) {
                                mostrarErro("Este evento já possui um atendimento cadastrado.");
                            } else {
                                acmeRescue.setContentPane(cadastrarAtendimento.getPainel());
                                acmeRescue.setSize(600, 400);
                            }
                            break;
                        }
                    }

                    if (eventoEncontrado && eventoSelecionado.getAtendimento() != null) {
                        mostrarMensagem("Evento selecionado.");
                    } else if (!eventoEncontrado) {
                        mostrarErro("Não existe evento com esse código.");
                    }
                } catch (NumberFormatException ex) {
                    mostrarErro("Formato inválido para código.");
                }
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acmeRescue.setSize(800, 600);
                acmeRescue.setContentPane(acmeRescue.getPainel());
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaTexto.setText("");
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
            areaTexto.setText("");
            if (appEvento.getEventos().isEmpty()) {
                areaTexto.append("Nenhum evento cadastrado.\n");
            } else {
                for (Evento evento : appEvento.getEventos()) {
                    areaTexto.append(evento.toString());
                }
            }
        });
    }

    private void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(painel, mensagem);
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(painel, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
