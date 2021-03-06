
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Panel1 extends JPanel implements KeyListener{
    
    int nB=1;
    int vel=5;
    int count=0;
    int count2=0;
    
    ArrayList<Bola> ConjuntoBolas = new ArrayList<>();//array con las bolas del panel
    ArrayList<Puntos> Conjunto_Puntos = new ArrayList<>();
    
    //Declaracion fuente:
  
    /**
     *
     */
      
    public static Font FMARCADOR = Panel1.cargarFuente("/Fuentes/digital_counter_7.ttf");
    

    
    private Bola bola;
    private Raqueta raqueta;
    private Borde bord;
    private Puntos punt;
    private int puntos = 0;//4010
    private int aux = 0;
    private JLabel marcador = new JLabel("           "+puntos);
    private int radioEsquina = 15;
    private Color backgroundColor = new Color(0,0,0,100);       
    Bola b = new Bola(200, 100, Color.BLUE, vel);

    public int getnB() {
        return nB;
    }

    public void setnB(int nB) {
        this.nB = nB;
    }

    public int getVel() {
        return vel;
    }

    public void setVel(int vel) {
        this.vel = vel;
    }
    
    
    
    
    public ArrayList<Bola> getConjuntoBolas() {
        return ConjuntoBolas;
    }

    public void setConjuntoBolas(ArrayList<Bola> ConjuntoBolas) {
        this.ConjuntoBolas = ConjuntoBolas;
    }

   
    public void setPuntos(int puntos){
        this.puntos = puntos;
    }
    
    public int getPuntos(){
        return puntos;
    }
    
    
    public Panel1(){
        
    }
    
    public static Font cargarFuente(String ruta) {
        //Referencia del método: https://www.youtube.com/watch?v=dTXXeZzQErk
        //Fuente tomada de: https://www.1001fonts.com/display+headline-fonts.html?page=10&items=10
        Font fuente = null;
  
        InputStream entradaB = ClassLoader.class.getResourceAsStream(ruta);
        
        try{
        fuente = Font.createFont(Font.TRUETYPE_FONT, entradaB);
        }catch(FontFormatException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        fuente = fuente.deriveFont(60f);
        
        return fuente;
    }
    
    public Panel1(int radio){
                
        
        this.setLayout(new FlowLayout());
        marcador.setLocation(410, 10);
        marcador.setFont(FMARCADOR);
        marcador.setForeground(Color.BLUE);
        add(marcador);
        this.setBackground(new Color(0,0,0,0));
        this.setPreferredSize(new Dimension(600, 450));
        radioEsquina=radio;
        bola = new Bola(200, 100, Color.BLUE, vel);
        raqueta = new Raqueta(200,330);
        bord = new Borde();
        bola.setIcon(new ImageIcon(getClass().getResource("/Imagenes/nave.png")));
    
        
        annadir_puntos(0, 'B');
        annadir_puntos(90, 'B');
        annadir_puntos(180, 'B');
        annadir_puntos(270, 'B');
        annadir_puntos(45, 'M');
        annadir_puntos(135, 'M');
        annadir_puntos(225, 'M');
        annadir_puntos(315, 'M');
        
        addKeyListener(this);
        this.setFocusable(true);
        
    }
    
    // INICIO METODOS DE SONIDO
    /* SE TOMO COMO REFERENCIA AL SIGUIENTE AUTOR PARA LOS METODOS DE SONIDOS: https://www.youtube.com/watch?v=p2PDn4Jdov8*/
    public static Clip loadS1(){
        Clip clip = null;
        
        try{
            InputStream is = ClassLoader.class.getResourceAsStream("/Sonidos/punto.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
            DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
        } catch(Exception e){
            e.printStackTrace();
        }
        return clip;
    }
    
        public static Clip loadS2(){
        Clip clip = null;
        
        try{
            InputStream is = ClassLoader.class.getResourceAsStream("/Sonidos/fail.wav");
            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
            DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
        } catch(Exception e){
            e.printStackTrace();
        }
        return clip;
    }
    
    
    
    public void playSound1(){
        loadS1().stop();
        loadS1().flush();
        loadS1().start();
    }
    
    
        public void playSound2(){
        loadS2().stop();
        loadS2().flush();
        loadS2().start();
    }
        
        //FIN METODOS DE SONIDO
    
    public void anadirBolas(int x){ 
        //Parte de este método fue tomado de: https://www.youtube.com/watch?v=7DZLPRYNRno
        if(x>ConjuntoBolas.size()){
        for(int i=0;i<x;i++){
            ConjuntoBolas.add(new Bola(200, 100, Color.BLUE, vel));
        }
        }
        else if(x<ConjuntoBolas.size()){
            eliminarBola(x);
        }
        
    }
    
    public void annadir_puntos(int coordenada, char tipo){
        Conjunto_Puntos.add(new Puntos(coordenada, tipo));     
    }
    
    public void eliminarBola(int x){
    //Parte de este método fue tomado de: https://www.youtube.com/watch?v=7DZLPRYNRno
        int i=ConjuntoBolas.size()-x;
        
        for(int a=0;a<i;a++){
        ConjuntoBolas.remove(ConjuntoBolas.size()-1);
        }
    }
    
    public boolean colision( Rectangle2D r, int n){
        //Parte de este método fue tomado de:https://www.youtube.com/watch?v=zMQ2sdFAvCQ
        return ConjuntoBolas.get(n).getBola().intersects(r);

    }
    
    public boolean Colision_Raqueta_2( Rectangle2D r, int n){
        //Parte de este método fue tomado de:https://www.youtube.com/watch?v=zMQ2sdFAvCQ
        return ConjuntoBolas.get(n).getBola().intersects(r);
    }
    
    public boolean Colision_Borde_2(Rectangle2D e, int n){
        return bord.getCulos().contains(ConjuntoBolas.get(n).getBola());
    }
    
    public int getCount(){
        return count;
        
    }
    void setCount(int n){
        count = n;
    }
    public int getCount2(){
        return count2;
    }
    void setCount2(int n){
        count2 = n;
    }
    
    public boolean Puntuacion(Rectangle2D e, int n, int i){
        int var = 0;
        int ns = 5;
        boolean f = false;
        boolean check = Conjunto_Puntos.get(i).getArco().intersects(ConjuntoBolas.get(n).getBola());
        
        if(ns == 5){
            setCount2(getCount2() + 1);
        }
        if(getCount2() > 100){
            f = true;
        }
        
        if(check && f){
            setCount(20);
            //System.out.println("entre --> " + getCount());
            if(Conjunto_Puntos.get(i).getTipo() == 'B'){
                puntos = puntos + 1;
                playSound1();
            }
            else{
                puntos = puntos - 1;
               playSound2();
            }
            
            marcador.setText("           "+puntos);
            check = false;
            var ++;            
            setCount2(0);
            return true;
        }
        else{           
            check = true;
            return false;
        }



    }
    
   
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        raqueta.pintarRaqueta(g2);  

        boolean aux;
        try {
            //Parte de este código fue tomado de: https://www.youtube.com/watch?v=7DZLPRYNRno
            this.anadirBolas(nB);
            for(int x=0;x<ConjuntoBolas.size();x++){
                for(int i = 0; i < Conjunto_Puntos.size(); i++){
                    Conjunto_Puntos.get(i).Pintar(g2);
                    Puntuacion(ConjuntoBolas.get(x).getBola(),x,i);
                }
                ConjuntoBolas.get(x).pintar(g2);
                ConjuntoBolas.get(x).mover((Colision_Raqueta_2(raqueta.getRaqueta(), x)), (!Colision_Borde_2(ConjuntoBolas.get(x).getBola(), x)));                
            }
            Thread.sleep(20);
        } 
        catch (InterruptedException ex) {
            System.out.println("Error de impresion");
        }

    }

    //MOVIMIENTO DE TECLADO
    /*SE TOMO COMO REFERENCIA  PARA LOS METODOS DE MOVIMIENTO AL SIGUIENTE AUTOR https://www.youtube.com/watch?v=KnKvwrx4ue8*/
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getExtendedKeyCode() == KeyEvent.VK_RIGHT){
            raqueta.moverRaqueta(1,this.getWidth());

        }
        
        if(e.getExtendedKeyCode() == KeyEvent.VK_LEFT){
            raqueta.moverRaqueta(2,0);

        } 
        
        if(e.getExtendedKeyCode() == KeyEvent.VK_DOWN){
            raqueta.moverRaqueta(3,this.getHeight());

        }
        
        if(e.getExtendedKeyCode() == KeyEvent.VK_UP){
            raqueta.moverRaqueta(4,this.getHeight());
          
        }  
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    
    
    
}
