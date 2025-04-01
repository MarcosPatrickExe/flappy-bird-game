package playGame;       
import model.IChao;      
import playGame.FlappyBird.GameState;     
    
/**      
 * @author Patrick   
 */   
class Chao implements IChao{
     
    private float X;
    protected static int Y = 0;
   // protected int X0;//posicao inicial (no eixo X) de onde o "chao" será desenhado 
    
    @Override
    public void desenhar(Tela t, String URLarquivo){
            t.imagem(URLarquivo, 292, 0, 308, 112, 0, 0-this.X, Chao.Y);
            t.imagem(URLarquivo, 292, 0, 308, 112, 0, 308-this.X, Chao.Y);// 308 é o valor da largura do chão. Desenhando o 'segundo' chão a partir do eixo X, em 308.
            t.imagem(URLarquivo, 292, 0, 308, 112, 0, (308*2)-this.X, Chao.Y);
    }  
    
    @Override  
    public void atualiza(float x){
         
        if(FlappyBird.estadoJogo != GameState.GAMEOVER)
            this.X = x;
    } 
}
 
