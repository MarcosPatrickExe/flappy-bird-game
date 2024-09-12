package model;
 
import playGame.FlappyBird;

public class Hitbox {
    
    // Canto superior esquerdo (x0, y0) e
    // inferior direito (x1, y1)
    public double x0, y0, x1, y1;
 
    public Hitbox(double x0, double y0, double x1, double y1) {
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1; 
    }
    
    public void moverCano(double x0) {//movendo a hitbox dos canos (de cima e baixo)
        this.x0 = x0;
        this.x1 = x0+52.0;
    }
    
    public void moverPassaro(double dy) {//movendo a hitbox do passaro
        y0 = dy;
        y1 = y0+24.0;
    }
    
    
    public int intersecao(Hitbox obstaculo) {

        
        if( this.y1 < obstaculo.y0 ||
            this.y0 > obstaculo.y1 ||
            this.x1 < obstaculo.x0 ||
            this.x0 > obstaculo.x1){
          // System.out.println("Não houve colisão");
            return 0;
        }else{
         //  System.out.println("houve colisao");
            return 1;
        }
        
        
        //CANO DE CIMA   (FAZENDO ALGUNS TESTES:)
        /*
         System.out.println(
                  "x0: "+Hitbox.x0+" \n"
                + "x1: "+Hitbox.x1+" \n"
                + "y0: "+Hitbox.y0+" \n"
                + "y1: "+Hitbox.y1+" \n");
        */
        
         
        //PASSARO
        /*
        System.out.println(
                  "x0: "+this.x0+" \n"
                + "x1: "+this.x1+" \n"
                + "y0: "+this.y0+" \n"
                + "y1: "+this.y1+" \n");
        */
    }
}
