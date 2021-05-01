import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class Main
{
    static Client client;
    static FileReader fileReader = new FileReader();

    public static void main( String[] args )
    {
        String sender = fileReader.getSender();
        ArrayList<String> victims = fileReader.getVictims();
        client = new Client("LOCALHOST",0);
        ArrayList<Mail> mails = fileReader.getMessage();
        for (Mail m : mails) {
            m.setTo(victims);
            m.setFrom(sender);
            client.send(m);
        }

    }
}
