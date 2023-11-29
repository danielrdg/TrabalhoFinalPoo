package GUI;

import app.AppEvento;
import dados.Ciclone;
import dados.Evento;
import dados.Seca;
import dados.Terremoto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class CadastroEvento implements ActionListener {
    private JTextField campoCodigo, campoData, campoLatitude, campoLongitude, txtSeca, txtTerremoto, txtPrecipitacao, txtCiclone;
    private JButton confirmarButton, limparButton, mostrarDadosButton, finalizarButton, botaoConfirmarCic, botaoConfirmarTer, botaoConfirmarSec;
    private JPanel painel;
    private JTextArea areaTexto;
    private JTextField campoTipo;
    private JLabel labelVelocidade, labelPrecipitacao, tipoTerremoto, tipoSeca, tipoCiclone;
    private final AppEvento appEvento;
    private final ACMERescue acmeRescue;

    public CadastroEvento(ACMERescue acmeRescue) {
        super();
        this.acmeRescue = acmeRescue;
        this.appEvento = acmeRescue.getAppEvento();
        confirmarButton.addActionListener(this);
        mostrarDadosButton.addActionListener(this);
        limparButton.addActionListener(this);
        finalizarButton.addActionListener(this);
    }

    private void cadastrarEvento() {

        if(campoCodigo.getText().trim().isEmpty() || campoData.getText().trim().isEmpty() || campoLatitude.getText().trim().isEmpty() || campoLongitude.getText().trim().isEmpty() || campoTipo.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this.getPainel(), "Erro! Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String codigoEvento = campoCodigo.getText().trim();
            String dataEvento = campoData.getText().trim();
            double latitudeEvento = Double.parseDouble(campoLatitude.getText().trim());
            double longitudeEvento = Double.parseDouble(campoLongitude.getText().trim());
            int tipoEvento = Integer.parseInt(campoTipo.getText().trim());

            if (existeCodigo(codigoEvento)) {
                JOptionPane.showMessageDialog(this.getPainel(), "Erro! Já existe um evento com esse código.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                switch (tipoEvento) {
                    case 1:
                        JFrame Ciclo = criarJanela("Ciclone");
                        adicionarComponentesCiclone(Ciclo);
                        adicionarAcaoBotaoCiclone(Ciclo, codigoEvento, dataEvento, latitudeEvento, longitudeEvento);
                        break;

                    case 2:
                        JFrame Terre = criarJanela("Terremoto");
                        adicionarComponentesTerremoto(Terre);
                        adicionarAcaoBotaoTerremoto(Terre, codigoEvento, dataEvento, latitudeEvento, longitudeEvento);
                        break;

                    case 3:
                        JFrame Seca = criarJanela("Seca");
                        adicionarComponentesSeca(Seca);
                        adicionarAcaoBotaoSeca(Seca, codigoEvento, dataEvento, latitudeEvento, longitudeEvento);
                        break;

                    default:
                        JOptionPane.showMessageDialog(this.getPainel(), "Erro! Tipo de evento deve ser de 1 a 3.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this.getPainel(), "Formato inválido para latitude ou longitude.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean existeCodigo(String codigo) {
        for (Evento evento : appEvento.getEventos()) {
            if (evento.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    private void mostrarDados() {
        if (appEvento.getEventos().isEmpty()) {
            areaTexto.append("Nenhum evento cadastrado.\n");
        } else {
            Collections.sort(appEvento.getEventos());
            for (Evento evento1 : appEvento.getEventos()) {
                areaTexto.append("Código: " + evento1.getCodigo() + "\n");
                areaTexto.append("Data: " + evento1.getData() + "\n");
                areaTexto.append("Latitude: " + evento1.getLatitude() + "\n");
                areaTexto.append("Longitude: " + evento1.getLongitude() + "\n");

                if (evento1 instanceof Ciclone ciclone) {
                    areaTexto.append("Tipo de evento: Ciclone\n");
                    areaTexto.append("Velocidade: " + ciclone.getVelocidade() + "\n");
                    areaTexto.append("Precipitação: " + ciclone.getPrecipitacao() + "\n");
                } else if (evento1 instanceof Terremoto terremoto) {
                    areaTexto.append("Tipo de evento: Terremoto\n");
                    areaTexto.append("Magnitude: " + terremoto.getMagnitude() + "\n");
                } else if (evento1 instanceof Seca seca) {
                    areaTexto.append("Tipo de evento: Seca\n");
                    areaTexto.append("Estiagem: " + seca.getEstiagem() + "\n");
                }
                areaTexto.append("--------------------------" + "\n");
            }
        }
    }

    private JFrame criarJanela(String title) {
        JFrame janela = new JFrame();
        janela.setSize(300, 150);
        janela.setTitle(title);
        ImageIcon imageIcon = new ImageIcon("icon.png");
        janela.setLocationRelativeTo(null);
        janela.setIconImage(imageIcon.getImage());
        janela.setLayout(new FlowLayout(FlowLayout.RIGHT));
        return janela;
    }

    private void adicionarComponentesCiclone(JFrame janela) {
        labelVelocidade = new JLabel("Velocidade: ");
        txtCiclone = new JTextField(10);
        labelPrecipitacao = new JLabel("Precipitação: ");
        txtPrecipitacao = new JTextField(10);
        botaoConfirmarCic = new JButton("Confirmar");
        janela.add(labelVelocidade);
        janela.add(txtCiclone);
        janela.add(labelPrecipitacao);
        janela.add(txtPrecipitacao);
        janela.add(botaoConfirmarCic);
        janela.pack();
    }

    private void adicionarAcaoBotaoCiclone(JFrame janela, String codigoEvento, String dataEvento, double latitudeEvento, double longitudeEvento) {
        botaoConfirmarCic.addActionListener(event -> {
            try {
                double vel = Double.parseDouble(txtCiclone.getText().trim());
                double prec = Double.parseDouble(txtPrecipitacao.getText().trim());
                Ciclone ciclone = new Ciclone(codigoEvento, dataEvento, latitudeEvento, longitudeEvento, vel, prec);

                if (appEvento.cadastrarEvento(ciclone)) {
                    JOptionPane.showMessageDialog(null, "Evento cadastrado com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro! Já existe um evento com esse código.", "Erro", JOptionPane.ERROR_MESSAGE);
                }

                janela.setVisible(false);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Formato inválido para velocidade ou precipitação. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setVisible(true);
    }

    private void adicionarComponentesTerremoto(JFrame janela) {
        tipoTerremoto = new JLabel("Magnitude: ");
        txtTerremoto = new JTextField(10);
        botaoConfirmarTer = new JButton("Confirmar");
        janela.add(tipoTerremoto);
        janela.add(txtTerremoto);
        janela.add(botaoConfirmarTer);
        janela.pack();
    }

    private void adicionarAcaoBotaoTerremoto(JFrame janela, String codigoEvento, String dataEvento, double latitudeEvento, double longitudeEvento) {
        botaoConfirmarTer.addActionListener(event -> {
            try {
                double mag = Double.parseDouble(txtTerremoto.getText().trim());
                Terremoto terremoto = new Terremoto(codigoEvento, dataEvento, latitudeEvento, longitudeEvento, mag);
                if (appEvento.cadastrarEvento(terremoto)) {
                    JOptionPane.showMessageDialog(null, "Evento cadastrado com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro! Já existe um evento com esse código.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                janela.setVisible(false);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Formato inválido para magnitude.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setVisible(true);
    }

    private void adicionarComponentesSeca(JFrame janela) {
        tipoSeca = new JLabel("Estiagem: ");
        txtSeca = new JTextField(10);
        botaoConfirmarSec = new JButton("Confirmar");
        janela.add(tipoSeca);
        janela.add(txtSeca);
        janela.add(botaoConfirmarSec);
        janela.pack();
    }

    private void adicionarAcaoBotaoSeca(JFrame janela, String codigoEvento, String dataEvento, double latitudeEvento, double longitudeEvento) {
        botaoConfirmarSec.addActionListener(event -> {
            try {
                int est = Integer.parseInt(txtSeca.getText().trim());
                Seca seca = new Seca(codigoEvento, dataEvento, latitudeEvento, longitudeEvento, est);

                if (appEvento.cadastrarEvento(seca)) {
                    JOptionPane.showMessageDialog(null, "Evento cadastrado com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(null, "Erro! Já existe um evento com esse código.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
                janela.setVisible(false);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Formato inválido para estiagem.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        janela.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmarButton) {
            cadastrarEvento();
        } else if (e.getSource() == limparButton) {
            campoCodigo.setText("");
            campoData.setText("");
            campoLatitude.setText("");
            campoLongitude.setText("");
            campoTipo.setText("");
            areaTexto.setText("");
        } else if (e.getSource() == mostrarDadosButton) {
            mostrarDados();
        } else if (e.getSource() == finalizarButton) {
            acmeRescue.setContentPane(acmeRescue.getPainel());
            acmeRescue.setSize(800, 600);
        }
    }

    public JPanel getPainel() {
        return painel;
    }

}
