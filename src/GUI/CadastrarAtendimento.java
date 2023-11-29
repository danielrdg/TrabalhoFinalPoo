package GUI;

import app.AppAtendimento;
import app.AppEvento;
import dados.Atendimento;
import dados.Evento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastrarAtendimento {
    private JTextField campoCodigo, campoData, campoDuracao;
    private JButton confirmarButton, limparButton, mostrarDadosButton, voltarButton;
    private JTextArea areaTexto;
    private JPanel painel;
    private AppAtendimento appAtendimento;
    private ACMERescue acmeRescue;
    private MostrarEvento mostrarEvento;
    private Evento eventoSelecionado;

    public CadastrarAtendimento(ACMERescue acmeRescue, MostrarEvento mostrarEvento) {
        this.acmeRescue = acmeRescue;
        this.appAtendimento = acmeRescue.getAppAtendimento();
        this.mostrarEvento = mostrarEvento;
        this.eventoSelecionado = mostrarEvento.getEventoSelecionado();

        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int codigo = Integer.parseInt(campoCodigo.getText().trim());
                    String dataInicio = campoData.getText().trim();
                    int duracao = Integer.parseInt(campoDuracao.getText().trim());
                    String status = "PENDENTE";
                    Evento evento = mostrarEvento.getEventoSelecionado();

                    if (evento.getAtendimento() != null) {
                        JOptionPane.showMessageDialog(null, "Erro! Já existe um atendimento para esse evento.", "Erro", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Atendimento atendimento = new Atendimento(codigo, dataInicio, duracao, status, evento);
                        if (appAtendimento.cadastrarAtendimento(atendimento)) {
                            JOptionPane.showMessageDialog(null, "Atendimento cadastrado com sucesso.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro! Já existe um atendimento com esse código.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro! Formato inválido para código ou duração.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                atualizarListaAtendimentos();
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoCodigo.setText("");
                campoData.setText("");
                campoDuracao.setText("");
                areaTexto.setText("");
            }
        });

        mostrarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (appAtendimento.getAtendimentosPendentes().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Erro! Nenhum atendimento cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    atualizarListaAtendimentos();
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
    }

    public void atualizarListaAtendimentos() {
        SwingUtilities.invokeLater(() -> {
            areaTexto.setText("");
            if (appAtendimento.getAtendimentosPendentes().isEmpty()) {
                areaTexto.append("Erro! Nenhum atendimento cadastrado\n");
            } else {
                for (Atendimento atendimento : appAtendimento.getAtendimentosPendentes()) {
                    areaTexto.append(atendimento.toString());
                }
            }
        });
    }

    public JPanel getPainel() {
        return painel;
    }

    public void setEventoSelecionado(Evento eventoSelecionado) {
        this.eventoSelecionado = eventoSelecionado;
    }
}
