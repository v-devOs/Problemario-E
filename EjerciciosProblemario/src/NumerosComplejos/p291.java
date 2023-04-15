package NumerosComplejos;

import java.util.Scanner;

public class p291 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Controlador controlador = new Controlador();
    String entradasSeparadas[];
    String entrada;


    try {
      entrada = input.nextLine();
      entradasSeparadas = entrada.split(" ");
      controlador.procesarEntradas(entradasSeparadas);

      input.close();
      
    } catch (Exception e) {
      input.close();
    }
  }
}



class Controlador{
  NumImaginario numIm1, numIm2, numImResult;

  public void procesarEntradas( String entradas[] ){
    String operacion = entradas[4];

    crearNumerosImaginarios(entradas);

    numImResult = realizarOperacion(operacion);
    numImResult.mostrar();
  }

  private void crearNumerosImaginarios( String entradas[] ){
    numIm1 = new NumImaginario( entradas[0], entradas[1] );
    numIm2 = new NumImaginario( entradas[2], entradas[3] );

  }

  private NumImaginario realizarOperacion( String operacion ){

    switch (operacion) {
      case "+":
        return numIm1.sumar(numIm2);

      case "-":
        return numIm1.restar(numIm2);

      case "*":
        return numIm1.multi(numIm2);

      case "/":
        return numIm1.sumar(numIm2);

      default:
        return null;
    }
  }
}


class NumImaginario{
  double partReal;
  double partImaginaria;

  public NumImaginario sumar( NumImaginario numIm2 ){
    String newPartReal = String.valueOf(this.partReal + numIm2.partReal);
    String newPartIma = String.valueOf(this.partImaginaria + numIm2.partImaginaria);

    return new NumImaginario(newPartReal, newPartIma);
  }
  public NumImaginario restar( NumImaginario numIm2 ){
    String newPartReal = String.valueOf(this.partReal - numIm2.partReal);
    String newPartIma = String.valueOf(this.partImaginaria - numIm2.partImaginaria);

    return new NumImaginario(newPartReal, newPartIma);
  }
  public NumImaginario multi( NumImaginario numIm2 ){
    String newPartReal = auxMultiPartReal(numIm2);
    String newPartIma = auxMultiParIma(numIm2);

    return new NumImaginario(newPartReal, newPartIma);
  }
  // public NumImaginario dividir( NumImaginario numIm2 ){
    
  // }

  private String auxMultiPartReal( NumImaginario numIm2){
    double part1 = this.partReal * numIm2.partReal;
    double part2 = ( this.partImaginaria * numIm2.partImaginaria) * -1;

    return String.valueOf( part1 + part2 );
  }

  private String auxMultiParIma( NumImaginario numIm2){
    double part1 = this.partReal * numIm2.partImaginaria;
    double part2 = ( this.partImaginaria * numIm2.partReal);

    return String.valueOf( part1 + part2 );
  }


  public void mostrar(){
    System.out.println(partReal + " " + partImaginaria + "i");
  }
 

  NumImaginario( String partReal, String partImaginaria){
    this.partReal = Double.parseDouble(partReal);
    this.partImaginaria = limpiarParteIma(partImaginaria);
  }

  private double limpiarParteIma( String partImagLimpiar ){
    double partImagiLimpia = Double.parseDouble(partImagLimpiar.split("i")[0]);
    return partImagiLimpia;
  }
}

