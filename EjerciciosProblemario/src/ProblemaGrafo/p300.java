package ProblemaGrafo;

import java.util.Scanner;

public class p300 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Grafo grafo;
    String conjVertices;
    int matriz[][];
    int numVertices, col, fila;

    try {

      numVertices = Integer.parseInt(input.nextLine());
      matriz = new int[numVertices][numVertices];

      for ( fila = 0; fila < matriz.length; fila++) {

        for ( col = 0; col < matriz.length; col++) {

          matriz[fila][col] = Integer.parseInt(input.nextLine());
        }
      }


      while (true) {
        conjVertices = input.nextLine();
        grafo = new Grafo(matriz, conjVertices);
        
        grafo.procesarVertices(conjVertices);
      }

      
    } catch (Exception e) {
      input.close();// TODO: handle exception
    }
  }
}


class Grafo{
  private int matriz[][];
  private int vertGrafo[];


  public void procesarVertices( String conjVert ){

    boolean hayCamino = true;
    int indexActual, indexBuscar, indexAux = 0;

    indexActual = vertGrafo[indexAux];
    indexBuscar = vertGrafo[indexAux + 1];

    while ( hayCamino && indexAux < vertGrafo.length - 1 ){
      
      if( matriz[indexActual][indexBuscar] == 1 ){
        indexActual = indexBuscar;
        indexBuscar = vertGrafo[++indexAux];
      }
      else{
        hayCamino = false;
      }
    }

    if( hayCamino ){
      System.out.println("Si hay ruta");
    }
    else{
      System.out.println("NO SUBGRAFO");
    }
  }

  

  Grafo( int[][] matriz, String conjVert ){
    this.matriz = matriz;
    vertGrafo = obtenerIndexVert(conjVert);
  }

  private int[] obtenerIndexVert( String conjVert ){

    String auxVerts[] = conjVert.split(" ");
    int indexVerts[] = new int[auxVerts.length];

    int index;

    for ( index = 0; index < auxVerts.length; index++) {

      indexVerts[index] = (int) auxVerts[index].charAt(index);
    }

    return indexVerts;
  }

}