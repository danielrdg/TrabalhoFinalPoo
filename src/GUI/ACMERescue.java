package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ACMERescue implements ActionListener {

    private JButton ccButton;
    private JButton buttonccButton;
    private JButton ccccButton;
    private JButton ccccButton1;
    private JButton button5;
    private JPanel ACMERescueMenu;
    private JPanel CadastroEvento;
    private JPanel panel1;

    public ACMERescue(){
        JFrame frame = new JFrame();
        frame.setContentPane(ACMERescueMenu);
        frame.setSize(600,400);
        frame.setTitle("ACMERescue");
        ImageIcon imageIcon = new ImageIcon("icon.png");
        frame.setLocationRelativeTo(null);
        frame.setIconImage(imageIcon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ccButton.addActionListener(this);
        buttonccButton.addActionListener(this);
        ccccButton.addActionListener(this);
        ccccButton1.addActionListener(this);
        button5.addActionListener(this);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==ccButton){
            new CadastroEvento();
            ACMERescueMenu.disable();

        }
        else if(e.getSource()==buttonccButton){
            new CadastroEquipe();
        }
    }
}
