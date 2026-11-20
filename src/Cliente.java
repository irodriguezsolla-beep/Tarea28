import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args){
        try {
            Socket socket = new Socket("localhost", 8000);

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);


            Scanner sc = new Scanner(System.in);
            System.out.println("Escribe un numero del 1 al 100");

            while (true) {
                String entradatexto = sc.nextLine();
                salida.println(entradatexto);
                String respuesta = entrada.readLine();
                if (respuesta == null) {
                    break;
                }

                if (respuesta.equals("OK Hasta luego")) {
                    System.out.println("Servidor: " + respuesta);
                    break;
                }

                if (respuesta.equals("MAYOR") || respuesta.equals("MENOR") ){
                    System.out.println("Servidor: "+ respuesta);
                }else{
                    System.out.println("Servidor: "+ respuesta);
                    break;
                }


            }
            entrada.close();
            salida.close();
            socket.close();
        }catch (ConnectException e){
            System.out.println("No hay nadie escuchando en ese puerto");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
