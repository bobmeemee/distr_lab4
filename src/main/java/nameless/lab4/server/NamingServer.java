package nameless.lab4.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

import static java.lang.Math.abs;

@RestController
public class NamingServer {
    private final CustomMap nodeMap;

    public NamingServer() {
        this.nodeMap = new CustomMap();
        UdpInterface udpInterface = new UdpInterface(this);
        new Thread(udpInterface).start();
    }

    public static int hashFunction(String name){
        return abs(name.hashCode()) % 32768;
    }

    @PostMapping("/NamingServer/Nodes/{node}")
    public String addNode(@PathVariable(value = "node") String name, @RequestBody String IP) throws IOException {
        int nodeID = hashFunction(name);
        if (nodeMap.putIfAbsent(nodeID, IP) == null) {
            nodeMap.exportMap();
            return "Node added with ID=" + nodeID + "!";
        } else {
            return "This name is not available!\n";
        }
    }

    @DeleteMapping("/NamingServer/Nodes/{node}")
    public String removeNode(@PathVariable(value = "node") String  name) throws IOException {
        int nodeID = hashFunction(name);
        if (nodeMap.remove(nodeID) == null) {
            return "Node " + name + " does not exist!\n";
        } else {
            // moet dit?
            nodeMap.exportMap();
            return "Node " + name + " deleted!\n";
        }
    }

    @GetMapping("/NamingServer/Nodes/{node}")
    public String getNode(@PathVariable(value="node") String name) {
        int nodeID = hashFunction(name);
        return nodeMap.get(nodeID);
    }

    public void addNode(int nodeID, String name) {
        nodeMap.put(nodeID, name);
    }

    public void deleteNode(int nodeID) {
        nodeMap.remove(nodeID);
    }

    public int getID() {
        return 1;
    }








}
