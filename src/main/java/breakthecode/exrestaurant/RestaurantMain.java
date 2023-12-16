package breakthecode.exrestaurant;

import com.opencsv.CSVReader;
import utils.logs.FilesUtils;
import utils.logs.Logger;

import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RestaurantMain {

    public static String pathToFile;

    static {
        try {
            pathToFile = FilesUtils.getRessourceFile("breakthecode/Restaurant.csv");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        List<Addition> additions = getAdditionsReadAllLines();

        for (Addition addition : additions) {
            Logger.logInfo(addition.total() + "");
        }
    }

    public static List<Addition> getAdditionsReadAllLines() throws Exception {
        List<Addition> additions = new ArrayList<>();

        CSVReader reader = new CSVReader(new FileReader(pathToFile), ',');
        List<String[]> records = reader.readAll();

        for (int i = 0; i < records.size(); i +=3) {
            Addition add = new Addition();
            add.setNbPersonnes(Integer.parseInt(records.get(i)[0]));
            add.setPrixMenu(Integer.parseInt(records.get(i + 1)[0]));
            add.setPrixBouteille(Integer.parseInt(records.get(i + 2)[0]));
            additions.add(add);
        }

        return additions;
    }

    public static List<String[]> readAllLines() throws Exception {
        CSVReader reader = new CSVReader(new FileReader(pathToFile), ',');
        return reader.readAll();
    }

    public static List<String> readAllLinesOneCol() throws Exception {
        CSVReader reader = new CSVReader(new FileReader(pathToFile), ',');
        List<String[]> records = reader.readAll();

        return records.stream().map(elem -> elem[0]).collect(Collectors.toList());

    }


}
