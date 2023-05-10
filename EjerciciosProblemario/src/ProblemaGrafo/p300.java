package ProblemaGrafo;

import java.util.Scanner;

public class p300 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Grafo grafo = new Grafo();
    String conjVertices, valFila;
    int matriz[][];
    int numVertices, fila;

    try {

      numVertices = Integer.parseInt(input.nextLine());
      matriz = new int[numVertices][numVertices];

      grafo.iniciarMatriz(numVertices);

      for ( fila = 0; fila < matriz.length; fila++) {   
        valFila = input.nextLine();

        grafo.llenarFila(fila, valFila);
      }

      while (true) {
        conjVertices = input.nextLine();

        grafo.setVertGrafo(conjVertices);
        grafo.procesarVertices();
      }
    
    } catch (Exception e) {
      System.err.println(e.toString());
      input.close();
    }
  }
}


class Grafo{
  private int matriz[][];
  private int vertGrafo[];

  public void iniciarMatriz( int numVert ){
    matriz = new int[numVert][numVert];
  }

  public void llenarFila( int fila, String valFila ){

    String valFilaArray[] = valFila.split(" ");
    int index;

    for ( index = 0; index < valFilaArray.length; index++) {
      matriz[fila][index] = Integer.parseInt(valFilaArray[index]);
    }
  }

  public void setVertGrafo( String conjVert){
    vertGrafo = obtenerIndexVert(conjVert);
  }

  public void procesarVertices(){

    boolean hayRuta = true;
    int indexAux = 0, indexActual = vertGrafo[indexAux], indexBuscar = vertGrafo[++indexAux];

    while (hayRuta && indexAux < vertGrafo.length - 1) {
      
      if( matriz[indexActual][indexBuscar] >= 1 ){
        indexActual = indexBuscar;
        indexBuscar = vertGrafo[++indexAux];
      }
      else{
        hayRuta = false;
      }
    }

    if( !hayRuta ){
      System.out.println("NO SUBGRAFO");
    }
    else{
      System.out.println(validarRuta(indexActual, indexBuscar));
    }
  }

  private String validarRuta( int indexActual, int indexBuscar ){
    
    if( matriz[indexActual][indexBuscar] >= 1 && vertGrafo[0] == indexBuscar){
      return "CIRCUITO";
    }
    else if( matriz[indexActual][indexBuscar] >= 1 ) return "CAMINO";
    else return "NO SUBGRAFO";
    
    
  }

  private int[] obtenerIndexVert( String conjVert ){

    String auxVerts[] = conjVert.split(" ");
    int indexVerts[] = new int[auxVerts.length];
    int index;

    for ( index = 0; index < auxVerts.length; index++) {

      indexVerts[index] = (int) auxVerts[index].charAt(0) - 65;
    }

    return indexVerts;
  }

}