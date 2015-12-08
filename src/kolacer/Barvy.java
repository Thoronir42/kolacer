package kolacer;

import java.awt.Color;
import java.util.LinkedList;
import java.util.Random;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Steven
 */
public class Barvy {
    
    static Color barvaPozadi = Color.WHITE,
        barvaOkoli = new Color(245,245,245);
    
    private static final Random RNG;
    private static final LinkedList<SouborBarev> soubory;
    static{
        RNG = new Random();
        soubory = new LinkedList<>();
        
        SouborBarev novy = new SouborBarev("Cervena");
        novy.novaBarva(new String[]{"#ED5314", "#FF8C00", "#FF1493", 
            "#CE0000", "#DD4B11", "#F54040",});
        soubory.add(novy);
        
        novy = new SouborBarev("Zluta");
        novy.novaBarva(new String[]{"#FFCC00", "#FFDE00", "#F7AD00",
            "#FFE500",});
        soubory.add(novy);
        
        novy = new SouborBarev("Zelena");
        novy.novaBarva(new String[]{"#7FFF00", "#ADFF2F", "#1FB600",
            "#86dd22",});
        soubory.add(novy);
        
        novy = new SouborBarev("Tyrkysova");
        novy.novaBarva(new String[]{"#56B8D1", "#A5F8F6", "#05FAE6"});
        soubory.add(novy);
        
        novy = new SouborBarev("Modra");
        novy.novaBarva(new String[]{"#3399FF", "#3C4BE6", "#4238C7", });
        soubory.add(novy);
        
        novy = new SouborBarev("Fialova");
        novy.novaBarva(new String[]{"#8A2BE2", "#7C43B5", "#C32ED1",
            "#E91660", });
        soubory.add(novy);
        
    }
    
    static private LinkedList<Color> getBarvy(int n){
        LinkedList<Color> returnList = new LinkedList<>();
        
        for(SouborBarev sb : soubory){
            sb.zamichej(n);
            System.out.println(sb.nazev+" - "+sb.barvy.size()+" - "+sb.barvyReturn.size());
            while(sb.maDalsi())
                returnList.add(sb.vratBarvu(false));
        }
        System.out.println(returnList.size());
        return returnList;
    }
    
    static public void nastavBarvy(PiePlot pp){
        DefaultPieDataset dpd = (DefaultPieDataset)pp.getDataset();
        int n = dpd.getItemCount();
        
        LinkedList<Color> barvy = (LinkedList<Color>) getBarvy(n);
        for(int i = 0; i<n; i++)
            pp.setSectionPaint(dpd.getKey(i), dalsiBarva(barvy));
    }
    
    static public void nastavBarvy(PiePlot pp, Color colA, Color colB){
        DefaultPieDataset dpd = (DefaultPieDataset)pp.getDataset();
        int n = dpd.getItemCount();
        
        for(int i = 0; i<n; i++){
            double percent = i * 1.0 / n;
            int red = (int)(colA.getRed() * percent + colB.getRed() * (1 - percent)),
                green = (int)(colA.getGreen() * percent + colB.getGreen() * (1 - percent)),
                blue = (int)(colA.getBlue() * percent + colB.getBlue() * (1 - percent));
            
            Color c = new Color(red, green, blue);
            pp.setSectionPaint(dpd.getKey(i), c);
        }
    }
    
    static private Color dalsiBarva(LinkedList<Color> barvy){
        if(barvy.isEmpty())
            return nahodnaBarva();
        int i = RNG.nextInt(barvy.size());
        return barvy.remove(i);
        
    }
    static private Color nahodnaBarva(){
        return Color.DARK_GRAY;
    }
    
    
    
}

class SouborBarev{
    String nazev;
    int nepouzito;
    LinkedList<Color> barvy, barvyReturn;
    
    public SouborBarev(String nazev){
        this.nazev = nazev;
        nepouzito = 0;
        barvy = new LinkedList<>();
        barvyReturn = new LinkedList<>();
    }
    
    public boolean novaBarva(String b){
        try{
            barvy.add(Color.decode(b));
        }
        catch(NumberFormatException e){
            System.err.println(e.getMessage());
            return false;
        }
            return true;
    }
    public boolean novaBarva(String[] b){
        int spatne = 0;
        for(String s : b){
            if (!SouborBarev.this.novaBarva(s)) spatne++;
        }
        return spatne==0;
    }
    
    public void zamichej(int seed){
        Random RNG = ( seed==0 ? new Random() : new Random(seed) );
        LinkedList<Color> tmp = (LinkedList<Color>)barvy.clone();
        
        barvyReturn.clear();
        while(!tmp.isEmpty())
            barvyReturn.add( tmp.remove( RNG.nextInt(tmp.size()) ) );
    }
    
    public boolean maDalsi(){
        return !barvyReturn.isEmpty();
    }
    
    public Color vratBarvu(boolean b){
        return barvyReturn.remove();
    }
}