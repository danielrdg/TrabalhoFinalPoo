package GUI;

import app.AppAtendimento;
import app.AppEquipamento;
import app.AppEquipe;
import app.AppEvento;
import dados.Evento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ACMERescue extends JFrame {
    private JButton cadastroEventoButton, cadastroEquipeButton, cadastroAtendimentoButton, mostrarRelatorioButton, cadastroEquipamentoButton, vincularEquipamentoButton, alocarAtendimentoButton, consultarAtendimentosButton, alterarAtendimentoButton, carregarDadosIniciaisButton, salvarDadosButton, finalizarButton;
    private JPanel painel;
    private CadastroEquipe cadastroEquipePainel;
    private CadastroEvento cadastroEventoPainel;
    private CadastroEquipamento cadastroEquipamentoPainel;
    private CadastrarAtendimento cadastrarAtendimento;
    private MostrarEvento mostrarEvento;
    private ImageIcon imageIcon;
    private Evento eventoSelecionado;
    private AppEvento appEvento;
    private AppAtendimento appAtendimento;
    private RelatorioGeral relatorioGeral;
    private AppEquipe appEquipe;
    private AppEquipamento appEquipamento;
    private VincularEquipamento vincularEquipamento;
    private AlocarAtendimento alocarAtendimento;
    private CarregarDadosIniciais carregarDadosIniciais;
    private AlocarAtendimentos alocarAtendimentos;

    public ACMERescue(AppEvento appEvento, AppAtendimento appAtendimento, AppEquipe appEquipe, AppEquipamento appEquipamento) {
        super();
        imageIcon = new ImageIcon("icon.png");
        this.appEvento = appEvento;
        this.appAtendimento = appAtendimento;
        this.appEquipe = appEquipe;
        this.appEquipamento = appEquipamento;
        this.cadastroEventoPainel = new CadastroEvento(this);
        this.mostrarEvento = new MostrarEvento(this);
        this.cadastrarAtendimento = new CadastrarAtendimento(this, mostrarEvento);
        this.cadastroEquipamentoPainel = new CadastroEquipamento(this);
        this.cadastroEquipePainel = new CadastroEquipe(this);
        this.relatorioGeral = new RelatorioGeral(this);
        this.vincularEquipamento = new VincularEquipamento(this);
        //this.alocarAtendimento = new AlocarAtendimento(this);
        this.carregarDadosIniciais = new CarregarDadosIniciais(this);
        this.alocarAtendimentos = new AlocarAtendimentos(this);
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
                mudarPainel(6);
            }
        });
        vincularEquipamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vincularEquipamento.atualizarExibicao();
                mudarPainel(7);
            }
        });
        alocarAtendimentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mudarPainel(8);
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
                mudarPainel(9);
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

            case 6:
                this.setContentPane(relatorioGeral.getPainel());
                this.setSize(800, 400);
                break;

            case 7:
                this.setContentPane(vincularEquipamento.getPainel());
                this.setSize(800, 400);
                break;

            case 8:
                this.setContentPane(alocarAtendimentos.getPainel());
                this.setSize(800,400);
                break;

            case 9:
                this.setContentPane(carregarDadosIniciais.getPainel());
                this.setSize(800,400);
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

    public AppEquipamento getAppEquipamento() {
        return appEquipamento;
    }

    public AppEquipe getAppEquipe() {
        return appEquipe;
    }
}
