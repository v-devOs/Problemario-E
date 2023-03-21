public class Examen {
  public static void main(String[] args) {
    Pila[] estruc = new Pila[32];
    int pos = 0;

    String nombreLeido = "";
    char caracterNomre = nombreLeido.charAt(0);
    boolean seInserto = false;

    while (!seInserto) {
      if(pos == 0){
        estruc[0] = new Pila();
        estruc[0].input(nombreLeido);
        estruc[0].key = nombreLeido.charAt(0);
        pos++;
        seInserto = true;
      }
      else if( Character.compare(estruc[pos - 1].key, caracterNomre) == 0){
        estruc[pos -1 ].input(nombreLeido);
        seInserto = true;
      }
      else{
        boolean seEncontroLugar = false;
        int aux = 0;

        while (!seEncontroLugar && aux < pos) {
          
          if( Character.compare(estruc[aux].key, caracterNomre) == 0){
            estruc[aux].input(nombreLeido);
            seEncontroLugar = true;
          }

          aux++;
        }

        if(!seEncontroLugar){
          estruc[pos] = new Pila();
          estruc[pos].input(nombreLeido);
          estruc[pos].key = nombreLeido.charAt(0);
          pos++;
          seInserto = true;
        }
      }
    }

  } 
}


class nodo{
  String nombre;
  nodo sig;
  

  nodo( String nombre ){
    this.nombre = nombre;
    sig = null;
  }
}



class Pila {
    char key;
    private nodo tope;
    private nodo nodoAux;

    public void input( String nombre ){
        nodo nodoInput = new nodo( nombre);

        if(tope == null){
            tope = nodoInput;
        }
        else{
            nodoAux = tope;
            tope = nodoInput;
            tope.sig = nodoAux;
        }     
    }
    

    public void recorrer(char key){
      nodoAux = tope;
    }
    Pila(){
        tope = null;
        nodoAux = null;
    }
}
