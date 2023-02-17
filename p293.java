import java.util.Scanner;

public class p293{
  public static void main(String[] args) {
    ControladorHibrido ctrlHibrido = new ControladorHibrido();
    try (Scanner input = new Scanner(System.in)) {
      String instruccion = "";
      int casosPrueba, casosHechos;

      casosPrueba = Integer.parseInt(input.nextLine());

      for ( casosHechos = 1; casosHechos <= casosPrueba; casosHechos++) {
        
        while ( !instruccion.equals("FINISH") ) {
          instruccion = input.nextLine();
          ctrlHibrido.procesarInstruccion(instruccion);
        }
        instruccion = "";
      }

      input.close();
    } catch (NumberFormatException e) {
      
    }
  }
}

class ControladorHibrido{
  public void procesarInstruccion(String instruccion){
    analizarInstruccion(instruccion);
  }

  private String[] separarInstruccion( String instruccion){
    return instruccion.split(" ");
  }

  private void analizarInstruccion( String instruccion ){
    String[] instruccionSeparada = separarInstruccion(instruccion);
    String instruccionEstructura;
    int valor = 0;

    if( instruccionSeparada.length > 1){
      instruccionEstructura = instruccionSeparada[0];
      valor = Integer.parseInt(instruccionSeparada[1]);
    }
    else
      instruccionEstructura = instruccionSeparada[0];
    
    determinarEjecutarInstruccion(instruccionEstructura, valor);
    
  }

  private void determinarEjecutarInstruccion( String instruccionEstructura, int valor ){
    // System.out.println(instruccionEstructura + " llegue hasta determinar " + valor);
    switch (instruccionEstructura) {
      case "PUSH":
      case "IN":
      case "INSERT":
      
        break;
      case "POP":
      case "OUT":
      case "REMOVE":
        realizarSalida(instruccionEstructura, valor);
      default:
        break;
    }
  }

  private String realizarSalida( String instruccion, int valor ){
    
    return "";
  }
}



class Hibrido{
  Nodo start, end, nodoAux;
  String cadenaFinal;

  
  Hibrido(){
    cadenaFinal = "";
    start = null;
    nodoAux = null;
    end = null;
  }
}

class Nodo{
  int valor;
  Nodo nodoSiguiente;

  Nodo(int valor){
    this.valor = valor;
    nodoSiguiente = null;
  }
}