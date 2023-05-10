package DTN;

import java.util.Scanner;

public class p383 {
  public static void main(String[] args) {
    Apilador apilador = new Apilador();
    Scanner input = new Scanner(System.in);
    int cantBloques, bloque;
    int count;

    try {
      cantBloques = input.nextInt();

      for ( count = 0; count < cantBloques; count++) {
        bloque = input.nextInt();
        apilador.apilarNumero(bloque);
      }

      apilador.mostarApilaciones();

    } catch (Exception e) {
      System.err.println(e.toString());
      input.close();// TODO: handle exception
    }
  }
}


class Apilador{
  private Pila pila;

  public void apilarNumero( int num ){
    pila.push(num);
  }

  public void mostarApilaciones(){
    pila.vaciar();
  }

  Apilador(){
    pila = new Pila();
  }
}


class Nodo{
  int num;
  Nodo sig;

  Nodo( int num ){
    this.num = num;
    sig = null;
  }
}


class Pila{
  Nodo tope;

  public void push( int num ){
    Nodo temp = new Nodo(num);

    if( tope == null ) tope = temp;
    else{
      validarPush(temp);
    }
  }

  private void validarPush( Nodo temp ){
    
    if( tope.num != temp.num ){
      temp.sig = tope;
      tope = temp;
    }
    else{
      apilarBloques(temp);
    }
  }

  private void apilarBloques( Nodo temp ){
    Nodo aux;

    do {
      aux = pop();
      temp.num = temp.num + aux.num;
    } while (tope != null && aux.sig != null &&aux.sig.num == temp.num);

    temp.sig = tope;
    tope = temp;
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

  public void vaciar(){
    Nodo aux = pop();

    while (aux != null) {
      System.out.println(aux.num);
      aux = pop();
    }
  }

  Pila(){
    tope = null;
  }
}