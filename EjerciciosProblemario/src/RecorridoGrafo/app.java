package RecorridoGrafo;

public class app {
  public static void main(String[] args) {
    DFS dfs;
    int matriz[][] = new int[][] {
            {0,0,0,1,1,0},
            {0,0,1,0,0,1},
            {0,1,0,0,0,1},
            {1,0,0,0,1,0},
            {1,0,0,1,0,1},
            {0,1,1,0,1,0},
          
          };

    dfs = new DFS(matriz);
    dfs.buscarRuta(4, 8);
  }
}

class DFS{
  private Pila pila;
  private int[] ruta;
  private int[][] matriz;

  public void buscarRuta( int vectIni, int vectFinal ){
    Nodo nodoActual;
    int vectActual;

    pila.push(vectIni, 0);

    do {

      nodoActual = pila.pop();

      vectActual = nodoActual.info;
      ruta[nodoActual.nivel] = vectActual;

      if( vectActual != vectFinal )
        expandir( vectActual, nodoActual.nivel+1 );

    } while ( vectActual != vectFinal && !pila.estaVacia());

    if( vectActual == vectFinal ){
      System.out.println("Si hay ruta");
      mostrarRuta( nodoActual.nivel );
    }
    else{
      System.out.println("No hay ruta de: " + vectIni +" hacia: " + vectFinal);
    }
  }

  private void expandir( int vectActual, int nivel ){
    int columna;

    for ( columna = 0; columna < matriz.length; columna++) {
      
      if( matriz[vectActual][columna] != 0 && noEstaEnRuta(columna, nivel)){

        pila.push( columna, nivel );
      }
    }
  }

  private boolean noEstaEnRuta( int vert, int nivel ){
    int cont = 0;

    while ( nivel > 0 && cont < nivel && ruta[cont] != vert) {
      cont++;
    }

    if( nivel == 0) return true;
    else{
      if( cont == nivel ) return true;
      else return false;
    }
  }

  private void mostrarRuta( int nivel ){
    int index;

    for ( index = 0; index < nivel; index++) {
      System.out.println(ruta[index]);
    }
  }

  DFS( int[][] matriz ){
    this.matriz = matriz;
    ruta = new int[matriz.length];
    pila = new Pila();
  }

}


class Pila{
  Nodo tope;

  public void push( int info, int nivel ){
    Nodo temp = new Nodo(info, nivel);

    if( tope == null ) tope = temp;
    else{
      temp.sig = tope;
      tope = temp;
    }
  }

  public Nodo pop(){

    Nodo aux;

    if( tope == null ) return null;
    else{
      aux = tope;
      tope = tope.sig;

      return aux;
    }
  }

  public boolean estaVacia(){
    return tope == null;
  }

  Pila(){
    tope = null;
  }
}

class Nodo{
  int info, nivel;
  Nodo sig;

  Nodo( int info, int nivel ){
    this.nivel = nivel;
    this.info = info;
    sig = null;
  }
}
