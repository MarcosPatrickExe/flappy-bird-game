package playGame;
   
import model.Acao; 
import model.Jogo;  
import model.Timer;
import java.util.ArrayList;   
import java.util.Set;  
import java.io.IOException; 
import java.util.Random; 
 
/*
 * @author Patrick 
 */ 

public class FlappyBird extends Jogo{ // classe de configuração do layout e velocidade dos canos e do chão do jogo 
    
    public enum GameState{ TELAINICIAL, TUTORIAL, JOGANDO, PAUSADO, GAMEOVER; } // conjunto de valores para o enum  
    
    private double chao_eixoX = 0;
    private final double gvx = 200.0; // velocidade do chao no eixoX, ou seja, 200px
    private final double vCano = 200.0; // velocidade dos canos horizontalmente
    protected static Passaro bird;
    private Chao chao;
    protected static ArrayList<Cano> canos = new ArrayList<Cano>();//lista de canos
    private  Timer timer_cano;
   
    public static GameState estadoJogo = GameState.TELAINICIAL;
    public static int contadorPontos = 0;
    public static int maxPts = 0;//maior ponto que o jogador obtêve
    private final GameIcons gameElements = new GameIcons(); 
    
    public FlappyBird(){
        
       new ContadorTempo();//iniciando o cromômetro que peritirá trocar os frames do passaro baseado no valor dos segundos
        
       FlappyBird.bird = new Passaro( 35.0,  ((getAltura()/2)-20) );//(this.getAltura()-112)/2
       
       this.chao = new Chao();
       
       Chao.Y = getAltura()-110;//era 122
       
       this.timer_cano = new Timer(1.0, true, addCano());//Cada vez que o valor de "dt" chega à 2 um novo cano é inserido!
      
      // addCano().executa();//inserindo um canos de forma adiantada (opcional)
    }
    
    
    private Acao addCano(){
     
      //IMPLEMENTANDO INTERFACE 'Acao' UTILIZANDO 'LAMBDA EXPRESSIOM':
      Acao action = ()->{//SOBRESCREVENDO O MÉTODO "public void executa()" DA INTERFACE "Acao"  
  
          if(FlappyBird.estadoJogo == GameState.JOGANDO)   
             canos.add( Cano.criarCano(getLargura(), getAltura(), vCano) ); 
             //inserindo um novo objeto do tipo "cano" na lista de "Canos"
      };
      return action;
        
      //EM VEZ DE USAR A "LAMBDA EXPRESSION" PODERIA TAMBÉM USAR UMA CLASSE ANÔNIMA PARA INSTANCIAR A INTERFACE:
      /*
        return new Acao(){ 
            public void executa(){
                //inserindo um novo objeto do tipo "cano" na lista de "Canos"
                canos.add( Cano.criarCano(getLargura(), getAltura(), vCano) ); 
                
                 //inicializando a lista de canos inserindo 3 canos inicialmente (para testes)
                 //canos.add( Cano.criarCano(getLargura(), getAltura(), this.vCano) );
                 //canos.add( Cano.criarCano(getLargura(), getAltura(), this.vCano) ); 
                 //canos.add( Cano.criarCano(getLargura(), getAltura(), this.vCano) ); 
                 //canos.add( new Cano(getLargura()+Math.random()*800+50, -260+(Math.random()*260), 0));
            }
        };
      */
    }
    
    
    @Override
    public String getTitulo(){
        return "Flappy Bird";
    };
    
    @Override
    public int getLargura(){
        return 384;
    };
    
    @Override
    public int getAltura(){
        return 512;
    };
    
    
    @Override
    public void tique(double dt){//o valor de 'dt' varia de 0.015~0.016
        //parâmetros anteriores: Set<String> teclas, double dt
        
        if(FlappyBird.estadoJogo != FlappyBird.GameState.PAUSADO){

            this.chao_eixoX += dt*gvx;
            this.chao_eixoX = this.chao_eixoX%308;//quando o resto for zero o chao retorna para a posicao inicial, ou seha, quand seu X=0
            //System.out.println("X: "+this.chao_eixoX);

            this.bird.atualiza(dt);//atualizando o objeto do tipo Passaro

            this.chao.atualiza( (float) this.chao_eixoX);

            Cano.atualiza(canos, dt);//atualizando todos os canos existentes na lista "canos"

            timer_cano.tique(dt);//passando o tempo atual para a funcao "timer_cano" que insere novos canos a cada 2 segundos
        
                   
            //VERIFICANDO COLISÃO ENTRE OS CANOS COM O PÁSSARO
            for(Cano c : this.canos){

                if(this.bird.boxPassaro.intersecao(c.boxCanoCima) !=0 ||    //verificando se o passaro colidiu com um dos canos de cima
                        this.bird.boxPassaro.intersecao(c.boxCanoBaixo) !=0 ||  //verificando se o passaro colidiu com um dos canos de baixo 
                            this.bird.boxPassaro.y0 < 0  ||     //verificando se o passaro está fora da tela
                                this.bird.boxPassaro.y1 > getAltura())  //verificando se o passaro está fora da tela

                    FlappyBird.estadoJogo = GameState.GAMEOVER;
              //    elseSystem.out.println(" \n  \n  \n  \n \n \n");  
            }
        
        }
        
    };
    

    @Override
    public void desenhar(Tela tela){
           //cortando a imagem do 0,0 à 288, 512  /rotacao(graus)    X   Y
        try{
            String url = "C:/Users/Patrick/Documents/Arquivos de Programacao/Java/flappy-bird-game/src/playGame/flappy.png";
            //endereco-da-imagem
            
            //DESENHO DE FUNDO DE JOGO
            tela.imagem(url, 0, 0, 288, 512, 0, 0, 0);
            tela.imagem(url, 0, 0, 288, 512, 0, 288, 0);
            
            /*
              x0: posicao X do fundo dentro da imagem "flappy.png"
              x0: posicao Y do fundo dentro da imagem "flappy.png"
              rotacao: numero de graus para rotacionar a imagem no sentido hor ário
              largura: largura da imagem
              altura: altura da imagem
              posX: posicao X do fundo em relacao a janela;
              posY: posicao Y do fundo em relacao a janela;;
            */
        
            Cano.desenha(tela, this.canos, url);//desenhando todos os canos aqui
            
            this.chao.desenhar(tela, url);
            
            this.bird.desenhar(tela, url);
            
            
            if(FlappyBird.estadoJogo == GameState.JOGANDO || FlappyBird.estadoJogo == GameState.PAUSADO)
                this.gameElements.telaJogoIniciado(tela, url, FlappyBird.contadorPontos, this.getLargura(), this.getAltura());
            
            else if(FlappyBird.estadoJogo == GameState.GAMEOVER)
                this.gameElements.telaFimDeJogo(tela, url, this.getLargura(), this.getAltura());
            
            else if(FlappyBird.estadoJogo == GameState.TELAINICIAL)
                this.gameElements.telaInicial(tela, url, this.getLargura(), this.getAltura());
            
            else if(FlappyBird.estadoJogo == GameState.TUTORIAL)
                this.gameElements.tutorial(tela, url, (double) this.getLargura(), (double) this.getAltura());
            
            
        }catch(RuntimeException err){
             System.out.println("erro ao tentar ler a imagem!!! \n"
                       + "descrição do erro: "+err.getMessage());
        }
    };
    
    
    /*
    @Override
    public void tecla(String tecla){
        if(tecla.equals(" ")){
             this.bird.flap();
        };
    };
    */
    
    public static void main(String[] args){
        new Motor(new FlappyBird());
        
    }
}











