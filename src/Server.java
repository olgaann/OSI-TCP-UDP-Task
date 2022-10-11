import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server starts");

            while (true) {
                try (Socket clientSocket = serverSocket.accept(); //ждем подключение
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    System.out.println("Сообщение от клиента: " + in.readLine());
                    out.println("Привет, клиент! Твой адрес: " + clientSocket.getInetAddress());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
