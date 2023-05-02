package ABOD;

public class p285 {
  public static void main(String[] args) {
    Arbol arbol = new Arbol();

    arbol.insert(5);
    arbol.insert(2);
    arbol.insert(1);
    arbol.insert(7);
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

      System.out.println( "Alt Rama: " + altRama);

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
