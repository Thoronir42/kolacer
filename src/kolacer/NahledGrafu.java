package kolacer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.ui.RectangleInsets;


public class NahledGrafu extends JFrame {

    private static final long serialVersionUID = 1L;
    static int WIDTH = 1000;
    static int HEIGHT = 550;
    
    String popisek;
    GrafPanel drawingArea;
    
    
    
    /**
     *
     * @param graf
     * @param popisek
     * @param parent
     * @param dekovala
     */
    public NahledGrafu(JFreeChart graf, String popisek, JFrame parent, String dekovala) {
        this.drawingArea = new GrafPanel(graf, popisek, dekovala.trim());
        this.drawingArea.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.add(getExpPanel((PiePlot)this.drawingArea.getChart().getPlot()), BorderLayout.NORTH);
        this.add(drawingArea, BorderLayout.CENTER);
        this.addResAct();
        this.pack();
        this.drawingArea.zmenGraf(this.getWidth());

        this.setTitle("Náhled grafu");
    this.setLocation(parent.getX()+parent.getWidth()/2-this.getWidth()/2, parent.getY()+parent.getHeight()/2-this.getHeight()/2);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void addResAct(){
        final NahledGrafu ng = this;
        this.addComponentListener(new ComponentAdapter(){

            @Override
            public void componentResized(ComponentEvent e) {
                ng.drawingArea.zmenGraf(ng.getWidth());
            }
        });
    }

    private JPanel getExpPanel(final PiePlot pp) {
        final JButton exp = new JButton("Export do souboru");
        JPanel jp = new JPanel();
        jp.setBackground(null);
        exp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                exp.setBackground(drawingArea.exportDoSouboru()?Color.green:Color.red);
            }
        });
        jp.add(exp);
        JButton nBarvy = new JButton("Nové barvy");
        nBarvy.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                Barvy.nastavBarvy(pp);
            }
            
        });
        jp.add(nBarvy);
        return jp;
    }
    
}

class GrafPanel extends ChartPanel{
    String popisek;
    String dekovala;
    JFreeChart graf;
    float nazHei;
    static float legPerc = 0.3f;
    
    public GrafPanel(JFreeChart graf, String popisek, String dekovala){
        super(graf, true);
        this.popisek = popisek;
        this.graf = graf;
        this.nazHei = getHeight(graf.getTitle());
        this.dekovala = dekovala;
        
    }
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        super.paint(g);
        Rectangle2D drawnPlot = this.getChartRenderingInfo().getPlotInfo().getDataArea();
        int vyska = (int) (drawnPlot.getHeight());
        int sirka = (int) (this.getWidth() - drawnPlot.getWidth()-drawnPlot.getX());
        Rectangle rectLeg = new Rectangle((int) (graf.getPlot().getInsets().getLeft()+drawnPlot.getWidth()),
                (int)(drawnPlot.getY()),
                (int)(this.getWidth()-2*drawnPlot.getX()-drawnPlot.getWidth()),
                (int) (Math.round(drawnPlot.getHeight())));
        vykresliLegendu(g2, rectLeg);
        vykresliPopisky(g2, sirka+(int)(drawnPlot.getX()+drawnPlot.getWidth()), (int) (vyska+drawnPlot.getY()));
    }
    private float getHeight(TextTitle tt){
        String nazev = tt.getText();
        if(nazev.length()==0) nazev = "Graf graf";
        TextLayout ll = new TextLayout(nazev,
                tt.getFont(),
                new FontRenderContext(null, false, false));
        return ll.getAscent()-ll.getDescent();
        
    }
    
    public boolean exportDoSouboru(){
        BufferedImage bi = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.getGraphics();
        paint(g);
        File f = getFile(this.getChart().getTitle().getText());
        try {
            ImageIO.write(bi, "png", f);
        } catch (FileNotFoundException e) {
            System.err.println("Nastala chyba pri zapisu do souboru.. "+e.getMessage());
            return false;
        } catch (IOException e) {
            System.err.println("Nastala chyba pri zapisu do souboru.. "+e.getMessage());
            return false;
        }
        return true;
    }
    private File getFile(String p){
        String cp = FileNameCleaner.cleanFileName(p);
        String prip = ".png";
        File f = new File(cp+prip);
        int i = 0;
        while(f.exists()){
            f = new File(cp+(++i)+prip);
        }
        return f;
    }
    
    private void vykresliPopisky(Graphics2D g, int wid, int hei){
        g.setColor(Color.gray);
        TextLayout tl = new TextLayout(popisek, Kolacer.popisekGrafuFont, g.getFontRenderContext());
        float y = hei;
        y = y - (tl.getAscent() - tl.getDescent())*1.4f;
        tl.draw(g, wid-20-tl.getAdvance(), y);
        
        tl = new TextLayout(dekovala, Kolacer.podekovaniAutoroviFont, g.getFontRenderContext());
        y = y - (tl.getAscent() - tl.getDescent())*1.8f;
        tl.draw(g, wid -20 - tl.getAdvance(), y);
    }

    private void vykresliLegendu(Graphics2D g, Rectangle oblast) {
        Shape orig = g.getClip();
        Font f = new Font("Helvetica", Font.BOLD, 12);
        g.setColor(Barvy.barvaPozadi);
        g.fill(oblast);
        if(graf.getPlot().getOutlinePaint()!=null){
           g.setPaint(graf.getPlot().getOutlinePaint());
           g.draw(oblast);
           g.setColor(Barvy.barvaPozadi);
           g.drawLine(oblast.x, oblast.y+1, oblast.x, oblast.y+oblast.height-2);
        }
        g.setClip(oblast);
        TextLayout tl;
        
        PiePlot pp = (PiePlot)graf.getPlot();
        int n=pp.getDataset().getItemCount();
        Comparable key;
        int posunY = 0;
        Rectangle ico;
        for(int i = 0; i<n; i++){
            key = pp.getDataset().getKey(i);
            g.setPaint(pp.getSectionPaint(key));
            ico = new Rectangle(oblast.x+2, oblast.y+posunY+28, 16, 16);
            
            g.fill(ico);
            g.setColor(Color.black);
            g.draw(ico);
            posunY = drawMultilineString(g ,f , pp.getLegendItems().get(i).getLabel(), oblast.x+26, oblast.y+40+posunY, 0, oblast.width-46);
        }
        g.setClip(orig);
    }
    
    static ArrayList<String> wrap (Graphics2D g, String text,Font font, int width) {
        ArrayList<String> result = new ArrayList<>();
        FontMetrics fm = g.getFontMetrics(font);
        if (text == null)
           return null;

        boolean hasMore = true;
        int current = 0;
        int lineBreak = -1;
        int nextSpace = -1;
        while (hasMore) 
        {
            int lineWidth = 0;
            while (lineWidth < width) 
            {
                lineBreak = nextSpace;
                if (lineBreak == text.length() - 1){
                    hasMore = false;
                    break;
                }
                else{
                    nextSpace = text.indexOf(' ', lineBreak+1);
                    if (nextSpace == -1)
                        nextSpace = text.length() -1;
                    String s = text.substring(current, nextSpace);
                    lineWidth = fm.stringWidth(s);
                }
        }
        String line = text.substring(current, lineBreak + 1);
        result.add(line);
        current = lineBreak;
    }
    return result;
}
    static public int drawMultilineString (Graphics2D g,Font font, String str, int x, int y,int anchor, int width){
        g.setFont (font);
        ArrayList<String> lines = wrap(g, str, font, width);
        int fontHeight = (int) font.getMaxCharBounds(g.getFontRenderContext()).getHeight();
        int lineY = 0;
        for (int i = 0; i<lines.size(); i++){
            lineY = y + (i * fontHeight);
            g.drawString(lines.get(i), x,lineY);
        }
        return lineY-2*fontHeight;
    }
    
    
    void zmenGraf(int sirka) {
        this.graf.getPlot().setInsets(new RectangleInsets(5,5,5,(int)(sirka*legPerc)));
    }
}


class FileNameCleaner {
    final static int[] illegalChars = {34, 60, 62, 124, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 58, 42, 63, 92, 47};
    static{
        java.util.Arrays.sort(illegalChars);
    }
    public static String cleanFileName(String badFileName) {
        StringBuilder cleanName = new StringBuilder();
        for (int i = 0; i < badFileName.length(); i++) {
            int c = (int)badFileName.charAt(i);
            if (java.util.Arrays.binarySearch(illegalChars, c) < 0) {
                cleanName.append((char)c);
            }
        }
        return cleanName.toString();
}
}