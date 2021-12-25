import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Client started.");

        Socket socket = new Socket("127.0.0.1", 6000);
        System.out.println("Client connected.");

        ObjectOutputStream oUs = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream oIs = new ObjectInputStream(socket.getInputStream());

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a message here : ");
        String msg = scanner.nextLine();
        //sent to server
        oUs.writeObject(msg);
        try {
            //received from server
            Object sMsg = oIs.readObject();
            System.out.println("From server : " + (String) sMsg);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
