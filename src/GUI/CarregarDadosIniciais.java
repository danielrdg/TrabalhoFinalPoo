package GUI;

import app.AppEvento;
import dados.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CarregarDadosIniciais extends JFrame implements ActionListener {
    private JPanel Carrega;
    private JTextField textField1;
    private JButton confirmarButton;
    private JButton voltarButton;
    private AppEvento appEvento;
    private ACMERescue acmeRescue;
    private ArrayList<Evento> eventos = new ArrayList<>();
    private ArrayList<Equipe>equipes = new ArrayList<>();
    private ArrayList<Equipamento>equipamentos = new ArrayList<>();

    public CarregarDadosIniciais(ACMERescue acmeRescue) {
        this.acmeRescue = acmeRescue;
        this.appEvento = acmeRescue.getAppEvento();
        confirmarButton.addActionListener(this);
        voltarButton.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmarButton) {
            carregarDadosIniciais();
        }
        else if(e.getSource() == voltarButton){
            acmeRescue.setContentPane(acmeRescue.getPainel());
            acmeRescue.setSize(800, 600);
        }
    }

    private void carregarDadosIniciais() {
        String nomeArquivo = textField1.getText().trim();
        String arquivoEventos = nomeArquivo + "-EVENTOS.CSV";
        String arquivoEquipe = nomeArquivo + "-EQUIPES.CSV";
        String arquivoEquipamento =nomeArquivo + "-EQUIPAMENTOS.CSV";

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoEventos));
        BufferedReader leitor1 = new BufferedReader(new FileReader(arquivoEquipe));
        BufferedReader leitor2 = new BufferedReader(new FileReader(arquivoEquipamento))) {
            String linhaEvento;
            leitor.readLine();
            while ((linhaEvento = leitor.readLine()) != null) {
                String[] dados = linhaEvento.split(";");
                String codigo = dados[0].trim();
                String data = dados[1].trim();
                double latitude = Double.parseDouble(dados[2].trim());
                double longitude = Double.parseDouble(dados[3].trim());
                int tipoEvento = Integer.parseInt(dados[4].trim());
                Evento evento = null;

                switch (tipoEvento) {
                    case 1:
                        double velocidade = Double.parseDouble(dados[5].trim());
                        double precipitacao = Double.parseDouble(dados[6].trim());
                        evento = new Ciclone(codigo, data, latitude, longitude, velocidade, precipitacao);
                        break;

                    case 2:
                        double magnitude = Double.parseDouble(dados[5].trim());
                        evento = new Terremoto(codigo, data, latitude, longitude, magnitude);

                        break;

                    case 3:
                        int estiagem = Integer.parseInt(dados[5].trim());
                        evento = new Seca(codigo, data, latitude, longitude, estiagem);
                        break;

                    default:
                        JOptionPane.showMessageDialog(this, "Erro! Tipo de evento inválido.");
                }
                if (evento!=null){
                    eventos.add(evento);
                    acmeRescue.getAppEvento().cadastrarEvento(evento);
                }
            }
            String linhaEquipe;
            leitor1.readLine();
            while ((linhaEquipe = leitor1.readLine()) != null) {
                String[] dados = linhaEquipe.split(";");
                String codinome = dados[0].trim();
                int membros = Integer.parseInt(dados[1].trim());
                double latitude = Double.parseDouble(dados[2].trim());
                double longitude = Double.parseDouble(dados[3].trim());
                Equipe equipe = new Equipe(codinome,membros,latitude,longitude);
                equipes.add(equipe);
                acmeRescue.getAppEquipe().cadastrarEquipe(equipe);
            }
            String linhaEquipamento;
            leitor2.readLine();
            while ((linhaEquipamento = leitor2.readLine()) != null) {
                String[] dados = linhaEquipamento.split(";");
                int id = Integer.parseInt(dados[0].trim());
                String nome = dados[1].trim();
                double custo = Double.parseDouble(dados[2].trim());
                String codinom = dados[3].trim();
                int tipoEquipamento = Integer.parseInt(dados[4].trim());
                Equipamento equipamento = null;

                switch (tipoEquipamento) {
                    case 1:
                        int capacidade = Integer.parseInt(dados[5].trim());
                        equipamento = new Barco(id,nome,custo,capacidade);
                        break;

                    case 2:
                        double capacidadeC = Double.parseDouble(dados[5].trim());
                        equipamento = new CaminhaoTanque(id,nome,custo,capacidadeC);

                        break;

                    case 3:
                        String combustivel = dados[5].trim();
                        double carga = Double.parseDouble(dados[6].trim());

                        equipamento = new Escavadeira(id,nome,custo,combustivel,carga);
                        break;

                    default:
                        JOptionPane.showMessageDialog(this, "Erro! Tipo de equipamento inválido.");
                }
                if (equipamento!=null){
                    equipamentos.add(equipamento);
                    acmeRescue.getAppEquipamento().cadastrarEquipamento(equipamento);
                }
            }

            JOptionPane.showMessageDialog(this, "Dados carregados com sucesso.");
            dispose();
        } catch (IOException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados. Verifique o nome do arquivo e o formato.");
            ex.printStackTrace();
        }
    }
    public JPanel getPainel(){
        return Carrega;
    }

}


