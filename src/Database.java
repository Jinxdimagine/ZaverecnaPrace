import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Database implements Serializable {
    private ArrayList<Account> database = new ArrayList<>();
    Database() throws IOException, ClassNotFoundException {
        start();
    }
    public boolean add(Account account) throws IOException, ClassNotFoundException {
        if (!database.isEmpty()){
            for (Account account1 :database){
                if(account1.getFirstName().matches(account.getFirstName())&&account1.getLastName().matches(account.getLastName())){
                    return false;
                }else {
                    account.setId(database.size()+1);
                    account.setAmount(10000);
                    database.add(account);
                    save();

                }
            }
        }else {
            account.setAmount(10000);
            account.setId(database.size()+1);
            database.add(account);
            save();
            return true;
        }
        return false;
    }
    private void start() throws IOException, ClassNotFoundException {
        FileReader fr = new FileReader("Database.txt");
        BufferedReader br = new BufferedReader(fr);
        if (br.readLine() !=null){
            load();
        }else {
            save();
        }
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
