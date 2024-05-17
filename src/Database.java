import java.io.*;
import java.util.ArrayList;

public class Database {
    private ArrayList<Account> database;

    Database() throws IOException, ClassNotFoundException {
     save();
     load();
    }

    void add(Account account) throws Exception {
        database.add(account);
        save();
    }

    void load() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream=new FileInputStream("Database.txt");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        if (objectInputStream.readObject() != null){
            database= (ArrayList<Account>) objectInputStream.readObject();
        }else {
            database=new ArrayList<>();
        }
        objectInputStream.close();
    }
    void save() throws IOException {
        FileOutputStream fileOutputStream=new FileOutputStream("Database.txt");
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(database);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public ArrayList<Account> getDatabase() {
        return database;
    }

    public void setDatabase(ArrayList<Account> database) {
        this.database = database;
    }
}
