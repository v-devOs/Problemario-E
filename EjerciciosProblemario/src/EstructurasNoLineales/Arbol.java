package EstructurasNoLineales;

public class Arbol{
  Nodo raiz, predecesor, padrePredecesor;

  public void insert( int valor ){
    Nodo temp = new Nodo(valor);

    if( raiz == null ) 
      raiz = temp;
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
  
  public Nodo remove( int valor ){
    if( raiz == null ){
      return null;
    }
    else{

      Nodo auxBusqueda = raiz, seguidor = auxBusqueda;


      while ( auxBusqueda != null && auxBusqueda.info != valor) {
        seguidor = auxBusqueda;

        if( valor > auxBusqueda.info )
          auxBusqueda = auxBusqueda.der;
        else
          auxBusqueda = auxBusqueda.izq;
      }

      if( auxBusqueda == null ){
        return null;
      }
      else{

        if( auxBusqueda.der == null && auxBusqueda.izq == null){

          if( valor > seguidor.info )
            seguidor.der = null;
          
          else
            seguidor.izq = null;
            
          return auxBusqueda;
        }
        else if( auxBusqueda.der != null && auxBusqueda.izq != null){
          
          buscaPredesesor(auxBusqueda.izq);
          auxBusqueda.info = predecesor.info;

          if(predecesor.izq != null ){
            padrePredecesor.der = predecesor.izq;
          }
          else{
            if( predecesor.info > padrePredecesor.info ){
              padrePredecesor.der = null;
            }
            else 
              padrePredecesor.izq = null;
          }

          return auxBusqueda;
          
        }
        else{

          if( auxBusqueda.izq == null ){
            if( seguidor.info < auxBusqueda.info ){
              seguidor.der = auxBusqueda.der;
            }
            else
              seguidor.izq = auxBusqueda.der;
          }
          else{
            if( seguidor.info < auxBusqueda.info)
              seguidor.der = auxBusqueda.izq;
            else 
              seguidor.izq = auxBusqueda.izq;
          }

          return auxBusqueda;
        }
      }

    }
  }

  private void buscaPredesesor(Nodo inicio ){
    predecesor = padrePredecesor = inicio;

    while ( predecesor.der != null) {
      padrePredecesor = predecesor;
      predecesor = predecesor.der;
    }
  }

  void inOrder( Nodo aux ){
    if( aux.izq != null )
      inOrder(aux.izq);

    System.out.println(aux.info); //Los procedmientos recursivos usan de manera automatica el segmento stack

    if( aux.der != null )
      inOrder(aux.der);
  }
  void preOrder( Nodo aux ){
    System.out.println(aux.info); //Los procedmientos recursivos usan de manera automatica el segmento stack

    if( aux.izq != null )
      preOrder(aux.izq);

      
      if( aux.der != null )
        preOrder(aux.der);
  }
  void postOrder( Nodo aux ){
    if( aux.izq != null )
      postOrder(aux.izq);


    if( aux.der != null )
      postOrder(aux.der);
    
    System.out.println(aux.info); //Los procedmientos recursivos usan de manera automatica el segmento stack
  }


  Arbol(){
    raiz = padrePredecesor = predecesor = null;
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
