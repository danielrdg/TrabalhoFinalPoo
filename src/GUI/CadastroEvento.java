package GUI;

import dados.Evento;

import javax.swing.*;
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
            if (existeCodigo(codigoEvento)) {
                textArea1.append("Erro! Já existe um evento com esse código."+"\n");
            }
            else {
                evento.add(new Evento(codigoEvento, dataEvento, latitudeEvento, longitudeEvento));
                textArea1.append("Evento cadastrado!"+"\n");
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
            if (evento.isEmpty()){
                textArea1.append("Nenhum evento cadastrado."+"\n");
            }
            else {
                Collections.sort(evento);
                for (Evento evento1:evento){
                    textArea1.append("Código: " + evento1.getCodigo()+"\n");
                    textArea1.append("Data: " + evento1.getData()+"\n");
                    textArea1.append("Latitude: " + evento1.getLatitude()+"\n");
                    textArea1.append("Longitude: " + evento1.getLongitude()+"\n");
                }}
        }
        else if (e.getSource()==finalizarButton){
            System.exit(0);
        }
        else if (e.getSource()==voltarButton){
            new ACMERescue();
        }
    }

}


