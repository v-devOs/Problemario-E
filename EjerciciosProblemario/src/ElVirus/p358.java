package ElVirus;

import java.io.File;
import java.util.Scanner;

public class p358 {
  public static void main(String[] args) {

    Buscador buscador = new Buscador();
    Scanner input = new Scanner(System.in);
    Scanner archivo;
    String nombrePrograma, huella;
    int countVirus = 0;

    try {

      String hola = "virus Hola virus virus virus";

      System.out.println(hola.substring(5).split("virus").length);

      // nombrePrograma = input.nextLine();
      // huella = input.nextLine();

      // archivo = new Scanner( new File(nombrePrograma));

      // while ( archivo.hasNextLine() ) {
      //   buscador.setValues(huella);
      //   countVirus += buscador.procesarLinea( archivo.nextLine() );
      // }

      // System.out.println(huella + " " + countVirus);
      
    } catch (Exception e) {
      System.out.println( e.toString() );
      input.close();
    }
  }
}

class Buscador{

  private String huella;

  public void setValues( String huella ){
    this.huella = huella;
  }

  public int procesarLinea( String lineaProcesar){
    return incluyeHuella(lineaProcesar) ? buscarVirus(lineaProcesar) : 0;
  }

  private boolean incluyeHuella( String lineaProcesar ){
    return lineaProcesar.contains(huella);
  }

  private int buscarVirus( String lineaProcesar ){
    int countVirus = 0;
    int auxStartEnd = 0;
    int lengthLineaSplitted = lineaProcesar.split(huella).length;

    if( lineaProcesar.endsWith(huella) ){ 
      countVirus++;
      auxStartEnd++;
    }

    if( lineaProcesar.startsWith(huella) ) {
      countVirus++;
      auxStartEnd++;
    }

    if( lengthLineaSplitted - auxStartEnd > 1 ){
      countVirus += lengthLineaSplitted - auxStartEnd - 1;
    }
       

    return countVirus;
  }
}
