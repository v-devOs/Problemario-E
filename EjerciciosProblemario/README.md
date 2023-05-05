## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

## Algoritmo DFS

```
int matriz[][];
  Pila pila = new Pila();
  int ruta[];

  void BPF(){
    int va, vf = 6, vi = 4;
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
      
    } while (va != vf && !pila.estaVacia());

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

```