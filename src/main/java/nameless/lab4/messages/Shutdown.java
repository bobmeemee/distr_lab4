package nameless.lab4.messages;

public class Shutdown extends Message{

    public Shutdown(int sender) {
        super(sender, "Shutdown");
    }
}
