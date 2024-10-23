package playGame;
import playGame.FlappyBird.GameState;

/**
 * @author Patrick   
 */   

class GameIcons {//classe que desenha os ícones e botões do jogo
    
    static int pontosC=0; //centenas
    static int pontosD=0; //dezenas
    static boolean exibirIconeNew = false;//Propriedade que define se o ícone "new" (quando há um novo recorde) irá ser renderizado ou não
    static boolean alreadyRun = false;
    static int totalPontosAtual=0; 
     
    //---------------------------------------------------------------------------------------------------------------------
   
    private static void runOnlyOnceTime(){
        GameIcons.totalPontosAtual = ((GameIcons.pontosC*100) + (GameIcons.pontosD*10) + FlappyBird.contadorPontos);
         
        if( GameIcons.totalPontosAtual > FlappyBird.maxPts ){
            
            FlappyBird.maxPts = GameIcons.totalPontosAtual;//Novo recorde!
            GameIcons.exibirIconeNew = true;
             
        }else{ GameIcons.exibirIconeNew = false; }
        
        GameIcons.alreadyRun = true;
        Motor.OK.setVisible(true);//tornando o botão "OK" visível
    }
       
    //---------------------------------------------------------------------------------------------------------------------
    
    private void exibirSaldoPontos(int pontos, Tela t, String urlFoto, double posX, double posY){//função executada quando usuário perde
        
        String[] digitosNumMax = String.valueOf(pontos).split("");
        
        if( digitosNumMax.length == 3){
                desenharNumeros(t, urlFoto, posX-15, posY, Integer.parseInt( digitosNumMax[0]) , false);//centenas
                desenharNumeros(t, urlFoto, posX, posY, Integer.parseInt( digitosNumMax[1]), false);//dezenas
                desenharNumeros(t, urlFoto, posX+15, posY, Integer.parseInt( digitosNumMax[2]), true);//unidades 
                                                       //getDigitN(pontos, 3)
                
        }else if( digitosNumMax.length == 2){//verificando se o numero tem duas casa decimais
                desenharNumeros(t, urlFoto, posX-15, posY, Integer.parseInt( digitosNumMax[0]), false);//dezenas
                desenharNumeros(t, urlFoto, posX, posY, Integer.parseInt( digitosNumMax[1]), true);//unidades
             
        }else if( digitosNumMax.length == 1){
                desenharNumeros(t, urlFoto, posX, posY, pontos, true);//unidades
        }
        
        /*
        //ALTERNANDO A VISIBILIDADE DOS BOTOES DE PAUSE E PLAY
        if(FlappyBird.estadoJogo == GameState.JOGANDO)
            Motor.pause.setVisible(true);
        
        else if(FlappyBird.estadoJogo == GameState.PAUSADO)
            Motor.play.setVisible(true);
*/
    }
    //---------------------------------------------------------------------------------------------------------------------
    
    //BOTOES "START", "PAUSE/CONTINUA" E "OK"
    
    
    protected void telaFimDeJogo(Tela t, String urlFoto, int largura, int altura){//funcao executada quando ocorre GAME OVER
      
        if(GameIcons.alreadyRun==false)
            GameIcons.runOnlyOnceTime();//funcao que roda somente uma vez na tela de game over
        
        t.imagem(urlFoto, 292, 398, 188, 38, 0, (double) (largura/4)+10, (double) (altura/3)-30);//desenho da frase "Game over"
        
        t.imagem(urlFoto, 292, 116, 226, 116, 0, (double) (largura/4)-10, (double) altura/2.8);//game over painel
        
       // t.imagem(urlFoto, 490, 270, 85, 30, 0, (double) (largura)/2 -40, (double) (altura/2)+100 ); //botao "OK"
        
        double posX = (largura/1.5)+10;//Cooredana X do numero de pontos
        double posY = (altura/2)+5;//Cooredana Y do numero de pontos
        
        if(GameIcons.exibirIconeNew ){
            t.imagem(urlFoto, 290, 480, 35, 30, 0, (double) (largura/2)+24, (double) (altura/2)-25); //icone "NEW"
            t.imagem(urlFoto, 600, 275, 50, 45, 0, (double) (largura/4)+10, (double) (altura/2)-30); //medalha de ouro
            
        }else{
            t.imagem(urlFoto, 530, 460, 50, 50, 0, (double) (largura/4)+15, (double) (altura/2)-28); //medalha de prata
        }
        
        exibirSaldoPontos(GameIcons.totalPontosAtual, t, urlFoto, posX, posY-46);//desenhar numero de pontos atuais
        exibirSaldoPontos(FlappyBird.maxPts, t, urlFoto, posX, posY);//desenhar o numero-recorde de pontos que o usuário alcançou na partida anterior
    }
    
    //---------------------------------------------------------------------------------------------------------------------
    
    protected void telaInicial(Tela t, String urlFoto, int largura, int altura){
         GameIcons.pontosC= 0;
         GameIcons.pontosD = 0;
         FlappyBird.contadorPontos = 0;
        
         t.imagem(urlFoto, 292, 346, 195, 45, 0, ((largura)/5.0)+20, altura/4 ); //title "flappy bird"
         
         //t.imagem(urlFoto, 470, 425, 95, 30, 0, (largura)/3.0, (altura/2)+90 ); //botao "START"
    }
    
    //---------------------------------------------------------------------------------------------------------------------
    
    protected void tutorial(Tela t, String urlFoto, double largura, double altura){
         t.imagem(urlFoto, 292, 442, 174, 44, 0, (largura/3.9)+20, altura/4 ); // "GET READY"
          
         t.imagem(urlFoto, 340, 240, 90, 105, 0, (largura/2.4)+10, (altura/2.5) );//figura "TAP" (click), com passaro na cor cinza
   
         //t.imagem(urlFoto, 575, 115, 25, 30, 0, 20, 20); //botao "pausar"
         
         //t.imagem(urlFoto, 575, 165, 25, 30, 0, 20, 20); //botao "play" 
        
         GameIcons.alreadyRun = false;//ativa essa funcao para que ela seja executada somente 1 vez quando a func "exibirMensagem()" for executada (que será varias vezes) no Game Over 
    }
    
    //---------------------------------------------------------------------------------------------------------------------
    
    protected void telaJogoIniciado(Tela t, String urlFoto, int pontos, int largura, int altura){
        double larg = (double) largura -50.0;
        double alt = (double) (altura/7.0)-50;
        
        if(pontos==10){
            GameIcons.pontosD++;
            FlappyBird.contadorPontos=0;// "contadorPontos" representa as unidades da contagem de pontos
        }
        
        if(GameIcons.pontosD==10){
            GameIcons.pontosD=0;
            GameIcons.pontosC++;     
        }
        
        desenharNumeros(t, urlFoto, larg-15, alt, pontosC, false);//centenas
        desenharNumeros(t, urlFoto, larg, alt, pontosD, false);//dezenas
        desenharNumeros(t, urlFoto, larg+15, alt, pontos, true);//unidades
        
        if(GameIcons.pontosC !=0 && GameIcons.pontosD==0)
           desenharNumeros(t, urlFoto, larg, alt, pontosD, true);//dezenas   //O paramentro 'true' permite que o número Zero seja desenhado
    }
    
    //---------------------------------------------------------------------------------------------------------------------
    
    //larg e alt corresponde a posição X e Y dos números
    private void desenharNumeros(Tela t, String urlFoto, double larg, double alt, int pts, boolean exibirNumZero){
        
        switch(pts){
            case 0:
               if(exibirNumZero)
                  t.imagem(urlFoto, 576, 200, 14, 20, 0, larg, alt); break;

            case 1:
              t.imagem(urlFoto, 578, 236, 14, 20, 0, larg, alt); break;

            case 2:
              t.imagem(urlFoto, 578, 268, 14, 20, 0, larg, alt); break;

            case 3:
              t.imagem(urlFoto, 578, 300, 14, 20, 0, larg, alt); break;

            case 4:
              t.imagem(urlFoto, 574, 346, 14, 20, 0, larg, alt); break;

            case 5:
              t.imagem(urlFoto, 574, 370, 14, 20, 0, larg, alt); break;

            case 6:
              t.imagem(urlFoto, 330, 490, 14, 20, 0, larg, alt); break;

            case 7:
              t.imagem(urlFoto, 350, 490, 14, 20, 0, larg, alt); break;

            case 8:
              t.imagem(urlFoto, 370, 490, 14, 20, 0, larg, alt); break;

            case 9:
              t.imagem(urlFoto, 390, 490, 14, 20, 0, larg, alt); break;

            default: ; 
        }
    }
    
    
    
     //System.out.println(" \n  \n  ");
    
}
