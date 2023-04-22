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
        conversor.setValues( String.valueOf( numConvertir ));
        conversor.convertirNumero();
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
    String[] partsNum = numeroConvertir.split("\\.");

    partEntera = Integer.parseInt(partsNum[0]);
    partFraccion = Float.parseFloat(("." + partsNum[1]));

    System.out.println(partEntera + " " + partFraccion);
  }

  public String convertirNumero(  ){
    

    return "";
  }

  private String convertirParteEntera(){
    return Integer.toBinaryString( partEntera );
  }

  private void convertirParteFraccionaria(){

  }

}
