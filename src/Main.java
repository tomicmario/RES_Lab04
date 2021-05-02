import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Mario Tomic
 *
 * Gère l'envoi des mails 
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

        // Essai d'envoi des mails
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

            // Set des différents attributs
            currentVictimsIndex += currentVictims.size();
            currentMail.setFrom(currentVictims.get(0));
            currentVictims.remove(0);
            currentMail.setTo(currentVictims);

            // Affichage
            System.out.println("Les personnes suivantes :");
            for(int j = 0; j < currentVictims.size(); ++j){
                System.out.println("    " + currentVictims.get(j));
            }
            System.out.println("Vont recevoir le mail suivant : \n");
            System.out.println(currentMail);

            // Envoi
            client.send(currentMail);
        }

    }
}
