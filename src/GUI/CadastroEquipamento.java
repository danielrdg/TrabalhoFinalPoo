package GUI;

import app.AppEquipamento;
import dados.Barco;
import dados.CaminhaoTanque;
import dados.Equipamento;
import dados.Escavadeira;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class CadastroEquipamento extends JFrame implements ActionListener {
    private JPanel painel;
    private JTextField campoID, campoNome, campoCustoDia;
    private JButton confirmarButton, limparButton, mostrarDadosButton, voltarButton;
    private JTextArea textArea1;
    private JTextField campoTipo;
    private ACMERescue acmeRescue;
    private AppEquipamento appEquipamento;

    public CadastroEquipamento(ACMERescue acmeRescue) {
        super();
        this.acmeRescue = acmeRescue;
        confirmarButton.addActionListener(this);
        limparButton.addActionListener(this);
        mostrarDadosButton.addActionListener(this);
        voltarButton.addActionListener(this);
        appEquipamento = new AppEquipamento();
    }


        private void cadastrarEquipamento() {
            try {
                int idEquipamento = Integer.parseInt(campoID.getText().trim());
                String nomeEquipamento = campoNome.getText().trim();
                double custoDia = Double.parseDouble(campoCustoDia.getText().trim());
                int tipo = Integer.parseInt(campoTipo.getText().trim());

                switch (tipo) {
                    case 1:
                        JFrame cadastrarBarco = new JFrame();
                        FlowLayout layoutBarco = new FlowLayout();
                        JPanel container = new JPanel();
                        container.setLayout(layoutBarco);
                        JLabel labelCapacidade = new JLabel("Capacidade de pessoas:");
                        JTextField campoCapacidade = new JTextField(10);
                        JButton botaoConfirmar = new JButton("Confirmar");
                        ImageIcon imageIcon = new ImageIcon("icon.png");
                        cadastrarBarco.setIconImage(imageIcon.getImage());
                        container.add(labelCapacidade);
                        container.add(campoCapacidade);
                        container.add(botaoConfirmar);
                        cadastrarBarco.add(container);
                        cadastrarBarco.setTitle("Cadastro de Barco");
                        cadastrarBarco.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        cadastrarBarco.pack();
                        cadastrarBarco.setVisible(true);
                        botaoConfirmar.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    int capacidade = Integer.parseInt(campoCapacidade.getText().trim());
                                    Barco barco = new Barco (idEquipamento, nomeEquipamento, custoDia, capacidade);
                                    if (appEquipamento.cadastrarEquipamento(barco)) {
                                        textArea1.append("Equipamento cadastrado.\n");
                                        cadastrarBarco.dispose();
                                    }
                                    else {
                                        textArea1.append("Nao foi possivel cadastrar, ja existe um equipamento com esse codigo.\n");
                                    }
                                }
                                catch (NumberFormatException ex) {
                                    textArea1.append("Formato invalido para capacidade, tente novamente.\n");
                                }
                            }
                        });
                        break;

                    case 2:
                        JFrame cadastrarCaminhaoTanque = new JFrame();
                        FlowLayout layoutTanque = new FlowLayout();
                        JPanel containerTanque = new JPanel();
                        containerTanque.setLayout(layoutTanque);
                        JLabel labelCapacidadeCombustivel = new JLabel("Capacidade de Combustivel: ");
                        JTextField campoCapacidadeCombustivel = new JTextField(10);
                        JButton confirmarTanque = new JButton("Confirmar");
                        ImageIcon imageIconTanque = new ImageIcon("icon.png");
                        cadastrarCaminhaoTanque.setIconImage(imageIconTanque.getImage());


                        containerTanque.add(labelCapacidadeCombustivel);
                        containerTanque.add(campoCapacidadeCombustivel);
                        containerTanque.add(confirmarTanque);
                        cadastrarCaminhaoTanque.add(containerTanque);
                        cadastrarCaminhaoTanque.setTitle("Cadastro de Caminhao Tanque");
                        cadastrarCaminhaoTanque.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        cadastrarCaminhaoTanque.pack();
                        cadastrarCaminhaoTanque.setVisible(true);

                        confirmarTanque.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    double capacidadeCombustivel = Double.parseDouble(campoCapacidadeCombustivel.getText().trim());
                                    CaminhaoTanque caminhaoTanque = new CaminhaoTanque(idEquipamento, nomeEquipamento, custoDia, capacidadeCombustivel);

                                    if (appEquipamento.cadastrarEquipamento(caminhaoTanque)) {
                                        textArea1.append("Equipamento cadastrado.\n");
                                        cadastrarCaminhaoTanque.dispose();
                                    }
                                    else {
                                        textArea1.append("Nao foi possivel cadastrar, ja existe um equipamento com esse codigo.\n");
                                    }
                                }

                                catch (NumberFormatException ex) {
                                    textArea1.append("Formato invalido para capacidade, tente novamente.\n");
                                }
                            }
                        });
                        break;

                    case 3:
                        JFrame cadastrarEscavadeira = new JFrame();
                        FlowLayout layoutEscavadeira = new FlowLayout();
                        JPanel containerEscavadeira = new JPanel();
                        containerEscavadeira.setLayout(layoutEscavadeira);
                        JLabel labelCombustivel = new JLabel("Combustivel:\n 1 - Diesel\n 2 - Gasolina\n 3 - Alcool");
                        JTextField campoCombustivel = new JTextField(10);
                        JLabel labelCarga = new JLabel("Carga: ");
                        JTextField campoCarga = new JTextField(10);
                        JButton confirmarEscavadeira = new JButton("Confirmar");
                        ImageIcon imageIconEscavadeira = new ImageIcon("icon.png");
                        cadastrarEscavadeira.setIconImage(imageIconEscavadeira.getImage());

                        containerEscavadeira.add(labelCombustivel);
                        containerEscavadeira.add(campoCombustivel);
                        containerEscavadeira.add(labelCarga);
                        containerEscavadeira.add(campoCarga);
                        containerEscavadeira.add(confirmarEscavadeira);
                        cadastrarEscavadeira.add(containerEscavadeira);
                        cadastrarEscavadeira.setTitle("Cadastro de Escavadeira");
                        cadastrarEscavadeira.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        cadastrarEscavadeira.pack();
                        cadastrarEscavadeira.setVisible(true);

                        confirmarEscavadeira.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    textArea1.setText("");
                                    String combustivel = null;

                                    do {
                                        int tipoCombustivel = Integer.parseInt(campoCombustivel.getText().trim());

                                        switch (tipoCombustivel) {
                                            case 1:
                                                combustivel = "GASOLINA";
                                                break;
                                            case 2:
                                                combustivel = "DIESEL";
                                                break;
                                            case 3:
                                                combustivel = "ÁLCOOL";
                                                break;
                                            default:
                                                JOptionPane.showMessageDialog(null, "Opção inválida. O tipo de combustível deve ser de 1 a 3.");
                                                campoCombustivel.setText("");
                                        }
                                    } while (combustivel == null);

                                    double carga = Double.parseDouble(campoCarga.getText().trim());

                                    Escavadeira escavadeira = new Escavadeira(idEquipamento, nomeEquipamento, custoDia, combustivel, carga);
                                    if (appEquipamento.cadastrarEquipamento(escavadeira)) {
                                        textArea1.append("Equipamento cadastrado.\n");
                                        cadastrarEscavadeira.dispose();
                                    } else {
                                        textArea1.append("Não foi possível cadastrar, já existe um equipamento com esse código.\n");
                                    }
                                } catch (NumberFormatException exception) {
                                    textArea1.append("Formato inválido, tente novamente!\n");
                                }
                            }
                        });
                        break;
                }
            }
                catch (NumberFormatException e) {
                textArea1.append("Formato inválido! Tente novamente."+"\n");
            }
        }


    private void mostrarEquipamentos() {
        if (appEquipamento.getEquipamentos().isEmpty()) {
            textArea1.append("Nenhum equipamento cadastrado." + "\n");
        } else {
            Collections.sort(appEquipamento.getEquipamentos());
            for (Equipamento equipamento : appEquipamento.getEquipamentos()) {
                textArea1.append("ID: " + equipamento.getId() + "\n");
                textArea1.append("Nome: " + equipamento.getNome() + "\n");
                textArea1.append("Custo do Dia: " + "R$"+ equipamento.getCustoDia() + "\n");

                // Verifica o tipo do equipamento
                if (equipamento instanceof Barco) {
                    Barco barco = (Barco) equipamento;
                    textArea1.append("Capacidade: " + barco.getCapacidade() + "\n");
                } else if (equipamento instanceof CaminhaoTanque) {
                    CaminhaoTanque caminhaoTanque = (CaminhaoTanque) equipamento;
                    textArea1.append("Capacidade do Tanque: " + caminhaoTanque.getCapacidadeTanque() + "\n");
                } else if (equipamento instanceof Escavadeira) {
                    Escavadeira escavadeira = (Escavadeira) equipamento;
                    textArea1.append("Combustível: " + escavadeira.getCombustivel() + "\n");
                    textArea1.append("Carga: " + escavadeira.getCarga() + "\n");
                }

            }
        }
    }


    private void limparCampos() {
        campoID.setText("");
        campoNome.setText("");
        campoCustoDia.setText("");
        campoTipo.setText("");
        textArea1.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()== confirmarButton){
            cadastrarEquipamento();
        }
        else if (e.getSource() == limparButton){
            limparCampos();
        }
        else if (e.getSource()==mostrarDadosButton){
            mostrarEquipamentos();
        }
        else if (e.getSource()== voltarButton){
            acmeRescue.setContentPane(acmeRescue.getPainel());
            acmeRescue.setSize(800, 600);
        }
    }

    public JPanel getPainel() {
        return painel;
    }
}


