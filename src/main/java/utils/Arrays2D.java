package utils;

import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Data
public class Arrays2D<T> {

    private T[][] array;
    private int largeur;
    private int longueur;
    private int maxLargeurIdx;
    private int maxLongueurIdx;

    public Arrays2D(Class<T> c, int largeur, int longueur) {
        // Use Array native method to create array
        // of a type only known at run time
        @SuppressWarnings("unchecked")
        final T[][] array = (T[][]) Array.newInstance(c, largeur, longueur);
        this.array = array;
        this.longueur = longueur;
        this.largeur = largeur;
        this.maxLargeurIdx = largeur - 1;
        this.maxLongueurIdx = longueur - 1;
    }


    public Arrays2D(Class<T> c, List<List<T>> liste) {

        int largeur = liste.size();
        int longueur = liste.get(0).size();

        final T[][] array = (T[][]) Array.newInstance(c, largeur, longueur);
        this.array = array;
        this.longueur = longueur;
        this.largeur = largeur;
        this.maxLargeurIdx = largeur - 1;
        this.maxLongueurIdx = longueur - 1;

        for (int x = 0; x < largeur; x++) {
            List<T> line = liste.get(x);
            for (int y = 0; y < longueur; y++) {
                T column = line.get(y);
                this.set( x, y, column);
            }
        }
    }

    public T get(int x, int y) {
        return array[maxLongueurIdx - y][x];
    }

    public T[][] set(int x, int y, T elem) {
        array[x][y] = elem;
        return this.array;
    }

    public List<T> getAdjacents(int x, int y) {
        List<T> result = new ArrayList<>();

        if (getNorthWest(x, y) != null) { result.add(getNorthWest(x, y));}
        if (getNorth(x, y) != null) { result.add(getNorth(x, y));}
        if (getNorthEast(x, y) != null) { result.add(getNorthEast(x, y));}
        if (getWest(x, y) != null) { result.add(getWest(x, y));}
        if (getEast(x, y) != null) { result.add(getEast(x, y));}
        if (getSouthWest(x, y) != null) { result.add(getSouthWest(x, y));}
        if (getSouth(x, y) != null) { result.add(getSouth(x, y));}
        if (getSouthEast(x, y) != null) { result.add(getSouthEast(x, y));}

        return result;
    }


    public T getNorth(int x, int y) {
        if (outOfBound(x, y+1)) return null;
        return get(x, y+1);
    }
    public T getSouth(int x, int y) {
        if (outOfBound(x, y-1)) return null;
        return get(x, y-1);
    }

    public T getWest(int x, int y) {
        if (outOfBound(x - 1, y)) return null;
        return get(x - 1, y);
    }

    public T getEast(int x, int y) {
        if (outOfBound(x + 1, y)) return null;
        return get(x + 1, y);
    }

    public T getNorthWest(int x, int y) {
        if (outOfBound(x - 1, y + 1)) return null;
        return get(x - 1, y + 1);
    }
    public T getNorthEast(int x, int y) {
        if (outOfBound(x + 1, y + 1)) return null;
        return get(x + 1, y + 1);
    }

    public T getSouthWest(int x, int y) {
        if (outOfBound(x - 1, y - 1)) return null;
        return get(x - 1, y - 1);
    }

    public T getSouthEast(int x, int y) {
        if (outOfBound(x + 1, y - 1)) return null;
        return get(x + 1, y - 1);
    }

    public boolean outOfBound(int x, int y ) {
        return (x > maxLargeurIdx || y > maxLongueurIdx || x < 0 || y < 0);
    }



    public void log() {
        for (T[] x : array)
        {
            for (T y : x)
            {
                System.out.print(y.toString() + " ");
            }
            System.out.println();
        }
    }
}
