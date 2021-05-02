import java.util.ArrayList;
import java.lang.String;
import java.io.*;

/**
 * Hello world!
 *
 */
public class ConfigManager
{

    private final String MESSAGE_START = "%MAIL_START%\n";
    private final String MESSAGE_END = "%MAIL_END%";
    private final String SUBJECT = "Subject: ";

    private final String VICTIMS;
    private final String CONFIG;
    private final String MAILS;

    ConfigManager() throws IOException {
        String currentPath = new File(".").getCanonicalPath() + "/config";
        BufferedReader configReader = new BufferedReader(new FileReader(currentPath + "/CONFIG"));
        String config = "";
        for (String line; (line = configReader.readLine()) != null; config += line + "\n");
        CONFIG = config;

        BufferedReader victimReader = new BufferedReader(new FileReader(currentPath + "/victims.txt"));
        String victims = "";
        for (String line; (line = victimReader.readLine()) != null; victims += line + "\n");
        VICTIMS = victims;

        BufferedReader mailsReader = new BufferedReader(new FileReader(currentPath + "/message.txt"));
        String mails = "";
        for (String line; (line = mailsReader.readLine()) != null; mails += line + "\n");
        MAILS = mails;
    }


    public ArrayList<String> getVictims(){
        ArrayList<String> victimsArray = new ArrayList<>();
        victimsArray.add(VICTIMS.substring(0, VICTIMS.indexOf("\n",1)));
        int currentIndex = VICTIMS.indexOf("\n", 0) + 1;
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
            body = mail.substring(mail.indexOf("\n", currentIndex));
            mailsArray.add(new Mail(subject, body));
            currentIndex = MAILS.indexOf(MESSAGE_END, currentIndex) + 1;
        } while (currentIndex != 0);

        return mailsArray;
    }

    public String getAddress(){
        String address = CONFIG.substring(CONFIG.indexOf("address"), CONFIG.indexOf("\n", CONFIG.indexOf("address")));
        return address.substring(address.indexOf("=") + 1);
    }

    public int getPort(){
        String port = CONFIG.substring(CONFIG.indexOf("port"), CONFIG.indexOf("\n", CONFIG.indexOf("port")));
        return Integer.parseInt(port.substring(port.indexOf("=") + 1));
    }

}
