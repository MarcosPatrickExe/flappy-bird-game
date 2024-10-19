package model;
import playGame.Tela; 
 
/**
 * @author Patrick  
 */ 
public interface IPassaro { 
    double G = 1000;
    double FLAP = -350;
    
    void atualiza(double dt);
    void flap(); 
    void desenhar(Tela tela, String urlImagem);
}
