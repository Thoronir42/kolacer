package kolacer;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Kůň
 */
public class Udaj {
    private static final Random RNG = new Random();
    
    String hodnota;
    int pocet;
    Color barva;

    public String getHodnota() {
        return hodnota;
    }

    public void setHodnota(String hodnota) {
        this.hodnota = hodnota;
    }

    public int getPocet() {
        return pocet;
    }

    public void setPocet(int pocet) {
        this.pocet = pocet;
    }

    public Color getBarva() {
        return barva;
    }

    public void setBarva(Color barva) {
        this.barva = barva;
    }
    
    public Udaj(String hodnota, int pocet, Color b){
        this.hodnota = hodnota;
        this.pocet = pocet;
        barva =b==null? new Color(RNG.nextInt(120)+100,RNG.nextInt(120)+100,RNG.nextInt(120)+100) : b;
    }
    
    @Override
    public String toString(){
        return pocet+" - "+hodnota;
    }
}
