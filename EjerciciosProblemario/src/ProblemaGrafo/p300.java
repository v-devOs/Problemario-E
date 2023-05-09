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
        grafo.procesarVertices(conjVertices);
      }
    
    } catch (Exception e) {
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

  public void procesarVertices( String conjVert ){

    boolean hayCamino = true;
    int indexActual = 0, indexBuscar, indexAux = 0, limit = 1;

    if( vertGrafo[0] == vertGrafo[vertGrafo.length - 1] ) limit = 2;


    while ( hayCamino && indexAux < vertGrafo.length - limit ){

      indexActual = vertGrafo[indexAux];
      indexBuscar = vertGrafo[indexAux + 1];
      
      if( matriz[indexActual][indexBuscar] == 1 ){
        indexActual = indexBuscar;
        indexBuscar = vertGrafo[++indexAux];
      }
      else{
        hayCamino = false;
      }
    }

    if( hayCamino ){
      System.out.println(validarRuta(indexActual , vertGrafo[0]));
    }
    else{
      System.out.println("NO SUBGRAFO");
    }
  }

  private String validarRuta( int indexActual, int indexBuscar ){
    
    if( vertGrafo[0] == vertGrafo[ vertGrafo.length -1 ] ){
      return validarCircuito(indexActual, indexBuscar);
    }
    else{
      return "Camino";
    }
    // return matriz[indexActual][indexBuscar] == 1 ? "CIRCUITO" : "CAMINO";
  }

  private String validarCircuito( int indexActual, int indexBuscar){
    return matriz[indexActual][indexBuscar] == 1 ? "CIRCUITO" : "NO SUBGRAFO";
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