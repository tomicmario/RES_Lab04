import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Mario Tomic
 *
 * Classe permettant d'envoyer le mail
 */
public class Client
{
    private final String ADDRESS;
    private final int PORT;

    /**
     * Constructeur
     * @param address adresse du serveur
     * @param port port smtp
     */
    public Client(String address, int port) {
        this.ADDRESS = address;
        this.PORT = port;
    }


    /**
     * Envoie le mail
     * @param m mail
     */
    public void send(Mail m){
        try {
            Socket socket = new Socket(ADDRESS, PORT);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream());

            wait(br);
            send(pw, "EHLO client");
            wait(br);
            send(pw, "MAIL FROM: " + m.getFrom());
            wait(br);

            for (String s : m.getTo()) {
                send(pw, "RCPT TO: " + s);
                wait(br);
            }

            send(pw, "DATA");
            wait(br);
            send(pw, m.toString() + "\r\n.\r\n");
            socket.close();
            br.close();
            pw.close();

            System.out.println("\n\nLe mail a été envoyé avec succès !");
        }catch (IOException e) {
            System.out.println("Erreur lors de la connexion au serveur");
        }
    }

    private void send(PrintWriter pw, String message) {
        pw.println(message);
        pw.flush();
    }

    private void wait(BufferedReader br) throws IOException {
        String message = br.readLine();
        while (message != null && !(message.contains("220 ") || message.contains("250 ") || message.contains("354 "))){
            message = br.readLine();
        }
    }
}
