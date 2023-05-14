package LGDG;

import java.util.Scanner;

public class p318 {
  public static void main(String[] args) {
    Procesador procesador = new Procesador();
    Scanner input = new Scanner(System.in);
    int casos, count;
    String vertices, aristas;
    
    try {

      casos = Integer.parseInt(input.nextLine());

      for ( count = 0; count < casos; count++) {
        vertices = input.nextLine();
        aristas = input.nextLine();
        procesador.iniciarDependencias(vertices, aristas);
        procesador.generarMatriz();
      }
      
    } catch (Exception e) {
      System.err.println(e.toString());
      input.close();
    }
  }
}

class Procesador{
  private String[] vertices;
  private String[] aristas;

  private int[][] matriz;

  public void iniciarDependencias( String vertCaso, String aristCaso ){
    inicarArrVert(vertCaso);
    iniciarArrArist(aristCaso);

    matriz = new int[vertices.length][vertices.length];

  }

  private void inicarArrVert( String vertCaso ){
    vertices = limpiarCadena(vertCaso, false).split(",");
  }

  private void iniciarArrArist( String aristCaso ){
    aristas = limpiarCadena(aristCaso, false).split("\\),");
  }

  private String limpiarCadena( String verCaso, boolean esAristas ){
    
    if( esAristas ) 
      return verCaso.substring(1);
    else
      return verCaso.substring(1, verCaso.length() - 1);
  }

  public void generarMatriz(){

    System.out.println(aristas.length);

    String splitedArista[];
    int posicionVert1, posicionVert2;
    int indexAris;

    for ( indexAris = 0; indexAris < aristas.length; indexAris++) {

      splitedArista = limpiarCadena(aristas[indexAris], true).split(",");

      posicionVert1 = obtenerPosicion(splitedArista[0]);
      posicionVert2 = obtenerPosicion(validarUltiPartVert(splitedArista[1], indexAris));

      marcarConexiones(posicionVert1, posicionVert2);
    }
  }

  private String validarUltiPartVert( String partVert, int index  ){
    return index == aristas.length - 1 ? partVert.substring(0, partVert.length() - 1) : partVert;
  }

  private int obtenerPosicion( String partArista ){

    int index = 0;
    boolean seEncontroPos = false;

    while ( !seEncontroPos && index < vertices.length ) {
      
      if( vertices[index].equals(partArista) ){
        seEncontroPos = true;
      }
      else{
        index++;
      }
    }
    return index;
  }

  private void marcarConexiones( int posVert1, int posVert2 ){
    matriz[posVert1][posVert2] = 1;
  }

  private String mostrarInfoTipoGradoGrafo(){
    String tipo = determinarTipo();

    return tipo;
  }

  private String determinarTipo(){
    
    boolean esGrafo = true;
    int col = 0, fila = 0;

    while ( esGrafo && fila < matriz.length) {

      col = 0;
      
      while ( esGrafo && col < matriz.length ) {

        if( col == fila ){
          esGrafo = validarDiagonalPrincipal(fila, col);
        }
        else{
          esGrafo = validarCasillas(fila, col);
        }
        col++;
      }
      fila++;
    }
    return esGrafo ? "GRAFO" : "DIGRAFO";
  }

  private boolean validarDiagonalPrincipal( int fila, int col ){
    return matriz[fila][col] == 0;
  }

  private boolean validarCasillas( int fila, int col ){
    return matriz[fila][col] == matriz[col][fila];
  }
}