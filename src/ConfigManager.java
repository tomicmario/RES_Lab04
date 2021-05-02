import java.util.ArrayList;
import java.lang.String;
import java.io.*;

/**
 * Hello world!
 *
 */
public class ConfigManager
{

    private final String MESSAGE_START = "%MAIL_START%";
    private final String MESSAGE_END = "%MAIL_END%";

    private final String VICTIMS;
    private final String CONFIG;
    private final String MAILS;

    ConfigManager() throws IOException {
        BufferedReader configReader = new BufferedReader(new FileReader("./CONFIG"));
        CONFIG = configReader.toString();

        BufferedReader victimReader = new BufferedReader(new FileReader("./victims.txt"));
        VICTIMS = victimReader.toString();

        BufferedReader mailsReader = new BufferedReader(new FileReader("./message.txt"));
        MAILS = mailsReader.toString();
    }


    public ArrayList<String> getVictims(){
        ArrayList<String> victimsArray = new ArrayList<>();
        String victims = VICTIMS;
        while(!victims.isEmpty()){
            victimsArray.add(victims.substring(0, victims.indexOf("\n")));
            if(!victims.contains("\n")){
                break;
            }
            victims = victims.substring(victims.indexOf("\n"));
        }

        return victimsArray;
    }

    public ArrayList<Integer> getGroupsNumbers(){
        ArrayList<Integer> groups = new ArrayList<>();
        int groupeID = 1;
        String config = CONFIG;
        String groupSize = "groupeSize" + groupeID + "=";

        while(config.contains(groupSize)){
            String number = config.substring(config.indexOf(groupSize), config.indexOf("\n"));
            groups.add(Integer.parseInt(number));
            groupSize = "groupeSize" + groupeID + "=";
        }

        return groups;
    }

    public ArrayList<Mail> getMessage(){
        ArrayList<Mail> mailsArray = new ArrayList<>();
        String mails = MAILS;
        String subject = "subject";
        String body = "body";
        mailsArray.add(new Mail("subject", "body"));
        return mailsArray;
    }


}
