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
      }
      
    } catch (Exception e) {
      System.err.println(e.toString());
      input.close();
    }
    
  }
}


class Evaluador{
  private Arbol arbol;
  private String alturasNodos;
  private int indexAltMayor;

  public void insertarValor( int valor ){

    int alturaNodo = arbol.insert(valor);

    if( alturasNodos.length() > 0 ) alturasNodos += " " + alturaNodo;
    else alturasNodos += alturaNodo;
   
  }

  public void encontrarAlturas(){

    int arrayAltNodos[] = convertirArrayStrings();

    int altMayor = encontrarAlturaMayor(arrayAltNodos);
    int altMenor = encontrarAlturaMenor(altMayor, arrayAltNodos);

    mostrarAlturas(altMayor, altMenor);
  }

  private int[] convertirArrayStrings(){

    String auxAlts[] = alturasNodos.split(" ");
    int arrayAltNodos[] = new int[ auxAlts.length];
    int index;

    for ( index = 0; index < auxAlts.length; index++) {
      
      arrayAltNodos[index] = Integer.parseInt(auxAlts[index]);
    }

    return arrayAltNodos;
  }

  private int encontrarAlturaMayor( int arrayAltNodos[] ){

    int index, altMayor = 1;

    for ( index = 0; index < arrayAltNodos.length; index++) {
      
      if( arrayAltNodos[index] > altMayor ){
        altMayor = arrayAltNodos[index];
        indexAltMayor = index;
      }
    }

    return altMayor;
  }

  private int encontrarAlturaMenor( int altMayor, int arrayAltNodos[] ){

    int index, altMenor = 1;
    boolean seEncontroAltMenor = false;

    for ( index = 0; index < arrayAltNodos.length; index++) {
      
      if( arrayAltNodos[index] > altMayor && index != indexAltMayor && arrayAltNodos[index] != altMayor){

        altMenor = arrayAltNodos[index];
        indexAltMayor = index;
        seEncontroAltMenor = true;
      }
    }

    return validarAltMenor(seEncontroAltMenor, altMenor, altMayor);
  }

  private int validarAltMenor( boolean seEncontro, int altMenor, int altMayor ){
    if( seEncontro ) return altMenor;
    else return altMayor;
  }

  private void mostrarAlturas( int altMayor, int altMenor ){
    System.out.println(altMayor + " " + altMenor);
  }


  Evaluador(){
    arbol = new Arbol();
    alturasNodos = "";
    indexAltMayor = 0;
  }
}



class Arbol{
  Nodo raiz;

  public int insert( int valor ){
    Nodo temp = new Nodo(valor);
    int altRama = 1;

    if( raiz == null ){
      raiz = temp;
      return altRama;
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

        altRama++;
      }while( auxBusqueda != null );
      
      if( valor > seguidor.info ){
        seguidor.der = temp;
      }
      else{
        seguidor.izq = temp;
      }

      System.out.println( "Alt Rama: " + altRama + " :" + valor);

      return altRama;
    }

  }

  public void limpiarArbol(){
    raiz = null;
  }

  Arbol(){
    raiz  = null;
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
