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

    public void carregaDados(){
        String nomeArquivo = textField1.getText().trim();
        String tipoArquivoSelecionado = (String) comboBox1.getSelectedItem();
        String arquivoSelecionado = nomeArquivo + "-" + tipoArquivoSelecionado + ".CSV";

        ArrayList<Evento> eventos = appEvento.getEventos();
        ArrayList<Equipe>equipes = appEquipe.getEquipes();
        ArrayList<Equipamento>equipamentos = appEquipamento.getEquipamentos();
        Queue<Atendimento> atendimentos = appAtendimento.getAtendimentosPendentes();

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoSelecionado)))
        {
            switch (tipoArquivoSelecionado) {
                case "EVENTOS":
                    String linhaEvento;
                    leitor.readLine();
                    while ((linhaEvento = leitor.readLine()) != null) {
                        Scanner dados = new Scanner(linhaEvento).useDelimiter(";");
                        String codigo = dados.next();
                        String data = dados.next();
                        String latitude = dados.next();
                        String longitude = dados.next();
                        String tipoEvento = dados.next();
                        if (tipoEvento.equals("1")) {
                            String velocidade = dados.next();
                            String precipitacao = dados.next();
                            Ciclone ciclone = new Ciclone(codigo, data, Double.parseDouble(latitude), Double.parseDouble(longitude), Double.parseDouble(velocidade), Double.parseDouble(precipitacao));
                            appEvento.cadastrarEvento(ciclone);
                        } else if (tipoEvento.equals("2")) {
                            String magnitude = dados.next();
                            Terremoto terremoto = new Terremoto(codigo, data, Double.parseDouble(latitude), Double.parseDouble(longitude), Double.parseDouble(magnitude));
                            appEvento.cadastrarEvento(terremoto);
                        } else if (tipoEvento.equals("3")) {
                            String estiagem = dados.next();
                            Seca seca = new Seca(codigo, data, Double.parseDouble(latitude), Double.parseDouble(longitude), Integer.parseInt(estiagem));
                            appEvento.cadastrarEvento(seca);
                        } else {
                            JOptionPane.showMessageDialog(this, "Erro! Tipo de evento inválido.");
                        }
                    }
                    break;

                case "EQUIPES":
                    String linhaEquipe;
                    leitor.readLine();
                    while ((linhaEquipe = leitor.readLine()) != null) {
                        Scanner dados = new Scanner(linhaEquipe).useDelimiter(";");
                        String codinome = dados.next();
                        String membros = dados.next();
                        String latitude = dados.next();
                        String longitude = dados.next();
                        Equipe equipe = new Equipe(codinome, Integer.parseInt(membros), Double.parseDouble(latitude), Double.parseDouble(longitude));
                        equipes.add(equipe);
                        acmeRescue.getAppEquipe().cadastrarEquipe(equipe);
                    }
                    break;

                case "EQUIPAMENTOS":
                    String linhaEquipamento;
                    leitor.readLine();
                    while ((linhaEquipamento = leitor.readLine()) != null) {
                        Scanner dados = new Scanner(linhaEquipamento).useDelimiter(";");
                        String id = dados.next();
                        String nome = dados.next();
                        String custo = dados.next();
                        String tipoEquipamento = dados.next();

                        if (tipoEquipamento.equals("1")) {
                            String capacidade = dados.next();
                            Barco barco = new Barco(Integer.parseInt(id), nome, Double.parseDouble(custo), Integer.parseInt(capacidade));
                            appEquipamento.cadastrarEquipamento(barco);
                        } else if (tipoEquipamento.equals("2")) {
                            String capacidadeC = dados.next();
                            CaminhaoTanque caminhaoTanque = new CaminhaoTanque(Integer.parseInt(id), nome, Double.parseDouble(custo), Double.parseDouble(capacidadeC));
                            appEquipamento.cadastrarEquipamento(caminhaoTanque);
                        } else if (tipoEquipamento.equals("3")) {
                            String combustivel = dados.next();
                            String carga = dados.next();
                            Escavadeira escavadeira = new Escavadeira(Integer.parseInt(id), nome, Double.parseDouble(custo), combustivel, Double.parseDouble(carga));
                            appEquipamento.cadastrarEquipamento(escavadeira);
                        }
                    }
                    break;

                case "ATENDIMENTOS":
                    String linhaAtendimento;
                    leitor.readLine();
                    while ((linhaAtendimento = leitor.readLine()) != null) {
                        Scanner dados = new Scanner(linhaAtendimento).useDelimiter(";");
                        String codStr = dados.next();
                        String dataIni = dados.next();
                        String duracaoStr = dados.next();
                        String status = dados.next();
                        String codEve = dados.next();
                        Evento evento = appEvento.buscarEvento(codEve);

                        Atendimento atendimento = new Atendimento(Integer.parseInt(codStr), dataIni, Integer.parseInt(duracaoStr), status, evento);
                        atendimentos.add(atendimento);
                        acmeRescue.getAppAtendimento().cadastrarAtendimento(atendimento);
                    }
                    break;
            }
        } catch (IOException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados. Verifique o nome do arquivo e o formato.");
            ex.printStackTrace();
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
