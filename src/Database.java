import java.io.*;
import java.util.ArrayList;

public class Database {
    private ArrayList<Account> database;
    Database() throws IOException, ClassNotFoundException {
        load();
    }
    void load() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("Database.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        database=(ArrayList<Account>) objectInputStream.readObject();

    }
    void save() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("Database.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(database);
    }
}
