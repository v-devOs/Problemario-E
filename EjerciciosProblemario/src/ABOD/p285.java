package ABOD;

import java.util.Scanner;

public class p285 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Evaluador evaluador = new Evaluador();

    int casosPrueba, valInsert, count;

    try {

      casosPrueba = input.nextInt();

      for ( count = 0; count < casosPrueba; count++) {
        
        valInsert = input.nextInt();

        while (valInsert != 0) {

          evaluador.insertarValor(valInsert);
          valInsert = input.nextInt();
        }
        
        evaluador.encontrarAlturas();
        evaluador.limpiarDatos();
      }

      
    } catch (Exception e) {
      System.err.println(e.toString());
      input.close();
    }
    
  }
}


class Evaluador{
  private Arbol arbol;
  // private int indexAltMayor;

  public void insertarValor( int valor ){
    arbol.insert(valor);
  }

  public void encontrarAlturas(){
    int altsArbol[] = obtenerAlturas();

    int altMayor = obtenerAltMayor(altsArbol);
    int altMenor = obtenerAltMenor(altsArbol, altMayor);

    mostrarAlturas(altMayor, altMenor);
  }

  private int[] obtenerAlturas(){
    arbol.recorerArbol(arbol.raiz);

    return parseToIntegerArray(arbol.getArrayAlturas());
  }

  private int[] parseToIntegerArray( String arrayToParse[] ){
    int arrayAltRamas[] = new int[arrayToParse.length];
    int index;

    for ( index = 0; index < arrayToParse.length; index++) {
      arrayAltRamas[index] = Integer.parseInt( arrayToParse[index] );
    }

    return arrayAltRamas;
  }

  private int obtenerAltMayor( int altsArbol[] ){
    int index, altMayor = 1;

    for ( index = 0; index < altsArbol.length; index++) {
      if( altsArbol[index] > altMayor ) altMayor = altsArbol[index];
    }

    return altMayor;
  }

  private int obtenerAltMenor( int altsArbol[], int altMayor ){

    boolean seEncontro = false;
    int index, altMenor = 1;

    for ( index = 0; index < altsArbol.length; index++) {

      if( altsArbol[index] > altMenor && altsArbol[index] != altMayor ){
        altMenor = altsArbol[index];
        seEncontro = true;
      }
    }

    return validarAlturaMenor(altMayor, altMenor, seEncontro);
  }

  private int validarAlturaMenor( int altMayor, int altMenor, boolean seEncontro ){
    return seEncontro ? altMenor : altMayor;
  }

  private void mostrarAlturas( int altMayor, int altMenor ){
    System.out.println(altMenor + " " + altMayor);
  }

  public void limpiarDatos(){
    arbol.limpiarArbol();
  }

  Evaluador(){
    arbol = new Arbol();
  }
}



class Arbol{
  Nodo raiz;
  private int altura;
  private String alturasRamas;

  public void insert( int valor ){
    Nodo temp = new Nodo(valor);

    if( raiz == null ){
      raiz = temp;
    } 
    else{
      Nodo auxBusqueda = raiz;
      Nodo seguidor = auxBusqueda;

      do{
        seguidor = auxBusqueda;

        if( valor > auxBusqueda.info ){
          auxBusqueda = auxBusqueda.der;
        }
        else{
          auxBusqueda = auxBusqueda.izq;
        }
      }while( auxBusqueda != null );
      
      if( valor > seguidor.info ){
        seguidor.der = temp;
      }
      else{
        seguidor.izq = temp;
      }
    } 
  }

  public void recorerArbol( Nodo aux ){

    if( aux.izq == null && aux.der == null ){
      insertAltura(altura);
    }

    if( aux.izq != null ){
      altura++;
      recorerArbol(aux.izq);
    }
        
    if( aux.der != null ){
      altura++;
      recorerArbol(aux.der);
    }

    altura--;
  }

  private void insertAltura( int altura ){
    if( alturasRamas.length() > 0) alturasRamas += " " + altura;
    else alturasRamas += altura;
  }

  public String[] getArrayAlturas(){
    return alturasRamas.split(" ");
  }

  public void limpiarArbol(){
    raiz = null;
    altura = 1;
    alturasRamas = "";
  }

  Arbol(){
    raiz  = null;
    altura = 1;
    alturasRamas = "";
  }
  
}

class Nodo {
  int info;

  Nodo izq, der;

  Nodo( int info ){
    this.info = info;
    izq = der = null;
  }
}
