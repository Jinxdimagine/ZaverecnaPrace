import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {

    private Database database;

    private Account account;
    private JLabel Balance;
    private JButton PayButton;

    public Window(Database database) {
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(200, 200, 600, 600);
        this.setLayout(null);
        setDatabase(database);
        setAccount(database.getAccount());
        addPanel();
        this.setVisible(true);
    }

    void addPanel(){
        JPanel panel=new JPanel();
        Balance=new JLabel();
        Balance.setText(account.getAmount()+"  Kč");
        PayButton =new JButton("New Payment");
        Balance.setFont(new Font("Arial",Font.PLAIN,60));
        PayButton.setBounds(150,100,150,50);
        Balance.setBounds(50,0,400,100);
        this.add(Balance);
        this.add(PayButton);
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
