import java.util.ArrayList;
import java.lang.String;
import java.io.*;

/**
 * @author Mario Tomic
 *
 * Gère de récupérer le contenu des fichiers de configuration.
 */
public class ConfigManager
{

    private final String MESSAGE_START = "%MAIL_START%\n";
    private final String MESSAGE_END = "%MAIL_END%";
    private final String SUBJECT = "Subject: ";

    // Fichiers
    private final String VICTIMS;
    private final String CONFIG;
    private final String MAILS;

    /**
     * Constructeur de la classe. Récupère le contenu dans les différents fichiers de configuration.
     * Note: La boucle for pas jolie vient d'ici https://stackoverflow.com/a/41305930
     * @throws IOException
     */
    ConfigManager() throws IOException {
        String currentPath = new File(".").getCanonicalPath() + "/config";

        // Récupération du fichier CONFIG
        BufferedReader configReader = new BufferedReader(new FileReader(currentPath + "/CONFIG"));
        String config = "";
        for (String line; (line = configReader.readLine()) != null; config += line + "\n");
        CONFIG = config;
        configReader.close();

        // Récupération des adresses mail
        BufferedReader victimReader = new BufferedReader(new FileReader(currentPath + "/victims.txt"));
        String victims = "";
        for (String line; (line = victimReader.readLine()) != null; victims += line + "\n");
        VICTIMS = victims;
        victimReader.close();

        // Récupération des mails à envoyer
        BufferedReader mailsReader = new BufferedReader(new FileReader(currentPath + "/message.txt"));
        String mails = "";
        for (String line; (line = mailsReader.readLine()) != null; mails += line + "\n");
        MAILS = mails;
        mailsReader.close();
    }


    /**
     * @return Le tableau contenant toutes les adresses mails
     */
    public ArrayList<String> getVictims(){
        ArrayList<String> victimsArray = new ArrayList<>();
        // Première victime
        victimsArray.add(VICTIMS.substring(0, VICTIMS.indexOf("\n",1)));
        int currentIndex = VICTIMS.indexOf("\n", 0) + 1;

        // Le reste
        while(currentIndex != 0){
            int end = VICTIMS.indexOf("\n",currentIndex + 1);
            if(end == -1){
                break;
            }
            victimsArray.add(VICTIMS.substring(currentIndex, end));
            currentIndex = VICTIMS.indexOf("\n", currentIndex) + 1;
        }

        return victimsArray;
    }


    /**
     * @return Un tableau contenant le nombre de mails dans un groupe. L'index est le numéro du groupe
     */
    public ArrayList<Integer> getGroupsNumbers(){
        ArrayList<Integer> groups = new ArrayList<>();
        int groupeID = 1;
        String config = CONFIG;
        String groupSize = "groupSize" + groupeID + "=";

        while(config.contains(groupSize)){
            String number = config.substring(config.indexOf(groupSize), config.indexOf("\n", config.indexOf(groupSize)));
            groups.add(Integer.parseInt(number.substring(number.indexOf("=") + 1)));
            groupSize = "groupSize" + ++groupeID + "=";
        }

        return groups;
    }

    /**
     * @return Tous les mails qu'on veut envoyer
     */
    public ArrayList<Mail> getMessage(){
        ArrayList<Mail> mailsArray = new ArrayList<>();

        String subject = "";
        String body = "";
        int currentIndex = 0;
        do {
            if(MAILS.indexOf(MESSAGE_START, currentIndex) == -1){
                break;
            }
            String mail = MAILS.substring(MAILS.indexOf(MESSAGE_START, currentIndex), MAILS.indexOf(MESSAGE_END, MAILS.indexOf(MESSAGE_START, currentIndex)));
            subject = mail.substring(mail.indexOf(SUBJECT), mail.indexOf("\n",mail.indexOf(SUBJECT)));
            body = mail.substring(mail.indexOf("\n", mail.indexOf(SUBJECT)) + 1);
            mailsArray.add(new Mail(subject, body));
            currentIndex = MAILS.indexOf(MESSAGE_END, currentIndex) + 1;
        } while (currentIndex != 0);

        return mailsArray;
    }

    /**
     * @return l'adresse du serveur
     */
    public String getAddress(){
        String address = CONFIG.substring(CONFIG.indexOf("address"), CONFIG.indexOf("\n", CONFIG.indexOf("address")));
        return address.substring(address.indexOf("=") + 1);
    }


    /**
     * @return Le port smtp
     */
    public int getPort(){
        String port = CONFIG.substring(CONFIG.indexOf("port"), CONFIG.indexOf("\n", CONFIG.indexOf("port")));
        return Integer.parseInt(port.substring(port.indexOf("=") + 1));
    }

}
