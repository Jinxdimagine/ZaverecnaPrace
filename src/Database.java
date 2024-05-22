import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Database implements Serializable {
    private ArrayList<Account> database = new ArrayList<>();
    Database() throws IOException, ClassNotFoundException {
        load();
        save();
    }
    void add(Account account) throws IOException, ClassNotFoundException {
        if (database == null){
            account.setId(1);
        }else {
            account.setId(database.size()+1);
        }
        database.add(account);
        save();
    }

    public boolean match(String username,char[] password){
        for(Account account: database){
            if (account.getUserName().matches(username)&& Arrays.equals(account.getPassword(), password)){
                return true;
            }
        }
        return false;
    }
    void load() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("Database.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        database=(ArrayList<Account>) objectInputStream.readObject();

    }
    void save() throws IOException, ClassNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream("Database.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(database);
    }

    public ArrayList<Account> getDatabase() {
        return database;
    }

    public void setDatabase(ArrayList<Account> database) {
        this.database = database;
    }
}
