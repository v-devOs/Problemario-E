import java.util.Scanner;

public class p402 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Procesador procesador = new Procesador();

    try {
      int contador, casosPrueba;

      casosPrueba = input.nextInt();

      for (contador = 0;contador < casosPrueba;contador++) {
        
      }
      
      input.close();
    } catch (Exception e) {
      input.close();
    }
  }
}

class Procesador{

}

class pila{
  Nodo tope;

  public void input(String entrada){
    Nodo nodoEntrada = new Nodo(entrada);

    if( tope == null )
      tope = nodoEntrada;
    else{
      nodoEntrada.sig = tope;
      tope = nodoEntrada;
    }
  }

  public Nodo output(){
    if( tope == null )
      return null;
    else{
      Nodo aux = tope;
      tope = tope.sig;
      return aux;
    }
  }

  pila(){
    tope = null;
  }
}

class Nodo{
  String valor;
  Nodo sig;

  Nodo(String valor){
    this.valor = valor;
  }
}