package nameless.lab4.Node;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class NodeUdpInterface implements Runnable{
    private final Node node;
    private final InetAddress multicastGroup;
    private final MulticastSocket socket;

    public NodeUdpInterface(Node node, InetAddress multicastGroup, int port) throws IOException {
        this.node = node;
        this.multicastGroup = multicastGroup;
        this.socket = new MulticastSocket(port);

    }

    @Override
    public void run() {
        try {
            this.socket.joinGroup(multicastGroup);
            byte[] buffer = new byte[2048];
            DatagramPacket request = new DatagramPacket(buffer,buffer.length);
            this.socket.receive(request);
            Thread requestHandler = new Thread(new NodeRequestHandler(this.node, this.socket, request));
            requestHandler.setPriority(Thread.NORM_PRIORITY - 1);
            requestHandler.start();
        } catch (IOException e) {
            System.err.println("[NODE UDP ERR] " + e);
        }


    }
}
