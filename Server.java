import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6000);
        System.out.println("Server started...");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected...");

            ObjectInputStream oIs = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oUs = new ObjectOutputStream(socket.getOutputStream());

            try {
                //read from client...
                Object cMsg = oIs.readObject();
                System.out.println("From Client : " + (String) cMsg);

                String serverMsg = (String) cMsg;
                serverMsg = serverMsg.toUpperCase();
                //write to the client...
                oUs.writeObject(serverMsg);
                System.out.println("Message delivered successfully");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
