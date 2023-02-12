import java.util.Scanner;


public class p145 {
  public static void main(String[] args) {
    Buscador buscador = new Buscador();
    boolean haFinalizado = false;
    int limitInfe, limitSup, datoBuscar;

    try (Scanner input = new Scanner(System.in)) {

      while (!haFinalizado) {
        limitInfe = input.nextInt();
        limitSup = input.nextInt();
        datoBuscar = input.nextInt();

        if( limitInfe == 0 && limitSup == 0 && datoBuscar == 0){
          haFinalizado = true;
        }
        else{
          System.out.println(buscador.buscarDato(limitInfe, limitSup, datoBuscar));
        }
      }
    } 
  }
}

class Buscador{
  
  public int buscarDato(int limitInfe, int limitSup, int datoBuscar ){
    int puntoMedio = (limitSup+limitInfe)/2 , comparaciones = 0;
    boolean seTerminaronComparaciones = false;

    while (!seTerminaronComparaciones && limitInfe < limitSup) {

      if( datoBuscar > puntoMedio  ){
        limitInfe = ++puntoMedio;
      }
      else{
        limitSup = puntoMedio;
      }
      comparaciones++;
      puntoMedio = (limitSup+limitInfe)/2;
    }

    return ( limitSup == datoBuscar ) ? ++comparaciones : -1;
     
  }
}
