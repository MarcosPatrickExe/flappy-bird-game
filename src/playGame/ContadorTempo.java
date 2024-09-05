package playGame;
import java.lang.*;

/**
 * @author Patrick 
 */

class ContadorTempo implements Runnable{
    
    protected static int segundoAtual=0;
    
    ContadorTempo(){
       new Thread(this).start();//iniciando o contador
    }
    
    @Override
    public void run(){ 
        
       try{
            for(int i=0; i>=0; i++){
                segundoAtual=i;
                Thread.sleep(100);
            }
        
       }catch(InterruptedException ie){
           System.out.println("Erro na execução do cronômetro. Error descrition: "+ie.getMessage());
       }
       
    }
}
