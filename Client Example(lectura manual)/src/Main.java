

public class Main {
    /** Programa principal, crea el socket cliente */
    public static void main (String [] args)
    {

        SocketCliente pipe= new SocketCliente("localhost",8888); //<<<<<<<<<<<<<<<<<<<<Aquí se inicia la conexion, localhost si es en la misma máquina o ip para otra en la misma red

        //Ejemplo que envía los números múltiplos de 100 en [0,1000]
        for (int i=0; i<=1000;i++) {
            if(i%100==0)
                pipe.sendMessage(Integer.toString(i));
        }
        pipe.closeSocket();//cierra comunicación.
        //TODO PARA PODER RECIBIR MENSAJES HAY QUE METER EL SOCKET EN UN THREAD O PARECIDO, YA QUE EL MÉTODO listenForMessage se queda esperando hasta que entra un mensaje.

    }

}
