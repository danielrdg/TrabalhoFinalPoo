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
    private JTextArea areaTexto;
    private JPanel painel;
    private JTextField campoID, campoNome, campoCustoDia, campoTipo;
    private JButton confirmarButton, limparButton, mostrarDadosButton, voltarButton;
    private JLabel labelCaminhao;
    private JLabel labelCaminhaoTanque;
    private JLabel campoEscavadeira;
    private ACMERescue acmeRescue;
    private AppEquipamento appEquipamento;

    public CadastroEquipamento(ACMERescue acmeRescue) {
        super();
        this.acmeRescue = acmeRescue;
        this.appEquipamento = acmeRescue.getAppEquipamento();
        confirmarButton.addActionListener(this);
        limparButton.addActionListener(this);
        mostrarDadosButton.addActionListener(this);
        voltarButton.addActionListener(this);
    }

    private void cadastrarEquipamento() {

        if (campoID.getText().equals("") || campoNome.getText().equals("") || campoCustoDia.getText().equals("") || campoTipo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Erro! Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

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
                    cadastrarBarco.setLocationRelativeTo(null);
                    cadastrarBarco.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    cadastrarBarco.pack();
                    cadastrarBarco.setVisible(true);
                    botaoConfirmar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                int capacidade = Integer.parseInt(campoCapacidade.getText().trim());
                                Barco barco = new Barco(idEquipamento, nomeEquipamento, custoDia, capacidade);

                                if (appEquipamento.cadastrarEquipamento(barco)) {
                                    JOptionPane.showMessageDialog(null, "Equipamento cadastrado com sucesso.");
                                    cadastrarBarco.dispose();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro! Já existe um equipamento com esse código.", "Erro", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Erro! Formato inválido para capacidade.", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                    break;

                case 2:
                    JFrame cadastrarCaminhaoTanque = new JFrame();
                    FlowLayout layoutTanque = new FlowLayout();
                    JPanel containerTanque = new JPanel();
                    containerTanque.setLayout(layoutTanque);
                    JLabel labelCapacidadeCombustivel = new JLabel("Capacidade de Combustível: ");
                    JTextField campoCapacidadeCombustivel = new JTextField(10);
                    JButton confirmarTanque = new JButton("Confirmar");
                    ImageIcon imageIconTanque = new ImageIcon("icon.png");
                    cadastrarCaminhaoTanque.setIconImage(imageIconTanque.getImage());
                    containerTanque.add(labelCapacidadeCombustivel);
                    containerTanque.add(campoCapacidadeCombustivel);
                    containerTanque.add(confirmarTanque);
                    cadastrarCaminhaoTanque.add(containerTanque);
                    cadastrarCaminhaoTanque.setLocationRelativeTo(null);
                    cadastrarCaminhaoTanque.setTitle("Cadastro de Caminhão Tanque");
                    cadastrarCaminhaoTanque.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    cadastrarCaminhaoTanque.pack();
                    cadastrarCaminhaoTanque.setVisible(true);

                    confirmarTanque.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                double capacidadeCombustivel = Double.parseDouble(campoCapacidadeCombustivel.getText().trim());
                                CaminhaoTanque caminhaoTanque = new CaminhaoTanque(idEquipamento, nomeEquipamento, custoDia, capacidadeCombustivel);

                                if (appEquipamento.cadastrarEquipamento(caminhaoTanque)) {
                                    JOptionPane.showMessageDialog(null, "Equipamento cadastrado com sucesso.");
                                    cadastrarCaminhaoTanque.dispose();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro! Já existe um equipamento com esse código.", "Erro", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Erro! Formato inválido para capacidade.", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                    break;

                case 3:
                    JFrame cadastrarEscavadeira = new JFrame();
                    FlowLayout layoutEscavadeira = new FlowLayout();
                    JPanel containerEscavadeira = new JPanel();
                    containerEscavadeira.setLayout(layoutEscavadeira);
                    JLabel labelCombustivel = new JLabel("Combustível:\n 1 - Diesel\n 2 - Gasolina\n 3 - Álcool");
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
                    cadastrarEscavadeira.setLocationRelativeTo(null);
                    cadastrarEscavadeira.setTitle("Cadastro de Escavadeira");
                    cadastrarEscavadeira.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                    cadastrarEscavadeira.pack();
                    cadastrarEscavadeira.setVisible(true);

                    confirmarEscavadeira.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
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
                                            JOptionPane.showMessageDialog(null, "Erro! O tipo deve ser de 1 a 3.", "Erro", JOptionPane.ERROR_MESSAGE);
                                            campoCombustivel.setText("");
                                    }
                                } while (combustivel == null);

                                double carga = Double.parseDouble(campoCarga.getText().trim());

                                Escavadeira escavadeira = new Escavadeira(idEquipamento, nomeEquipamento, custoDia, combustivel, carga);
                                if (appEquipamento.cadastrarEquipamento(escavadeira)) {
                                    JOptionPane.showMessageDialog(null, "Equipamento cadastrado com sucesso.");
                                    cadastrarEscavadeira.dispose();

                                } else {
                                    JOptionPane.showMessageDialog(null, "Erro! Já existe um equipamento com esse código.", "Erro", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NumberFormatException exception) {
                                JOptionPane.showMessageDialog(null, "Erro! Formato inválido para carga.", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });
                    break;

                default:
                    JOptionPane.showMessageDialog(this, "Erro! Escolha um tipo de 1 a 3.", "Erro", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro! Formato inválido para ID ou custo.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarEquipamentos() {
        if (appEquipamento.getEquipamentos().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Erro! Nenhum equipamento cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            Collections.sort(appEquipamento.getEquipamentos());
            for (Equipamento equipamento : appEquipamento.getEquipamentos()) {
                StringBuilder infoEquipamento = new StringBuilder();
                infoEquipamento.append("ID: ").append(equipamento.getId()).append("\n");
                infoEquipamento.append("Nome: ").append(equipamento.getNome()).append("\n");
                infoEquipamento.append("Custo do Dia: ").append("R$").append(equipamento.getCustoDia()).append("\n");

                infoEquipamento.append("Tipo: ");
                if (equipamento instanceof Barco) {
                    infoEquipamento.append("Barco\n");
                    Barco barco = (Barco) equipamento;
                    infoEquipamento.append("Capacidade: ").append(barco.getCapacidade()).append("\n");
                } else if (equipamento instanceof CaminhaoTanque) {
                    infoEquipamento.append("Caminhão Tanque\n");
                    CaminhaoTanque caminhaoTanque = (CaminhaoTanque) equipamento;
                    infoEquipamento.append("Capacidade do Tanque: ").append(caminhaoTanque.getCapacidadeTanque()).append("\n");
                } else if (equipamento instanceof Escavadeira) {
                    infoEquipamento.append("Escavadeira\n");
                    Escavadeira escavadeira = (Escavadeira) equipamento;
                    infoEquipamento.append("Combustível: ").append(escavadeira.getCombustivel()).append("\n");
                    infoEquipamento.append("Carga: ").append(escavadeira.getCarga()).append("\n");
                }

                infoEquipamento.append("--------------------------").append("\n");
                for (Equipamento e : appEquipamento.getEquipamentos()) {
                    areaTexto.append(infoEquipamento.toString());
                }
            }
        }
    }

    private void limparCampos() {
        campoID.setText("");
        campoNome.setText("");
        campoCustoDia.setText("");
        campoTipo.setText("");
        areaTexto.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmarButton) {
            cadastrarEquipamento();
        } else if (e.getSource() == limparButton) {
            limparCampos();
        } else if (e.getSource() == mostrarDadosButton) {
            mostrarEquipamentos();
        } else if (e.getSource() == voltarButton) {
            acmeRescue.setContentPane(acmeRescue.getPainel());
            acmeRescue.setSize(800, 600);
        }
    }

    public JPanel getPainel() {
        return painel;
    }

}
