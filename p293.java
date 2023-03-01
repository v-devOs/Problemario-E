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
    switch (instruccionEstructura) {
      case "PUSH":
      case "IN":
      case "INSERT":
        realizarEntrada(instruccionEstructura, valor);
        break;
      case "POP":
      case "OUT":
      case "REMOVE":
        realizarSalida(instruccionEstructura, valor);
        break;
      case "FINISH":
        cadenaFinal += aplicarFormato("****");
        break;
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

  private String aplicarFormato( String valorSalda ){
    return ( cadenaFinal.length() == 0) ? valorSalda : "," + valorSalda;
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

    if( start == null ) 
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
    start.nodoSiguiente = nodoEntrada;
    tope = start.nodoSiguiente;

    System.out.println( start.valor + " " + tope.valor);
  }
  
  private void insert( Nodo nodoEntrada ){
    Nodo nodoAuxIntercambio;

    if( start.valor > nodoEntrada.valor ){
      nodoAux = start;
      start = nodoEntrada;
      start.nodoSiguiente = nodoAux;

    }
    else if( tope.valor < nodoEntrada.valor ){
      tope.nodoSiguiente = nodoEntrada;
      tope = tope.nodoSiguiente;
    }
    else{
      buscarPoscicion(nodoEntrada.valor);
      nodoAuxIntercambio = nodoAux.nodoSiguiente;
      nodoAux.nodoSiguiente = nodoEntrada;
      nodoAux.nodoSiguiente.nodoSiguiente = nodoAuxIntercambio;
    }
    System.out.println( start.valor + " " + tope.valor);

  }
 
  private void buscarPoscicion( int valor ){
    System.out.println("Entre al buscador");
    nodoAux = start;

    while (nodoAux.nodoSiguiente != null && nodoAux.nodoSiguiente.valor < valor) {
      nodoAux = nodoAux.nodoSiguiente;
    }
  }

  public String controlSalida( String instruccion, int valor ){
    if( start == null )
      return determinarTipoUnderFlow(instruccion);
    else
      return realizarSalida(instruccion, valor);
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
      case "POP":
      case "OUT":
        return popOutAndRemoveStart();
      case "REMOVE":
        return remove(valor);
      default:
        return "";
    }
  }

  private String popOutAndRemoveStart(){
    nodoAux = start;
    start = start.nodoSiguiente;
    return String.valueOf(nodoAux.valor);
  }

  private String remove( int valor ){
    if( start.valor == valor )
      return popOutAndRemoveStart();
    else{
      buscarPoscicion(valor);
      return compararRemover(valor);
    }
  }

  private String compararRemover( int valor ){
    Nodo nodoSalida;

    if( nodoAux.nodoSiguiente == null  )
      return "NO DATA";
    else if( nodoAux.nodoSiguiente.valor == valor ){
      nodoSalida = nodoAux.nodoSiguiente;
      nodoAux.nodoSiguiente = nodoAux.nodoSiguiente.nodoSiguiente;

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

  Nodo(int valor){
    this.valor = valor;
    nodoSiguiente = null;
  }
}