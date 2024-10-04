package model;
  
import playGame.Tela;

//CLASSE ABSTRATA QUE SERVIRÁ DE REFERÊNCIA PARA A CLASSE PRINCIPAL DE EXECUÇÃO DO JOGO, A CLASSE "FlappyBird"
abstract public class Jogo {
    abstract public String getTitulo();
    abstract public int getLargura();  
    abstract public int getAltura();
    abstract public void tique(double dt);
   // abstract public void tique(java.util.Set<String> teclas, double dt);
   // abstract public void tecla(String tecla);
    abstract public void desenhar(Tela tela);
}
