package breakthecode.exrestaurant;

import lombok.Data;

@Data
public class PointCardinal {
    public int nbPas;
    public String pointCardinal;

    PointCardinal (int nbPas, String pointCardinal) {
        this.nbPas = nbPas;
        this.pointCardinal = pointCardinal;
    }

    public String toString(){
        return " " + this.nbPas + "" + this.pointCardinal;
    }
}