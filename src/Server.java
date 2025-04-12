import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {
//    consumer method
//represent an operation that accepts a single input argument and returns no result
    public Consumer<Socket> getConsumer(){
        return (clientSocket) -> {
            try {
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
                toClient.println("Hello from the server");
                toClient.close();
                clientSocket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {
//        assign socket
        int port = 8010;
        Server server = new Server();
//        socket object
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
            System.out.println("Server socket is running");
            while (true){
                Socket clientSocket = serverSocket.accept();
                Thread thread = new Thread(() -> server.getConsumer().accept(clientSocket));
                thread.start();
            }
        }catch (Exception e){
            System.err.println(e);
        }

//        timeout
//        accept client
    }
}

