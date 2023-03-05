import java.io.RandomAccessFile;

public class p357 {
  public static void main(String[] args) {
    try {
      RandomAccessFile file = new RandomAccessFile("datos.suPtM", "rw");
      int contador = 0;

      while ( contador < 20) {
        int band = (int) (Math.random() * 10);

        if( band % 2 == 0 ){
          int num = (int) (Math.random() * 100);
          file.writeInt(num);
          System.out.println(num);
        }
        else{
          float num = (float) (Math.random() * 100);
          file.writeFloat(num);
          System.out.println(num);
        }

        contador++;
        
      }

      file.close();
      
    } catch (Exception e) {
      System.out.println("error de escritura");
    }
  }
}
