import java.util.Scanner;

public class p272 {
  public static void main(String[] args) {
    Algoritmo[] algoritmos;
    Algoritmo algoritmo, algoritmoEficiente = new Algoritmo(), algoritmoCosteado;
    Scanner input = new Scanner(System.in);
    String instrucciones;
    Procesador procesador = new Procesador();
    int casosPrueba, casosAlgoritmo;
    int pruebasHechas, algoritmoHechos;


    casosPrueba = Integer.parseInt( input.nextLine() );

    for ( pruebasHechas = 0; pruebasHechas < casosPrueba; pruebasHechas++) {

      casosAlgoritmo = Integer.parseInt( input.nextLine() );
      algoritmos = new Algoritmo[ casosAlgoritmo ];

      for ( algoritmoHechos = 0; algoritmoHechos < casosAlgoritmo; algoritmoHechos++) {
        instrucciones = input.nextLine();
        algoritmo = new Algoritmo(instrucciones);

        algoritmoCosteado = procesador.procesarAlgoritmo(algoritmo, ( algoritmoHechos++));
        algoritmos[algoritmoHechos] = algoritmoCosteado;
      }

      algoritmoEficiente = procesador.definirAlgoritmoEficiente(algoritmos);

      System.out.println("Algoritmo "+algoritmoEficiente.index+" => "+algoritmoEficiente.costoAlgoritmo+" bytes");
      

    }
    input.close();
  }
}

class Procesador{
  private String[] arrayInstrucciones;
  private int costoAlgoritmo;
  Costos costoTipoDato;

  public Algoritmo procesarAlgoritmo( Algoritmo algoritmo, int index ){
    
    arrayInstrucciones = algoritmo.obtenerArreglo();
    calcularCosto();

    algoritmo.costoAlgoritmo = costoAlgoritmo;
    algoritmo.totalInstrucciones = arrayInstrucciones.length;
    algoritmo.index = index;
    
    return algoritmo;
  }

  private void calcularCosto(){
    int index;

    for ( index = 0; index < arrayInstrucciones.length; index++) {
      sumarBytes( arrayInstrucciones[index]);
    }
  }

  private void sumarBytes(String tipoDato){
    if( tipoDato.equalsIgnoreCase("char") || tipoDato.equalsIgnoreCase("boolean") || tipoDato.equalsIgnoreCase("byte")){
      costoAlgoritmo += costoTipoDato.caracterBoleanoByte;
    }
    else if( tipoDato.equalsIgnoreCase("int") || tipoDato.equalsIgnoreCase("float")){
      costoAlgoritmo += costoTipoDato.enteroFlotante;
    }
    else if( tipoDato.equalsIgnoreCase("long") || tipoDato.equalsIgnoreCase("double")){
      costoAlgoritmo += costoTipoDato.longDouble;
    }
    else{
      costoAlgoritmo += costoTipoDato.string;
    }    // }
  }

  public Algoritmo definirAlgoritmoEficiente( Algoritmo[] algoritmos ){
    Algoritmo algoritmoEficiente = new Algoritmo(), algoritmoComparado = new Algoritmo();
    int index;

    for (index = 0; index < algoritmos.length; index++) {
      algoritmoComparado = compararAlgoritmos(algoritmoEficiente, algoritmos[index]);
      algoritmoEficiente.costoAlgoritmo = algoritmoComparado.costoAlgoritmo;
      algoritmoEficiente.index = algoritmoComparado.index;
    }

    return algoritmoEficiente;

  }

  private Algoritmo compararAlgoritmos( Algoritmo algotimoEficiente, Algoritmo algoritmoComparar  ){
    
    if( algotimoEficiente.costoAlgoritmo < algoritmoComparar.costoAlgoritmo )
      return algotimoEficiente;
    else if( algoritmoComparar.costoAlgoritmo < algotimoEficiente.costoAlgoritmo)
      return algoritmoComparar;
    else
      return compararTotalInstrucciones(algotimoEficiente, algoritmoComparar);
  }

  private Algoritmo compararTotalInstrucciones( Algoritmo algoritmoEficiente, Algoritmo algoritmoComparar){
    if( algoritmoEficiente.totalInstrucciones < algoritmoComparar.totalInstrucciones)
      return algoritmoEficiente;
    else 
      return algoritmoComparar;
  }

  Procesador(){
    costoTipoDato = new Costos();
  }
}

class Algoritmo{
  private String instrucciones;
  public int costoAlgoritmo;
  public int totalInstrucciones, index;

  public String[] obtenerArreglo(){
    return instrucciones.split(",");
  }

  Algoritmo(String instrucciones){
    this.instrucciones = instrucciones;
    costoAlgoritmo = 0;
    totalInstrucciones = 0;
    index = 0;
  }

  Algoritmo(){
    this.instrucciones = "";
    costoAlgoritmo = 2000000;
    totalInstrucciones = 0;
    index = 0;
  }
}

class Costos{
  int string = 14;
  int enteroFlotante = 4;
  int longDouble = 8;
  int caracterBoleanoByte = 1;

}
