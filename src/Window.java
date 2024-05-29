import javax.swing.*;

public class Window extends JFrame {

    private Database database;

    private Account account;

    public Window(Database database,Account account) {
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(200, 200, 600, 600);
        this.setLayout(null);
        setDatabase(database);
        setAccount(account);
        this.setVisible(true);
    }

    void addPanel(){
        JPanel panel= new JPanel();
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
