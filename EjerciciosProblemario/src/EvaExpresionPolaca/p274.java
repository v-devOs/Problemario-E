package EvaExpresionPolaca;

import java.util.Scanner;


public class p274 {
  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);
    Evaluador evaluador = new Evaluador();
    String expresion;
    
    try {

      while (true) {

        expresion = input.nextLine();
        evaluador.evaluarExpresion(expresion.split(" "));

      }
      
    } catch (Exception e) {

      input.close();
    }
  }
  
}

class Evaluador{
  private Pila pilaDatos;
  

  public void evaluarExpresion( String[] splitedExp ){
    boolean esValidaPorOperadores, esValidaPorOperandos = true;
    int index = 0;

    while ( esValidaPorOperandos && index < splitedExp.length ) {
      
      esValidaPorOperandos = evaluarPartExp(splitedExp[index]);

      index++;
    }

    esValidaPorOperadores = validarPorOperadores();

    mostrarMensaje( esValidaPorOperandos, esValidaPorOperadores );

    limpiarPila();
  }

  private boolean evaluarPartExp( String partExp ){

    switch (partExp) {
      case "+":
      case "-":
      case "*":
      case "/":
      case "^":
        return realizarOperacion(partExp);

      default:
        return apilarOperando(partExp);
    }
  }

  private boolean realizarOperacion( String op ){
    
    String operan1, operan2;

    if( pilaDatos.size < 2 ){
      return false;
    }
    else{

      operan1 = pilaDatos.pop();
      operan2 = pilaDatos.pop();

      pilaDatos.push( operan1 + op + operan2 );

      return true;
    }
  }

  private boolean apilarOperando( String operando ){

    if( !operando.equals("(") ){
      pilaDatos.push(operando);
    }

    return true; 
  }

  private boolean validarPorOperadores(){
    
    if( pilaDatos.size == 1 ) return true;
    else return false;
  }

  private void mostrarMensaje( boolean validOperandos, boolean validOperadores ){

    if( validOperadores && validOperandos ) System.out.println("OK");
    else if( !validOperandos && validOperadores ) System.out.println("FALTA OPERANDO");
    else if( validOperandos && !validOperadores ) System.out.println("FALTA OPERADOR");
  }

  private void limpiarPila(){
    pilaDatos.limpiar();
  }

  Evaluador(){
    pilaDatos = new Pila();
  }

}

class Pila{
  int size;
  private Nodo tope;

  public void push( String valor ){
    Nodo temp = new Nodo(valor);

    if( tope == null ) tope = temp;
    else{
      temp.sig = tope;
      tope = temp;
    }

    size++;
  }

  public String pop(){
    Nodo aux;

    if( tope == null ) return null;
    else{
      aux = tope;
      tope = tope.sig;
      size--;
      return aux.valor;
    }
  }

  public void limpiar(){
    tope = null;
    size = 0;
  }

  Pila(){
    tope = null;
    size = 0;
  }
}

class Nodo{
  String valor;
  Nodo sig;

  Nodo( String valor ){
    this.valor = valor;
    sig = null;
  }
}