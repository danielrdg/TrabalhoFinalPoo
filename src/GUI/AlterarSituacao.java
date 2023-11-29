package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dados.Atendimento;

public class AlterarSituacao {
    private ACMERescue acmeRescue;
    private JPanel painel;
    private JLabel labelAtendimento, labelCodigo;
    private JTextField campoCodigo;
    private JTextArea areaTexto;
    private JButton confirmarButton, limparButton, voltarButton;

    public AlterarSituacao(ACMERescue acmeRescue) {
        this.acmeRescue = acmeRescue;
        confirmarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    realizarAcaoConfirmar();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro! Digite um código válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                areaTexto.setText("");
                campoCodigo.setText("");
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acmeRescue.setContentPane(acmeRescue.getPainel());
                acmeRescue.setSize(800, 600);
            }
        });
    }

    private void realizarAcaoConfirmar() {
        int cod = Integer.parseInt(campoCodigo.getText().trim());
        boolean atendimentoEncontrado = false;

        for (Atendimento a : acmeRescue.getAppAtendimento().getAtendimentosPendentes()) {
            if (a.getCod() == cod) {
                atendimentoEncontrado = true;
                areaTexto.append(a.toString() + "\n");

                if (a.getStatus().equalsIgnoreCase("FINALIZADO")) {
                    JOptionPane.showMessageDialog(null, "Erro! O atendimento já foi finalizado.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    abrirJanelaAlterarSituacao(a);
                }
            }
        }

        if (!atendimentoEncontrado) {
            JOptionPane.showMessageDialog(null, "Erro! Nenhum atendimento encontrado com o código indicado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirJanelaAlterarSituacao(Atendimento atendimento) {
        JFrame alterarSituacao = new JFrame();
        JLabel labelSituacaoNova = new JLabel("Escolha a nova situação: \n 1 - PENDENTE\n 2 - EXECUTANDO\n 3 - FINALIZADO\n 4 - CANCELADO");
        JTextField campoSituacaoNova = new JTextField(20);
        JButton confirmarSituacaoNova = new JButton("Confirmar");
        JButton limparSituacaoNova = new JButton("Limpar");
        JButton cancelar = new JButton("Cancelar");

        alterarSituacao.setLayout(new FlowLayout());
        alterarSituacao.add(labelSituacaoNova);
        alterarSituacao.add(campoSituacaoNova);
        alterarSituacao.add(confirmarSituacaoNova);
        alterarSituacao.add(limparSituacaoNova);
        alterarSituacao.add(cancelar);

        confirmarSituacaoNova.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int situacaoNova = Integer.parseInt(campoSituacaoNova.getText().trim());
                    if (situacaoNova >= 1 && situacaoNova <= 4) {
                        switch (situacaoNova) {
                            case 1:
                                atendimento.setStatus("PENDENTE");
                                break;
                            case 2:
                                atendimento.setStatus("EXECUTANDO");
                                break;
                            case 3:
                                atendimento.setStatus("FINALIZADO");
                                break;
                            case 4:
                                atendimento.setStatus("CANCELADO");
                                break;
                        }
                        JOptionPane.showMessageDialog(null, "Situação do atendimento alterada com sucesso.");
                        alterarSituacao.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro! Situação deve ser um número de 1 até 4.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro! Digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        limparSituacaoNova.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoSituacaoNova.setText("");
            }
        });

        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarSituacao.dispose();
            }
        });

        alterarSituacao.pack();
        alterarSituacao.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        alterarSituacao.setVisible(true);
    }

    public JPanel getPainel() {
        return painel;
    }
}
