package TCPClient;

import java.net.*;
import java.io.*;
import java.util.Observable;


public class SocketCliente extends Observable implements Runnable {
   private  Socket socket;
   private  DataInputStream bufferEntrada;
   private  DataOutputStream bufferSalida;
   public volatile boolean running = true;


    /**
     * Crea el socket cliente y lee los datos
     */
    public SocketCliente(String host, int port){
        try {
            /* Se crea el socket cliente */
            socket = new Socket (host, port);
            System.out.println ("conectado");
            /* Se hace que el cierre espere a la recogida de todos los datos desde
             * el otro lado */
            socket.setSoLinger (true, 10);
            /* Se obtiene un stream de lectura para leer objetos */
            bufferEntrada = new DataInputStream (socket.getInputStream());

            /* Se lee un Datosocket que nos envía el servidor y se muestra
             * en pantalla */

            System.out.println ("Cliente Java: Recibido " + this.listenForMessage());

            /* Se obtiene un flujo de envio de datos para enviar un dato al servidor */
            bufferSalida = new DataOutputStream (socket.getOutputStream());

            /* Se crea el dato y se escribe en el flujo de salida */
            this.sendMessage("Hola desde cliente");

        }
        catch (Exception e)
        {
            System.out.println("fallé en creación de socket");
            e.printStackTrace();
        }
    }
    public void closeSocket(){
        try {
            running=false;
            bufferEntrada.close();
            bufferSalida.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("fallé en cerrar socket");
        }
    }

    public void sendMessage(String message){
        try {
            DatoSocket aux = new DatoSocket (message);
            aux.writeObject (bufferSalida);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public String listenForMessage(){
       DatoSocket dato = new DatoSocket("");
       try {
           dato.readObject(bufferEntrada);
       } catch (IOException e) {
           e.printStackTrace();
       }
       return dato.data;
   }



   public void run(){
        while(running){
            try {
            String mensaje = this.listenForMessage();
            setChanged();
            notifyObservers(mensaje);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Fallé en ejecución de thread");
                running=false;
            }
        }
   }
}