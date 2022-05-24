package nameless.lab4.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpInterface implements Runnable{
    DatagramSocket socket;
    NamingServer ns;

    public UdpInterface(NamingServer namingServer) {
        this.ns = namingServer;
        try {
            socket = new DatagramSocket(8001);

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[2048];
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);
            this.socket.receive(request);
            Thread handler = new Thread(new NamingServerRequestHandler(this.ns, this.socket, request));
            handler.setPriority(2);
            handler.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
