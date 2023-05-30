package BusquedaBina;

public class p145 {
  public static void main(String[] args) {
    
  } 
}

class Buscador{
  private int limitIn,limitSu,numBus;

  public int buscarNumero(){
    return numBus < limitIn || numBus > limitSu ? -1 : realizarBusqueda();
  }

  public int realizarBusqueda(){
    int preguntas = 0;
    int puntoMedio = (limitSu + limitIn) / 2;

    while ( limitIn < limitSu ) {
      
      if( numBus > puntoMedio ){
        limitIn = puntoMedio + 1;
      }
      else{
        limitSu = puntoMedio;
      }

      preguntas++;
      puntoMedio = (limitSu + limitIn )/2;
    }

    return preguntas;


    

  }

  Buscador(int limitIn, int limitSu, int numBus){
    this.limitIn = limitIn;
    this.limitSu = limitSu;
    this.numBus = numBus;
  }


}
