package GUI;

import app.AppAtendimento;
import app.AppEquipamento;
import app.AppEquipe;
import app.AppEvento;
import dados.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class CarregarDados extends Component implements ActionListener {
    private JTextField textField1;
    private JPanel painel;
    private JButton voltarButton;
    private JButton confirmarButton;
    private JButton escolherArquivosButton;
    private JComboBox comboBox1;
    private AppEvento appEvento;
    private AppEquipe appEquipe;
    private AppEquipamento appEquipamento;
    private AppAtendimento appAtendimento;
    private ACMERescue acmeRescue;

    public CarregarDados(ACMERescue acmeRescue){
        this.acmeRescue = acmeRescue;
        this.appEvento = acmeRescue.getAppEvento();
        this.appEquipe = acmeRescue.getAppEquipe();
        this.appEquipamento = acmeRescue.getAppEquipamento();
        this.appAtendimento = acmeRescue.getAppAtendimento();
        confirmarButton.addActionListener(this);
        voltarButton.addActionListener(this);

    }

    public void carregarDadosSalvos(String nomeArquivo) {
        carregarEventos(nomeArquivo + "-EVENTOS.CSV");
        carregarEquipes(nomeArquivo + "-EQUIPES.CSV");
        carregarEquipamentos(nomeArquivo + "-EQUIPAMENTOS.CSV");
        carregarAtendimentos(nomeArquivo + "-ATENDIMENTOS.CSV");
    }

    private void carregarEventos(String arquivoEventos) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoEventos))) {
            String linha;
            leitor.readLine();

            while ((linha = leitor.readLine()) != null) {
                try (Scanner scanner = new Scanner(linha).useDelimiter(";")) {
                    String codigo = scanner.next();
                    String data = scanner.next();
                    double latitude = scanner.nextDouble();
                    double longitude = scanner.nextDouble();
                    String tipoEvento = scanner.next();

                    Evento evento;

                    switch (tipoEvento) {
                        case "1":
                            double velocidade = scanner.nextDouble();
                            double precipitacao = scanner.nextDouble();
                            evento = new Ciclone(codigo, data, latitude, longitude, velocidade, precipitacao);
                            break;
                        case "2":
                            double magnitude = scanner.nextDouble();
                            evento = new Terremoto(codigo, data, latitude, longitude, magnitude);
                            break;
                        case "3":
                            int estiagem = scanner.nextInt();
                            evento = new Seca(codigo, data, latitude, longitude, estiagem);
                            break;
                        default:
                            throw new IllegalArgumentException("Tipo de evento inválido.");
                    }

                    appEvento.cadastrarEvento(evento);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Erro ao carregar eventos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao ler arquivo de eventos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarEquipes(String arquivoEquipes) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoEquipes))) {
            String linha;
            leitor.readLine();
            while ((linha = leitor.readLine()) != null) {
                try (Scanner scanner = new Scanner(linha).useDelimiter(";")) {
                    String codinome = scanner.next();
                    int membros = scanner.nextInt();
                    double latitude = scanner.nextDouble();
                    double longitude = scanner.nextDouble();

                    Equipe equipe = new Equipe(codinome, membros, latitude, longitude);
                    appEquipe.cadastrarEquipe(equipe);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Erro ao carregar eventos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao ler arquivo de eventos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarEquipamentos(String arquivoEquipamentos) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoEquipamentos))) {
            String linha;
            leitor.readLine();

            while ((linha = leitor.readLine()) != null) {
                try (Scanner scanner = new Scanner(linha).useDelimiter(";")) {
                    String id = scanner.next();
                    String nome = scanner.next();
                    double custo = scanner.nextDouble();
                    String codinome = scanner.next();
                    Equipe equipe = appEquipe.buscarEquipe(codinome);
                    String tipoEquipamento = scanner.next();

                    switch (tipoEquipamento) {
                        case "1":
                            int capacidade = scanner.nextInt();
                            Barco barco = new Barco(Integer.parseInt(id), nome, custo, capacidade);
                            appEquipamento.cadastrarEquipamento(barco);
                            break;
                        case "2":
                            double capacidadeC = scanner.nextDouble();
                            CaminhaoTanque caminhaoTanque = new CaminhaoTanque(Integer.parseInt(id), nome, custo, capacidadeC);
                            caminhaoTanque.setEquipe(equipe);
                            appEquipamento.cadastrarEquipamento(caminhaoTanque);
                            break;
                        case "3":
                            String combustivel = scanner.next();
                            double carga = scanner.nextDouble();
                            Escavadeira escavadeira = new Escavadeira(Integer.parseInt(id), nome, custo, combustivel, carga);
                            escavadeira.setEquipe(equipe);
                            appEquipamento.cadastrarEquipamento(escavadeira);
                            break;
                        default:
                            throw new IllegalArgumentException("Tipo de equipamento inválido.");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Erro ao carregar eventos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao ler arquivo de eventos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void carregarAtendimentos(String arquivoAtendimentos) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoAtendimentos))) {
            String linha;
            leitor.readLine();

            while ((linha = leitor.readLine()) != null) {
                try (Scanner scanner = new Scanner(linha).useDelimiter(";")) {
                    int codStr = scanner.nextInt();
                    String dataIni = scanner.next();
                    int duracaoStr = scanner.nextInt();
                    String status = scanner.next();
                    String codEve = scanner.next();
                    Evento evento = appEvento.buscarEvento(codEve);

                    Atendimento atendimento = new Atendimento(codStr, dataIni, duracaoStr, status, evento);
                    appAtendimento.cadastrarAtendimento(atendimento);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Erro ao carregar eventos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao ler arquivo de eventos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    public JPanel getPainel() {
        return painel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmarButton) {
            carregaDados();
        }
        else if(e.getSource() == voltarButton){
            acmeRescue.setContentPane(acmeRescue.getPainel());
            acmeRescue.setSize(800, 600);
        }
    }
}
