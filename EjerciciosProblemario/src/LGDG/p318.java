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
        System.out.println(procesador.mostrarInfoTipoGradoGrafo());
        procesador.limpiar();
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
  private boolean noHayDatosErroneos;
  private String auxVertices;

  private int[][] matriz;

  public void iniciarDependencias( String vertCaso, String aristCaso ){
    inicarArrVert(vertCaso);
    iniciarArrArist(aristCaso);

    matriz = new int[vertices.length][vertices.length];

  }

  private void inicarArrVert( String vertCaso ){
    int index;
    vertices = limpiarCadena(vertCaso, false).split(",");

    for ( index = 0; index < vertices.length; index++) {
      auxVertices += " " + vertices[index];
    }
    
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

    String splitedArista[];
    int posicionVert1, posicionVert2;
    int indexAris = 0;

    while( indexAris < aristas.length && noHayDatosErroneos ){

      splitedArista = limpiarCadena(aristas[indexAris], true).split(",");

      validarPartVer(splitedArista[0]);
      validarPartVer(splitedArista[1]);

      if( noHayDatosErroneos ){
        posicionVert1 = obtenerPosicion(splitedArista[0]);
        posicionVert2 = obtenerPosicion(validarUltiPartVert(splitedArista[1], indexAris));
  
        marcarConexiones(posicionVert1, posicionVert2);
      }
    
      indexAris++;
    }
  }

  private void validarPartVer( String partVert ){

    System.out.println(auxVertices);
    
    if( !auxVertices.contains(partVert)) noHayDatosErroneos = false;
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

  public String mostrarInfoTipoGradoGrafo(){
    return noHayDatosErroneos ? determinarTipo() + calcularGradoVerts() : "DATOS ERRONEOS";
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
    return esGrafo ? "Grafo " : "Digrafo ";
  }

  private boolean validarDiagonalPrincipal( int fila, int col ){
    return matriz[fila][col] == 0;
  }

  private boolean validarCasillas( int fila, int col ){
    return matriz[fila][col] == matriz[col][fila];
  }

  private String calcularGradoVerts(){
    int[] indexVertsGradoMax = new int[matriz.length];
    int index = 0, totalVertMax = 0;

    int maxGrado = 0, col, fila, auxGradoVert;

    for ( fila = 0; fila < matriz.length; fila++) {

      auxGradoVert = 0;

      for ( col = 0; col < matriz.length; col++) {
        
        if( matriz[fila][col] > 0) auxGradoVert++;
      }

      if( auxGradoVert > maxGrado ){
        index = 0;
        totalVertMax = 0;
        indexVertsGradoMax[index] = fila;
        maxGrado = auxGradoVert;
        totalVertMax++;
        index++;
      }
      else if( auxGradoVert == maxGrado ){
        indexVertsGradoMax[index] = fila;
        index++;
        totalVertMax++;
      }
    }

    return "Grado " + maxGrado + " VMayor " + buscarVertGradoMax(totalVertMax, indexVertsGradoMax);

  }

  private String buscarVertGradoMax( int totalVertMax, int[] indexVertsGradoMax ){

    String vertMaxGrado = "";
    int indexVertGrado, indexVert = 0;
    boolean seEncontroVert;

    for ( indexVertGrado = 0; indexVertGrado < totalVertMax; indexVertGrado++) {

      seEncontroVert = false;
      indexVert = 0;
      
      while ( !seEncontroVert  && indexVert < vertices.length ) {
        
        if( indexVert == indexVertsGradoMax[indexVertGrado] ){

          if( vertMaxGrado.length() == 0) vertMaxGrado += vertices[indexVert];
          else vertMaxGrado += " " + vertices[indexVert];
          seEncontroVert = true;
        }

        indexVert++;
      }
    }

    return "[" + vertMaxGrado + "]";
  }

  public void limpiar(){
    matriz = null;
    vertices = null;
    aristas = null;
    noHayDatosErroneos = true;
  }

  Procesador(){
    noHayDatosErroneos = true;
    auxVertices = "";
  }
}