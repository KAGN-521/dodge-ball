
public class DodgeBall {

    
    public static void main(String[] args) throws InterruptedException {
        Ventana ven = new Ventana();
       
        while(true){
            ven.repaint();
            Thread.sleep(20);
        }

    }
    
}
