package playGame;
  
import java.util.ArrayList;
import java.util.Random;  
import model.Hitbox;   
import playGame.Cano;    
import model.ICano;
 
/*   
 * @author Patrick
 */   

public class Cano implements ICano{
     
    public double x, y;
    public final double vxCano;//velocidade do cano
    public static final int ENTRADA = 120;//tamanho do espaço entre os canos
    private static Random gerarValor = new Random(); 
    
    public Hitbox boxCanoCima;
    public Hitbox boxCanoBaixo;  
    
    public Cano(double x, double y, double vCano){
        this.x = x;
        this.y = y;
        this.vxCano = vCano;
        
        this.boxCanoCima = new Hitbox(x, 0, x+52.0, y+270.0);// X, Y, largura, altura da hitbox do cano de cima
        this.boxCanoBaixo = new Hitbox(x, y+270+Cano.ENTRADA, x+52, y+270+Cano.ENTRADA+242);// X, Y, largura, altura da hitbox do cano de baixo
    }
     
    
    static Cano criarCano(double largura, double altura, double velocidadeCano){
         return ICano.criarCano(largura, -230+(gerarValor.nextInt(230)), velocidadeCano);//acessando métoda estático da classe pai "ICano"
    }
    
    
    static void atualiza(ArrayList<Cano> canos, double dt){
         ICano.atualiza(canos, dt);
    }
    
    
    static void desenha(Tela t, ArrayList<Cano> canos, String urlImagem){
         ICano.desenha(t, canos, urlImagem);
    }
}
