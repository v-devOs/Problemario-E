import java.util.Scanner;

public class TareaListas {
  public static void main(String[] args) {
    try {
      Scanner input = new Scanner(System.in);
      ListaE lista = new ListaE(5);

      lista.insert( input.nextInt() );
      lista.insert( input.nextInt() );
      
      lista.listar();

      lista.remove( input.nextInt() );
      lista.insert( input.nextInt());
      lista.insert( input.nextInt());

      lista.listar();

      lista.insert( input.nextInt() );
      lista.insert( input.nextInt() );

      lista.remove( input.nextInt() );
      lista.listar();


      input.close();
    } catch (Exception e) {
      
    }
  }
}

class ListaE{
  int datos[];
  int cont;
  boolean error;

  public void insert( int dato ){
    if( cont == datos.length ){
      System.out.println("Overflow");
    }
    else if( cont == 0 ){
      datos[ 0 ] = dato;
      cont++;
    }
    else{
      int auxCont = cont;
      
      while ( auxCont > 0 && dato < datos[(auxCont - 1)]) {
        datos[ auxCont ] = datos[ auxCont -1 ];
        auxCont--;
      }

      datos[ auxCont ] = dato;
      cont++;
    }
  }

  public void remove( int dato ){
    int auxDato = 0;

    if( cont == 0 ){
      error = true;
      System.out.println("Underflow");
    }
    else if( datos[0] == dato ){
      int index = 0;
      auxDato = datos[0];

      while (index < cont) {
        datos[ index ] = datos[ index + 1];
        index++;
      }
      cont--;
      System.out.println( "Salio el: " + auxDato); 

    }
    else if( datos[ cont ] == dato){
      auxDato = datos[ cont ];
      cont--;
      System.out.println(auxDato); 

    }
    else{
      int auxCont = 0;
      boolean seEcontro = false;

      while ( auxCont < cont && !seEcontro) {
        if( datos[ auxCont ] == dato ){
          auxDato = datos[ auxCont ];
          seEcontro = true;
        }
        else{
          auxCont++;
        }
      }

      if( seEcontro ){

        while ( auxCont < cont ) {
          datos[ auxCont + 1] = datos[ auxCont ];
          auxCont++;
        }
        System.out.println(auxDato); 
        cont--;

      }
      else{
        error = false;
        System.out.println("Underflow"); 

      }

    
    }
  }

  public void listar(){
    System.out.println("-------------------");
    for (int i = 0; i < cont; i++) {
      System.out.println(datos[ i ]);
    }
  }

  ListaE( int cuantos ){
    datos = new int[cuantos];
    cont = 0;
    error = false;
  }

}
