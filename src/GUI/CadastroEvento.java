package GUI;

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
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton confirmarButton;
    private JButton limparButton;
    private JButton mostrarDadosButton;
    private JButton finalizarButton;
    private JPanel CadastroE;
    private JTextArea textArea1;
    private JButton voltarButton;
    private JTextField textField5;
    private JLabel labelVelocidade;
    private JTextField txtCiclone;
    private JLabel labelPrecipitacao;
    private JTextField txtPrecipitacao;
    private JButton botaoConfirmarCic;
    private JLabel labelTerremoto;
    private JTextField txtTerremoto;
    private JButton botaoConfirmarTer;
    private JLabel labelSeca;
    private JTextField txtSeca;
    private JButton botaoConfirmarSec;



    private ArrayList<Evento>evento=new ArrayList<>();

    public CadastroEvento(){
        super();
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;
            }}

        confirmarButton.addActionListener(this);
        mostrarDadosButton.addActionListener(this);
        limparButton.addActionListener(this);
        finalizarButton.addActionListener(this);
        voltarButton.addActionListener(this);



        JFrame frame = new JFrame();
        frame.setContentPane(CadastroE);
        frame.setSize(600,400);
        frame.setTitle("ACMERescue");
        ImageIcon imageIcon = new ImageIcon("icon.png");
        frame.setLocationRelativeTo(null);
        frame.setIconImage(imageIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void cadastrarEvento(){
        try {
            String codigoEvento =textField1.getText();
            String dataEvento =textField2.getText();
            double latitudeEvento = Double.parseDouble(textField3.getText());
            double longitudeEvento = Double.parseDouble(textField4.getText());
            int tipoEvento = Integer.parseInt(textField5.getText());


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
                            double vel = Double.parseDouble(txtCiclone.getText());
                            double prec = Double.parseDouble(txtPrecipitacao.getText());
                            evento.add(new Ciclone(codigoEvento, dataEvento, latitudeEvento, longitudeEvento, vel, prec));
                            textArea1.append("Evento cadastrado!" + "\n");
                            Ciclo.setVisible(false);
                        } catch (NumberFormatException ex) {
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
                            double mag = Double.parseDouble(txtTerremoto.getText());
                            evento.add(new Terremoto(codigoEvento, dataEvento, latitudeEvento, longitudeEvento, mag));
                            textArea1.append("Evento cadastrado!" + "\n");
                            Terre.setVisible(false);
                        } catch (NumberFormatException ex) {
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
                            int est = Integer.parseInt(txtSeca.getText());
                            evento.add(new Seca(codigoEvento, dataEvento, latitudeEvento, longitudeEvento, est));
                            textArea1.append("Evento cadastrado!" + "\n");
                            Seca.setVisible(false);
                        } catch (NumberFormatException ex) {
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
        for(Evento eventos: evento){
            if (eventos.getCodigo().equals(codigo)){
                return true;
            }
        }
        return false;
    }
    private void mostrarDados() {
        if (evento.isEmpty()) {
            textArea1.append("Nenhum evento cadastrado."+"\n");
        } else {
            Collections.sort(evento);
            for (Evento evento1 : evento){
                textArea1.append("Código: "+evento1.getCodigo()+"\n");
                textArea1.append("Data: " +evento1.getData()+"\n");
                textArea1.append("Latitude: "+evento1.getLatitude()+"\n");
                textArea1.append("Longitude: "+evento1.getLongitude()+"\n");

                if (evento1 instanceof Ciclone){
                    Ciclone ciclone= (Ciclone) evento1;
                    textArea1.append("Tipo de evento: Ciclone\n");
                    textArea1.append("Velocidade: "+ciclone.getVelocidade()+ "\n");
                    textArea1.append("Precipitação: "+ciclone.getPrecipitacao()+"\n");
                } else if (evento1 instanceof Terremoto){
                    Terremoto terremoto= (Terremoto) evento1;
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
        if (e.getSource()==confirmarButton){
            cadastrarEvento();}
        else if (e.getSource()==limparButton){
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
            textArea1.setText("");
        }
        else if (e.getSource()==mostrarDadosButton){
            mostrarDados();
        }
        else if (e.getSource()==finalizarButton){
            System.exit(0);
        }
        else if (e.getSource()==voltarButton){
            CadastroE.setVisible(false);
        }
        else if (e.getSource() == botaoConfirmarCic){
            cadastrarEvento();
        }

    }

}


