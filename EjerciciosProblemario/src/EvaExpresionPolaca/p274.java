package EvaExpresionPolaca;

import java.util.Scanner;

public class p274 {
  public static void main(String[] args) {
    
    Evaluador evaluador = new Evaluador();
    Scanner input = new Scanner(System.in);
    String expresionEvaluar;

    try {
      while (true) {

        expresionEvaluar = input.nextLine();
        System.out.println(evaluador.evaluarExpresion(expresionEvaluar));
      }
    } catch (Exception e) {

      input.close();
    }
  }
}

class Evaluador{
  private boolean esValida;
  private Pila pilaDatos; 

  public String evaluarExpresion( String expresion ){
    String partsExpresion[] = expresion.split(" ");
    int index = 0;

    while (esValida && index < partsExpresion.length) {
      
      index++;
    }

    return "";
  }

  private void procesarDato( String dato ){

    switch (dato) {
      case "+":
      case "-":
      case "/":
      case "*":
    
      default:
        break;
    }
  }

  private void sumar(){
    
  }
  private void restar(){

  }
  private void dividir(){

  }
  private void multiplicar(){

  }

  private void apilarOperando( String operando ){

  }

  Evaluador(){
    esValida = true;
    pilaDatos = new Pila();
  }


}

class Pila{
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

  Pila(){
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
