import java.util.Scanner;

public class p272 {
  public static void main(String[] args) {
    Algoritmo[] algoritmos;
    Algoritmo algoritmo, algoritmoCosteado;
    Scanner input = new Scanner(System.in);
    String instrucciones;
    Procesador procesador = new Procesador();
    int casosPrueba, index;


    casosPrueba = Integer.parseInt( input.nextLine() );

    for ( index = 0; index < casosPrueba; index++) {

      procesador.leerInstrucciones();

      System.out.println("Algoritmo "+algoritmoEficiente.index+" => "+algoritmoEficiente.costoAlgoritmo+" bytes");
      

    }
    input.close();
  }
}

class Procesador{
  private Algoritmo[] algoritmoEvaluados;
  Costos costoTipoDato;

  public Algoritmo leerInstrucciones(){
    Algoritmo[] algoritmosProcesados;
    Scanner input = new Scanner(System.in);
    String instrucciones;
    int pruebasHechas, casosEvaluar;

    casosEvaluar = Integer.parseInt(input.nextLine());
    algoritmosProcesados = new Algoritmo[casosEvaluar];

    for ( pruebasHechas = 0; pruebasHechas < casosEvaluar; pruebasHechas++) {
      instrucciones = input.nextLine();
      procesarAlgoritmo(instrucciones, pruebasHechas);
      

    // arrayInstrucciones = algoritmo.obtenerArreglo();
    // calcularCosto();

    // algoritmo.costoAlgoritmo = costoAlgoritmo;
    // algoritmo.totalInstrucciones = arrayInstrucciones.length;
    // algoritmo.index = index;

    input.close();

    return algoritmo;
  }

  private Algoritmo procesarAlgoritmo(String instrucciones, int index){
    String[] arrayInstrucciones = instrucciones.split(",");
    int costoAlgoritmo = calcularCosto(arrayInstrucciones);
    int totalInstrucciones = calcularTotalInstrucciones(arrayInstrucciones);

    Algoritmo algoritmoProcesado = new Algoritmo(index, costoAlgoritmo, totalInstrucciones );
    return algoritmoProcesado;

  }

  private int calcularCosto(String[] arrayInstrucciones){
    int index, costoAlgoritmo = 0;

    for ( index = 0; index < arrayInstrucciones.length; index++) {
      costoAlgoritmo = sumarBytes( arrayInstrucciones[index]);
    }

    return costoAlgoritmo;
  }

  private int calcularTotalInstrucciones( String[] instrucciones ){
    return instrucciones.length;
  }

  private int sumarBytes(String tipoDato){
    int costoAlgoritmo = 0;

    // if( tipoDato.equalsIgnoreCase("char") || tipoDato.equalsIgnoreCase("boolean") || tipoDato.equalsIgnoreCase("byte")){
    //   costoAlgoritmo += costoTipoDato.caracterBoleanoByte;
    // }
    // else if( tipoDato.equalsIgnoreCase("int") || tipoDato.equalsIgnoreCase("float")){
    //   costoAlgoritmo += costoTipoDato.enteroFlotante;
    // }
    // else if( tipoDato.equalsIgnoreCase("long") || tipoDato.equalsIgnoreCase("double")){
    //   costoAlgoritmo += costoTipoDato.longDouble;
    // }
    // else{
    //   costoAlgoritmo += costoTipoDato.string;
    // } 

    switch (tipoDato) {
      case "char":
      case "boolean":
      case "byte":
        costoAlgoritmo += costoTipoDato.caracterBoleanoByte;
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
    algoritmoEvaluados = new Algoritmo[0];
    costoTipoDato = new Costos();
  }
}

class Algoritmo{
  // private String instrucciones;
  // private String[] arrayInstrucciones;
  public int costoAlgoritmo;
  public int totalInstrucciones, index;

  

  

  Algoritmo(int index, int costo, int totalInstrucciones){
    // this.instrucciones = "";
    costoAlgoritmo = costo;
    this.totalInstrucciones = totalInstrucciones;
    this.index = index;
  }
}

class Costos{
  int string = 14;
  int enteroFlotante = 4;
  int longDouble = 8;
  int caracterBoleanoByte = 1;

}
