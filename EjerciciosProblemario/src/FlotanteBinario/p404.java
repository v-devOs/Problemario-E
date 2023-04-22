package FlotanteBinario;

import java.util.Scanner;

public class p404 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Conversor conversor = new Conversor();

    float numConvertir;
    try {
      
      while (true) {
        numConvertir = input.nextFloat();
        conversor.setValues( String.valueOf( numConvertir ) );
        System.out.println(conversor.convertirNumero());
      }

    } catch (Exception e) {
      input.close();
    }
  }
}


class Conversor{

  private int partEntera;
  private float partFraccion;

  public void setValues( String numeroConvertir ){
    String[] partsNum = separarNumero(numeroConvertir);

    partEntera = Integer.parseInt(partsNum[0]);
    partFraccion = Float.parseFloat(("." + partsNum[1]));

    System.out.println(partEntera + " " + partFraccion);
  }

  public String convertirNumero(  ){
    return convertirParteEntera() + "." + convertirParteFraccionaria();
  }

  private String convertirParteEntera(){
    return Integer.toBinaryString( partEntera );
  }

  private String convertirParteFraccionaria(){
    String partsNum[];
    String conversion = "";
    float numAux;

    while( this.partFraccion != 0.0 ){

      numAux = partFraccion * 2;
      partsNum = separarNumero( String.valueOf(numAux));

      conversion += partsNum[0];
      this.partFraccion = Float.parseFloat(( "." + partsNum[1]));
    }

    return conversion;
  }

  private String[] separarNumero( String num ){
    return num.split("\\.");
  }

}
