package nameless.lab4.messages;

public class Message {
    private int sender;
    private String type;

    public Message(int sender, String type) {
        this.sender = sender;
        this.type = type;
    }

    public int getSender() {
        return sender;
    }

    public String getType() {
        return type;
    }
}
