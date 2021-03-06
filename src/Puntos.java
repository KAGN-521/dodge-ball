
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;

public class Puntos {
    private int coordenada;
    private char tipo; // B o M
    
    Puntos(int coordenada, char type){
        this.coordenada = coordenada;
        this.tipo = type;
    }

    public char getTipo() {
        return tipo;
    }
    
    public Arc2D getArco(){
        return new Arc2D.Double(60, 35, 460, 460, coordenada, 30, Arc2D.CHORD); 
    }
    
    public void Pintar(Graphics2D g){
      if(coordenada == 0 || coordenada == 90 || coordenada == 180 || coordenada == 270){
            g.setColor(Color.GREEN);
        }
        
        else{
            g.setColor(Color.RED);
        }
        g.draw(getArco());
    }
    
    
    
    
    
    
    
    
    
    
    
}
