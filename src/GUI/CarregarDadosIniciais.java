package GUI;

import app.AppAtendimento;
import app.AppEquipamento;
import app.AppEquipe;
import app.AppEvento;
import dados.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CarregarDadosIniciais extends JFrame implements ActionListener {
    private JPanel Carrega;
    private JTextField textField1;
    private JButton confirmarButton;
    private JButton voltarButton;
    private JTextArea textArea;
    private AppEvento appEvento;
    private AppEquipe appEquipe;
    private AppEquipamento appEquipamento;
    private AppAtendimento appAtendimento;
    private ACMERescue acmeRescue;

    public CarregarDadosIniciais(ACMERescue acmeRescue) {
        this.acmeRescue = acmeRescue;
        this.appEvento = acmeRescue.getAppEvento();
        this.appEquipe = acmeRescue.getAppEquipe();
        this.appEquipamento = acmeRescue.getAppEquipamento();
        this.appAtendimento = acmeRescue.getAppAtendimento();
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
        String arquivoAtendimento = nomeArquivo + "-ATENDIMENTOS.CSV";

        ArrayList<Evento>eventos = appEvento.getEventos();
        ArrayList<Equipe>equipes = appEquipe.getEquipes();
        ArrayList<Equipamento>equipamentos = appEquipamento.getEquipamentos();
        Queue<Atendimento>atendimentos = appAtendimento.getAtendimentosPendentes();

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivoEventos));
        BufferedReader leitor1 = new BufferedReader(new FileReader(arquivoEquipe));
        BufferedReader leitor2 = new BufferedReader(new FileReader(arquivoEquipamento));
        BufferedReader leitor3 = new BufferedReader(new FileReader(arquivoAtendimento))) {

            String linhaEvento;
            leitor.readLine();
            while ((linhaEvento = leitor.readLine()) != null) {
                Scanner dados = new Scanner(linhaEvento).useDelimiter(";");
                String codigo = dados.next();
                String data = dados.next();
                String latitude = dados.next();
                String longitude = dados.next();
                String tipoEvento = dados.next();
                if (appEvento.existeCodigo(codigo)) {
                    mostrarErro("Erro! Já existe um evento com esse código.");
                } else {

                if (tipoEvento.equals("1")) {
                    String velocidade = dados.next();
                    String precipitacao = dados.next();
                    Ciclone ciclone = new Ciclone(codigo, data, Double.parseDouble(latitude), Double.parseDouble(longitude), Double.parseDouble(velocidade), Double.parseDouble(precipitacao));
                    appEvento.cadastrarEvento(ciclone);
                }

                else if (tipoEvento.equals("2")){
                    String magnitude = dados.next();
                    Terremoto terremoto = new Terremoto(codigo, data, Double.parseDouble(latitude), Double.parseDouble(longitude), Double.parseDouble(magnitude));
                    appEvento.cadastrarEvento(terremoto);
                }
                else if (tipoEvento.equals("3")){
                    String estiagem = dados.next();
                    Seca seca = new Seca(codigo, data, Double.parseDouble(latitude), Double.parseDouble(longitude), Integer.parseInt(estiagem));
                    appEvento.cadastrarEvento(seca);
                }
                else {
                    mostrarErro("Erro! Tipo de evento inválido.");
                }
            }}
            String linhaEquipe;
            leitor1.readLine();
            while ((linhaEquipe = leitor1.readLine()) != null) {
                Scanner dados = new Scanner(linhaEquipe).useDelimiter(";");
                String codinome = dados.next();
                String membros = dados.next();
                String latitude = dados.next();
                String longitude = dados.next();
                Equipe equipe = new Equipe(codinome,Integer.parseInt(membros),Double.parseDouble(latitude),Double.parseDouble(longitude));
                equipes.add(equipe);
                acmeRescue.getAppEquipe().cadastrarEquipe(equipe);
            }

            String linhaEquipamento;
            leitor2.readLine();
            while ((linhaEquipamento = leitor2.readLine()) != null) {
                Scanner dados = new Scanner(linhaEquipamento).useDelimiter(";");
                String nome = dados.next();
                String custo = dados.next();
                String id = dados.next();
                String tipoEquipamento = dados.next();

                if (tipoEquipamento.equals("1")){
                    String capacidade = dados.next();
                    Barco barco = new Barco(Integer.parseInt(id),nome,Double.parseDouble(custo),Integer.parseInt(capacidade));
                    appEquipamento.cadastrarEquipamento(barco);
                }

                else if (tipoEquipamento.equals("2")){
                    String capacidadeC = dados.next();
                    CaminhaoTanque caminhaoTanque= new CaminhaoTanque(Integer.parseInt(id),nome,Double.parseDouble(custo),Double.parseDouble(capacidadeC));
                    appEquipamento.cadastrarEquipamento(caminhaoTanque);
                }

                else if (tipoEquipamento.equals("3")){
                    String combustivel = dados.next();
                    String carga = dados.next();
                    Escavadeira escavadeira = new Escavadeira(Integer.parseInt(id),nome,Double.parseDouble(custo),combustivel,Double.parseDouble(carga));
                    appEquipamento.cadastrarEquipamento(escavadeira);
                }
            }
            String linhaAtendimento;
            leitor3.readLine();
            while ((linhaAtendimento = leitor3.readLine()) != null) {
                Scanner dados = new Scanner(linhaAtendimento).useDelimiter(";");
                String codStr = dados.next();
                String dataIni = dados.next();
                String duracaoStr = dados.next();
                String status = dados.next();
                String codEve = dados.next();
                Evento evento = appEvento.buscarEvento(codEve);

                Atendimento atendimento = new Atendimento(Integer.parseInt(codStr), dataIni, Integer.parseInt(duracaoStr), status,evento);
                atendimentos.add(atendimento);
                acmeRescue.getAppAtendimento().cadastrarAtendimento(atendimento);
                }
            textArea.append("Eventos cadastrados:\n");
            for (Evento evento : eventos) {
                textArea.append(evento.toString() + "\n");
            }
            textArea.append("----------------------------\n");
            textArea.append("\nEquipes cadastradas:\n");
            for (Equipe equipe : equipes) {
                textArea.append(equipe.toString() + "\n");
            }
            textArea.append("----------------------------\n");
            textArea.append("\nEquipamentos cadastrados:\n");
            for (Equipamento equipamento : equipamentos) {
                textArea.append(equipamento.toString() + "\n");
            }
            textArea.append("----------------------------\n");
            textArea.append("\nAtendimentos cadastrados:\n");
            for (Atendimento atendimento : atendimentos) {
                textArea.append(atendimento.toString() + "\n");
            }
            textArea.append("----------------------------\n");

            mostrarMensagem("Dados carregados com sucesso!");
            dispose();
        } catch (IOException | NumberFormatException ex) {
            mostrarErro("Erro ao carregar dados. Verifique o nome do arquivo e o formato.");
            ex.printStackTrace();
        }
    }
    public JPanel getPainel(){
        return Carrega;
    }

    private void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }


}


