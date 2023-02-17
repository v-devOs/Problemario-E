import java.util.Scanner;

public class p293{
  public static void main(String[] args) {
    Hibrido hibrido = new Hibrido();
    try (Scanner input = new Scanner(System.in)) {
      String instruccion = "";
      int casosPrueba, casosHechos;

      casosPrueba = Integer.parseInt(input.nextLine());

      for ( casosHechos = 1; casosHechos <= casosPrueba; casosHechos++) {
        
        while ( !instruccion.equals("FINISH") ) {
          instruccion = input.nextLine();
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
    String[] instruccionSeparada = separarInstruccion(instruccion);
  }

  private String[] separarInstruccion( String instruccion){
    return instruccion.split(" ");
  }

  private void aInstruccion( String[] instruccionSeparada ){
    String instruccionEstructura;
    int valor;

    if( instruccionSeparada.length > 1){
      instruccionEstructura = instruccionSeparada[0];
      valor = Integer.parseInt(instruccionSeparada[1]);
    }
    
    instruccionEstructura = instruccionSeparada[0];
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