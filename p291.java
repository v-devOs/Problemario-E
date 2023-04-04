// import java.text.DecimalFormat;

// public class p291 {
//   // public static void main(String[] args) {
//     double num = 123.35423523;
//     String aux3 = "12313,12313";
//     String aux = String.valueOf(num);
//     String aux2[] = aux3.split(",");

//     System.out.println(aux2[0]);
   
//     // DecimalFormat formater = new DecimalFormat("#");

//     // System.out.println(formater.format(num));
//     // NumComplejo numCpm1 = new NumComplejo(-4, 5);
//     // NumComplejo numCpm2 = new NumComplejo(8,2.5);
//     // Operador op = new Operador();

//     // NumComplejo suma = op.sumar(numCpm1, numCpm2);

//     // suma.mostrar();
//   }
// }


// class NumComplejo{
//   double partReal;
//   double partImaginaria;

//   public void mostrar(){
//     System.out.println( aplicarFormatoString(partReal) + " " + aplicarFormatoString(partImaginaria));
//   }
//   private String aplicarFormatoString( double parteNumComplejo ){
//     return ( partImaginaria < 0 ) ? "-" + parteNumComplejo : "+" + parteNumComplejo;
//   }

//   // private String redondear( double parteNumComplejo ){
//   //   String auxCadenaNumCpj = String.valueOf(parteNumComplejo);
//   // }


//   NumComplejo( double partReal, double partImaginaria){
//     this.partReal = partReal;
//     this.partImaginaria = partImaginaria;
//   }
// }

// class Operador{
//   DecimalFormat formaterEnteros, formaterDoubles;

//   public NumComplejo sumar( NumComplejo numComp1, NumComplejo numComp2 ){
//     double sumaParteReal = numComp1.partReal + numComp2.partReal;
//     double sumaParteImaginaria = numComp1.partImaginaria + numComp2.partImaginaria;

//     return new NumComplejo(sumaParteReal, sumaParteImaginaria);
//   }

//   Operador(){
//     formaterEnteros = new DecimalFormat("#");
//     formaterDoubles = new DecimalFormat("#.##");
//   }
// }