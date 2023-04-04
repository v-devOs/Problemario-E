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
  String binarioInvertido;
  pila pila;

  public void procesarNumero(int numero){
    String binario = Integer.toBinaryString(numero);
    invertirNumero( binario );
  }

  private void invertirNumero( String numero ){
    apilarNumero(numero);

  }
  private void apilarNumero( String numero){
    int index = 0;
    String valorEntrada;


    for ( index = 0; index < numero.length(); index++) {
      valorEntrada = String.valueOf(numero.charAt(index));
      pila.push(valorEntrada);
    }
  }

 

  Procesador(){
    binarioInvertido = "";
    pila = new pila();
  }
}

class pila{
  Nodo tope;

  public void push(String entrada){
    Nodo nodoEntrada = new Nodo(entrada);

    if( tope == null )
      tope = nodoEntrada;
    else{
      nodoEntrada.sig = tope;
      tope = nodoEntrada;
    }
  }

  public String pop(){
    if( tope == null )
      return null;
    else{
      Nodo aux = tope;
      tope = tope.sig;
      return aux.valor;
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