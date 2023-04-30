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

        generador.iniciarMatriz();

        System.out.println("Desea agregar otro arista?\n 1. No\n 2. Si");
        res = Integer.parseInt(input.nextLine());

      }



      
    } catch (Exception e) {
      input.close();
    }

  }
}

class Generador{
  private String vertices;
  private String aristas;
  private int[][] matriz;

  private int posicionVert1, posicionVert2;

  public void validarVertices( String arista ){

    String[] partsArista = arista.split("");
    int index = 0;

    for ( index = 0; index < partsArista.length; index++) {
      if( !vertices.contains(partsArista[index]) ) vertices += partsArista[index];
    }
  }

  public void apilarArista( String arista ){
    aristas += " " + arista;
  }

  public void iniciarMatriz(){
    matriz = new int[vertices.length()][vertices.length()];
  }

  public void generarMatriz(){
    String splitedVertices[] = vertices.split("");
    String splitedAristas[] = aristas.split(" ");

    int indexVert, indexAris;

    for ( indexAris = 0; indexAris < splitedAristas.length; indexAris++) {

    }
    
  }

  private void obtenerPosiciones( String arista, String[] splitedVertices ){

    String splitedArista[] = arista.split("");

    posicionVert1 = 

  }

  private int obtenerPosicion( String partArista, String[] splitedVertices ){

    int indexVertices;

    for ( indexVertices = 0; indexVertices < splitedVertices.length; indexVertices++) {
      
    }
  }


  Generador(){
    vertices = aristas = "";
  }

}