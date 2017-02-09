
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ServidorMultiCliente extends Thread {

    private Socket conexao;
    private String ipSensor;    

    public ServidorMultiCliente(Socket socket) {

        this.conexao = socket;

    }

    public static void main(String args[]) {

        try {

            ServerSocket server = new ServerSocket(3000);
            System.out.println("ServidorSocket rodando");

            while (true) {

                Socket conexao = server.accept();
                Thread t = new ServidorMultiCliente(conexao);
                t.start();

            }

        } catch (IOException e) {

            System.out.println("IOException: " + e);

        }
    }

    public void run() {

        try {           
            //Objetos que permitem controlar fluxo de comunicação
            PrintStream saida = new PrintStream(this.conexao.getOutputStream(), true);
            Scanner entrada = new Scanner(this.conexao.getInputStream());
            //Nome do cliente é o IP dele!        
            this.ipSensor = conexao.getInetAddress().getHostAddress();            

           System.out.println("Dispositivo " + this.ipSensor + " : Conectado ao Servidor!");        

            while (entrada.hasNextLine()) {
                
                System.out.println(entrada.nextLine());

            }
            
        } catch (IOException e) {

            System.out.println("Falha na Conexão" + " IOException: " + e);

        }
    }
}