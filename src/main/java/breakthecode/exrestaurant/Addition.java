package breakthecode.exrestaurant;

import lombok.Data;

@Data
public class Addition {
    public int nbPersonnes;
    public int prixMenu;
    public int prixBouteille;


    public int total() {
        return nbPersonnes * prixMenu + prixBouteille;
    }
}
