import GUI.ACMERescue;
import app.AppAtendimento;
import app.AppEvento;

public class Main {
    public static void main(String[] args) {
        AppEvento appEvento = new AppEvento();
        AppAtendimento appAtendimento = new AppAtendimento();
        ACMERescue acmeRescue = new ACMERescue(appEvento, appAtendimento);
    }

}
