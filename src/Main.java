import GUI.ACMERescue;
import app.AppAtendimento;
import app.AppEquipamento;
import app.AppEquipe;
import app.AppEvento;

public class Main {
    public static void main(String[] args) {
        new ACMERescue(new AppEvento(), new AppAtendimento(), new AppEquipe(), new AppEquipamento());
        //new JanelaCarregarDadosEventos();
    }

}
