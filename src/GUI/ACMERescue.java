package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ACMERescue extends JFrame {

    private JButton cadastroEvento;
    private JButton cadastroEquipe;
    private JButton cadastroEquipamento;
    private JButton ccccButton1;
    private JButton button5;
    private JPanel painel;
    private JPanel CadastroEvento;
    private JPanel panel1;
    private CadastroEquipe cadastroEquipePainel = new CadastroEquipe(this);

    public ACMERescue(){
        super();
        this.setContentPane(painel);
        this.setSize(600,400);
        this.setTitle("ACMERescue");
        ImageIcon imageIcon = new ImageIcon("icon.png");
        this.setLocationRelativeTo(null);
        this.setIconImage(imageIcon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        cadastroEquipe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               mudarPainel(1);
            }
        });
    }

    public void mudarPainel(int painel) {
        switch(painel) {
            case 1:
                this.setContentPane(cadastroEquipePainel.getPainel());
                this.setSize(800,400);
                break;
        }

    }

    public JPanel getPainel() {
        return painel;
    }
}

