package nameless.lab4.Node;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class NodeRequestHandler implements Runnable{
    private final Node node;
    private DatagramPacket request;



    public NodeRequestHandler(Node node, DatagramSocket socket, DatagramPacket request) {
        this.node = node;
        this.request = request;
    }

    @Override
    public void run() {
        InetAddress senderIP = request.getAddress();
        int senderPort = request.getPort();

        String json = new String(this.request.getData(), 0, this.request.getLength());

    }
}
