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

      while(true){
        
        huella = input.nextLine();
        archivo = new Scanner( new File(nombrePrograma));
  
        while ( archivo.hasNextLine() ) {
          buscador.setValues(huella);
          countVirus += buscador.procesarLinea( archivo.nextLine().toLowerCase() );
        }
  
        System.out.println(huella + " " + countVirus);
        archivo.close();
        countVirus = 0;
      }
      
    } catch (Exception e) {
      System.out.println( e.toString() );
      input.close();
    }
  }
}

class Buscador{

  private String huella;
  private int countVirus;

  public void setValues( String huella ){
    this.huella = huella.toLowerCase();
    this.countVirus = 0;
  }

  public int procesarLinea( String lineaProcesar){
    return incluyeHuella(lineaProcesar) ? buscarVirus(lineaProcesar) : 0;
  }

  private boolean incluyeHuella( String lineaProcesar ){
    return lineaProcesar.contains(huella);
  }

  private int buscarVirus( String lineaProcesar ){

    while ( lineaProcesar.contains( huella ) ) {
      
      if( lineaProcesar.startsWith(huella) || lineaProcesar.endsWith(huella) ){
        lineaProcesar = recortarExtremos(lineaProcesar);
      }
      else{
        lineaProcesar = recortarMedio(lineaProcesar);
      }
    }
    
    return countVirus;
  }

  private String recortarExtremos( String linea ){

    if( linea.startsWith(huella) ){
      linea = recortarInicio(linea);
      countVirus++;
    }


    if( linea.endsWith(huella) && linea.length() > huella.length() ){
      linea = recortarFinal(linea);
      countVirus++;
    }

    return linea;
  }

  private String recortarInicio( String linea ){
    return linea.substring(huella.length()).trim();
  }
  private String recortarFinal( String linea ){
    return linea.substring(0, linea.length() - huella.length()).trim();
  }

  private String recortarMedio( String linea ){
    char auxHuella = huella.charAt(0);
    char auxLinea;
    int index = 0;

    while ( !linea.startsWith( huella ) && index < linea.length() ) {
      
      auxLinea = linea.charAt(index);

      if( Character.compare(auxHuella, auxLinea) == 0 ){
        linea = linea.substring(index);
        System.out.println(linea);
      }

      index++;
    }
    
    return linea.trim();
  }
}
