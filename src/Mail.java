import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class Mail
{
    private String from;
    private ArrayList<String> to;
    private String subject;
    private String body;

    public Mail(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public ArrayList<String> getTo() {
        return to;
    }

    public void setTo(ArrayList<String> to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public String toString(){
        String mail = "";
        mail += "From: " + from + "\n";
        mail += "To: ";
        for (int i = 0; i < to.size(); ++i) {
            mail += to.get(i);
            if (i != to.size() - 1) {
                mail += ", ";
            } else {
                mail += "\n";
            }
        }
        mail += subject;
        mail += body;

        return mail;
    }
}
