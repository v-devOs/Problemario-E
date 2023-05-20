package LDH;

import java.util.Hashtable;
import java.util.Scanner;

public class p325 {
  public static void main(String[] args) {
    Organizador organizador;
    Scanner input = new Scanner(System.in);
    String llaves;
    int casosPrueba;
    int count;

    try {
      casosPrueba = Integer.parseInt(input.nextLine());

      for ( count = 0; count < casosPrueba; count++) {
        llaves = input.nextLine();

        organizador = new Organizador(llaves);

        System.out.println(organizador.procesarLlaves() + "%");
      }


    } catch (Exception e) {
      input.close();
    }
    
  }
}

class Organizador{
  private Hashtable<Integer, String> map;
  private String[] arrLlaves;

  public int procesarLlaves(){

    int tColisiones = procesarArrLlaves();

    return (tColisiones/ arrLlaves.length) * 100;
  }

  private int procesarArrLlaves(){

    int index, tColisiones = 0;
    Integer dirLlave;
    String[] arrLlave;
    String valAsciiLlave;

    for ( index = 0; index < arrLlaves.length; index++) {

      arrLlave = arrLlaves[index].split("");

      valAsciiLlave = obtenerValAsciiLLave(arrLlave);
      dirLlave = procesarValAsciiLlave(valAsciiLlave, arrLlaves.length);

      tColisiones += validarDirLLave(dirLlave, arrLlaves[index]);
    }

    return tColisiones;
  }

  private String obtenerValAsciiLLave( String[] llave ){

    String valAscii = "";
    int index;

    for ( index = 0; index < llave.length; index++) {
      valAscii += String.valueOf( (int) llave[index].charAt(0) );
    }

    return valAscii;
  }

  private Integer procesarValAsciiLlave( String valAscii, int tDatos ){

    int partLlave1 = Integer.parseInt(valAscii.substring(0, valAscii.length()/2));
    int partLlave2 = Integer.parseInt(valAscii.substring((valAscii.length()/2)));

    Integer key = (partLlave1 + partLlave2 ) % tDatos;
    return key;
  }

  private int validarDirLLave( Integer dirLlave, String llave ){

    if( !map.containsKey(dirLlave) ){
      map.put(dirLlave, llave);
      return 0;
    }
    else{
      return 1;
    }
  }


  Organizador(String llaves){
    map = new Hashtable<>();
    arrLlaves = llaves.split(":");
  }
}
