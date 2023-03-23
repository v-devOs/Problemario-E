package EstructurasNoLineales;

public class App {
  public static void main(String[] args) {
    Arbol arbol = new Arbol();

    // for (int i = 0; i < 8; i++) {
    //   int randomNumber = (int) (Math.random() * 100);
    //   arbol.insert( randomNumber );
    //   System.out.println(randomNumber);
    // }

    arbol.insert(96);
    arbol.insert(46);
    arbol.insert(59);
    arbol.insert(41);
    arbol.insert(33);
    arbol.insert(79);
    arbol.insert(50);
    arbol.insert(43);

    System.out.println("-----------------Recorrido----------------");

    Nodo raiz = arbol.raiz;

    arbol.recorre(raiz);
    arbol.remove(46);
    // arbol.remove(33);
    // arbol.remove(43);
    // arbol.remove(50);
    // arbol.remove(79);
    System.out.println("-----------------Recorrido----------------");

    arbol.recorre(raiz);

    
  }
}
