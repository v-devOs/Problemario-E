import java.util.Scanner;

public class p293{
  public static void main(String[] args) {
    Hibrido hibrido = new Hibrido();
    Scanner input = new Scanner(System.in);
    String[] instruccion;
    int casosPrueba, casosHechos;

    // casosPrueba = Integer.parseInt(input.nextLine());

    // for ( casosHechos = 1; casosHechos <= casosPrueba; casosHechos++) {
      
    //   while ( !instruccion.equals("FINISH") ) {
    //     instruccion = input.nextLine();
    //   }
    //   instruccion = "";
    // }

    



    input.close();
  }
}



class Hibrido{
  Nodo start, end, nodoAux;
  String cadenaFinal;

  public void procesarInstruccion(String instruccion){
    
  }


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