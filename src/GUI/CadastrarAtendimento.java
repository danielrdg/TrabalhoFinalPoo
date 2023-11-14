    package GUI;

    import app.AppAtendimento;
    import dados.Atendimento;
    import dados.Evento;

    import javax.swing.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.util.ArrayList;

    public class CadastrarAtendimento extends JFrame{
        private JPanel painel;
        private JTextField textField1, textField2, textField3;
        private ArrayList<JTextField> camposDeTexto;
        private JTextArea textArea1;
        private JButton cadastrarButton, limparButton, mostrarDadosButton, finalizarButton;
        private ACMERescue acmeRescue;
        private Evento eventoSelecionado;
        private AppAtendimento appAtendimento = new AppAtendimento();

        public CadastrarAtendimento(ACMERescue acmeRescue, Evento eventoSelecionado){
            this.acmeRescue = acmeRescue;
            this.eventoSelecionado = eventoSelecionado;
            camposDeTexto = new ArrayList<>();
            camposDeTexto.add(textField1);
            camposDeTexto.add(textField2);
            camposDeTexto.add(textField3);

            cadastrarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   try {
                       int cod = Integer.parseInt(textField1.getText().trim());
                       String dataInicio = textField2.getText().trim();
                       int duracao = Integer.parseInt(textField3.getText().trim());
                       String status = "PENDENTE"; //Todos obj atendimento quando instanciado fica no estado pendente.

                       Atendimento atendimento = new Atendimento(cod,dataInicio,duracao,status,eventoSelecionado);

                       if (eventoSelecionado.getAtendimento() != null){
                           textArea1.append("Erro! Esse evento já possuí um atendimento.");
                       }
                       if (appAtendimento.add(atendimento)){
                           textArea1.append("Atendimendo cadastrado com sucesso!");
                       } else {
                           textArea1.append("Erro! Já existe um atendimento com esse código.");
                       }
                   }
                   catch(NumberFormatException ex){
                       textArea1.append("Formato invalido, tente novamente.");
                   }
                }
            });
            limparButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    for (JTextField campoDeTexto : camposDeTexto) {
                        campoDeTexto.setText("");
                    }

                    textArea1.setText("");
                }
            });
            mostrarDadosButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (appAtendimento.getAtendimentosPendentes().isEmpty()){
                        textArea1.append("Nenhum atendimento cadastrado.");
                    } else {
                        for (Atendimento atendimento : appAtendimento.getAtendimentosPendentes()){
                            textArea1.append("Atendimento:\n" + atendimento.toString());
                        }
                    }
                }
            });
            finalizarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    acmeRescue.setContentPane(acmeRescue.getPainel());
                    acmeRescue.setSize(800,400);
                }
            });
        }

        public JPanel getPainel() {
            return painel;
        }
    }
