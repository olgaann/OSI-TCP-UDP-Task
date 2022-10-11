import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class Server {

    private static final int PORT = 8080;
    static String oldCity;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server starts");

            boolean isFirst = true;
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); //ждем подключение
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    if (isFirst) {
                        out.println("Вы первый игрок. Назовите любой город.");
                        String newCity = in.readLine();
                        System.out.println("Ход игрока: " + newCity);
                        out.println("ок");
                        oldCity = newCity;
                        isFirst = false;
                        continue;
                    } else {
                        out.println("Текущий город: " + oldCity + ". Назовите город на последнюю букву текущего.");
                    }

                    String newCity = in.readLine();
                    String newCityLowCase = newCity.toLowerCase();
                    System.out.println("Ход игрока: " + newCity);

                    List<String> badChars = Arrays.asList("ы", "ъ", "ь");
                    char lastChar = oldCity.charAt(oldCity.length() - 1);
                    if (badChars.contains(String.valueOf(lastChar))) {
                        lastChar = oldCity.charAt(oldCity.length() - 2);
                    }

                    if (newCityLowCase.startsWith(String.valueOf(lastChar))) {
                        out.println("ок");
                        oldCity = newCity;
                    } else {
                        out.println("не ок");
                    }

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
