package GUI;

import dados.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ACMERescue extends JFrame {

    private JButton cadastroEvento, cadastroEquipe, cadastroAtendimento, button5, cadastroEquipamento;
    private JPanel painel,painel1;
    private CadastroEquipe cadastroEquipePainel = new CadastroEquipe(this);
    private CadastroEvento cadastroEventoPainel = new CadastroEvento(this);
    private CadastroEquipamento cadastroEquipamentoPainel = new CadastroEquipamento(this);
    private MostrarEvento mostrarEvento = new MostrarEvento(this);
    private ImageIcon imageIcon;

    public ACMERescue(){
        super();
        this.setContentPane(painel);
        this.setSize(600,400);
        this.setTitle("ACMERescue");
        imageIcon = new ImageIcon("icon.png");
        this.setLocationRelativeTo(null);
        this.setIconImage(imageIcon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        cadastroEquipe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               mudarPainel(2);
            }
        });
        cadastroAtendimento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Evento eventoSelecionado = mostrarEvento.getEventoSelecionado();
                CadastrarAtendimento cadastrarAtendimento = new CadastrarAtendimento(ACMERescue.this, eventoSelecionado);
                mudarPainel(4);
            }
        });
        cadastroEvento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mudarPainel(1);
            }
        });
        cadastroEquipamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mudarPainel(3);
            }
        });
    }

    public void mudarPainel(int painel) {
        switch(painel) {
            case 1:
                this.setContentPane(cadastroEventoPainel.getPainel());
                this.setSize(800,400);
                break;

            case 2:
                this.setContentPane(cadastroEquipePainel.getPainel());
                this.setSize(800,400);
                break;

            case 3:
                this.setContentPane(cadastroEquipamentoPainel.getPainel());
                this.setSize(800,400);
                break;

            case 4:
                Evento eventoSelecionado = mostrarEvento.getEventoSelecionado();
                this.setContentPane(mostrarEvento.getPainel());
                this.setSize(800,400);
                break;
        }

    }

    public JPanel getPainel() {
        return painel;
    }
}

