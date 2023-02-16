import java.io.RandomAccessFile;

public class Tarea2 {
  public static void main(String[] args) {
    Controlador controlador = new Controlador();

    controlador.listarDatos();

    // controlador.escribirDatos();
    controlador.actulizarDato(1, 999);
    System.out.println("-------------------------------");
    controlador.listarDatos();
    controlador.cerrarCanal();
  }
}

class Controlador{
  private RandomAccessFile file;

  public void listarDatos(){
    int index, posicion;
    try {
      file.seek(0);
      for ( index = 0, posicion = 0; index < file.length() - 1; index++, posicion++) {
        System.out.println(posicion +") "+ file.read());
      }
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }

  public void escribirDatos(){
    int index;

    try {
      for ( index = 0; index < 20; index++) {
        file.write((int) (Math.random() * 200));
      }
    } catch (Exception e) {
    }
  }

  public void actulizarDato( int index, int datoParaActualizar ){
    try {
      file.seek(5 );
      file.write(datoParaActualizar);
    } catch (Exception e) {
      System.out.println("Error al actualizar");
    }
  }

  public void cerrarCanal(){
    try {
      file.close();
    } catch (Exception e) {
    }
  }

  Controlador(){
    try {
      file = new RandomAccessFile("NumeosBinarios.bin", "rw");
    
    } catch (Exception e) {
      System.out.println("Ocurrio un error");
    }
  }
}
