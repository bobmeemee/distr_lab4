package nameless.lab4.Node;

import java.io.IOException;
import java.net.InetAddress;

import static java.lang.Math.abs;

public class Node {
    private String name;
    private int nodeID;
    private int nextNodeID;
    private int prevNodeID;
    private NodeUdpInterface nodeUdpInterface;
    private final int port;

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getNodeID() {return nodeID;}
    public void setNodeID(int nodeID) {this.nodeID = nodeID;}

    public int getNextNodeID() {return nextNodeID;}
    public void setNextNodeID(int nextNodeID) {this.nextNodeID = nextNodeID;}

    public int getPrevNodeID() {return prevNodeID;}
    public void setPrevNodeID(int prevNodeID) {this.prevNodeID = prevNodeID;}

    public NodeUdpInterface getNodeUdpInterface() {return nodeUdpInterface;}
    public void setNodeUdpInterface(NodeUdpInterface nodeUdpInterface) {this.nodeUdpInterface = nodeUdpInterface;}

    public int getPort() {return port;}


    public Node(String name,int port, String multiCastIPAddress) {
        this.name = name;
        this.port = port;
        this.nodeID = hashFunction(name);
        try {
            InetAddress multicastAddress = InetAddress.getByName(multiCastIPAddress);
            this.nodeUdpInterface = new NodeUdpInterface(this, multicastAddress, this.port);
            new Thread(this.nodeUdpInterface).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static int hashFunction(String name){
        return abs(name.hashCode()) % 32768;
    }



}
