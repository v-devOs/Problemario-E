package NumerosComplejos;

import java.util.Scanner;

public class p291 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Controlador controlador = new Controlador();
    String entradasSeparadas[];
    String entrada;


    try {
      while (true) {
        entrada = input.nextLine();
        entradasSeparadas = entrada.split(" ");
        controlador.procesarEntradas(entradasSeparadas);
      }
            
    } catch (Exception e) {
      System.out.println(e);
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
        return numIm1.dividir(numIm2);

      default:
        return null;
    }
  }
}

class NumImaginario{
  double partReal;
  double partImaginaria;
  private Formateador formateador;

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
    String newPartReal = auxMultiPartReal(numIm2, false);
    String newPartIma = auxMultiPartIma(numIm2, false);

    return new NumImaginario(newPartReal, newPartIma);
  }
  public NumImaginario dividir( NumImaginario numIm2 ){
    String parteRealCociente = auxMultiPartReal(numIm2, true);
    String parteImaginariaCociente = auxMultiPartIma(numIm2, true);
    String divisor = auxDivPartDividendo(numIm2);

    return new NumImaginario(auxDivCocienteDivisor(parteRealCociente, divisor), 
                            auxDivCocienteDivisor(parteImaginariaCociente, divisor));
  }

  private String auxMultiPartReal( NumImaginario numIm2, boolean esDivision ){
    double part1 = this.partReal * numIm2.partReal;
    double part2;

    if( esDivision ) part2 = ( this.partImaginaria * (numIm2.partImaginaria * -1)) * -1;
    else part2 = ( this.partImaginaria * numIm2.partImaginaria) * -1;

    return String.valueOf( part1 + part2 );
  }

  private String auxMultiPartIma( NumImaginario numIm2, boolean esDivision ){
    double part1;
    double part2 = ( this.partImaginaria * numIm2.partReal);

    if( esDivision ) part1 = this.partReal * (numIm2.partImaginaria *-1);
    else part1 = this.partReal * numIm2.partImaginaria;

    return String.valueOf( part1 + part2 );
  }

  private String auxDivPartDividendo( NumImaginario numIm2 ){
    double part1 = numIm2.partReal * numIm2.partReal;
    double part2 = (numIm2.partImaginaria * ( numIm2.partImaginaria * -1)) * -1;

    return String.valueOf( part1 + part2 );
  }

  private String auxDivCocienteDivisor( String cociente, String divisor ){
    double cocienteToWork = Double.parseDouble(cociente);
    double divisorToWork = Double.parseDouble(divisor);

    return String.valueOf(cocienteToWork / divisorToWork );
  }
  
  public void mostrar(){

    String partMostrarReal = formateador.formatearNumero(partReal, true);
    String partMostrarIma = formateador.formatearNumero(partImaginaria, false);

    System.out.println(partMostrarReal +  partMostrarIma + "i");
  }
 
  NumImaginario( String partReal, String partImaginaria){
    this.partReal = Double.parseDouble(partReal);
    this.partImaginaria = limpiarParteIma(partImaginaria);
    formateador = new Formateador();
  }

  private double limpiarParteIma( String partImagLimpiar ){
    double partImagiLimpia = Double.parseDouble(partImagLimpiar.split("i")[0]);
    return partImagiLimpia;
  }
}

class Formateador{

  public String formatearNumero( double numero, boolean esPartReal ){
    String numeroRedondeado = redondearNumero(String.valueOf(numero));

    if(esPartReal)
      return numeroRedondeado;
    else{
      String partImaFormateada = formatearParteImaginaria(Double.parseDouble(numeroRedondeado));
      return partImaFormateada;
    }
  }

  private String redondearNumero( String numero  ){
    int partCompleta = Integer.parseInt(numero.split("\\.")[0]);
    String parteDecimal = numero.split("\\.")[1];

    if( parteDecimal.compareTo("0") == 0){
      if( Double.parseDouble(numero) < 0 && partCompleta == 0 ){
        return "-" + String.valueOf(partCompleta);
      }
      else{
        return String.valueOf(partCompleta);
      }
    }
    else{
      return partCompleta + "." + parteDecimal.substring(0, 1);
    }
  }

  
  private String formatearParteImaginaria( double numero ){
    return numero < 0 ? " -" + redondearNumero(String.valueOf(numero)) : " +" + redondearNumero(String.valueOf(numero));
  }
  
}
