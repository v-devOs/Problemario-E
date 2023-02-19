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

  private String[] separarInstruccion( String instruccion){
    return instruccion.split(" ");
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
  Nodo start, end, nodoAux;

  public void controlEntrada( String instruccion, int valor ){

    // System.out.println("Llegue hasta control entrada");
    Nodo nodoEntrada = new Nodo(valor);

    if( start == null ) {
      // System.out.println("Inserte en el inicio");

      end = start = nodoEntrada;}
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

  // System.out.println(start.valor + " entrada en modo pila");
}
private void in( Nodo nodoEntrada ){
  end.nodoSiguiente = nodoEntrada;
  end = end.nodoSiguiente;

  // System.out.println(end.valor + " entrada en modo cola");
}
private void insert( Nodo nodoEntrada ){
  Nodo nodoAuxIntercambio;

  if( start.valor > nodoEntrada.valor ){
    nodoAux = start;
    start = nodoEntrada;
    start.nodoSiguiente = nodoEntrada;
    // System.out.println(start.valor + " entrada en modo lista inicio");

  }
  else if( end.valor < nodoEntrada.valor ){
    end.nodoSiguiente = nodoEntrada;
    end = end.nodoSiguiente;
    // System.out.println(end.valor + " entrada en modo lista final");

  }
  else{
  
    buscarPoscicion(nodoEntrada.valor);
    nodoAuxIntercambio = nodoAux.nodoSiguiente;
    nodoAux.nodoSiguiente = nodoEntrada;
    nodoAux.nodoSiguiente.nodoSiguiente = nodoAuxIntercambio;
    // System.out.println(end.valor + " entrada en modo lista medio");

  }

}
private void buscarPoscicion( int valor ){
  System.out.println("Entre al buscador");
  nodoAux = start;

  while (nodoAux.nodoSiguiente != null && nodoAux.nodoSiguiente.valor < valor) {
    nodoAux = nodoAux.nodoSiguiente;
  }
}
  
  Hibrido(){
    start = null;
    nodoAux = null;
    end = null;
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