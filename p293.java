public class p293{
  public static void main(String[] args) {
    
  }
}



class Hibrido{
  Nodo start, nodoAux;
  String datosSalida;

  public void controlDatos( String operacion, int datoInsertar ){
    switch (operacion) {
      case "POP":
      case "IN":
      case "INSERT":
        agregarDato(operacion, datoInsertar); break;
      case "PUSH":
      case "OUT":
      case "REMOVE":
        removerDato(operacion); break;
      default:
        break;
    }

  }

  private void agregarDato( String operacion, int datoInsertar ){
    Nodo nodoInsertar = new Nodo(datoInsertar);

    if( start == null ) start = nodoInsertar;
    else if( operacion.equals("PUSH")) push( nodoInsertar );
    else if( operacion.equals("IN")) in( nodoInsertar );
    else insert( nodoInsertar );
  }

  private void removerDato( String operacion ){
    if( operacion.equals("PUSH")) pop();
    else if( operacion.equals("IN")) out();
    else remove();
  }

  private void push( Nodo nodoInsertar ){
    nodoAux = start;
    start = nodoInsertar;
    nodoInsertar.nodoSiguiente = nodoAux;
  }
  private void in( Nodo nodoInsertar ){
    buscarPosicion();
    nodoAux.nodoSiguiente = nodoInsertar;
  }
  private void insert( Nodo nodoInsertar ){
    Nodo nodoIntercambio;

    if( start.valor > nodoInsertar.valor ){
      nodoAux = start;
      start = nodoInsertar;
      start.nodoSiguiente = nodoAux;
    }
    else{
      buscarPosicion(nodoInsertar.valor);
      nodoIntercambio = nodoAux.nodoSiguiente;
      nodoAux.nodoSiguiente = nodoInsertar;
      nodoAux.nodoSiguiente.nodoSiguiente = nodoIntercambio;
    }
  }

  private void buscarPosicion(){
    nodoAux = start;
    while (nodoAux.nodoSiguiente != null) {
      nodoAux = nodoAux.nodoSiguiente;
    }
  }

  private void buscarPosicion( int valorInsertar ){
    nodoAux = start;
    while ( nodoAux.nodoSiguiente != null && nodoAux.nodoSiguiente.valor < valorInsertar ) {
      nodoAux = nodoAux.nodoSiguiente;
    }
    
  }

  private void pop(){
    String valorSalida;
    
    if( start == null ){
      valorSalida = "UNDERFLOW";
    }
    else{
      nodoAux = start;
      start = start.nodoSiguiente;
      valorSalida = String.valueOf( nodoAux.valor );
    }

    datosSalida += aplicarFormato(valorSalida);
  }
  private void out(){}
  private void remove(){}

  private String aplicarFormato( String valorSalida ){
    return (datosSalida.length() == 0)
            ? valorSalida
            : ("," + valorSalida);
  }

  Hibrido(){
    datosSalida = "";
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