package playGame;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent; 
import java.awt.image.BufferStrategy;
import java.util.TreeSet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Canvas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException; 
import model.Jogo;
import playGame.FlappyBird.GameState;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/** 
 * @author Patrick
 * 
 * Motor do jogo, gerencia a parte gráfica e os eventos de botão, mouse e ActionPerformed
 * 
 */  

//------------------------------ INICIO DO CONSTRUTOR ------------------------------------------
class Motor{
    public Jogo jogo;
    public BufferStrategy strategy;//BufferStrategy é um mecanismo que adapta o jogo à renderização da tela do usuário
   // public TreeSet<String> keySet = new TreeSet<String>();
    
   // private static char teclaAtual = 'a';
    
    static protected JButton start = new JButton();//"start"
    static protected JButton pause = new JButton();//"pause"
    static protected JButton play = new JButton();//botão que tira do pause
    static protected JButton OK = new JButton();//botão que reinicia o jogo após o jogador perder
    
    
    public Motor(FlappyBird j) {//o objeto "j" do tipo "Jogo" ou também o tipo "FlappyBird" vem da classe-filha "FlappyBird" e classe-pai "jogo"
        this.jogo = j;
        Canvas canvas = new Canvas();
        JFrame container = new JFrame(jogo.getTitulo()); 
        JPanel panel = new JPanel(); //(JPanel) container.getContentPane();  JPanel panel = new JPanel(); //(JPanel) container.getContentPane(); 
        
        //------------------------------------
        CliqueBotao.inserirDimensoesImagem(j.getLargura(), j.getAltura(), "C:/Users/Patrick/Documents/Arquivos de Programacao/Java/flappy-bird-game/src/playGame/flappy.png");
        
        try{
            //TORNANDO OS BOTOES CLICAVÉIS
            CliqueBotao.tornar_Start_Clicavel( Motor.start);
            CliqueBotao.tornar_OK_Clicavel( Motor.OK);
            CliqueBotao.tornar_Pause_Clicavel( Motor.pause);
            CliqueBotao.tornar_Play_Clicavel( Motor.play);
            CliqueBotao.ajustarBotoes();
                    
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, null, "Erro ao tentar ler a imagem dos botões!!!", JOptionPane.ERROR_MESSAGE);
        }
         
        //ADICIONANDO OS BOTÕES AO JFRAME
        panel.add(Motor.start);
        panel.add(Motor.OK);
        panel.add(Motor.pause);
        panel.add(Motor.play);
        //panel.revalidate();
        //panel.repaint();
        
        panel.setPreferredSize(new Dimension(jogo.getLargura(), jogo.getAltura())); //panel.setSize(new Dimension(jogo.getLargura(), jogo.getAltura()));
        panel.setLayout(null);//estava como null   //petendo deixar com: new BorderLayout()
        
        
        //AJUSTANDO O GRÁFICO DO JOGO CONFORME O AMBIENTE DO USUÁRIO:
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        Rectangle bounds = gs[gs.length-1].getDefaultConfiguration().getBounds();
        
        container.setResizable(false);//Desativando o redimensionamento de tela
        
        //Configurando o local por onde a tela do jogo será exibida no PC do usuário
        container.setBounds( bounds.x+(bounds.width - jogo.getLargura())/2, 
                                   bounds.y+(bounds.height - jogo.getAltura())/2,
                                       jogo.getLargura(),
                                             jogo.getAltura() );
      
        container.setMinimumSize( new Dimension(jogo.getLargura(), jogo.getAltura()) );//container.setSize( 700, 700);
        
        //setando o Bounds do canvas de acordo com o tamanho do "container" do tipo "JFrame"
        canvas.setBounds(0, 0, jogo.getLargura(), jogo.getAltura());
        panel.add(canvas);        
        canvas.setIgnoreRepaint(true);
        container.add(panel);
        container.pack();
        container.setVisible(true);
        
        //ATRIBUINDO UM EVENTO AO jFrame "container"
        container.addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        
        //ATRIBUINDO UM EVENTO DE  PRESSIONAMENTO DE TECLA AO CANVAS 
        canvas.addKeyListener( new KeyListener() {
            
            //methid does not override or implement a method from a supertype
            @Override
            public void keyPressed(KeyEvent evt) {
               // keySet.add(Motor.keyString(evt));
            }
            
            @Override
            public void keyReleased(KeyEvent evt) {
               // keySet.remove(Motor.keyString(evt));
            }
            
            @Override
            public void keyTyped(KeyEvent evt) {
                //jogo.tecla(Motor.keyString(evt));
                FlappyBird.bird.flap();
                System.out.println("tecla pulou");
            }
        });
        
       
        //ATRIBUINDO UM EVENTO AO jFrame "container"
        canvas.addMouseListener( new MouseListener(){
             @Override
             public void mouseClicked(MouseEvent e){
                 
                 switch(FlappyBird.estadoJogo){
                    case TUTORIAL:
                        FlappyBird.estadoJogo = GameState.JOGANDO; 
                        FlappyBird.bird.flap();//pulo automatico apertar o botao ok (opcional)
                        break;

                    case JOGANDO:
                        //jogo.tecla(" "); 
                        FlappyBird.bird.flap();
                        break;
                        
                    default:
                        System.out.println("Erro ao trocar de tela....");
                 }
             };
           
             @Override
             public void mousePressed(MouseEvent e){ };
             @Override
             public void mouseReleased(MouseEvent e){ };
             @Override
             public void mouseEntered(MouseEvent e){ };
             @Override
             public void mouseExited(MouseEvent e){ };
        });
        
        
        canvas.createBufferStrategy(2);//criando um numero de estratégias para melhor performance de acordo com os 2 buffers implementados
        strategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        mainLoop();
    }
    //------------------------ FIM DO CONSTRUTOR -----------------------------------
    
    
    //método com uma thread de loop infinito
    private void mainLoop() {
         Timer t = new Timer(5, new ActionListener() {//delay de 5 milisegundos ´para executar um ActionListener 
       //Esse "Timer" está no pacote java.swing   
            
            long t0 = 0;
       
            @Override
            public void actionPerformed(ActionEvent evt) {//chamado quando uma ação ocorre
                long t1 = System.currentTimeMillis();
                
                if(t0 == 0)
                    t0 = t1;
                
                if(t1 > t0) {
                    Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
                    g.setColor( Color.GRAY );
                    g.fillRect(0, 0, jogo.getLargura(), jogo.getAltura());

                    double dt = (t1 - t0) / 1000.0;

                    t0 = t1;//passando os segundos decorridos atualmente para o 't0'
                    jogo.tique(dt);//passando uma lista das teclas que foram clicadas junto com o segundos decorridos    
                    jogo.desenhar(new Tela(g));

                    strategy.show();
                }
                
            }//actionPerformed
        });//Timer
            
        t.start();
    }

    //----------------------------------------------------------------------------
    
    
    /*
    private static String keyString(KeyEvent evt) {//recebendo um objeto do tipo "KeyEvent" lançado pelo evento ""
            
        if(evt.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {
            return String.valueOf(evt.getKeyChar()).toLowerCase();
            
        } else {//verificando o código ASCII da tecla apertada caso ela não tenha um valor em 'char'
            switch(evt.getKeyCode()) {
                case KeyEvent.VK_ALT: return "alt";
                case KeyEvent.VK_CONTROL: return "control";
                case KeyEvent.VK_SHIFT: return "shift";
                case KeyEvent.VK_LEFT: return "left";
                case KeyEvent.VK_RIGHT: return "right";
                case KeyEvent.VK_UP: return "up";
                case KeyEvent.VK_DOWN: return "down";
                case KeyEvent.VK_ENTER: return "enter";
                case KeyEvent.VK_DELETE: return "delete";
                case KeyEvent.VK_TAB: return "tab";
                case KeyEvent.VK_WINDOWS: return "windows";
                case KeyEvent.VK_BACK_SPACE: return "backspace";
                case KeyEvent.VK_ALT_GRAPH: return "altgr";
                case KeyEvent.VK_F1: return "F1";
                case KeyEvent.VK_F2: return "F2";
                case KeyEvent.VK_F3: return "F3";
                case KeyEvent.VK_F4: return "F4";
                case KeyEvent.VK_F5: return "F5";
                case KeyEvent.VK_F6: return "F6";
                case KeyEvent.VK_F7: return "F7";
                case KeyEvent.VK_F8: return "F8";
                case KeyEvent.VK_F9: return "F9";
                case KeyEvent.VK_F10: return "F10";
                case KeyEvent.VK_F11: return "F11";
                case KeyEvent.VK_F12: return "F12";
                default: return "";
            }
        }
    }
    
    //----------------------------------------------------------------------------

    public static void tocar(String filename) {
        try {
            Clip clip = AudioSystem.getClip(); 
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.start();
        } catch(Exception e) {
            System.out.print("não foi possivel tocar o som.... / Error: "+e);
        }
    }

    */
}
