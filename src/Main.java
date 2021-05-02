import java.io.IOException;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class Main
{
    static Client client;
    static final int MIN_VICTIMS = 3;

    public static void main( String[] args ) throws IOException {

        ConfigManager conf = new ConfigManager();
        ArrayList<String> victims = conf.getVictims();
        client = new Client(conf.getAddress(), conf.getPort());
        ArrayList<Mail> mails = conf.getMessage();
        ArrayList<Integer> groups = conf.getGroupsNumbers();

        int currentVictimsIndex = 0;
        for (int i = 0; i < mails.size(); ++i) {
            Mail currentMail = mails.get(i);
            ArrayList<String> currentVictims = new ArrayList<>();

            for(int j = 0; j < groups.get(i); ++j) {
                currentVictims.add(victims.get(currentVictimsIndex + j));
            }
            if(currentVictims.size() < MIN_VICTIMS){
                System.out.println("Le mail numero " + i + " n'a pas été envoyé, car contient moins de victimes que le nombre minimal : " + MIN_VICTIMS);
                continue;
            }
            currentVictimsIndex += currentVictims.size();

            currentMail.setFrom(currentVictims.get(0));
            currentVictims.remove(0);
            currentMail.setTo(currentVictims);
            client.send(currentMail);
        }

    }
}
