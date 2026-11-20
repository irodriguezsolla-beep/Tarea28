import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Servidor {
    public static  void main(String[] args){
        try{
            ServerSocket servidor = new ServerSocket(8000);
            System.out.println("Esperando conexiones....");

            Socket cliente = servidor.accept();
            System.out.println("Conectandose desde "+ cliente.getInetAddress());

            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(),true);

            Random num = new Random() ;
            int numero = num.nextInt(1,101);
            int intentos = 0;


            while (true){
                String numerocliente = entrada.readLine();
                if (numerocliente == null) {
                    break; // El cliente cerró con Ctrl+C
                }

                if (numerocliente.trim().equalsIgnoreCase("ADIOS")) {
                    salida.println("OK Hasta luego");
                    break;
                }


                System.out.println("El cliente dice el numero "+ numerocliente);
                // pasar de String a int
                int nCliente;
                try {
                    nCliente = Integer.parseInt(numerocliente.trim());
                } catch (NumberFormatException e) {
                    salida.println("ERR Comando no válido");
                    continue; // Vuelve arriba del while a esperar otra línea
                }

                intentos++;

                if(nCliente < numero ){
                    String Mayor ="MAYOR";
                    salida.println(Mayor);
                }
                if (nCliente > numero){
                    String Menor = "MENOR";
                    salida.println(Menor);
                }
                if(nCliente == numero){
                    String Acertar =  "ACIERTO en " + intentos+ " intentos" ;
                    salida.println(Acertar);
                    break;
                }

            }
            entrada.close();
            salida.close();
            cliente.close();
            servidor.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
