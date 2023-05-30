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
      System.out.println(e.toString());
      input.close();
    }
    
  }
}

class Organizador{
  private Hashtable<Long, String> map;
  private String[] arrLlaves;

  public int procesarLlaves(){

    float tColisiones = procesarArrLlaves();

    tColisiones = tColisiones / arrLlaves.length * 100;

    return limparResultadoColisiones(tColisiones);
  }

  private int limparResultadoColisiones( float tColisiones ){
    String auxCol = String.valueOf(tColisiones);

    return Integer.parseInt(auxCol.split("\\.")[0]);
  }

  private int procesarArrLlaves(){

    int index, tColisiones = 0;
    Long dirLlave;
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

  private Long procesarValAsciiLlave( String valAscii, int tDatos ){

    Long partLlave1 = Long.parseLong(valAscii.substring(0, valAscii.length()/2));
    Long partLlave2 = Long.parseLong(valAscii.substring((valAscii.length()/2)));

    Long key = (partLlave1 + partLlave2 ) % tDatos;
    return key;
  }

  private int validarDirLLave( Long dirLlave, String llave ){

    if( !map.containsKey(dirLlave) ){
      map.put(dirLlave, llave);
      return 0;
    }
    else if ( !map.containsValue(llave)){
      return 1;
    }
    else{
      return 0;
    }
  }

  Organizador(String llaves){
    map = new Hashtable<>();
    arrLlaves = llaves.split(":");
  }
}
