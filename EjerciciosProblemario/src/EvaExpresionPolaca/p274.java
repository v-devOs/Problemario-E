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
        evaluador.limpiar();
      }
    } catch (Exception e) {

      input.close();
    }
  }
}

class Evaluador{
  private Pila pilaDatos;
  private String operando1, operando2; 
  private boolean esValidaPorOperandos, esValidaPorOperadores;

  public String evaluarExpresion( String expresion ){
    String partsExpresion[] = expresion.split(" ");
    int index = 0;

    while (esValidaPorOperandos && index < partsExpresion.length) {
      procesarDato( partsExpresion[index] );
      index++;
    }

    validarPorOperadores();

    if( esValidaPorOperadores && esValidaPorOperandos ) return "OK";
    else if( !esValidaPorOperandos ) return "FALTA OPERANDO";
    else return "FALTA OPERADOR";        
  }

  private void validarPorOperadores(){
   if( pilaDatos.totalDatos > 1 ) esValidaPorOperadores = false;
  }

  public void limpiar(){
    pilaDatos.vaciarPila();
    esValidaPorOperadores = esValidaPorOperandos = true;
  }
  private void procesarDato( String dato ){

    switch (dato) {
      case "+":
      case "-":
      case "/":
      case "*":
        controlOperacion(dato);
        break;
    
      default:
        apilarOperando(dato);
        break;
    }
  }

  private void controlOperacion( String operacion ){
    desapilarOperandos();
    validarOperandos();

    String simboloOperacion = "";

    switch (operacion) {
      case "+":
        simboloOperacion = "+";
        break;
      case "-":
        simboloOperacion = "-";
        break;
      case "/":
        simboloOperacion = "/";
        break;
      case "*":
        simboloOperacion = "*";
        break;
    
      default:
        break;
    }

    pilaDatos.push( operando1 + simboloOperacion + operando2 );
  }

  private void desapilarOperandos(){
    operando1 = pilaDatos.pop();
    operando2 = pilaDatos.pop();
  }

  private void validarOperandos(){
    if( operando1 == null || operando2 == null ) esValidaPorOperandos = false;
  }

  private void apilarOperando( String operando ){
    pilaDatos.push(operando);
  }

  Evaluador(){
    esValidaPorOperandos = esValidaPorOperadores = true;
    pilaDatos = new Pila();
    operando1 = operando2 = "";
  }


}

class Pila{
  Nodo tope;
  int totalDatos;

  public void push(String entrada){
    Nodo nodoEntrada = new Nodo(entrada);

    if( tope == null )
      tope = nodoEntrada;
    else{
      nodoEntrada.sig = tope;
      tope = nodoEntrada;
    }
    
    totalDatos++;
  }

  public String pop(){
    if( tope == null )
      return null;
    else{
      Nodo aux = tope;
      tope = tope.sig;
      totalDatos--;
      return aux.valor;
    }
  }

  public void vaciarPila(){
    if( tope != null ){
      while( tope != null ){
        pop();
      }
    }
  }

  Pila(){
    tope = null;
    totalDatos = 0;
  }
}

class Nodo{
  String valor;
  Nodo sig;

  Nodo(String valor){
    this.valor = valor;
  }
}
