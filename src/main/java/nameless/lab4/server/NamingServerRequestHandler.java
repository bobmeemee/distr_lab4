package nameless.lab4.server;

import com.google.gson.Gson;
import nameless.lab4.messages.Message;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class NamingServerRequestHandler extends Thread{
    NamingServer ns;
    DatagramSocket socket;
    DatagramPacket request;


    public NamingServerRequestHandler(NamingServer namingServer, DatagramSocket socket, DatagramPacket request) {
        this.ns = namingServer;
        this.socket = socket;
        this.request = request;
    }

    @Override
    public void run() {
        String json = new String(this.request.getData(), 0, this.request.getLength());
        Gson gson = new Gson();
        Message message = gson.fromJson(json, Message.class);

        System.out.println("[NAMESERVER]: received " + message.getType() +  " request from: " + request.getAddress() +
                ":" + request.getPort() );

        if(message.getSender() == this.ns.getID()) {
            try {
                throw new Exception("[NAMESERVER]: received message from nameserver");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Message response;
        switch(message.getType()) {
            case "Hello":
                this.ns.addNode(message.getSender(), request.getAddress().toString().replace("/",""));
                System.out.println("[NAMESERVER]: added node " + message.getSender() );
                break;

            case "Shutdown":
                this.ns.deleteNode(message.getSender());
                System.out.println("[NAMESERVER]: shut down node " + message.getSender() );
                break;
        }




    }

}
