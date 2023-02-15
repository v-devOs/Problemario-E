
public class p293{
  public static void main(String[] args) {
    String prueba = "REMOVE 30";

    System.out.println( prueba.startsWith("REMOVE"));
  }
}



class Hibrido{
  Nodo start, nodoAux, end;
  String datosSalida;

  public void controlDatos( String operacion, int valor ){    

    switch ( operacion ) {
      case "PUSH":
      case "IN":
      case "INSERT":
        agregarDato(operacion, valor); break;
      case "POP":
      case "OUT":
      case "REMOVE":
        removerDato(operacion, valor); break;
      default:
        break;
    }
  }

  private void agregarDato( String operacion, int datoInsertar ){
    Nodo nodoInsertar = new Nodo(datoInsertar);

    if( start == null ) 
      start = end = nodoInsertar;
    else if( operacion.equals("PUSH")) 
      push( nodoInsertar );
    else if( operacion.equals("IN")) 
      in( nodoInsertar );
    else 
      insert( nodoInsertar );
  }

  private void push( Nodo nodoInsertar ){
    nodoAux = start;
    start = nodoInsertar;
    nodoInsertar.nodoSiguiente = nodoAux;
  }
  private void in( Nodo nodoInsertar ){
    nodoAux = nodoInsertar;
    end.nodoSiguiente = nodoAux;
    end = nodoAux;
  }
  private void insert( Nodo nodoInsertar ){
    Nodo nodoIntercambio;

    if( start.valor > nodoInsertar.valor ){
      nodoAux = start;
      start = nodoInsertar;
      start.nodoSiguiente = nodoAux;
    }
    else if( end.valor < nodoInsertar.valor){
      end.nodoSiguiente = nodoInsertar;
      end = end.nodoSiguiente;
    }
    else{
      buscarPosicion(nodoInsertar.valor);
      nodoIntercambio = nodoAux.nodoSiguiente;
      nodoAux.nodoSiguiente = nodoInsertar;
      nodoAux.nodoSiguiente.nodoSiguiente = nodoIntercambio;
    }
  }

  private void buscarPosicion( int valorInsertar ){
    nodoAux = start;
    while ( nodoAux.nodoSiguiente != null && nodoAux.nodoSiguiente.valor < valorInsertar ) {
      nodoAux = nodoAux.nodoSiguiente;
    } 
  }

  private void removerDato( String operacion, int valorRemove ){
    if( start == null && end == null ) controlUnderflow(operacion);
    else controlSalidaDatos(operacion, valorRemove);
  }

  private void controlUnderflow( String operacion ){
    String valorSalida;

    switch (operacion) {
      case "OUT":
      case "POP":
        valorSalida = "UNDERFLOW";
        break;
      default:
        valorSalida = "NO DATA";
        break;
    }
    datosSalida += aplicarFormato(valorSalida);
  }

  private void controlSalidaDatos( String operacion, int valorRemove ){
    switch (operacion) {
      case "POP":
        pop();
        break;
      case "OUT":
        out();
        break;
      default:
        // remove( operacion );
        break;
    }
  }

  private void pop(){
    String valorSalida;
    
    nodoAux = start;
    start = start.nodoSiguiente;
    valorSalida = String.valueOf( nodoAux.valor );
    
    datosSalida += aplicarFormato(valorSalida);
  }
  private void out(){
    String valorSalida;

    nodoAux = start;
    start = start.nodoSiguiente;
    valorSalida = String.valueOf(nodoAux.valor);

    datosSalida += aplicarFormato(valorSalida);
   
  }
  private void remove( int valorRemove ){
    if( start.valor == valorRemove ){
      nodoAux = start;
      start = start.nodoSiguiente;
    }
    else{
      
    }
  }

  private String aplicarFormato( String valorSalida ){
    return (datosSalida.length() == 0)
            ? valorSalida
            : ("," + valorSalida);
  }

  Hibrido(){
    datosSalida = "";
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