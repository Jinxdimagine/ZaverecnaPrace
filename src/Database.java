import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Database implements Serializable {
    private ArrayList<Account> database = new ArrayList<>();

    private Account account;
    Database() throws IOException, ClassNotFoundException {
        start();
    }
    /**
     *Method send is used to send money from one account into another account.
     * */
    public boolean send(Account account,int Amount,String receiver) throws IOException {
        if (account.getBalance()-Amount>=0){
            for (Account account1:database){
                Payment payment=new Payment();
                if (account1.toString().equals(receiver)){
                    payment.setAmount(Amount);
                    payment.setSender(account);
                    payment.setReceiver(account1);
                    account.addPayment(payment,TypOfPayment.SENDING);
                    account1.addPayment(payment,TypOfPayment.RECEIVING);
                    save();
                }
            }
            return true;
        }else {
            System.out.println(Amount);
            return false;
        }
    }
    /**
     * In method add database will try to add account into database.
     * If database will be empty it will automatically add account into database.
     * Else if matches is not  found it will be added into database and assigned id also balance will set on 10000.
     * */
    public boolean add(Account account) throws IOException {
        if (!database.isEmpty()){
            for (Account account1 :database){
                if(account1.getFirstName().equalsIgnoreCase(account.getFirstName())&&account1.getLastName().equalsIgnoreCase(account.getLastName())){
                    return false;
                }
            }
            account.setId(database.size()+1);
            account.setBalance(10000);
        }else {
            account.setBalance(10000);
            account.setId(database.size()+1);
        }
        database.add(account);
        save();
        return true;
    }
    /**
     * In method start it will check if file Database.txt is empty.
     * if file is empty it write into file newly created Arraylist and load it back.
     * Else it will load the file .
     * */
    private void start() throws IOException, ClassNotFoundException {
        FileReader fr = new FileReader("Database.txt");
        BufferedReader br = new BufferedReader(fr);
        if (br.readLine() !=null){
            load();
        }else {
            save();
        }
    }
    /**
     * Method match is used to find account with same username and password as imported String and char array.
     * */
    public boolean match(String username,char[] password){
        for(Account account: database){
            if (account.getUserName().matches(username)&& Arrays.equals(account.getPassword(), password)){
                setAccount(account);
                return true;
            }
        }
        return false;
    }

    public boolean check(String username){
        for(Account account: database){
            if (account.getUserName().equals(username)){
               setAccount(account);
                return true;
            }
        }
        return false;
    }

    void changePassword(char[] Password) throws Exception {
        account.setPassword(Password);
    }
    /**
     * Method load is used to load file.
     * */
    void load() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("Database.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        database=(ArrayList<Account>) objectInputStream.readObject();
    }
    /**
     * Method load is used to write into file.
     * */
    void save() throws IOException {
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Database{" +
                "database=" + database +
                '}';
    }
}
