package TareaGrafos;

import java.util.Scanner;

public class app {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Generador generador = new Generador();
    String aristaLeido;
    int res = 0;

    try {

      while ( res != 1) {
        
        System.out.println("Ingrese un arista");
        aristaLeido = input.nextLine();

        generador.apilarArista(aristaLeido);
        generador.validarVertices(aristaLeido);

        
        System.out.println("Desea agregar otro arista?\n 1. No\n 2. Si");
        res = Integer.parseInt(input.nextLine());
      }

      generador.iniciarDependencias();

      generador.generarMatriz();
      
      generador.mostrarMatriz();

    } catch (Exception e) {
      input.close();
    }

  }
}

class Generador{
  private String vertices;
  private String aristas;
  private int[][] matriz;

  private String splitedVertices[];
  private String splitedAristas[];


  public void validarVertices( String arista ){

    String[] partsArista = arista.split("");
    int index = 0;

    for ( index = 0; index < partsArista.length; index++) {
      if( !vertices.contains(partsArista[index]) ) vertices += partsArista[index];
    }
  }

  public void apilarArista( String arista ){
    if( aristas.length() > 0 ){
      aristas += " " + arista;
    }
    else{
      aristas += arista;
    }
  }

  public void iniciarDependencias(){

    matriz = new int[vertices.length()][vertices.length()];

    splitedVertices = vertices.split("");
    splitedAristas = aristas.split(" ");
  }

  public void generarMatriz(){

    String splitedArista[];
    int posicionVert1, posicionVert2;
    int indexAris;

    for ( indexAris = 0; indexAris < splitedAristas.length; indexAris++) {

      splitedArista = splitedAristas[indexAris].split("");

      posicionVert1 = obtenerPosicion(splitedArista[0]);
      posicionVert2 = obtenerPosicion(splitedArista[1]);

      marcarConexiones(posicionVert1, posicionVert2);
    }
  }

  private int obtenerPosicion( String partArista ){

    int index = 0;
    boolean seEncontroPos = false;

    while ( !seEncontroPos && index < splitedVertices.length ) {
      
      if( splitedVertices[index].equals(partArista) ){
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
    matriz[posVert2][posVert1] = 1;
  }

  public void mostrarMatriz(){

    System.out.print("\033[H\033[2J");
    System.out.flush();

    int indexFila, indexColum;

    llenarMatriz();
    mostrarVertices();

    for ( indexFila = 0; indexFila < matriz.length; indexFila++) {

      System.out.print(" " + splitedVertices[indexFila]);

      for ( indexColum = 0; indexColum < matriz.length; indexColum++) {
    
        System.out.print(" " + matriz[indexFila][indexColum] + " "); 
      }

      System.out.println("\n");
    }
  }

  private void llenarMatriz(){

    for (int i = 0; i < matriz.length; i++) {
      for (int j = 0; j < matriz.length; j++) {
        
        if( matriz[i][j] != 1 ){
          matriz[i][j] = 0;
        }

      }
    }
  }

  private void mostrarVertices(){
    int index;

    System.out.print("  ");

    for ( index = 0; index < matriz.length; index++) {
      System.out.print(" " + splitedVertices[index] + " ");
    }

    System.out.println("\n");
  }

 


  Generador(){
    vertices = aristas = "";
  }

}