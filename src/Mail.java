import java.util.ArrayList;

/**
 * @author Mario Tomic
 *
 * Classe encapsulant les différentes données d'un mail.
 */
public class Mail
{
    private String from;
    private ArrayList<String> to;
    private final String subject;
    private final String body;

    /**
     * Constructeur
     * @param subject Objet du mail
     * @param body Contenu du mail
     */
    public Mail(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    /**
     * @return expéditeur
     */
    public String getFrom() {
        return from;
    }

    /**
     * Assigne l'expéditeur
     * @param from mail de l'expéditeur
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return mail(s) du ou des destinataires
     */
    public ArrayList<String> getTo() {
        return to;
    }

    /**
     * Prend la liste des adresses
     * @param to adresses mails du ou des destinataires
     */
    public void setTo(ArrayList<String> to) {
        this.to = to;
    }

    /**
     * @return Mail au format string, à mettre après "DATA"
     */
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

        mail += subject + "\n\n";
        mail += body;

        return mail;
    }
}
