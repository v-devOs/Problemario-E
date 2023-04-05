package Hibrido;

import java.util.Scanner;

public class p293{
  public static void main(String[] args) {
    ControladorHibrido ctrlHibrido = new ControladorHibrido();
    try (Scanner input = new Scanner(System.in)) {
      String instruccion = "";
      int casosPrueba, casosHechos;

      casosPrueba = Integer.parseInt(input.nextLine());

      for ( casosHechos = 1; casosHechos <= casosPrueba; casosHechos++) {
        
        while ( !instruccion.equals("FINISH") ) {
          instruccion = input.nextLine();
          ctrlHibrido.procesarInstruccion(instruccion);
        }

        System.out.println(ctrlHibrido.cadenaFinal);
        instruccion = "";
        ctrlHibrido.resetValuesCtrl();
      }

      input.close();
    } catch (NumberFormatException e) {
      
    }
  }
}

class ControladorHibrido{
  private Hibrido hibrido;
  public String cadenaFinal;

  public void procesarInstruccion(String instruccion){
    analizarInstruccion(instruccion);
  }

  private void analizarInstruccion( String instruccion ){
    String[] instruccionSeparada = separarInstruccion(instruccion);
    ejecutarInstruccion(instruccionSeparada);
  }

  private String[] separarInstruccion( String instruccion){
    return instruccion.split(" ");
  }

  private void ejecutarInstruccion(String[] instruccionSeparada){
    String instruccion = instruccionSeparada[0];
    int valor;

    if( instruccionSeparada.length == 2){
      valor = Integer.parseInt( instruccionSeparada[1]);
      ejecutarInstruccionDoble(instruccion, valor);
    }
    else if( instruccionSeparada.length == 1 ){
      ejecutarInstruccionSimple(instruccion);
    }
  }

  private void ejecutarInstruccionDoble(String instruccion, int valor){
    switch (instruccion) {
      case "PUSH":
      case "IN":
      case "INSERT":
        realizarEntrada( instruccion, valor);
      break;
      case "REMOVE":
        realizarSalida(instruccion, valor);
      
      default:
        break;
    }
  }

  private void ejecutarInstruccionSimple(String instruccion){
    switch (instruccion) {
      case "POP":
      case "OUT":
        realizarSalida(instruccion);
        break;
      case "FINISH":
        cadenaFinal += aplicarFormato("****");
      default:
        break;
    }
  }

  private void realizarEntrada( String instruccion, int valor ){
    hibrido.controlEntrada(instruccion, valor);
  }

  private void realizarSalida( String instruccion, int valor ){
    String valorSalida = hibrido.controlSalida(instruccion, valor);
    cadenaFinal += aplicarFormato(valorSalida);
  }

  private void realizarSalida( String instruccion ){
    String valorSalida = hibrido.controlSalida(instruccion);
    cadenaFinal += aplicarFormato(valorSalida);
  }

  private String aplicarFormato( String valorSalda ){
    return ( cadenaFinal.length() == 0) ? " " + valorSalda : ", " + valorSalda;
  }

  public void resetValuesCtrl(){
    cadenaFinal = "";
    hibrido = new Hibrido();
  }

  ControladorHibrido(){
    cadenaFinal = "";
    hibrido = new Hibrido();
  }
}

class Hibrido{
  Nodo start, tope, nodoAux;
  
  public void controlEntrada( String instruccion, int valor ){
    Nodo nodoEntrada = new Nodo(valor);

    if( start == null && tope == null ) 
      start = tope = nodoEntrada;
    else
      ejecutarEntrada(instruccion, nodoEntrada);
  }

  private void ejecutarEntrada( String instruccion, Nodo nodoEntrada ){
    switch (instruccion) {
      case "PUSH":
      case "IN":
        pushIn(nodoEntrada);
        break;
      case "INSERT":
        insert(nodoEntrada);
        break;    
      default:
        break;
    }
  }

  private void pushIn( Nodo nodoEntrada ){
    tope.nodoSiguiente = nodoEntrada;
    nodoEntrada.nodoAnterior = tope;
    tope = tope.nodoSiguiente;
  }
  
  private void insert( Nodo nodoEntrada ){
    Nodo nodoAuxIntercambio;

    if( start.valor > nodoEntrada.valor ){
      nodoAux = start;
      start = nodoEntrada;
      start.nodoSiguiente = nodoAux;
      nodoAux.nodoAnterior = start;
    }
    else if( tope.valor < nodoEntrada.valor ){
      nodoEntrada.nodoAnterior = tope;
      tope.nodoSiguiente = nodoEntrada;
      tope = tope.nodoSiguiente;
    }
    else{
      buscarPoscicion(nodoEntrada.valor);
      nodoAuxIntercambio = nodoAux.nodoSiguiente;
      nodoEntrada.nodoSiguiente = nodoAuxIntercambio;
      nodoEntrada.nodoAnterior = nodoAux;
      nodoAux.nodoSiguiente = nodoEntrada;
    }
  }
 
  private void buscarPoscicion( int valor ){
    nodoAux = start;

    while (nodoAux.nodoSiguiente != null && nodoAux.nodoSiguiente.valor < valor) {
      nodoAux = nodoAux.nodoSiguiente;
    }
  }

  public String controlSalida( String instruccion, int valor ){
    if( start == null && tope == null )
      return determinarTipoUnderFlow(instruccion);
    else if( start == tope ){
      nodoAux = start;
      start = tope = null;
      return String.valueOf(nodoAux.valor);
    }
    else
      return realizarSalida(instruccion, valor);
  }
  public String controlSalida( String instruccion){
    if( start == null && tope == null )
      return determinarTipoUnderFlow(instruccion);
    else if( start == tope ){
      nodoAux = start;
      start = tope = null;
      return String.valueOf(nodoAux.valor);
    }
    else
      return realizarSalida(instruccion);
  }

  private String determinarTipoUnderFlow( String instruccion ){
    switch (instruccion) {
      case "POP":
      case "OUT":
        return "UNDERFLOW";
      case "REMOVE":
        return "NO DATA";
      default:
        return "";
    }
  }

  private String realizarSalida( String instruccion, int valor ){
    switch (instruccion) {
      case "REMOVE":
        return remove(valor);
      default:
        return "";
    }
  }
  private String realizarSalida( String instruccion){
    switch (instruccion) {
      case "POP":
        return pop();
      case "OUT":
        return outAndRemoveStart();
      default:
        return "";
    }
  }

  private String pop(){
    nodoAux = tope;
    tope = tope.nodoAnterior;

    return String.valueOf(nodoAux.valor);
  }


  private String outAndRemoveStart(){
    nodoAux = start;
    start = start.nodoSiguiente;
    return String.valueOf(nodoAux.valor);
  }

  private String remove( int valor ){
    if( start.valor == valor )
      return outAndRemoveStart();
    else if( tope.valor == valor )
      return pop();
    else{
      buscarPoscicion(valor);
      return compararRemover(valor);
    }
  }

  private String compararRemover( int valor ){
    Nodo nodoSalida, nodoIntercambio;

    if( nodoAux.nodoSiguiente == null  )
      return "NO DATA";
    else if( nodoAux.nodoSiguiente.valor == valor ){
      nodoSalida = nodoAux.nodoSiguiente;
      nodoIntercambio = nodoAux.nodoSiguiente.nodoSiguiente;
      nodoIntercambio.nodoAnterior = nodoAux;
      nodoAux.nodoSiguiente = nodoIntercambio;

      return String.valueOf(nodoSalida.valor);
    }
    else
      return "NO DATA";
  }

  Hibrido(){
    start = null;
    nodoAux = null;
    tope = null;
  }
}

class Nodo{
  int valor;
  Nodo nodoSiguiente;
  Nodo nodoAnterior;

  Nodo(int valor){
    this.valor = valor;
    nodoSiguiente = null;
    nodoAnterior = null;
  }
}