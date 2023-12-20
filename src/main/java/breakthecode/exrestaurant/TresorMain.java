package breakthecode.exrestaurant;

import com.opencsv.CSVReader;
import utils.logs.FilesUtils;
import utils.logs.Logger;

import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class TresorMain {

    public static String pathToFile;

    static {
        try {
            pathToFile = FilesUtils.getRessourceFile("breakthecode/JDD_tresor_btc");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        List<PointCardinal> points = getAdditionsReadAllLines();
        List<PointCardinal> pointsFinaux = calculerCheminLePLusCourt(points);

        Logger.logInfo("Résultat : " + pointsFinaux);
    }

    private static List<PointCardinal> calculerCheminLePLusCourt(List<PointCardinal> points) {
        List<PointCardinal> pointsFinaux = new ArrayList<>();
        int countN = 0;
        int countE = 0;
        for (PointCardinal pointCourrant : points) {
            if ("N".equals(pointCourrant.getPointCardinal())) {
                countN += pointCourrant.getNbPas();
            } else if ("S".equals(pointCourrant.getPointCardinal())) {
                countN -= pointCourrant.getNbPas();
            } else if ("E".equals(pointCourrant.getPointCardinal())) {
                countE += pointCourrant.getNbPas();
            } else if ("W".equals(pointCourrant.getPointCardinal())) {
                countE -= pointCourrant.getNbPas();
            }
        }
        pointsFinaux.add(new PointCardinal(Math.abs(countN), countN > 0 ? "N" : "S"));
        pointsFinaux.add(new PointCardinal(Math.abs(countE), countE > 0 ? "E" : "W"));
        return pointsFinaux;
    }

    public static List<PointCardinal> getAdditionsReadAllLines() throws Exception {
        List<PointCardinal> additions = new ArrayList<>();

        CSVReader reader = new CSVReader(new FileReader(pathToFile), ',');
        List<String[]> records = reader.readAll();

        String liste = records.get(0)[0];

        for (String pointCourrant : liste.split(" ")) {
            PointCardinal add = new PointCardinal(Integer.parseInt(pointCourrant.substring(0, Math.min(pointCourrant.length(), pointCourrant.length() - 1))), pointCourrant.substring(pointCourrant.length() - 1));
            additions.add(add);
        }

        return additions;
    }
}