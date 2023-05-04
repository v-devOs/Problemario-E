package RecorridoGrafo;

public class app {
  public static void main(String[] args) {
    app ap = new app();

    ap.BPF();
  }

  int matriz[][];
  Pila pila = new Pila();
  int ruta[];

  void BPF(){
    int va, vf = 1, vi = 4;
    Nodo temp;

    matriz = new int[][] {
            {0,0,0,1,1,0},
            {0,0,1,0,0,1},
            {0,1,0,0,0,1},
            {1,0,0,0,1,0},
            {1,0,0,1,0,1},
            {0,1,1,0,1,0},
          
          };

    ruta = new int[matriz.length];

    pila.push(vi, 0);

    do {
      temp = pila.pop();

      va = temp.info;

      ruta[temp.nivel] = va;

      if( va != vf )
        expandir( va, temp.nivel+1 );
      
    } while (va != vf && pila != null);

    if( va == vf ){
      System.out.println("Si hay ruta");
      for (int i = 0; i < temp.nivel; i++) {
        System.out.println( ruta[i]);
      }
    }
      
    else 
      System.out.println("No hay ruta");

  }

  void expandir( int vt, int nivel ){
    int columna;

    for ( columna = 0; columna < matriz.length; columna++) {
      if( matriz[ vt ][columna] != 0 && noEstaEnRuta(columna, nivel) )
        pila.push( columna, nivel );
    }
  }

  boolean noEstaEnRuta( int vert, int nivel ){
    int cont = 0;

    while ( nivel > 0 && cont < nivel && ruta[cont] != vert ) {
      cont++;
    }

    if( nivel == 0 )
      return true;
    else 
      if( cont == nivel ) return true;
      else return false;
  }
}

class DFS{
  // TODO: Refactorizar codigo
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
