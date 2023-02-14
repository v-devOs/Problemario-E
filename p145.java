import java.util.Scanner;


public class p145 {
  public static void main(String[] args) {
    Buscador buscador;
    boolean haFinalizado = false;
    int limitInfe, limitSup, datoBuscar;

    Scanner input = new Scanner(System.in);

      while (!haFinalizado) {
        limitInfe = input.nextInt();
        limitSup = input.nextInt();
        datoBuscar = input.nextInt();

        buscador = new Buscador(limitInfe, limitSup, datoBuscar);

        if( limitInfe == 0 && limitSup == 0 && datoBuscar == 0){
          haFinalizado = true;
        }
        else{
          System.out.println(buscador.buscarDato());
        }
      }
    
      input.close();
  }
}

class Buscador{
  int limitInfe, limitSup, datoBuscar;
  
  public int buscarDato(){

    hacerPositivo();
    cambiarLimites();
  
    int puntoMedio = (limitSup+limitInfe)/2 , comparaciones = 1;

    while (limitInfe < limitSup) {

      if( datoBuscar > puntoMedio  ){
        comparaciones++;

        limitInfe = ++puntoMedio;
      }
      else{
        comparaciones++;
        limitSup = puntoMedio;
      }
      puntoMedio = (limitSup+limitInfe)/2;
    }

    return ( limitSup == datoBuscar ) ? comparaciones : -1;
     
  }

  private void hacerPositivo(){
    if( limitInfe < 0 && limitSup > 0) limitInfe *= 1;
    if( limitInfe < 0 && limitSup < 0  ) limitInfe *= -1;
    if( limitSup < 0 ) limitSup *= -1;
    if( datoBuscar < 0 ) datoBuscar *= -1;
  }

  private void cambiarLimites(){
    int aux;

    if( limitInfe > limitSup ){
      aux = limitInfe;
      limitInfe = limitSup;
      limitSup = aux;
    }
  }

  Buscador( int limitInfe, int limitSup, int datoBuscar){
    this.datoBuscar = datoBuscar;
    this.limitInfe = limitInfe;
    this.limitSup = limitSup;
  }
}
