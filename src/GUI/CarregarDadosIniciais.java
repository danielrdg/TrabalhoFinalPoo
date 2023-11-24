package GUI;

import app.AppEvento;
import dados.Ciclone;
import dados.Seca;
import dados.Terremoto;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CarregarDadosIniciais extends JFrame implements ActionListener {
    private JPanel Carrega;
    private JTextField textField1;
    private JTextArea textArea1;
    private JButton confirmarButton;
    private JButton voltarButton;
    private AppEvento appEvento;
    private ACMERescue acmeRescue;

    public CarregarDadosIniciais(ACMERescue acmeRescue) {
        this.acmeRescue = acmeRescue;
        this.appEvento = acmeRescue.getAppEvento();
        confirmarButton.addActionListener(this);
        voltarButton.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmarButton) {
            carregarDadosEventos();
        }
        else if(e.getSource() == voltarButton){
            acmeRescue.setContentPane(acmeRescue.getPainel());
            acmeRescue.setSize(800, 600);
        }
    }

    private void carregarDadosEventos() {
        String nomeArquivo = textField1.getText().trim();
        String arquivoEventos = nomeArquivo + "-EVENTOS.CSV";

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoEventos))) {
            String linha;
            leitor.readLine();
            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");
                String codigo = dados[0].trim();
                String data = dados[1].trim();
                double latitude = Double.parseDouble(dados[2].trim());
                double longitude = Double.parseDouble(dados[3].trim());
                int tipoEvento = Integer.parseInt(dados[4].trim());

                switch (tipoEvento) {
                    case 1:
                        double velocidade = Double.parseDouble(dados[5].trim());
                        double precipitacao = Double.parseDouble(dados[6].trim());
                        Ciclone ciclone = new Ciclone(codigo, data, latitude, longitude, velocidade, precipitacao);
                        break;

                    case 2:
                        double magnitude = Double.parseDouble(dados[5].trim());
                        Terremoto terremoto = new Terremoto(codigo, data, latitude, longitude, magnitude);

                        break;

                    case 3:
                        int estiagem = Integer.parseInt(dados[5].trim());
                        Seca seca = new Seca(codigo, data, latitude, longitude, estiagem);
                        break;

                    default:
                        JOptionPane.showMessageDialog(this, "Erro! Tipo de evento inválido.");
                }
            }

            JOptionPane.showMessageDialog(this, "Dados de eventos carregados com sucesso.");
            dispose();
        } catch (IOException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados de eventos. Verifique o nome do arquivo e o formato.");
            ex.printStackTrace();
        }
    }
    public JPanel getPainel(){
        return Carrega;
    }

}


