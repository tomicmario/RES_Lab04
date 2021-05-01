import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class FileReader
{

    private final String OBJECT_SEPARATOR = "%OBJ%";
    private final String MESSAGE_SEPARATOR = "%MES%";
    FileReader(){

    }

    public String getSender(){
        String sender = "test";
        return sender;
    }

    public ArrayList<String> getVictims(){
        ArrayList<String> victims = new ArrayList<>();
        victims.add("test");
        return victims;
    }

    public ArrayList<Mail> getMessage(){
        ArrayList<Mail> mails = new ArrayList<>();

        String subject = "subject";
        String body = "body";
        mails.add(new Mail("subject", "body"));
        return mails;
    }
}
