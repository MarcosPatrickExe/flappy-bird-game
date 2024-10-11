package playGame;
import model.Hitbox; 
import playGame.Tela;
import model.IPassaro;
import playGame.FlappyBird.GameState;
 
/**
 * @author Patrick
 */ 
  
public class Passaro implements IPassaro{ 
    double x, y;
    double vy = 0;//velocidade vertical
    private int angulacao = 0;
    
    static final double G = 1000;//valor que sera multiplicado pela variavel 'dt' a cada frame
    static final double FLAP = -350;//forca do "pulo" do passaro. Quanto menor o valor, menor a altura do "pulo"
    private boolean frameAsaPraCima = false;
    
    protected Hitbox boxPassaro;
    
    Passaro(double x, double y){
        this.x = x;
        this.y = y; 
        
        this.boxPassaro = new Hitbox(x, y, x+34, y+24);
    }
     
    
    @Override
    public void atualiza(double dt){
        
       if( FlappyBird.estadoJogo == GameState.JOGANDO || 
               FlappyBird.estadoJogo == GameState.GAMEOVER){
           
            vy += G*dt;
            y += vy*dt;

            if(angulacao+3 <= 90)
               this.angulacao += 2.5;

            this.boxPassaro.moverPassaro(y);
           //movendo a hitbox do passaro de acordo com a sua posicao vertical na tela
       }
       
       
       if(ContadorTempo.segundoAtual%2==0)
           this.frameAsaPraCima = true;
       else
          this.frameAsaPraCima = false;
    }
    
    
    @Override
    public void flap(){
        
        if(FlappyBird.estadoJogo == GameState.JOGANDO){
            vy = Passaro.FLAP;
            this.angulacao = -90;
        }
    }
    
    
    @Override
    public void desenhar(Tela tela, String urlImagem){
        
        //passaro parado (frame sem bater as asas):
        tela.imagem(urlImagem, 528, 180, 34, 24, Math.toRadians((double) this.angulacao), this.x, this.y);
        
        
        if(this.frameAsaPraCima)
            //frame 1 do passaro (asas pra cima)
            tela.imagem(urlImagem, 528, 128, 34, 24, Math.toRadians((double) this.angulacao), this.x, this.y);
       
        else//caso frame seja -1
            //frame 2 do passaro (asas pra baixo):
            tela.imagem(urlImagem, 446, 248, 34, 24, Math.toRadians((double) this.angulacao), this.x, this.y);
        
        
        //desenhando hitbox do passaro:
       // tela.imagem("passaro", this.x, this.y, 34.0, 24.0, Math.toRadians((double) this.angulacao), this.boxPassaro);
                                               //(this.boxPassaro.x1-this.x), (this.boxPassaro.y1-this.y)
        //OU:
        //tela.imagem(urlImagem, 528, 128, 34, 24, Math.atan(vy/150), this.x, this.y);
    }
   
}

 //link do codigo original:
 //https://www.dropbox.com/s/a2wy4bsskrpugq2/Flappy%20Bird.rar?dl=
