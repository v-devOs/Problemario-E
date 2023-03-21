import java.io.RandomAccessFile;


public class p357 {
  public static void main(String[] args) {
    
    try {
      RandomAccessFile file = new RandomAccessFile("datos.suPtM", "rw");
      int linea = 0;
      
      
      while (linea < file.length() ) {
        
        System.out.println(file.readFloat());



        linea+=4;
      }
      

      file.close();
    } catch (Exception e) {
      System.out.println("Error en main");
    }
    // try {
    //   RandomAccessFile file = new RandomAccessFile("datos.suPtM", "rw");
    //   int contador = 0;

    //   while ( contador < 20) {
    //     int band = (int) (Math.random() * 10);

    //     if( band % 2 == 0 ){
    //       int num = (int) (Math.random() * 100);
    //       file.writeInt(num);
    //       System.out.println(num);
    //     }
    //     else{
    //       float num = (float) (Math.random() * 100);
    //       file.writeFloat(num);
    //       System.out.println(num);
    //     }

    //     contador++;
        
    //   }

    //   file.close();
      
    // } catch (Exception e) {
    //   System.out.println("error de escritura");
    // }
  }
}

class random{

}

// class Controlador{
//   int totalEnteros, totalFlotantes;

//   public void procesarArchivo(String nombreArchivo){
//     try {
//       RandomAccessFile file = new RandomAccessFile(nombreArchivo, "rw");
//       int linea = 0;

//       while ( linea < file.length() ) {
        

//         linea++;
//       }

//       file.close();
      
//     } catch (Exception e) {
//     }
//   }

//   Controlador(){
//     totalEnteros = totalFlotantes = 0;
//   }
// }
