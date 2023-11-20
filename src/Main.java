import GUI.ACMERescue;
import app.AppAtendimento;
import app.AppEquipamento;
import app.AppEquipe;
import app.AppEvento;

public class Main {
    public static void main(String[] args) {
        AppEvento appEvento = new AppEvento();
        AppAtendimento appAtendimento = new AppAtendimento();
        AppEquipe appEquipe = new AppEquipe();
        AppEquipamento appEquipamento = new AppEquipamento();
        ACMERescue acmeRescue = new ACMERescue(appEvento, appAtendimento, appEquipe, appEquipamento);
    }

}
