package DatosMezclados;

import java.util.Scanner;

public class p282 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Operador operador;
    String cadena;
    int casosPrueba = Integer.parseInt(input.nextLine());
    int caso = 0;

    while( caso < casosPrueba ){
      cadena = input.nextLine();
      operador = new Operador(cadena);
      
      System.out.println("Cadena final: "+operador.procesarCadena());

      caso++;
    }
    
    input.close();

  }
}

class Operador{
  Pila pila;
  Cola cola;
  String cadenaOperaciones, cadenaFinal;
  char arrayCadenaOperaciones[];
  int indexActual;

  public String procesarCadena(){
    String valorCadena;

    while (indexActual < arrayCadenaOperaciones.length) {
      valorCadena = obtenerInfoCadena();
      procesarValoresCadena(valorCadena);
    }

    cadenaFinal += cola.liberar();
    cadenaFinal += pila.liberar();

    return cadenaFinal;
  }

  private String obtenerInfoCadena(){
    String valores = "";
    int vuelta = 0;

    while (vuelta < 2) {
      valores += arrayCadenaOperaciones[indexActual];
      vuelta++;
      indexActual++;
    }
    // System.out.println(valores);
    return valores;
  }

  private void procesarValoresCadena( String valorCadena ){
    if( valorCadena.equalsIgnoreCase("PU") || valorCadena.equalsIgnoreCase("LL")){
      realizarInput(valorCadena);
    }
    else{
      realizarOutput(valorCadena);
    }
  }

  private void realizarInput( String accion){
    String valorInput = obtenerInfoCadena();

    if( accion.equalsIgnoreCase("PU")){
      pila.push(valorInput);
    }
    else{
      cola.input(valorInput);
    }

    System.out.println(" input de "+ valorInput +" realizado correctamente");
  }

  private void realizarOutput( String accion){
    String valorOutput;

    if( accion.equalsIgnoreCase("PO")){
      valorOutput = pila.pop();
    }
    else{
      valorOutput = cola.output();
    }

    cadenaFinal += formatearDatos(valorOutput);
    
    System.out.println("'Se realizo output de'" + valorOutput);
  }

  private String formatearDatos( String datoSalida ){
    if( cadenaFinal.length() > 2){
      return datoSalida + ",";
    }
    else{
      return datoSalida;
    }
  }
  Operador(String cadena){
    cadenaFinal = "";
    cadenaOperaciones = cadena;
    arrayCadenaOperaciones = cadenaOperaciones.toCharArray();
    indexActual = 0;
    pila = new Pila();
    cola = new Cola();
  }
}

class Pila {
  private Nodo tope;
    private Nodo nodoAux;

    public void push(String dato){
        Nodo nodoInput = new Nodo(dato);

        if(tope == null){
            tope = nodoInput;
        }
        else{
            nodoAux = tope;
            tope = nodoInput;
            tope.nodoSiguiente = nodoAux;
        }

        // System.out.println("Se inserto"+ nodoInput.dato);
    }

    public String pop(){
        if(!sePuedeOutput()){
           return "##";
        }
        else{
            nodoAux = tope;
            tope = tope.nodoSiguiente;
            return nodoAux.dato;
        }
    }

    private boolean sePuedeOutput(){
        
        if(tope == null){ return false;}
        else{return true;}
    }

    public String liberar(){
      String datos = "";
      boolean sePuedePop = sePuedeOutput();

      while (sePuedePop) {
        datos += pop();
        sePuedePop = sePuedeOutput();
      }

      return datos;
    }


    Pila(){
        tope = null;
        nodoAux = null;
    } 
}

class Cola{
  private Nodo inicio;
    private Nodo nodoAux;

    public void input(String dato){
        Nodo nodoInput = new Nodo(dato);

        if(inicio == null){
            inicio = nodoInput;
        }
        else{
            buscarPosicion();
            nodoAux.nodoSiguiente = nodoInput;
        }
    }

    private void buscarPosicion(){
        nodoAux = inicio;

        while(nodoAux.nodoSiguiente != null){
            nodoAux = nodoAux.nodoSiguiente;
        }
    }

    public String output(){
        if( !sePuedeOutput() ){
            return "$$";
        }
        else{
            nodoAux = inicio;
            inicio = inicio.nodoSiguiente;
            return nodoAux.dato;
        }
    }

    private boolean sePuedeOutput(){
      if( inicio == null) return false;
      else return true;
    }

    public String liberar(){
      String datos = "";
      boolean sePuedeOut = sePuedeOutput();

      while (sePuedeOut) {
        datos += output();
        sePuedeOut = sePuedeOutput();
      }

      return datos;
    }

    Cola(){
        inicio = null;
        nodoAux = null;
    }
}

class Nodo {

  String dato;
    Nodo nodoSiguiente;

    Nodo(String dato){
        this.dato = dato;
        nodoSiguiente = null;
    }
}

