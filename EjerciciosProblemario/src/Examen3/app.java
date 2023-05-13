package Examen3;

public class app {
  
}


class ArbolB{
  pagina raiz;


  public pagina buscarElemento( int elemento ){

    if( raiz.N == 0 ) return null;
    else{
      return buscar(elemento, raiz);
    }

  }

  private pagina buscar( int elemento, pagina aux ){

    int index = 0;
    boolean seEncontro = false;

    while ( aux != null && !seEncontro && index < aux.N) {
      
      if( aux.valor[index] == elemento ) seEncontro = true;
      else{
        index++;
      }
    }

    if( seEncontro ){
      System.out.println("Se encontro el elemento: "+ elemento);
      return raiz;
    }
    else{
      int indexHijo = 0;

      while( indexHijo < aux.N && elemento > aux.valor[indexHijo]){
        indexHijo++;
      }

      return buscar(elemento, aux.hijos[indexHijo]);
    }
    
  }


  ArbolB( int orden){
    raiz = new pagina(orden);
  }

}



class pagina{
  int valor[];
  pagina hijos[];
  int orden,N;

  pagina(int pOrden){
     orden=pOrden;
     N=0;
     valor = new int[orden*2];
     hijos =new pagina[orden*2+1];
  }

  
}