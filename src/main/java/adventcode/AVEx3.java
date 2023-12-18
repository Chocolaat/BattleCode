package adventcode;

import com.opencsv.CSVReader;
import utils.Arrays2D;
import utils.logs.FilesUtils;
import utils.logs.Logger;

import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AVEx3 {

    public static String pathToFile;

    static {
        try {
            pathToFile = FilesUtils.getRessourceFile("adventcode/ex3/example.csv");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isSymbol(String s) {
        if (s.equals(".")) return false;

        try {
            int i = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {

        Arrays2D input = getArray2D();
        input.log();
        Logger.logInfo(input.get(3, 0).toString());

        List<String> adj = input.getAdjacents(3, 0);
        List<Boolean> symb = adj.stream().map(AVEx3::isSymbol).toList();
        Logger.logInfo(adj.toString());
        Logger.logInfo(symb.toString());

//        Logger.logInfo(input.get(1, input.getMaxLongueurIdx()-1).toString());
//        Logger.logInfo(input.getAdjacents(1, input.getMaxLongueurIdx()-1).toString());

//        Logger.logInfo(input.get(0, 0).toString());
//        Logger.logInfo(input.get(0, 1).toString());
//        Logger.logInfo(input.get(0, 2).toString());
//        Logger.logInfo(input.get(0, 3).toString());
//        Logger.logInfo("----");
//        Logger.logInfo(input.get(1, 0).toString());
//        Logger.logInfo(input.get(1, 1).toString());
//        Logger.logInfo(input.get(1, 2).toString());
//        Logger.logInfo(input.get(1, 3).toString());
//        Logger.logInfo("----");
//        Logger.logInfo(input.get(2, 0).toString());
//        Logger.logInfo(input.get(2, 1).toString());
//        Logger.logInfo(input.get(2, 2).toString());
//        Logger.logInfo(input.get(2, 3).toString());
//        Logger.logInfo("----");
//        Logger.logInfo(input.get(3, 0).toString());
//        Logger.logInfo(input.get(3, 1).toString());
//        Logger.logInfo(input.get(3, 2).toString());
//        Logger.logInfo(input.get(3, 3).toString());
//        Logger.logInfo("----");


    }


    public static Arrays2D getArray2D() throws Exception {
        List<String> input = readAllLinesOneCol();

        int largeur = input.get(0).length();
        int longueur = input.size();

        Logger.logInfo("largeur = " + largeur);
        Logger.logInfo("longueur = " + longueur);

        List<List<String>> inputAsListString = new ArrayList<>();
        for (String line : input) {
            List<String> newLine = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                String c = String.valueOf(line.charAt(i));
                newLine.add(c);
            }
            inputAsListString.add(newLine);
        }
        Logger.logInfo(inputAsListString.toString());
        return new Arrays2D<>(String.class, inputAsListString);
    }



    public static List<String> readAllLinesOneCol() throws Exception {
        CSVReader reader = new CSVReader(new FileReader(pathToFile), ',');
        List<String[]> records = reader.readAll();

        return records.stream().map(elem -> elem[0]).collect(Collectors.toList());

    }
}



