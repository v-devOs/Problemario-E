package ABOD;

import java.util.Scanner;

public class p285 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Evaluador evaluador = new Evaluador();

    int casosPrueba, valInsert, count;

    try {

      casosPrueba = input.nextInt();

      for ( count = 0; count < casosPrueba; count++) {
        
        valInsert = input.nextInt();

        while (valInsert != 0) {

          evaluador.insertarValor(valInsert);
          valInsert = input.nextInt();
        }
        
        evaluador.encontrarAlturas();
        evaluador.limpiarDatos();
      }

      
    } catch (Exception e) {
      System.err.println(e.toString());
      input.close();
    }
    
  }
}


class Evaluador{
  private Arbol arbol;
  // private int indexAltMayor;

  public void insertarValor( int valor ){
    arbol.insert(valor);
  }

  public void encontrarAlturas(){
    arbol.recorerArbol(arbol.raiz);
    // int altMayor = 0, altMenor = 0;

    // mostrarAlturas(altMayor, altMenor);
  }


  private void mostrarAlturas( int altMayor, int altMenor ){
    System.out.println(altMenor + " " + altMayor);
  }

  public void limpiarDatos(){
    arbol.limpiarArbol();
  }


  Evaluador(){
    arbol = new Arbol();
  }
}



class Arbol{
  Nodo raiz;

  public void insert( int valor ){
    Nodo temp = new Nodo(valor);

    if( raiz == null ){
      raiz = temp;
    } 
    else{
      Nodo auxBusqueda = raiz;
      Nodo seguidor = auxBusqueda;

      do{
        seguidor = auxBusqueda;

        if( valor > auxBusqueda.info ){
          auxBusqueda = auxBusqueda.der;
        }
        else{
          auxBusqueda = auxBusqueda.izq;
        }
      }while( auxBusqueda != null );
      
      if( valor > seguidor.info ){
        seguidor.der = temp;
      }
      else{
        seguidor.izq = temp;
      }
    } 
  }

  int altura = 1;

  public void recorerArbol( Nodo aux ){


    if( aux.izq == null && aux.der == null ){
      System.out.println(altura + " Salida altura" + " " + aux.info);
    }

    if( aux.izq != null ){
      altura++;
      recorerArbol(aux.izq);
    }

    if( aux == raiz ) altura = 1;
        
    if( aux.der != null ){
      altura++;
      recorerArbol(aux.der);
    }

    altura--;
  }

  public void limpiarArbol(){
    raiz = null;
    altura = 1;
  }

  Arbol(){
    raiz  = null;
  }
  
}

class Nodo {
  int info;

  Nodo izq, der;

  Nodo( int info ){
    this.info = info;
    izq = der = null;
  }
}
