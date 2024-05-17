import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Database {
    private ArrayList<Account> database;

    Database() throws IOException, ClassNotFoundException {
     load();
    }

    void load() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream=new FileInputStream("Database.txt");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        if (objectInputStream.readObject() != null){
            database= (ArrayList<Account>) objectInputStream.readObject();
        }else {
            database=new ArrayList<>();
        }
    }

    public ArrayList<Account> getDatabase() {
        return database;
    }

    public void setDatabase(ArrayList<Account> database) {
        this.database = database;
    }
}
