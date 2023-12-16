package adventcode;

import com.opencsv.CSVReader;
import utils.logs.FilesUtils;
import utils.logs.Logger;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AdventCodeEx1 {

    public static String pathToFile;

    static {
        try {
            pathToFile = FilesUtils.getRessourceFile("adventcode/ex1.csv");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        List<String> ex1 = readAllLinesOneCol();
        Logger.logInfo(ex1.toString());

        int result = 0;
        for (String line : ex1) {
            Logger.logInfo("------------");
            result += extractDigit(ex1ParseLigneSimple(line));
        }

        Logger.logInfo("RESULT = " + result);

    }

    public static String ex1ParseLigneSimple(String input) {

        String res = "" + input;

        res = res.replace("one", "one1one");
        res = res.replace("two", "two2two");
        res = res.replace("three", "three3three");
        res = res.replace("four", "four4four");
        res = res.replace("five", "five5five");
        res = res.replace("six", "six6six");
        res = res.replace("seven", "seven7seven");
        res = res.replace("eight", "eight8eight");
        res = res.replace("nine", "nine9nine");

        System.out.println("Input = " + input);
        System.out.println("Output = " + res);

        return res;
    }

    public static int extractDigit(String input) {

        Pattern findFirstDigit = Pattern.compile("\\d");
        Pattern findLastDigit = Pattern.compile("\\d");

        Matcher matchFirstDigit = findFirstDigit.matcher(input);
        matchFirstDigit.find();
        String firstDigit = matchFirstDigit.group(0);

        Matcher matchLastDigit = findLastDigit.matcher(new StringBuilder(input).reverse());
        matchLastDigit.find();
        String lastDigit = matchLastDigit.group(0);

        int res = Integer.valueOf(firstDigit + lastDigit);
        Logger.logInfo("Result = " + res);
        return res;
    }

    public static List<String> readAllLinesOneCol() throws Exception {
        CSVReader reader = new CSVReader(new FileReader(pathToFile), ',');
        List<String[]> records = reader.readAll();

        return records.stream().map(elem -> elem[0]).collect(Collectors.toList());

    }


    public static int ex1ParseLigne(String input) {

        Logger.logInfo("PARSE BEGIN String = " + input);
        int result = 0;

        Pattern findFirstDigit = Pattern.compile("\\d");
        Pattern findLastDigit = Pattern.compile("\\d");

        Pattern firstLettersNumber = Pattern.compile("(?=(one|two|three|four|five|six|seven|eight|nine))");

        String col = input;
        Matcher firstToReplace = firstLettersNumber.matcher(input);



        while (firstToReplace.find()) {
            for (int i = 0; i < firstToReplace.groupCount(); i++) {
                System.out.println("firstToReplace.group(i) = " + firstToReplace.group(i));
                switch (firstToReplace.group(i)) {
                    case "one":
                        col = col.replace("one", "one1");
                        break;
                    case "two":
                        col = col.replace("two", "two2");
                        break;
                    case "three":
                        col = col.replace("three", "three3");
                        break;
                    case "four":
                        col = col.replace("four", "four4");
                        break;
                    case "five":
                        col = col.replace("five", "five5");
                        break;
                    case "six":
                        col = col.replace("six", "six6");
                        break;
                    case "seven":
                        col = col.replace("seven", "seven7");
                        break;
                    case "eight":
                        col = col.replace("eight", "eight8");
                        break;
                    case "nine":
                        col = col.replace("nine", "nine9");
                        break;
                    case "default":
                        break;
                }

            }


        }

        Logger.logInfo("NEW COL = " + col);

        Matcher matchFirstDigit = findFirstDigit.matcher(col);
        matchFirstDigit.find();
        String firstDigit = matchFirstDigit.group(0);

        Matcher matchLastDigit = findLastDigit.matcher(new StringBuilder(col).reverse());
        matchLastDigit.find();
        String lastDigit = matchLastDigit.group(0);

//        System.out.println("String = " + input);
//        System.out.println("String = " + col);
//        System.out.println("firstDigit = " + firstDigit);
//        System.out.println("lastDigit = " + lastDigit);

        int res = Integer.valueOf(firstDigit + lastDigit);
        Logger.logInfo("PARSE RESULT = " + res);

        return res;
    }


}