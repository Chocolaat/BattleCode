package utils;

import utils.logs.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVFileReader {

    //public static String inputDirPath = "C:\\Work\\BattleCode\\Inputs\\Samples\\";
    public static String inputDirPath = "C:\\Work\\BattleCode\\Inputs\\Advent\\";

    public static List<List<String>> read(String delimiter, String fileName) throws FileNotFoundException {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(inputDirPath + fileName))) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine(), delimiter));
            }
        }
        return records;
    }


    private static List<String> getRecordFromLine(String line, String delimiter) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(delimiter);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }


    public static List<List<String>> log(List<List<String>> listeLignes) {

        int idxLigne = 0;
        int idxColonne = 0;
        for (List<String> listeColonnes : listeLignes) {
            Logger.logInfo("Ligne " + idxLigne);
            for (String cellValue : listeColonnes) {
                Logger.logInfo("-- Colonne " + idxColonne + " = " + cellValue);
                idxColonne ++;
            }
            idxLigne++;
            idxColonne = 0;
        }
        return listeLignes;
    }


}
