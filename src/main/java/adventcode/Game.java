package adventcode;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Game {
    int numGame;
    List<SetBalles> plays = new ArrayList<>();

    public void add(SetBalles sb)  {
        plays.add(sb);
    }


}
