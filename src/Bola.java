import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bola extends JLabel {
    private int x;
    private int y;
    private int dir;
    private int speed;
    private int fspeed;
    private int randNum;
    private Color color;
    private final int tamBola = 45;

    public Bola(int x, int y, Color color, int v) {
        dir = 0;
        this.x = x;
        this.y = y;
        this.color = color;
        speed=v;
        fspeed=v;
        
    }
    
     public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getFspeed() {
        return fspeed;
    }

    public void setFspeed(int fspeed) {
        this.fspeed = fspeed;
    }
    
    public void pintar(Graphics2D g){
        ImageIcon im = new ImageIcon(getClass().getResource("/Imagenes/ma.png"));
        g.setColor(color);
        g.fillRoundRect(x, y, tamBola, tamBola, tamBola, tamBola);
        g.drawImage(im.getImage(), x-8, y-8, tamBola+20, tamBola+20, null);
        
    }
    
    public  Rectangle2D getBola(){
        return new Rectangle2D.Double(x,y,tamBola,tamBola);
    } 
    
    public void mover(boolean colisionR1, boolean colisionR2){
        /*SE TOMO COMO REFERENCIA AL SIGUIENTE AUTOR PARA ESTE METODO https://www.youtube.com/watch?v=5EZw5DTIKjg*/
             // System.out.println(colisionR2);
        if(colisionR1){
           speed=-(getSpeed());
           
           while(speed >0){
               speed=-(getSpeed());
           }
          rebotar();
        }
 
        
        if(colisionR2) rebotar();

        if(dir == 0){
            y += speed;
        }
        else if(dir == 1){
            y -= speed;
        }
        else if(dir == 2){
            x +=speed;
        }
        else if(dir == 3){
            x -=speed;
        }
        else if(dir == 4){
            x += speed;
            y += speed;
        }
        else if(dir == 5){
            x -= speed;
            y -= speed;
            
        } else if(dir == 6){
            x -= speed;
            y += speed;
        }
        else if(dir == 7){
            x += speed;
            y -= speed;
        }
        
        if(colisionR1 || !colisionR1){
            //speed = 5;
            rebotar();
        }
        
        
   }
    
    public void rebotar(){
  /*SE TOMO COMO REFERENCIA AL SIGUIENTE AUTOR PARA ESTE METODO https://www.youtube.com/watch?v=5EZw5DTIKjg*/
 int limX = 600;
        int limY = 600;
 
        
        if(y <= 50){
            setRandNum(calcularRand());
      
            dir = getRandNum() == 1 ? 6 : getRandNum() == 2 ? 0 : 4;
            speed = fspeed;
            //entrada = true;
        } else if(x <= 70){  
            setRandNum(calcularRand());
            dir = getRandNum() == 1 ? 7 : getRandNum() == 2 ? 2 : 4;
            speed = fspeed;
             //entrada = true;
        } else if(y >= 500 - tamBola){
            
            setRandNum(calcularRand());
            dir = getRandNum() == 1 ? 5 : getRandNum() == 2 ? 1 : 7;
            speed = fspeed;
             //entrada = true;
        }
        else if(x >= 510 - tamBola){
            setRandNum(calcularRand());
            dir = getRandNum() == 1 ? 6 : getRandNum() == 2 ? 3 : 5;
            speed = fspeed;
            //entrada = true;
        }       
        
    }
    
    public int calcularRand(){
        return (int) (Math.random() * 3) + 1;
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
    
    public int getRandNum(){
        return randNum;
    }
    
    public void setRandNum(int randNum){
        this.randNum = randNum;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    
}
