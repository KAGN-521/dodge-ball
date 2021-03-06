import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

public class Borde {

    public Borde() {

    }

    public RoundRectangle2D getCulos(){ return new RoundRectangle2D.Double(60, 35, 460, 460, 460, 460); }
    
    public void pintarBorde(Graphics2D g){
        //g.setColor(Color.BLUE);
        //g.draw(getCulos());
    }
}
