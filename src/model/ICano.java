package model;
import java.util.ArrayList;
import playGame.Cano;
import playGame.Tela;
import java.lang.String; 
import playGame.FlappyBird;  
import playGame.FlappyBird.GameState; 
 
/**
 * @author Patrick
 */
public interface ICano {  
    
    int ENTRADA = 120; 
    
    static Cano criarCano(double largura, double altura, double velocidadeCano){
        return new Cano(largura+20.0, altura, -velocidadeCano);
    };
    
    
    static void atualiza(ArrayList<Cano> canos, double dt){
         //VERIFICANDO SE TEM ALGUM CANO PARA FORA DA TELA. SE HOUVER ELE SERÁ APAGADO
        if(canos.size()>0 && canos.get(0).x <= -52){//VERIFICANDO SE TEM ALGUM CANO PARA FORA DA TELA. SE HOUVER ELE SERÁ APAGADO
             canos.remove(canos.get(0));
        } 
     
        
        if(FlappyBird.estadoJogo == GameState.JOGANDO){
            
            for(Cano can : canos){//ATUALIZANDO A POSICAO DE CADA CANO NA LISTA

                 //can.x -= 5; //velocidade constante dos canos
                 can.x += dt*can.vxCano;//velocidade variada (acelerada)
                 //acelerando os canos: V = Vo + a*t;

                 can.boxCanoCima.moverCano(can.x);
                 can.boxCanoBaixo.moverCano(can.x);
                 
                 
                 if(can.boxCanoCima.x0 >= 21.0 && can.boxCanoCima.x0 <= 25.0)
                     ++FlappyBird.contadorPontos;
            }
        }
    };
    
    
    static void desenha(Tela t, ArrayList<Cano> canos, String urlImagem){
         for(Cano cano : canos){
            t.imagem(urlImagem,  604,  0, 52, 270, (double) 0, (double) cano.x, (double) cano.y);//desenhando cano de cima
            t.imagem(urlImagem,  660,  0, 52, 242, (double) 0, (double) cano.x, (double) cano.y+270+Cano.ENTRADA);//desenhando cano de baixo
            
            
            //desenhando hitbox do cano de cima
        /*      t.imagem("cano", cano.x, cano.y, 
                             (cano.boxCanoCima.x1 - cano.x), cano.boxCanoCima.y1, 
                             0, cano.boxCanoCima);
        */    
            
            //desenhando hitbox do cano de baixo
               /*      t.imagem("cano", cano.boxCanoBaixo.x0, cano.boxCanoBaixo.y0, 
                             (cano.boxCanoBaixo.x1 - cano.x), cano.boxCanoBaixo.y1, 
                             0, cano.boxCanoBaixo);
        */
        }
    }
}
