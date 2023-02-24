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
        instruccion = "";
      }

      ctrlHibrido.mostar();

      

      input.close();
    } catch (NumberFormatException e) {
      
    }
  }
}

class ControladorHibrido{
  private Hibrido hibrido;
  private String cadenaFinal;

  public void procesarInstruccion(String instruccion){
    analizarInstruccion(instruccion);
  }

  public void mostar(){

    hibrido.mostar();
  }

  private void analizarInstruccion( String instruccion ){
    String[] instruccionSeparada = separarInstruccion(instruccion);
    String instruccionEstructura;
    int valor = 0;

    if( instruccionSeparada.length > 1){
      instruccionEstructura = instruccionSeparada[0];
      valor = Integer.parseInt(instruccionSeparada[1]);
    }
    else
      instruccionEstructura = instruccionSeparada[0];
    
    determinarEjecutarInstruccion(instruccionEstructura, valor);
    
  }

  private String[] separarInstruccion( String instruccion){
    return instruccion.split(" ");
  }

  private void determinarEjecutarInstruccion( String instruccionEstructura, int valor ){
    // System.out.println(instruccionEstructura + " llegue hasta determinar " + valor);
    switch (instruccionEstructura) {
      case "PUSH":
      case "IN":
      case "INSERT":
        realizarEntrada(instruccionEstructura, valor);
        break;
      case "POP":
      case "OUT":
      case "REMOVE":
      default:
        break;
    }
  }

  private String realizarEntrada( String instruccion, int valor ){
    hibrido.controlEntrada(instruccion, valor);
    return "";
  }

  ControladorHibrido(){
    cadenaFinal = "";
    hibrido = new Hibrido();
  }
}

class Hibrido{
  Nodo start, nodoAux;

  public void controlEntrada( String instruccion, int valor ){
    Nodo nodoEntrada = new Nodo(valor);

    if( start == null ) 
      start = nodoEntrada;
    else
      ejecutarEntrada(instruccion, nodoEntrada);

  }

  private void ejecutarEntrada( String instruccion, Nodo nodoEntrada ){
    switch (instruccion) {
      case "PUSH":
        push(nodoEntrada);
        break;
      case "IN":
        in(nodoEntrada);
        break;
      case "INSERT":
        insert(nodoEntrada);
        break;    
      default:
        break;
    }
  }

  private void push( Nodo nodoEntrada ){
    nodoAux = start;
    start = nodoEntrada;
    start.nodoSiguiente = nodoAux;
  }
  private void in( Nodo nodoEntrada ){
    buscarPoscicion();
    nodoAux.nodoSiguiente = nodoEntrada;
  }
  private void insert( Nodo nodoEntrada ){
    Nodo nodoAuxIntercambio;

    if( start.valor > nodoEntrada.valor ){
      nodoAux = start;
      start = nodoEntrada;
      start.nodoSiguiente = nodoAux;
    }
    else{
      buscarPoscicion(nodoEntrada.valor);
      nodoAuxIntercambio = nodoAux.nodoSiguiente;
      nodoAux.nodoSiguiente = nodoEntrada;
      nodoAux.nodoSiguiente.nodoSiguiente = nodoAuxIntercambio;
    }
  }
  private void buscarPoscicion(){
    nodoAux = start;

    while (nodoAux.nodoSiguiente != null) {
      nodoAux = nodoAux.nodoSiguiente;
    }
  }
  private void buscarPoscicion( int valor ){
    System.out.println("Entre al buscador");
    nodoAux = start;

    while (nodoAux.nodoSiguiente != null && nodoAux.nodoSiguiente.valor < valor) {
      nodoAux = nodoAux.nodoSiguiente;
    }
  }

  public void mostar(){

    nodoAux = start;

    System.out.println(nodoAux.valor);

    while (nodoAux.nodoSiguiente != null) {
      nodoAux = nodoAux.nodoSiguiente;
      System.out.println(nodoAux.valor);
    }
  }
  
  Hibrido(){
    start = null;
    nodoAux = null;
  }
}

class Nodo{
  int valor;
  Nodo nodoSiguiente;

  Nodo(int valor){
    this.valor = valor;
    nodoSiguiente = null;
  }
}