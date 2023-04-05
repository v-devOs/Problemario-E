package CostoMemoria;

import java.util.Scanner;

public class p272 {
  public static void main(String[] args) {
    Algoritmo[] algoritmos;
    Algoritmo algoritmoEficiente, algoritmoProcesado;
    Procesador procesador = new Procesador();

    try (Scanner input = new Scanner(System.in)) {
      int casosPrueba, index, casosEvaluar, casosEvaluados;
      String instrucciones;

      casosPrueba = Integer.parseInt( input.nextLine() );
      for ( index = 0; index < casosPrueba; index++) {

        casosEvaluar = Integer.parseInt( input.nextLine());
        algoritmos = new Algoritmo[ casosEvaluar ];

        for ( casosEvaluados = 0; casosEvaluados < casosEvaluar; casosEvaluados++) {
          instrucciones = input.nextLine();
          algoritmoProcesado = procesador.leerProcesarInstrucciones( casosEvaluados, casosEvaluar, instrucciones );
          algoritmos[ casosEvaluados ] = algoritmoProcesado;
        }

        algoritmoEficiente = procesador.definirAlgoritmoEficiente(algoritmos);

        System.out.println("Algoritmo "+algoritmoEficiente.index+" => "+algoritmoEficiente.costoAlgoritmo+" bytes");
        
        
      }

      input.close();
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }
  }
}

class Procesador{
  private Costos costoTipoDato;


  public Algoritmo leerProcesarInstrucciones(int index, int casosEvaluar, String instrucciones){
    Algoritmo algoritmoProcesado;
    
    algoritmoProcesado = procesarAlgoritmo(instrucciones, index);
    
    return algoritmoProcesado;
  }

  private Algoritmo procesarAlgoritmo(String instrucciones, int index){
    String[] arrayInstrucciones = instrucciones.split(",");
    int costoAlgoritmo = calcularCosto(arrayInstrucciones);
    int totalInstrucciones = calcularTotalInstrucciones(arrayInstrucciones);

    Algoritmo algoritmoProcesado = new Algoritmo((index+1), costoAlgoritmo, totalInstrucciones );
    return algoritmoProcesado;

  }

  private int calcularCosto(String[] arrayInstrucciones){
    int index, costoAlgoritmo = 0;

    for ( index = 0; index < arrayInstrucciones.length; index++) {
      costoAlgoritmo += sumarBytes( arrayInstrucciones[index]);
    }

    return costoAlgoritmo;
  }

  private int calcularTotalInstrucciones( String[] instrucciones ){
    return instrucciones.length;
  }

  private int sumarBytes(String tipoDato){
    int costoAlgoritmo = 0;

    switch (tipoDato) {
      case "char":
        costoAlgoritmo += costoTipoDato.caracter;
        break;
      case "boolean":
      case "byte":
        costoAlgoritmo += costoTipoDato.boleanoByte;
        break;
      case "int":
      case "float":
        costoAlgoritmo += costoTipoDato.enteroFlotante;
        break;
      case "long":
      case "double":
        costoAlgoritmo += costoTipoDato.longDouble;
        break;
      case "String":
        costoAlgoritmo += costoTipoDato.string;
        break;
        
        default:
        break;
    }
    
    return costoAlgoritmo;
  }

  public Algoritmo definirAlgoritmoEficiente( Algoritmo[] algoritmos ){
    Algoritmo algoritmoEficiente = new Algoritmo();
    int index;

    for (index = 0; index < algoritmos.length; index++) {
      if( index == 0 ) 
        algoritmoEficiente = algoritmos[index];
      else
        algoritmoEficiente = compararAlgoritmos(algoritmoEficiente, algoritmos[index]);
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
  public int costoAlgoritmo;
  public int totalInstrucciones, index;

  Algoritmo(int index, int costo, int totalInstrucciones){
    costoAlgoritmo = costo;
    this.totalInstrucciones = totalInstrucciones;
    this.index = index;
  }
  Algoritmo(){}
}

class Costos{
  int string = 16;
  int enteroFlotante = 4;
  int longDouble = 8;
  int boleanoByte = 1;
  int caracter = 2;
}
