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



      
    } catch (Exception e) {
      input.close();
    }

  }
}

class Generador{
  private String vertices;
  private String aristas;

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

  Generador(){
    vertices = aristas = "";
  }

}




