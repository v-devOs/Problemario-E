import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Tarea {
  public static void main(String[] args) {
    Controlador controlador = new Controlador();

    controlador.actualizarArchivo();
    File doc = new File("Numeros.txt");
    File doc2 = new File("Actulizacion.txt");


    doc.delete();
    doc2.renameTo( new File("Numeros.txt"));
  }
}


class Controlador{
  

  public void actualizarArchivo(){
    
    try {
      File numeros = new File("Numeros.txt");
      File actualizacion = new File("Actualizacion.txt");

      Scanner file = new Scanner(numeros);
      Scanner input = new Scanner(System.in);

      PrintWriter escritor = new PrintWriter(actualizacion);

      int numeroLeido, datoActualizar, datoParaActualizar;

      
      datoActualizar = input.nextInt();
      datoParaActualizar = input.nextInt();

      while (file.hasNextInt()) {
        numeroLeido = file.nextInt();

        if( numeroLeido == datoActualizar ){
          escritor.println(datoParaActualizar);
        }
        else {
          escritor.println( numeroLeido );
        }
      }

      numeros.delete();

      input.close();
      escritor.close();
      
    } catch (Exception e) {
      System.out.println("No se encontro el archivo");
    }
  }

  private void borrarArchivoViejo(){
    try {
      File numeros = new File("Numeros.txt");
      numeros.delete();
    } catch (Exception e) {
      System.out.println("No se pudo borrar archivo");
    }
  }

  private void renombrarArchivo(){
    File oldFile = new File("Actulizacion.txt");
    File newFile = new File("Numeros.txt"); 

    oldFile.renameTo(newFile);
  }

 
  

}