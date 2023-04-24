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

      nombrePrograma = input.nextLine();
      huella = input.nextLine();

      archivo = new Scanner( new File(nombrePrograma));

      while ( archivo.hasNextLine() ) {
        buscador.setValues(huella);
        countVirus += buscador.procesarLinea( archivo.nextLine() );
      }

      System.out.println(huella + " " + countVirus);
      
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
    int countVirus = 0, index = 0;

    while ( lineaProcesar.contains(huella )) {
      
      if( lineaProcesar.startsWith(huella) ){
        countVirus++;
        lineaProcesar = recortarInicio(lineaProcesar);
      }

    }
    
    
    return countVirus;
  }

  private String recortarInicio( String linea ){
    return linea.substring(huella.length());
  }

  
}
