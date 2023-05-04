package HuellaTexto;

import java.io.File;
import java.util.Scanner;

public class p359 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Scanner archivo;
    String nombreArchivo;

    try {

      nombreArchivo = input.nextLine();

      archivo = new Scanner( new File(nombreArchivo) );

      while ( archivo.hasNextLine() ) {
        
      }

      archivo.close();
      
    } catch (Exception e) {
      input.close();
      System.err.println(e.toString());
    }

  }
}


class Evaluador extends Validador{
  private int totalPalabras;




}

class Validador{

}

class Cola{
  private Nodo start;
  int size;

  public void in( String palabra ){

    Nodo temp = new Nodo(palabra);
    Nodo aux;

    if( start == null ) start = temp;
    else{
      aux = buscarPosicion();
      aux.sig = temp;
    }

    size++;
  }

  private Nodo buscarPosicion(){
    Nodo auxBusqueda = start;

    while ( auxBusqueda.sig != null ) {
      auxBusqueda = auxBusqueda.sig;
    }

    return auxBusqueda;
  }

  public Nodo out(){
    Nodo salida;

    if( start == null ) return null;
    else{
      salida = start.sig;
      start = start.sig;
      size--;

      return salida;
    }
  }


  public void limpiarCola(){
    start = null;
  }

  Cola(){
    start = null;
    size = 0;
  }
}

class Nodo{
  String palabra;
  Nodo sig;
  int repeticiones;

  public void incrementarRepeticiones(){repeticiones++;}

  Nodo( String palabra ){
    this.palabra = palabra;
    repeticiones = 0;
    sig = null;
  }
  
}

