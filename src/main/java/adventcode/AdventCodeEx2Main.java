package adventcode;

import com.opencsv.CSVReader;
import utils.logs.FilesUtils;
import utils.logs.Logger;

import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class AdventCodeEx2Main {
    public static String pathToFile;

    static {
        try {
            pathToFile = FilesUtils.getRessourceFile("adventcode/ex2.csv");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {



        List<String[]> listeLigne = readAllLines();
        List<Game> listGames = new ArrayList<>();

        int numGame = 1;
        for (String[] ligne : listeLigne) {
            Logger.logInfo("-----------");
            Game game = listeForGame(ligne);
            game.setNumGame(numGame);
            Logger.logInfo(game.toString());
            listGames.add(game);
            numGame++;
        }


        int result = 0;

        Logger.logInfo("**********");
        Logger.logInfo("**********");
        Logger.logInfo("**********");
        for (Game game : listGames) {
            Logger.logInfo("-------------");
            Logger.logInfo(game.toString());

            result += getPowerOfGame(game);

        }
        Logger.logInfo("**********");
        Logger.logInfo("**********");
        Logger.logInfo("**********");
        Logger.logInfo("RESULT = " + result);
    }

    public static boolean gameImpossible(Game game) {
        boolean res = false;
        for (SetBalles set : game.plays) {
            if (set.nbBlue > 14 || set.nbGreen > 13 || set.nbRed > 12) {
                return true;
            }
        }
        return res;
    }

    public static int getPowerOfGame(Game game) {

        int maxBlue = 0;
        int maxRed = 0;
        int maxGreen = 0;

        for (SetBalles set : game.plays) {
            maxBlue = Math.max(maxBlue, set.nbBlue);
            maxRed = Math.max(maxRed, set.nbRed);
            maxGreen = Math.max(maxGreen, set.nbGreen);
        }

        return maxBlue * maxRed * maxGreen;

    }

    public static Game listeForGame(String[] input) {
        Game game = new Game();
        List<SetBalles> list = new ArrayList<>();

        for (String col : input) {
            Logger.logInfo(col);



            // On enleve Game 1 :
            String allSets = col.substring(col.indexOf(":") + 1);
            String[] pioche = allSets.split(",");
            SetBalles setBalle = new SetBalles();
            for (String subset : pioche) {
                if (subset.contains("red")) {
                    String nbRed = subset.substring(1, subset.indexOf("r") - 1);
                    setBalle.setNbRed(Integer.valueOf(nbRed));
                } else if (subset.contains("blue")) {
                    String nbBlue = subset.substring(1, subset.indexOf("b") - 1);
                    setBalle.setNbBlue(Integer.valueOf(nbBlue));
                }else if (subset.contains("green")) {
                    String nbGreen = subset.substring(1, subset.indexOf("g") - 1);
                    setBalle.setNbGreen(Integer.valueOf(nbGreen));
                }
            }
            list.add(setBalle);
        }

        game.setPlays(list);
        return game;
    }



    public static List<String[]> readAllLines() throws Exception {
        CSVReader reader = new CSVReader(new FileReader(pathToFile), ';');
        return reader.readAll();
    }
}
