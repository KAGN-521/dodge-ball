import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;

public class Raqueta {
    int x;
    int y;
    int vel = 50;
    private Color color;

    public Raqueta(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Rectangle2D getRaqueta(){
        return new Rectangle2D.Double(x,y,60,10);
        
    }
    
    public void pintarRaqueta(Graphics2D g){
         /*SE TOMO COMO REFERENCIA AL SIGUIENTE AUTOR PARA ESTE METODO http://www.edu4java.com/es/game/game5.html*/
        ImageIcon img = new ImageIcon(getClass().getResource("/Imagenes/naveF.png"));
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 65, 12);
        g.drawImage(img.getImage(), x-40, y-40, 130, 100, null);
    }
    
    public void moverRaqueta(int dir, int lim){
          /*SE TOMO COMO REFERENCIA AL SIGUIENTE AUTOR PARA ESTE METODO http://www.edu4java.com/es/game/game5.html*/

        //CALCULA LOS MOVIMIENTOS DE LA RAQUETA
        
        if(dir == 1){ // DERECHA
            if (x + vel > 0 && x + vel < lim -60)
                x = x + vel;    
        }
        if(dir == 2){ //IZQUIERDA
            if (this.getX() - 20 >= 0)
                x = x - vel;             
        }
        
        if(dir == 3){ //ABAJO
            if (y + vel > 0 && y + vel < lim - 10)
                y = y + vel;              
        }

        if(dir == 4){ // ARRIBA
            if (this.getY() - 20 >= 0)
                y = y - vel;              
        }        
        
  }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
