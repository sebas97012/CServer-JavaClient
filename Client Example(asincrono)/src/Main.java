import TCPClient.SocketCliente;

import java.util.Observable;

public class Main {
    public static void main (String [] args)
    {
        //**************************</INICIALIZACIÓN DE LA CONEXIÓN************************************************
        //Se debe de hacer solo una vez cuando el programa inicia
        SocketCliente pipe= new SocketCliente("localhost",8888); //<<<<<<<<<<<<<<<<<<<<localhost si es en la misma máquina o ip para otra en la misma red
        pipe.addObserver((Observable obj, Object arg) -> {//Añade un observer que se va a encargar de escuchar los mensajes entrantes, es asíncrono
            System.out.println("\nRespuesta recibida: " + arg);//arg es el mensaje recibido.
        });
        Thread thread=new Thread(pipe);
        thread.start();
        //**************************INICIALIZACIÓN DE LA CONEXIÓN/>************************************************




        //Ejemplo de como enviar mensajes, envía los números múltiplos de 100 en [0,1000]
        for (int i=0; i<=1000;i++) {
            if(i%100==0)
                pipe.sendMessage(Integer.toString(i));
        }


        //************************</SECUENCIA PARA CERRAR CONEXIÓN*************************************
        try {
            thread.stop();//meh, no se debería de hacer, pero si no se hace el observer puede seguir intentado de leer el mensaje del socket que ya está cerrado.
            pipe.closeSocket();//cierra comunicación.
            thread.join();//termina el thread
        } catch (InterruptedException e) {
            System.out.println("Fallé en cerrar conexión");
            e.printStackTrace();
        }
        //************************SECUENCIA PARA CERRAR CONEXIÓN/>*************************************
    }

}
