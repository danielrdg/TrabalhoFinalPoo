package GUI;

import dados.Equipamento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CadastroEquipamento {

    private JFrame frame;
    private JTextField campoId;
    private JTextField campoNome;
    private JTextField campoCusto;
    private JTextArea areaEquipamentos;
    private ArrayList<Equipamento> equipamentos;

    public CadastroEquipamento() {

        equipamentos = new ArrayList<>();

        frame = new JFrame("Cadastro de Equipamentos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 310);
        frame.setResizable(false);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));

        campoId = new JTextField(10);
        campoNome = new JTextField(10);
        campoCusto = new JTextField(10);
        areaEquipamentos = new JTextArea(10, 30);
        areaEquipamentos.setEditable(false);

        JButton botaoCadastrar = new JButton("Cadastrar Equipamento");
        JButton botaoVerEquipamentos = new JButton("Ver Equipamentos Salvos");
        JButton botaoLimparCampos = new JButton("Limpar Campos");
        JButton botaoLimparArea = new JButton("Limpar Console");
        JButton botaoSair = new JButton("Sair do Programa");

        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarEquipamento();
            }
        });

        botaoVerEquipamentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarEquipamentos();
            }
        });

        botaoLimparCampos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        botaoLimparArea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparArea();
            }
        });

        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sairDoPrograma();
            }
        });

        frame.add(new JLabel("ID: "));
        frame.add(campoId);
        frame.add(new JLabel("Equipamento: "));
        frame.add(campoNome);
        frame.add(new JLabel("Custo por dia: "));
        frame.add(campoCusto);
        frame.add(botaoCadastrar);
        frame.add(botaoVerEquipamentos);
        frame.add(botaoLimparCampos);
        frame.add(botaoLimparArea);
        frame.add(botaoSair);

        frame.add(new JScrollPane(areaEquipamentos));
        frame.setVisible(true);
    }

    private void cadastrarEquipamento() {
        try {
            int id = Integer.parseInt(campoId.getText());
            String nome = campoNome.getText();
    
            if (!nome.matches("^[a-zA-Z]*$")) {
                throw new NumberFormatException();
            }
    
            double custo = Double.parseDouble(campoCusto.getText());
    
            Equipamento equipamento = new Equipamento(id, nome, custo);
            equipamentos.add(equipamento);
    
            appendTextoArea("Equipamento cadastrado com sucesso!", Color.GREEN);
            limparCampos();
        } catch (NumberFormatException ex) {
            appendTextoArea("Insira valores válidos.", Color.RED);
        }
    }
    

    private void mostrarEquipamentos() {
        if (equipamentos.isEmpty()) {
            appendTextoArea("Nenhum equipamento cadastrado.", Color.BLUE);
        } else {
            StringBuilder listaEquipamentos = new StringBuilder("Equipamentos Salvos:\n");

            for (Equipamento equipamento : equipamentos) {
                listaEquipamentos.append(equipamento.toString()).append("\n");
            }

            appendTextoArea(listaEquipamentos.toString(), Color.BLACK);
        }
    }

    private void appendTextoArea(String texto, Color cor) {
        limparArea();
        areaEquipamentos.append(texto + "\n");
        areaEquipamentos.setForeground(cor);
    }

    private void limparCampos() {
        campoId.setText("");
        campoNome.setText("");
        campoCusto.setText("");
    }

    private void limparArea() {
        areaEquipamentos.setText("");
    }

    private void sairDoPrograma() {
        System.exit(0);
    }

}