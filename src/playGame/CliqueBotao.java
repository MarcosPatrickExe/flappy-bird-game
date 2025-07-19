package playGame;
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener;    
import java.awt.image.BufferedImage;      
import java.io.File;          
import java.io.IOException;   
import javax.imageio.ImageIO;     
import javax.swing.Icon;     
import javax.swing.ImageIcon;   
import javax.swing.JButton;   
import javax.swing.SwingConstants;   
import playGame.FlappyBird.GameState; 

/**    
 * @author Patrick 
 please dont't change
 */

class Arrive{
  String nameArri_ = "";
  int myIntNormal = 12;
 
}

//Botao is not abstract and does not override abstract method mouseExited() in MouseLitener
 abstract class CliqueBotao implements MouseListener{
    
    private static int largura;
    private static int altura;
    private static String urlImagem; 
     
    static protected void inserirDimensoesImagem(int larg, int alt, String urlImg){
        CliqueBotao.largura = larg;
        CliqueBotao.altura = alt; 
        CliqueBotao.urlImagem = urlImg;
    }
    
    /*
    private String tipoBotao;
    
    Botao(String nomeBotao){
        super(nomeBotao);
        this.tipoBotao = nomeBotao;
    }
    
    protected void ocultarBotao(){
        super.setVisible(false);
    };
    
    protected void exibirBotao(){ 
        super.setVisible(true);
    }
    */
    
     @Override
     public abstract void mouseClicked(MouseEvent e);
     //ESSE MÉTODO ABSTRATO É POLIMÓRFICO, OU SEJA, SEU COMPORTAMENTO MUDA PARA CADA TIPD DE BOTÃO, PODENDO ELE SER "START", "PAUSE" OU "OK"

     @Override
     public void mousePressed(MouseEvent e){ };
             
     @Override
     public void mouseReleased(MouseEvent e){ };
             
     @Override
     public void mouseEntered(MouseEvent e){ };
             
     @Override
     public void mouseExited(MouseEvent e){ };

  
     static protected void ajustarBotoes2(){ // metodo para teste de bug
        Motor.start.setVisible(false);
        Motor.OK.setVisible(false);
        Motor.pause.setVisible(true);
        Motor.play.setVisible(false);
     }
     
     
     static protected void ajustarBotoes(){
        Motor.start.setVisible(true);
        Motor.OK.setVisible(false);
        Motor.pause.setVisible(false);
        Motor.play.setVisible(false);
     }
     
     //---------------------------------------------------------------------------------------------------------------------
     
     static protected void tornar_Start_Clicavel( JButton str) throws IOException{//PASSAGEM POR REFERÊNCIA
     
         str.addMouseListener( new CliqueBotao(){//sobrecrevendo o metodo "mouseClicked()" para o botao "START"
        
             @Override
             public void mouseClicked(MouseEvent e){
                 
                   if( FlappyBird.estadoJogo == GameState.TELAINICIAL){
                        FlappyBird.estadoJogo = GameState.TUTORIAL;
                        Motor.start.setVisible(false);
                        Motor.pause.setVisible(true);
                   }
                   
             };//MouseEvent
         });//mouseClicked
         
         
         //unreported exception IOException; must be caught or declared to be thrown
         BufferedImage originalImage = ImageIO.read( new File(CliqueBotao.urlImagem));
         str.setIcon( new ImageIcon( originalImage.getSubimage( 484, 425, 80, 30) ));//localizacao do botao "START" dentro da imagem "flappy.png"
         //str.setHorizontalAlignment( SwingConstants.CENTER );//localização do botao no eixo X
         //str.setVerticalAlignment( SwingConstants.BOTTOM );//localização do botão no eixo Y
         
         str.setSize(80, 28);
         str.setLocation( (CliqueBotao.largura/3)+20, (CliqueBotao.altura/2)+90 );
     }
     
     //---------------------------------------------------------------------------------------------------------------------
     
     static protected void tornar_OK_Clicavel( JButton OK) throws IOException{//PASSAGEM POR REFERÊNCIA
     
         OK.addMouseListener( new CliqueBotao(){//sobrecrevendo o metodo "mouseClicked()" para o botao "OK"
        
             @Override
             public void mouseClicked(MouseEvent e){
                 
                 if( FlappyBird.estadoJogo == GameState.GAMEOVER){//REINICIANDO O JOGO
                        FlappyBird.bird = new Passaro( 35,(512/2)-20);
                        FlappyBird.canos.clear();
                        FlappyBird.estadoJogo = GameState.TELAINICIAL;
                        Motor.start.setVisible(true);
                        Motor.OK.setVisible(false);
                        Motor.pause.setVisible(false);
                 }
                 
             }//mouseClicked
        });//mouseClicked
         
         
        BufferedImage originalImage = ImageIO.read( new File(CliqueBotao.urlImagem));
        OK.setIcon( new ImageIcon( originalImage.getSubimage( 490, 267, 85, 30) ));//localizacao do botao "START" dentro da imagem "flappy.png"
        OK.setSize(81, 28);
        OK.setLocation( (CliqueBotao.largura/3)+20, (CliqueBotao.altura/2)+90 );
     }
     
     //---------------------------------------------------------------------------------------------------------------------
     
     static protected void tornar_Pause_Clicavel( JButton pse) throws IOException{//PASSAGEM POR REFERÊNCIA
     
         pse.addMouseListener( new CliqueBotao(){//sobrecrevendo o metodo "mouseClicked()" para o botao "PAUSE"
        
             @Override
             public void mouseClicked(MouseEvent e){
               
                  if( FlappyBird.estadoJogo == GameState.JOGANDO){
                        FlappyBird.estadoJogo = GameState.PAUSADO;
                        Motor.pause.setVisible(false);
                        Motor.play.setVisible(true);
                  }
                  
             }
        });//mouseClicked
         
         
        BufferedImage originalImage = ImageIO.read( new File(CliqueBotao.urlImagem));
        pse.setIcon( new ImageIcon( originalImage.getSubimage( 575, 115, 25, 30)) );//localizacao do botao "START" dentro da imagem "flappy.png"
        pse.setSize(25, 30);
        pse.setLocation(20, 20);
        
     }
     
     //---------------------------------------------------------------------------------------------------------------------
     
     static protected void tornar_Play_Clicavel( JButton play) throws IOException{//PASSAGEM POR REFERÊNCIA
     
         play.addMouseListener( new CliqueBotao(){//sobrecrevendo o metodo "mouseClicked()" para o botao "PLAY"
        
             @Override
             public void mouseClicked(MouseEvent e){
               
                 if(FlappyBird.estadoJogo == GameState.PAUSADO){
                      FlappyBird.estadoJogo = GameState.JOGANDO;
                      Motor.pause.setVisible(true);
                      Motor.play.setVisible(false);
                 }
                 
             }
        });//mouseClicked
         
        BufferedImage originalImage = ImageIO.read( new File(CliqueBotao.urlImagem));
        play.setIcon( new ImageIcon( originalImage.getSubimage( 575, 165, 25, 30) ));//localizacao do botao "START" dentro da imagem "flappy.png"
        play.setSize(25, 27);
        play.setLocation(20, 20);
     }
}


//OU:
        /*
        KeyListener keyE = new KeyListener(){
            
            public void keyPressed(KeyEvent evt) {
                keySet.add(Motor.keyString(evt));
            }
            
            public void keyReleased(KeyEvent evt) {
                keySet.remove(Motor.keyString(evt));
            }
            
            public void keyTyped(KeyEvent evt) {
                jogo.tecla(Motor.keyString(evt));
            }
        };
        
        canvas.addKeyListener(keyE);
     */
