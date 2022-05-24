package nameless.lab4.server;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class CustomMap extends ConcurrentSkipListMap<Integer, String> {

    public void exportMap() throws IOException {
        // fix this address
        FileOutputStream fo = new FileOutputStream("src/main/java/nameless/lab4/database");
        ObjectOutputStream out = new ObjectOutputStream(fo);
        out.writeObject(this);
        out.close();
        fo.close();
        System.out.println("Current database saved to src/main/java/nameless/lab4/database");
    }

    public void importMap() throws IOException, ClassNotFoundException {
        FileInputStream fi = new FileInputStream("src/main/java/nameless/lab4/database");
        ObjectInputStream in = new ObjectInputStream(fi);
        CustomMap c = (CustomMap) in.readObject();
        in.close();
        fi.close();

        this.putAll(c);
        System.out.println("Database imported from src/main/java/nameless/lab4/database");
    }
}
