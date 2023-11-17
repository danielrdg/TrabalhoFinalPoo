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

public class CadastroEvento extends JFrame implements ActionListener {
    private JTextField textField1, textField2,textField3,textField4,textField5, txtSeca,txtTerremoto,txtPrecipitacao,txtCiclone;
    private JButton confirmarButton, limparButton, mostrarDadosButton, finalizarButton,botaoConfirmarCic,botaoConfirmarTer,botaoConfirmarSec;
    private JPanel painel;
    private JTextArea textArea1;
    private JLabel labelVelocidade, labelPrecipitacao,labelTerremoto,labelSeca;
    private AppEvento appEvento;
    private ACMERescue acmeRescue;

    public CadastroEvento(ACMERescue acmeRescue, AppEvento appEvento) {
        super();
        this.acmeRescue = acmeRescue;
        this.appEvento = appEvento;
        confirmarButton.addActionListener(this);
        mostrarDadosButton.addActionListener(this);
        limparButton.addActionListener(this);
        finalizarButton.addActionListener(this);
    }

    private void cadastrarEvento(){
        try {
            String codigoEvento = textField1.getText().trim();
            String dataEvento = textField2.getText().trim();
            double latitudeEvento = Double.parseDouble(textField3.getText().trim());
            double longitudeEvento = Double.parseDouble(textField4.getText().trim());
            int tipoEvento = Integer.parseInt(textField5.getText().trim());

            if (existeCodigo(codigoEvento)) {
                textArea1.append("Erro! Já existe um evento com esse código."+"\n");
            }
            else{
                if (tipoEvento == 1) {
                    JFrame Ciclo = new JFrame();
                    Ciclo.setSize(300, 150);
                    Ciclo.setTitle("Ciclone");
                    ImageIcon imageIcon = new ImageIcon("icon.png");
                    Ciclo.setLocationRelativeTo(null);
                    Ciclo.setIconImage(imageIcon.getImage());
                    Ciclo.setLayout(new BorderLayout());
                    labelVelocidade = new JLabel("Velocidade: ");
                    txtCiclone = new JTextField(10);
                    labelPrecipitacao = new JLabel("Precipitação: ");
                    txtPrecipitacao = new JTextField(10);
                    botaoConfirmarCic = new JButton("Confirmar");

                    Ciclo.setLayout(new FlowLayout(FlowLayout.RIGHT));
                    Ciclo.add(labelVelocidade);
                    Ciclo.add(txtCiclone);
                    Ciclo.add(labelPrecipitacao);
                    Ciclo.add(txtPrecipitacao);
                    Ciclo.add(botaoConfirmarCic);
                    Ciclo.pack();

                    botaoConfirmarCic.addActionListener(event -> {
                        try {
                            double vel = Double.parseDouble(txtCiclone.getText().trim());
                            double prec = Double.parseDouble(txtPrecipitacao.getText().trim());
                            Ciclone ciclone = new Ciclone(codigoEvento, dataEvento, latitudeEvento, longitudeEvento, vel, prec);

                            if (appEvento.cadastrarEvento(ciclone)) {
                                textArea1.append("Evendo cadastrado!" + "\n");
                            }
                            else {
                                textArea1.append("Erro! Já existe um evento com esse código." + "\n");
                            }

                            Ciclo.setVisible(false);

                        }
                        catch (NumberFormatException ex) {
                            textArea1.append("Formato inválido para velocidade ou precipitação. Tente novamente." + "\n");
                        }
                    });

                    Ciclo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    Ciclo.setVisible(true);


                } else if (tipoEvento == 2) {
                    JFrame Terre = new JFrame();
                    Terre.setSize(300, 150);
                    Terre.setTitle("Terremoto");
                    ImageIcon imageIcon = new ImageIcon("icon.png");
                    Terre.setLocationRelativeTo(null);
                    Terre.setIconImage(imageIcon.getImage());
                    Terre.setLayout(new BorderLayout());
                    labelTerremoto = new JLabel("Magnitude: ");
                    txtTerremoto = new JTextField(10);
                    botaoConfirmarTer = new JButton("Confirmar");

                    Terre.setLayout(new FlowLayout(FlowLayout.RIGHT));
                    Terre.add(labelTerremoto);
                    Terre.add(txtTerremoto);
                    Terre.add(botaoConfirmarTer);
                    Terre.pack();

                    botaoConfirmarTer.addActionListener(event -> {
                        try {
                            double mag = Double.parseDouble(txtTerremoto.getText().trim());
                            Terremoto terremoto = new Terremoto(codigoEvento,dataEvento,latitudeEvento,longitudeEvento,mag);
                            if (appEvento.cadastrarEvento(terremoto)) {
                                textArea1.append("Evento cadastrado!" + "\n");
                            }
                            else {
                                textArea1.append("Erro! Já existe um evento com esse código" + "\n");
                            }
                            Terre.setVisible(false);
                        }
                        catch (NumberFormatException ex) {
                            textArea1.append("Formato inválido para velocidade ou precipitação. Tente novamente." + "\n");
                        }
                    });

                    Terre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    Terre.setVisible(true);


                } else if (tipoEvento == 3) {

                    JFrame Seca = new JFrame();
                    Seca.setSize(300, 150);
                    Seca.setTitle("Seca");
                    ImageIcon imageIcon = new ImageIcon("icon.png");
                    Seca.setLocationRelativeTo(null);
                    Seca.setIconImage(imageIcon.getImage());
                    Seca.setLayout(new BorderLayout());
                    labelSeca = new JLabel("Estiagem: ");
                    txtSeca = new JTextField(10);
                    botaoConfirmarSec = new JButton("Confirmar");

                    Seca.setLayout(new FlowLayout(FlowLayout.RIGHT));
                    Seca.add(labelSeca);
                    Seca.add(txtSeca);
                    Seca.add(botaoConfirmarSec);
                    Seca.pack();

                    botaoConfirmarSec.addActionListener(event -> {
                        try {
                            int est = Integer.parseInt(txtSeca.getText().trim());
                            Seca seca = new Seca(codigoEvento,dataEvento,latitudeEvento,longitudeEvento,est);

                            if (appEvento.cadastrarEvento(seca)) {
                                textArea1.append("Evento cadastrado." + "\n");
                            }
                            else {
                                textArea1.append("Erro! Já existe um evento com esse código" + "\n");
                            }
                            Seca.setVisible(false);

                        }
                        catch (NumberFormatException ex) {
                            textArea1.append("Formato inválido para velocidade ou precipitação. Tente novamente." + "\n");
                        }
                    });

                    Seca.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    Seca.setVisible(true);

                }
            }
        }
        catch (NumberFormatException e){
            textArea1.append("Formato inválido! Tente novamente."+"\n");
        }
    }
    private boolean existeCodigo(String codigo){
        for (Evento evento : appEvento.getEventos()){
            if (evento.getCodigo().equals(codigo)){
                return true;
            }
        }
        return false;
    }
    private void mostrarDados() {

        if (appEvento.getEventos().isEmpty()) {
            textArea1.append("Nenhum evento cadastrado."+"\n");
        } else {
            Collections.sort(appEvento.getEventos());
            for (Evento evento1 : appEvento.getEventos()){
                textArea1.append("Código: "+evento1.getCodigo()+"\n");
                textArea1.append("Data: " +evento1.getData()+"\n");
                textArea1.append("Latitude: "+evento1.getLatitude()+"\n");
                textArea1.append("Longitude: "+evento1.getLongitude()+"\n");

                if (evento1 instanceof Ciclone){
                    Ciclone ciclone = (Ciclone) evento1;
                    textArea1.append("Tipo de evento: Ciclone\n");
                    textArea1.append("Velocidade: "+ciclone.getVelocidade()+ "\n");
                    textArea1.append("Precipitação: "+ciclone.getPrecipitacao()+"\n");
                } else if (evento1 instanceof Terremoto){
                    Terremoto terremoto = (Terremoto) evento1;
                    textArea1.append("Tipo de evento: Terremoto\n");
                    textArea1.append("Magnitude: " + terremoto.getMagnitude() + "\n");
                } else if (evento1 instanceof Seca){
                    Seca seca = (Seca) evento1;
                    textArea1.append("Tipo de evento: Seca\n");
                    textArea1.append("Estiagem: " +seca.getEstiagem()+ "\n");
                }
                textArea1.append("--------------------------"+ "\n");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmarButton){
            cadastrarEvento();}
        else if (e.getSource() == limparButton){
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
            textField5.setText("");
            textArea1.setText("");
        }
        else if (e.getSource() == mostrarDadosButton){
            mostrarDados();
        }
        else if (e.getSource() == finalizarButton){
            acmeRescue.setContentPane(acmeRescue.getPainel());
            acmeRescue.setSize(800, 600);
        }

        else if (e.getSource() == botaoConfirmarCic){
            cadastrarEvento();
        }

    }

    public JPanel getPainel() {
        return painel;
    }
}


