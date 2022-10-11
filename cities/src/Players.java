import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Players {
    private static final int PORT = 8080;
    private static final String HOST = "localhost";

    static List<String> citiesList = Arrays.asList("Москва", "Астрахань", "Надым");


    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(HOST, PORT);

             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            Collections.shuffle(citiesList);
            String city = citiesList.get(0);

            String serversReply1 = in.readLine();
            System.out.println(serversReply1);

            out.println(city);
            System.out.println("Игрок называет город: " + city);
            String serversReply2 = in.readLine();
            System.out.println("Ответ сервера: " + serversReply2);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
