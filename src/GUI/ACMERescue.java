package GUI;

import app.AppAtendimento;
import app.AppEvento;
import dados.Evento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ACMERescue extends JFrame {
    private JButton cadastroEventoButton, cadastroEquipeButton, cadastroAtendimentoButton, mostrarRelatorioButton, cadastroEquipamentoButton;
    private JPanel painel;
    private JButton alterarAtendimentoButton;
    private JButton consultarAtendimentosButton;
    private JButton salvarDadosButton;
    private JButton vincularEquipamentoButton;
    private JButton alocarAtendimentoButton;
    private JButton carregarDadosIniciaisButton;
    private JButton finalizarButton;
    private CadastroEquipe cadastroEquipePainel = new CadastroEquipe(this);
    private CadastroEvento cadastroEventoPainel;
    private CadastroEquipamento cadastroEquipamentoPainel;
    private CadastrarAtendimento cadastrarAtendimento;
    private MostrarEvento mostrarEvento;
    private ImageIcon imageIcon;
    private Evento eventoSelecionado;
    private AppEvento appEvento;
    private AppAtendimento appAtendimento;

    public ACMERescue(AppEvento appEvento, AppAtendimento appAtendimento) {
        super();
        imageIcon = new ImageIcon("icon.png");
        this.appEvento = appEvento;
        this.appAtendimento = appAtendimento;
        this.cadastroEventoPainel = new CadastroEvento(this, appEvento);
        this.mostrarEvento = new MostrarEvento(this);
        this.cadastrarAtendimento = new CadastrarAtendimento(this, mostrarEvento);
        this.cadastroEquipamentoPainel = new CadastroEquipamento(this);
        this.setContentPane(painel);
        this.setSize(800, 600);
        this.setTitle("ACMERescue");
        this.setLocationRelativeTo(null);
        this.setIconImage(imageIcon.getImage());
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setVisible(true);

        cadastroEquipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mudarPainel(2);
            }
        });
        cadastroAtendimentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mudarPainel(4);
            }
        });
        cadastroEventoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mudarPainel(1);
            }
        });
        cadastroEquipamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mudarPainel(3);
            }
        });
        cadastroAtendimentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mudarPainel(5);
            }
        });
        mostrarRelatorioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        vincularEquipamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        alocarAtendimentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        consultarAtendimentosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        alterarAtendimentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        carregarDadosIniciaisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        salvarDadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        finalizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void mudarPainel(int painel) {
        switch (painel) {
            case 1:
                this.setContentPane(cadastroEventoPainel.getPainel());
                this.setSize(800, 400);
                break;

            case 2:
                this.setContentPane(cadastroEquipePainel.getPainel());
                this.setSize(800, 400);
                break;

            case 3:
                this.setContentPane(cadastroEquipamentoPainel.getPainel());
                this.setSize(800, 400);
                break;

            case 4:
                mostrarEvento.atualizarListaEventos();
                eventoSelecionado = mostrarEvento.getEventoSelecionado();
                this.setContentPane(mostrarEvento.getPainel());
                this.setSize(800, 400);
                break;

            case 5:
                cadastrarAtendimento.setEventoSelecionado(eventoSelecionado);
                cadastrarAtendimento.atualizarListaAtendimentos();
                this.setContentPane(cadastrarAtendimento.getPainel());
                this.setSize(800, 400);
                break;
        }
    }

    public JPanel getPainel() {
        return painel;
    }

    public AppEvento getAppEvento() {
        return appEvento;
    }

    public AppAtendimento getAppAtendimento() {
        return appAtendimento;
    }
}
