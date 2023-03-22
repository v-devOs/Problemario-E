public class p291 {
  public static void main(String[] args) {
    String pruebaString = "-5i";
    String[] prueba = pruebaString.split("i");

    System.out.println(Integer.parseInt(prueba[0]));
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

    return new NumComplejo(sumaParteReal, "");
  }

  private double separarPartCompleja( NumComplejo numComp){
    
  }
}