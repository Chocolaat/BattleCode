package breakthecode.exrestaurant;

import com.opencsv.CSVReader;
import utils.logs.FilesUtils;
import utils.logs.Logger;

import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.List;

public class CannelesMain {

    public static String pathToFile;

    public static int activationsMax;
    public static int totalAttendu;
    public static int bouton1;
    public static int bouton2;
    public static int bouton3;

    static {
        try {
            pathToFile = FilesUtils.getRessourceFile("breakthecode/JDD_Parametrages_btc");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        getAdditionsReadAllLines();
        calculerNbActivations3();
    }

    private static void calculerNbActivations3() {
        for(int i = 0; i <= activationsMax; i ++) {
            for(int j = 0; j <= activationsMax; j ++) {
                for(int k = 0; k <= activationsMax; k ++) {
                    if ((bouton1 * i + bouton2 * j + bouton3 * k) == totalAttendu){
                        Logger.logInfo("Résultat : " + i + " " + j + " " + k);
                    }
                }
            }
        }
    }

    public static void getAdditionsReadAllLines() throws Exception {
        CSVReader reader = new CSVReader(new FileReader(pathToFile), ',');
        List<String[]> records = reader.readAll();
        for (int i = 0; i < records.size(); i +=5) {
            bouton1 = Integer.parseInt(records.get(i)[0]);
            bouton2 = Integer.parseInt(records.get(i+1)[0]);
            bouton3 = Integer.parseInt(records.get(i+2)[0]);
            activationsMax = Integer.parseInt(records.get(i+3)[0]);
            totalAttendu = Integer.parseInt(records.get(i+4)[0]);
        }
    }
}