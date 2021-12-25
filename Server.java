import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6000);

        while (true) {
            Socket socket = serverSocket.accept();

            ObjectInputStream oIs = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oUs = new ObjectOutputStream(socket.getOutputStream());

            try {
                //read from client...
                Object cMsg = oIs.readObject();
                System.out.println("From Client : " + (String) cMsg);

                String severMsg = (String) cMsg;
                severMsg = severMsg.toUpperCase();
                //write to the client..
                oUs.writeObject(severMsg);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
