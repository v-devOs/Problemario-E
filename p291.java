public class p291 {
  public static void main(String[] args) {
    NumComplejo numCpm1 = new NumComplejo(-4, "+5i");
    NumComplejo numCpm2 = new NumComplejo(8, "-2.5i");
    Operador op = new Operador();

    NumComplejo suma = op.sumar(numCpm1, numCpm2);

    System.out.println( suma.partReal + " " + suma.partImaginaria);
  }
}


class NumComplejo{
  double partReal;
  String partImaginaria;

  NumComplejo( double partReal, String partImaginaria){
    this.partReal = partReal;
    this.partImaginaria = partImaginaria;
  }
}

class Operador{

  public NumComplejo sumar( NumComplejo numComp1, NumComplejo numComp2 ){
    double sumaParteReal = numComp1.partReal + numComp2.partReal;
    String sumaParteImaginaria = ( separarParteImaginaria(numComp1) + separarParteImaginaria(numComp2) ) + "i";

    return new NumComplejo(sumaParteReal, sumaParteImaginaria);
  }

  private double separarParteImaginaria( NumComplejo numComp){
    return Double.parseDouble(numComp.partImaginaria.split("i")[0]);
  }
}